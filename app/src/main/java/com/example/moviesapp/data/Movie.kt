package com.example.moviesapp.data

import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    @SerializedName("Search")
    val Search: List<Movie>
)

data class Movie(
    @SerializedName("Title") val Title: String,
    @SerializedName("Year") val Year: String,
    @SerializedName("imdbID") val ImdbID: String,
    @SerializedName("Poster") val Poster: String,
    @SerializedName("Plot") val Synopsis: String,
    @SerializedName("Runtime") val Runtime: String,
    @SerializedName("Director") val Director: String,
    @SerializedName("Genre") val Genre: String,
    @SerializedName("Country") val Country: String
)


data class Poster(
    val url: String
)