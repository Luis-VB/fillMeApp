package com.example.fillmeapp.network

import com.example.fillmeapp.network.dto.MovieDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/")
    suspend fun getMovie(
        @Query("apikey") apikey: String,
        @Query("t") title: String
    ): Response<MovieDTO>

}