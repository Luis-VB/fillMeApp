package com.example.fillmeapp.network

import com.example.fillmeapp.network.dto.MovieDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/")
    suspend fun getMovieByTitle(
        @Query("apikey") apikey: String,
        @Query("t") title: String,

    ): Response<MovieDTO>

    @GET("/")
    suspend fun getMovieByID(
        @Query("apikey") apikey: String,
        @Query("i") id: String,
    ): Response<MovieDTO>
    @GET("/")
    suspend fun getMovieBySearch(
        @Query("apikey") apikey: String,
        @Query("s") title: String,
    ): Response<MovieDTO>
}