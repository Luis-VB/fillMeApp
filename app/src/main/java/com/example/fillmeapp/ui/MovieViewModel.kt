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
    var movieLiveDataByTitle = MutableLiveData<MovieData>()
    var movieLiveDataByID = MutableLiveData<MovieData>()
    var movieLiveDataBySearch = MutableLiveData<MovieData>()


    private var movieRepository: MovieRepository = MovieRepository()
    private var job: Job? = null
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun searchMovie(title: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = movieRepository.getMovieByTitle(title)
                withContext(Dispatchers.Main) {
                    response?.let {
                        movieLiveDataByTitle.value = it
                    }
                }
            } catch (e: Exception) {
                // Handle or log exception
                Log.e("MovieSearch", "Error occurred while searching for movie: $title", e)
            }
        }
    }

    fun searchMovieByID(id: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = movieRepository.getMovieByID(id)
                withContext(Dispatchers.Main) {
                    response?.let {
                        movieLiveDataByID.value = it
                    }
                }
            } catch (e: Exception) {
                // Handle or log exception
                Log.e("MovieSearchByID", "Error occurred while searching for movie with ID: $id", e)
            }
        }
    }

    fun searchMovieBySearch(title: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = movieRepository.getMovieBySearch(title)
                withContext(Dispatchers.Main) {
                    response?.let {
                        movieLiveDataBySearch.value = it
                    }
                }
            } catch (e: Exception) {
                // Handle or log exception
                Log.e("MovieSearchBySearch", "Error occurred while searching for movie: $title", e)
            }
        }
    }
}