package com.example.fillmeapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitInstance {

    val api by lazy {
        Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            //.addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(MovieApi::class.java)
    }
}