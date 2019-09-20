package com.lambdaschool.datapersistencesprintchallenge.model

class MovieSearchResult(val results: List<Movie>)

class Movie(
        val id: Int,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Float,
        val poster_path: String,
        val release_date: String,
        val title: String,
        val isVideo: Boolean,
        val vote_average: Float,
        val vote_count: Int
)