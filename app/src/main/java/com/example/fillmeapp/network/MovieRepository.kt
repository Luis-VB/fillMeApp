package com.example.fillmeapp.network

import android.util.Log
import com.example.fillmeapp.data.MovieData

class MovieRepository {

    private val movieApi: MovieApi = RetroFitInstance.api
    private val apiKey = "976aacaa"

    suspend fun getMovieByTitle(title: String): MovieData? {
        val response = movieApi.getMovieByTitle(apiKey, title)
        return if (response.isSuccessful) {
            Log.d("MovieTest", "onResponse: ${response.body()}")
            response.body()?.let {
                MovieData(
                    title = it.Title,
                    director = it.Director,
                    imageURL = it.Poster,
                    genre = it.Genre,
                    year = it.Year,
                    imdbID = it.imdbID
                )
            }
        } else {
            Log.d("MovieTest", "onFailed: ${response.message()}")
            null
        }
    }

    suspend fun getMovieByID(id: String): MovieData? {
        val response = movieApi.getMovieByID(apiKey, id)
        return if (response.isSuccessful) {
            Log.d("MovieTest", "onResponse: ${response.body()}")
            response.body()?.let {
                MovieData(
                    title = it.Title,
                    director = it.Director,
                    imageURL = it.Poster,
                    genre = it.Genre,
                    year = it.Year,
                    imdbID = it.imdbID
                )
            }
        } else {
            Log.d("MovieTest", "onFailed: ${response.message()}")
            null
        }
    }

    suspend fun getMovieBySearch(title: String): MovieData? {
        val response = movieApi.getMovieBySearch(apiKey, title)
        return if (response.isSuccessful) {
            Log.d("MovieTest", "onResponse: ${response.body()}")
            response.body()?.let {
                MovieData(
                    title = it.Title,
                    director = it.Director,
                    imageURL = it.Poster,
                    genre = it.Genre,
                    year = it.Year,
                    imdbID = it.imdbID
                )
            }
        } else {
            Log.d("MovieTest", "onFailed: ${response.message()}")
            null
        }
    }
}