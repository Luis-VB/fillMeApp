package com.example.fillmeapp.network
import android.icu.text.CaseMap.Title
import com.example.fillmeapp.data.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("http://www.omdbapi.com/")
    fun getRandomMovie(@Query("apikey") apikey: String, @Query("t") title: String):Call<MovieDTO>

}