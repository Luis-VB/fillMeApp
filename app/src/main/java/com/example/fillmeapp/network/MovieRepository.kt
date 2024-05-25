package com.example.fillmeapp.network

import android.util.Log
import com.example.fillmeapp.data.MovieData

class MovieRepository {

    private val movieApi: MovieApi = RetroFitInstance.api
    private val apiKey = "976aacaa"

    suspend fun getMovieByTitle(title: String): MovieData? {
        val response = movieApi.getMovie(apiKey, title)
        return if (response.isSuccessful) {
            Log.d("MovieTest", "onResponse: ${response.body()}")
            response.body()?.let {
                MovieData(
                    title = it.Title,
                    director = it.Director,
                    imageURL = it.Poster,
                    genre = it.Genre,
                    year = it.Year
                )
            }
        } else {
            Log.d("MovieTest", "onFailed: ${response.message()}")
            null
        }
    }
}