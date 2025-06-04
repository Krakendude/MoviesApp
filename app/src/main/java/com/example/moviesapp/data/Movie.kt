package com.example.moviesapp.data

import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    val results: List<Movie>
)

data class Movie(
    val Title: String,
    val ImdbID: String,
    val Poster: Poster
)

data class Poster(
    val url: String
)