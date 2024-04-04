package com.example.fillmeapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    var pacoProfile = UserProfileData()
    lateinit var userName: TextView
    lateinit var userAge: TextView
    lateinit var userCity: TextView

    fun initViews() {

        userName = findViewById(R.id.txt1_1)
        userAge = findViewById(R.id.txt1_2)
        userCity = findViewById(R.id.txt1_3)
    }

    fun initTextViews() {
        // Take user profile data and displays in the TextView
        userName.text = pacoProfile.userName
        userAge.text = pacoProfile.userAge.toString()
        userCity.text = pacoProfile.userCity
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
        initTextViews()

        userName.text = intent.getStringExtra("data")
        userAge.text = intent.getStringExtra("data1")
    }
}

/*
Pasar un numero y otro texto igual que hemos hecho con el userName a SecondActivity desde la MainActivity*/
