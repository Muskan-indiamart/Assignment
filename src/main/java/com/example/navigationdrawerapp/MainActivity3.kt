package com.example.navigationdrawerapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main3.*
import java.util.prefs.AbstractPreferences

class MainActivity3 : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    var isRemember =false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        sharedPreferences=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        isRemember=sharedPreferences.getBoolean("CHECKBOX",false)

        if(isRemember)
        {
            val intent= Intent(this,AnotherActivity::class.java)
            startActivity(intent)
            finish()
        }
        login.setOnClickListener {
           val name=nameEt.text.toString()
            val age=ageEt.text.toString().toInt()
            val checked : Boolean=checkbox.isChecked

            val editor:SharedPreferences.Editor=sharedPreferences.edit()
            editor.putString("Name",name)
            editor.putInt("Age",age)
            editor.putBoolean("CheckBox",checked)

            editor.apply()

            Toast.makeText(this,"Information saved",Toast.LENGTH_LONG).show()
            val intent= Intent(this,AnotherActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
}