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

        sharedPreferences
            .edit()
            .putInt("pushups_total", sharedPreferences.getInt("pushups_totak",0))
            .apply()

        initPushups()

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
    }

    private fun updatePushUpsCount(numberOfPushups: Int) {
        var newPushUpsToday = sharedPreferences.getInt("pushups_today",100) - numberOfPushups
        var newPushUpsTotal = sharedPreferences.getInt("pushups_total",0) + numberOfPushups
        newPushUpsToday = newPushUpsToday.let { if(it < 0) 0 else it }
        newPushUpsTotal = newPushUpsTotal.let { if(it < 0) 0 else it }

        sharedPreferences
            .edit()
            .putInt("pushups_today", newPushUpsToday)
            .putInt("pushups_total", newPushUpsTotal)
            .apply()

        number_push_ups_today.text = newPushUpsToday.toString()
        number_push_ups_total.text = newPushUpsTotal.toString()
    }

    private fun initPushups() {
        number_push_ups_today.text = sharedPreferences.getInt("pushups_today",100).toString()
        number_push_ups_total.text = sharedPreferences.getInt("pushups_total",0).toString()
    }
}
