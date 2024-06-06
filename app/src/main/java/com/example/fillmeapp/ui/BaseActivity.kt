package com.example.fillmeapp.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fillmeapp.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar

        actionBar?.apply {
            actionBar?.apply {
                setDisplayShowHomeEnabled(false)
                setDisplayShowTitleEnabled(false)
                setDisplayUseLogoEnabled(false)
                setDisplayShowCustomEnabled(true)
                setCustomView(R.layout.app_bar_layout)

                val titleView: TextView = customView.findViewById(R.id.appBarTitle)
                titleView.text = "FillMeApp"
            }
        }
    }
}