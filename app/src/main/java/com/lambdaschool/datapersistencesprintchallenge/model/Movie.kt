package com.lambdaschool.datapersistencesprintchallenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// ok so we added a DBindex as our primarykey and set to autogenerate, I'm betting we could have used the servers IDs
//but I want to be safe and I can play with that later -- worse case here I think would be entries being repeated... we'll see

class MovieSearchResult(val results: List<Movie>)
@Entity(tableName = "movie_table")
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
        val vote_count: Int,

        @PrimaryKey(autoGenerate = true)
        var DBindex:Int,

        //these should only be important locally and will not correspond (and hopefully effect/be effected by) remote values on the server
        var isFavorite:Boolean = false,
        var isWatched:Boolean = false
)