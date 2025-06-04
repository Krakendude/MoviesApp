package com.example.moviesapp.utils

import com.example.moviesapp.data.Movie
import com.example.moviesapp.data.MovieSearchResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface movieService {

    @GET(".")
    suspend fun findByTitle(
        @Query("apikey") apiKey: String = "a9dfac08",
        @Query("t") title: String
    ): Response<Movie>

    @GET(".")
    suspend fun findById(
        @Query("apikey") apiKey: String = "a9dfac08",
        @Query("i") imdbId: String
    ): Response<Movie>

    @GET(".")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String = "a9dfac08",
        @Query("s") search: String
    ): Response<MovieSearchResponse>

    companion object {
        fun getInstance(): movieService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/") // URL correcta para OMDb
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(movieService::class.java)
        }
    }
}