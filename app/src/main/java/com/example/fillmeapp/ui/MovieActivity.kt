package com.example.fillmeapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fillmeapp.R

class MovieActivity : AppCompatActivity() {
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_view)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        // Observe the LiveData from the ViewModel
        viewModel.movieLiveDataBySearch.observe(this) { movie ->
            // Update the UI
        }
    }
}
