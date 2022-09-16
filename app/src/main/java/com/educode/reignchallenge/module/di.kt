package com.educode.reignchallenge.module

import android.app.Application
import com.educode.reignchallenge.data.database.ArticleDatabase
import com.educode.reignchallenge.data.database.ArticleDbDataSource
import com.educode.reignchallenge.data.repository.ArticleRepository
import com.educode.reignchallenge.data.service.ArticleServiceDataSource
import com.educode.reignchallenge.data.source.LocalDataSource
import com.educode.reignchallenge.data.source.RemoteDataSource
import com.educode.reignchallenge.domain.usecases.DeleteArticle
import com.educode.reignchallenge.domain.usecases.GetArticles
import com.educode.reignchallenge.presentation.main.MainActivity
import com.educode.reignchallenge.presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI(){
    startKoin {
        androidLogger(Level.ERROR)
        androidContext(this@initDI)
        modules(listOf(appModule,dataModule,scopesModule))
    }
}

private val appModule = module{
    single { ArticleDatabase.build(get()) }
    factory<RemoteDataSource> { ArticleServiceDataSource() }
    factory<LocalDataSource> { ArticleDbDataSource(get()) }
}

private val dataModule = module{
    factory { ArticleRepository(get(),get()) }
}

private val scopesModule = module{
    scope(named<MainActivity>()){
        viewModel { MainViewModel(get(),get()) }
        scoped { GetArticles(get()) }
        scoped { DeleteArticle(get()) }
    }
}