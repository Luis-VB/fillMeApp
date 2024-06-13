package com.example.fillmeapp.network

import android.util.Log
import com.example.fillmeapp.data.Movie
import com.example.fillmeapp.data.MovieList

class MovieRepository {

    private val movieApi: MovieApi = RetroFitInstance.api
    private val apiKey = "976aacaa"

    suspend fun getMovieByTitle(title: String): Movie? {
        val response = movieApi.getMovieByTitle(apiKey, title)
        return if (response.isSuccessful) {
            Log.d("MovieTest", "onResponse: ${response.body()}")
            response.body()?.let {
                Movie(
                    title = it.Title?:"",
                    director = it.Director?:"",
                    poster = it.Poster?:"",
                    genre = it.Genre?:"",
                    year = it.Year?:"",
                    imdbID = it.imdbID?:"",
                    Type = it.Type?:""
                )
            }
        } else {
            Log.d("MovieTest", "onFailed: ${response.message()}")
            null
        }
    }

    suspend fun getMovieByID(id: String): Movie? {
        val response = movieApi.getMovieByID(apiKey, id)
        return if (response.isSuccessful) {
            Log.d("MovieTest", "onResponse: ${response.body()}")
            response.body()?.let {
                Movie(
                    title = it.Title?:"",
                    director = it.Director?:"",
                    poster = it.Poster?:"",
                    genre = it.Genre?:"",
                    year = it.Year?:"",
                    imdbID = it.imdbID?:"",
                    Type = it.Type?:""
                )
            }
        } else {
            Log.d("MovieTest", "onFailed: ${response.message()}")
            null
        }
    }

    suspend fun getMovieByInputText(title: String): MovieList? {
        val response = movieApi.getMovieByInputText(apiKey, title)
        return when (response.code()) {
            200 -> {
                Log.d("Movie", "onResponse: ${response.body()}")
                response.body()?.let { moviesDto ->
                    val movies = moviesDto.movies.map { movieDto ->
                        Movie(
                            title = movieDto.Title ?: "",
                            director = movieDto.Director ?: "",
                            poster = movieDto.Poster ?: "",
                            genre = movieDto.Genre ?: "",
                            year = movieDto.Year ?: "",
                            imdbID = movieDto.imdbID ?: "",
                            Type = movieDto.Type?:""
                        )
                    }
                    MovieList(movies)
                }
            }
            else -> {
                Log.d("MovieTest", "onFailed: ${response.message()}")
                null
            }
        }
    }
}