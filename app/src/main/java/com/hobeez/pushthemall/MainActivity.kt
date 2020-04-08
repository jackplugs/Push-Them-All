package com.hobeez.pushthemall

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("pushthemall_sharedpref", Context.MODE_PRIVATE)

        cancel_one.setOnClickListener {
            updatePushUpsCount(-1)
        }

        add_one.setOnClickListener {
            updatePushUpsCount(1)
        }

        add_ten.setOnClickListener {
            updatePushUpsCount(10)
        }

        add_twenty.setOnClickListener {
            updatePushUpsCount(20)
        }

        // In case I forgot to add my pompas
        //resetTotalCounterToFitNumberOfdays()
    }

    private fun resetTotalCounterToFitNumberOfdays() {
        // Override total counter
        sharedPreferences
            .edit()
            .putInt("pushups_today", 100)
            .putInt("pushups_total", 100 * ( getDayNumber(sharedPreferences) - 1 ))
            .apply()
    }

    override fun onResume() {
        super.onResume()
        updateSharedPreferencesVariables()
        initTextviews()
    }

    private fun updateSharedPreferencesVariables() {
        // If day has changed
        Log.d("First day : ", sharedPreferences.getLong("first_day", 0).toString())
        Log.d("get Day Number : ", getDayNumber(sharedPreferences).toString())
        Log.d("Saved Day Number : ", sharedPreferences.getInt("saved_day_number", -1).toString())

        getDayNumber(sharedPreferences).let { day ->
            if (day != sharedPreferences.getInt("saved_day_number", -1)) {
                sharedPreferences
                    .edit()
                    .putInt("saved_day_number", day)
                    .apply()

                sharedPreferences
                    .edit()
                    .putInt("pushups_today", 100)
                    .apply()
            }
        }
    }

    private fun getDayNumber(sharedPreferences: SharedPreferences): Int {
        val today = Date()
        val diffMilliSeconds =  today.time - sharedPreferences.getLong("first_day",today.time)
        return TimeUnit.DAYS.convert(diffMilliSeconds, TimeUnit.MILLISECONDS).toInt() + 1
    }

    private fun updatePushUpsCount(pushupsDone: Int) {
        val newPushUpsToday = sharedPreferences.getInt("pushups_today",100) - pushupsDone

        if ( (pushupsDone > 0 && newPushUpsToday >= 0) || (pushupsDone < 0 && newPushUpsToday <= 100) ) {
            val newPushUpsTotal = sharedPreferences.getInt("pushups_total",0) + pushupsDone
            sharedPreferences
                .edit()
                .putInt("pushups_today", newPushUpsToday)
                .putInt("pushups_total", newPushUpsTotal)
                .apply()

            number_push_ups_today.text = newPushUpsToday.toString()
            number_push_ups_total.text = newPushUpsTotal.toString()
        }
    }

    private fun initTextviews() {
        day_number.text = getString(R.string.day, sharedPreferences.getInt("saved_day_number", 0))
        number_push_ups_today.text = sharedPreferences.getInt("pushups_today",100).toString()
        number_push_ups_total.text = sharedPreferences.getInt("pushups_total",0).toString()

        val date = Date(sharedPreferences.getLong("first_day",0))
        start_date_textview.text = getString(R.string.start_date, SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(date))
    }
}
