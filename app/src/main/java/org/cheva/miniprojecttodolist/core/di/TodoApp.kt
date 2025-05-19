package org.cheva.miniprojecttodolist.core.di

import android.app.Application

class TodoApp : Application() {

    companion object {
        lateinit var appContext: Application
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

}