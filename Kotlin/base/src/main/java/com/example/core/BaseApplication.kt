package com.example.core

import android.app.Application
import android.content.Context

class BaseApplication : Application(){


    companion object{
        private lateinit var currentApplication: Context
        fun currentApplication(): Context = currentApplication
    }

    //取代attachBaseContext
    override fun onCreate() {
        super.onCreate()
        currentApplication = this
    }
}