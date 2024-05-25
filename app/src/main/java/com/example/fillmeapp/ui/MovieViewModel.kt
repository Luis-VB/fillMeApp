package com.example.fillmeapp.ui

import androidx.lifecycle.MutableLiveData
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fillmeapp.data.MovieData
import com.example.fillmeapp.network.MovieApi
import com.example.fillmeapp.network.MovieRepository
import com.example.fillmeapp.network.dto.MovieDTO
import com.example.fillmeapp.network.RetroFitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel : ViewModel() {
    var movieLiveData = MutableLiveData<MovieData>()


    private var movieRepository: MovieRepository = MovieRepository()
    private var job: Job? = null
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun searchMovie(title: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = movieRepository.getMovieByTitle(title)
            withContext(Dispatchers.Main) {
                response?.let {
                    movieLiveData.value = it
                }
            }
        }
    }
}