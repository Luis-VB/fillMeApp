package com.example.fillmeapp.ui

import androidx.lifecycle.MutableLiveData
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fillmeapp.network.MovieDTO
import com.example.fillmeapp.network.RetroFitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    var randomMoviesLiveData = MutableLiveData<MovieDTO>()
    fun getRandomMovie() {
        RetroFitInstance.api.getRandomMovie("976aacaa","Rambo").enqueue(object : Callback<MovieDTO> {
            override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
                if (response.body() != null) {
                    val randomMovie: MovieDTO = response.body()!!
                    randomMoviesLiveData.value = randomMovie
                    Log.d("MovieTest", "onResponse: ${response.body()}")
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
                Log.d("MovieFailedTest", "onFailure: ${t.message}")
            }
        })
    }
}