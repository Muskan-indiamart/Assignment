package com.example.navigationdrawerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity2 : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarTitle("New Activity")
        setContentView(R.layout.activity_main2)
        val s=findViewById<Switch>(R.id.switch_1)
        val factsView = findViewById<TextView>(R.id.textView)
        val updateButton = findViewById<Button>(R.id.button2)
        s.isChecked=true
        s.isChecked=false
        val btn=findViewById<Button>(R.id.button)
        val c= Runnable {
            Toast.makeText(this,"Button is clicked",Toast.LENGTH_SHORT).show()
        }

        btn.setOnClickListener {
            val handler = Handler()
            handler.postDelayed(c,2000)
            }
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.factsLiveData.observe(this , Observer {
            factsView.text=it
        })
        updateButton.setOnClickListener {
            mainViewModel.updateLiveData()
        }


        }fun setToolbarTitle(title : String){
        supportActionBar?.title=title
    }
    }
