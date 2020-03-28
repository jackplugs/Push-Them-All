package com.hobeez.pushthemall

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.util.*
import java.util.concurrent.TimeUnit


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initDay()
    }

    private fun initDay() {
        val sharedPreferences = getSharedPreferences("pushthemall_sharedpref", Context.MODE_PRIVATE)

        // This code is only triggered the first time we open the app
        if (sharedPreferences.getLong("first_day", 0L) == 0L) {

            val todayDateMidnight: Date =
                GregorianCalendar().apply {
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                }
                .time

            sharedPreferences
                .edit()
                .putLong("first_day", todayDateMidnight.time)
                .apply()
        }

    }
}
