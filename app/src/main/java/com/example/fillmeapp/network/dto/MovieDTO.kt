package com.example.fillmeapp.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class MovieDTO(
@SerialName("Actors") val actors: String? = null,
    @SerialName("Awards") val awards: String? = null,
    @SerialName("BoxOffice") val boxOffice: String? = null,
    @SerialName("Country") val Country: String? = null,
    @SerialName("DVD") val DVD: String? = null,
    @SerialName("Director") val Director: String? = null,
    @SerialName("Genre") val Genre: String? = null,
    @SerialName("Language") val Language: String? = null,
    @SerialName("Metascore") val Metascore: String? = null,
    @SerialName("Plot") val Plot: String? = null,
    @SerialName("Poster") val Poster: String? = null,
    @SerialName("Production") val Production: String? = null,
    @SerialName("Rated") val Rated: String? = null,
    @SerialName("Ratings") val Ratings: List<Rating>? = null,
    @SerialName("Released") val Released: String? = null,
    @SerialName("Response") val Response: String? = null,
    @SerialName("Runtime") val Runtime: String? = null,
    @SerialName("Title") val Title: String? = null,
    @SerialName("Type") val Type: String? = null,
    @SerialName("Website") val Website: String? = null,
    @SerialName("Writer") val Writer: String? = null,
    @SerialName("Year") val Year: String? = null,
    @SerialName("imdbID") val imdbID: String? = null,
    @SerialName("imdbRating") val imdbRating: String? = null,
    @SerialName("imdbVotes") val imdbVotes: String? = null
)
@Serializable
data class Rating(
    @SerialName("Source") val Source: String,
    @SerialName("Value") val Value: String
)

@Serializable
data class MoviesDTO(
    @SerialName("Search") val movies: List<MovieDTO>,
    @SerialName("totalResults") val totalResults: String? = null
)