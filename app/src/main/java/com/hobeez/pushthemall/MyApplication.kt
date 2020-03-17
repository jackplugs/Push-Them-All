package com.hobeez.pushthemall

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val sharedPreferences = getSharedPreferences("pushthemall_sharedpref", Context.MODE_PRIVATE)

        // TODO : Do this only for new days
        sharedPreferences
            .edit()
            .putInt("pushups_today", 100)
            .apply()
    }
}
