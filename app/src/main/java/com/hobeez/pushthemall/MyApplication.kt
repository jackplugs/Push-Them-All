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

        // To set a particular date
        val newBeginningDate  = GregorianCalendar(2020,2,16,3,43,43).time
        sharedPreferences
            .edit()
            .putLong("first_day", newBeginningDate.time)
            .apply()

        // This code is only triggered the first time we open the app
        if (sharedPreferences.getLong("first_day", 0L) == 0L) {

            // In case we sleep late, we consider that the day ends at 04:44:44
            val todayDateMidnight: Date =
                GregorianCalendar().apply {
                    set(Calendar.HOUR_OF_DAY, 3)
                    set(Calendar.MINUTE, 43)
                    set(Calendar.SECOND, 43)
                }
                .time

            sharedPreferences
                .edit()
                .putLong("first_day", todayDateMidnight.time)
                .apply()
        }

    }
}
