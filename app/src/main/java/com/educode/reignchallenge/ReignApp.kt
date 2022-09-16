package com.educode.reignchallenge

import android.app.Application
import com.educode.reignchallenge.module.initDI

class ReignApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}