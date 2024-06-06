package com.example.fillmeapp.ui
/*
Pasar un numero y otro texto igual que hemos hecho con el userName a SecondActivity desde la MainActivity*/

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fillmeapp.R
import com.example.fillmeapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var binding2: ActivitySecondBinding
    fun initViews() {

        binding2.txt21.setTextColor(getColor(R.color.colorPrimaryDark))
        binding2.txt21.text = intent.getStringExtra("data")
        binding2.txt22.text = intent.getStringExtra("data1")
        binding2.txt23.setTextColor(getColor(R.color.colorPrimaryDark))
        binding2.txt23.text = intent.getStringExtra("data2")
        binding2.txt24.text = intent.getStringExtra("data3")
        binding2.txt25.setTextColor(getColor(R.color.colorPrimaryDark))
        binding2.txt25.text = intent.getIntExtra("data4",0).toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding2.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
    }
}
