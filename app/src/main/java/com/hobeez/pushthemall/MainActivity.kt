package com.hobeez.pushthemall

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("pushthemall_sharedpref", Context.MODE_PRIVATE)

        // TODO : Do this every new day in MyApplication
        /*sharedPreferences
            .edit()
            .putInt("pushups_today", 100)
            .apply()*/

        initPushups()

        plus_one.setOnClickListener {
            val newPushUpsTotal = sharedPreferences.getInt("pushups_total",0) - 1
            val newPushUpsToday = sharedPreferences.getInt("pushups_today",100) + 1
            sharedPreferences
                .edit()
                .putInt("pushups_today", newPushUpsToday)
                .putInt("pushups_total", newPushUpsTotal)
                .apply()
            number_push_ups_today.text = newPushUpsToday.toString()
            number_push_ups_total.text = newPushUpsTotal.toString()
        }

        minus_one.setOnClickListener {
            val newPushUpsTotal = sharedPreferences.getInt("pushups_total",0) + 1
            val newPushUpsToday = sharedPreferences.getInt("pushups_today",100) - 1
            sharedPreferences
                .edit()
                .putInt("pushups_today", newPushUpsToday)
                .putInt("pushups_total", newPushUpsTotal)
                .apply()
            number_push_ups_today.text = newPushUpsToday.toString()
            number_push_ups_total.text = newPushUpsTotal.toString()
        }

        minus_ten.setOnClickListener {
            val newPushUpsTotal = sharedPreferences.getInt("pushups_total",0) + 10
            val newPushUpsToday = sharedPreferences.getInt("pushups_today",100) - 10
            sharedPreferences
                .edit()
                .putInt("pushups_today", newPushUpsToday)
                .putInt("pushups_total", newPushUpsTotal)
                .apply()
            number_push_ups_today.text = newPushUpsToday.toString()
            number_push_ups_total.text = newPushUpsTotal.toString()
        }

        minus_twenty.setOnClickListener {
            val newPushUpsTotal = sharedPreferences.getInt("pushups_total",0) + 20
            val newPushUpsToday = sharedPreferences.getInt("pushups_today",100) - 20
            sharedPreferences
                .edit()
                .putInt("pushups_today", newPushUpsToday)
                .putInt("pushups_total", newPushUpsTotal)
                .apply()
            number_push_ups_today.text = newPushUpsToday.toString()
            number_push_ups_total.text = newPushUpsTotal.toString()
        }
    }

    private fun initPushups() {
        number_push_ups_today.text = sharedPreferences.getInt("pushups_today",100).toString()
        number_push_ups_total.text = sharedPreferences.getInt("pushups_total",0).toString()
    }
}
