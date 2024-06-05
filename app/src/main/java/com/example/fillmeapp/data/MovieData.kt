package com.example.fillmeapp.data


data class Movie(
    val title: String,
    val director: String,
    val genre: String,
    val poster: String,
    val year: String,
    val imdbID: String,

)
data class MovieList(
        val movies: List<Movie>
)

