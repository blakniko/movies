package com.movies.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Nicolas Pino on 28,diciembre,2022
 */
data class Movie(
    val id: String,
    val title: String,
    val year: String,
    val image: String,
    @SerializedName("imDbRating") val rating: String,
    @SerializedName("genreList") val genres: List<Genre>,
    val plot: String,
    @SerializedName("runtimeMins") val duration: String,
    @SerializedName("directors") val directors: String,

)