package com.example.navigationdrawerapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
       val actionString = intent.action
        Toast.makeText(context,actionString,Toast.LENGTH_LONG).show()
        val timeZone=intent.getStringExtra("time-zone")
        Log.d("MyReceiver","$timeZone")

    }
}