package com.example.fillmeapp.ui

import androidx.lifecycle.MutableLiveData
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fillmeapp.data.Movie
import com.example.fillmeapp.data.MovieList
import com.example.fillmeapp.network.MovieRepository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel : ViewModel() {
//    var movieLiveDataByTitle = MutableLiveData<Movie>()
    var movieLiveDataByID = MutableLiveData<Movie>()
    var movieLiveDataByInputText = MutableLiveData<MovieList>()

    private var movieRepository: MovieRepository = MovieRepository()
    private var job: Job? = null
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

//    fun searchMovie(title: String) {
//        job = CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val response = movieRepository.getMovieByTitle(title)
//                withContext(Dispatchers.Main) {
//                    response?.let {
//                        movieLiveDataByTitle.value = it
//                    }
//                }
//            } catch (e: Exception) {
//                // Handle or log exception
//                Log.e("MovieSearch", "Error occurred while searching for movie: $title", e)
//            }
//        }
//    }

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

    fun searchMovieByInputText(title: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = movieRepository.getMovieByInputText(title)
                withContext(Dispatchers.Main) {
                    response?.let {
                        movieLiveDataByInputText.value = it
                    }
                }
            } catch (e: Exception) {
                // Handle or log exception
                Log.e("MovieSearchByInputText", "Error occurred while searching for movie: $title", e)
            }
        }
    }
}