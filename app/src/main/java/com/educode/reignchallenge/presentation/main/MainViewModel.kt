package com.educode.reignchallenge.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educode.reignchallenge.domain.Article
import com.educode.reignchallenge.domain.usecases.DeleteArticle
import com.educode.reignchallenge.domain.usecases.GetArticles
import kotlinx.coroutines.launch

class MainViewModel(private val getArticles: GetArticles,private val deleteArticle: DeleteArticle): ViewModel() {

    private val _articles= MutableLiveData<List<Article>>(listOf())
    val articles: LiveData<List<Article>> get() = _articles

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    fun loadArticles() {
        viewModelScope.launch {
            try{
                _loading.value = true
                _articles.value = getArticles.invoke()
                _loading.value = false

            }catch (e: Exception){
                Log.e("Main Activity","Error retrieving articles $e")
                _loading.value = false
            }
        }
    }

    fun deleteArticle(position: Int){
        viewModelScope.launch {
            try{
                val article = _articles.value?.get(position)
                _articles.value = _articles.value?.toMutableList()?.apply{
                    removeAt(position)
                }?.toList()
                article?.let {
                    it.also {
                        it.isVisible = false
                        deleteArticle.invoke(it)
                    }
                }
            }catch (e: Exception){
                Log.e("Main Activity","Error deleting article")
            }
        }


    }
}