package com.lambdaschool.datapersistencesprintchallenge.model

import androidx.room.PrimaryKey

object MovieMockData {
    var MovieList = mutableListOf<Movie>()

    init {
        MovieList.add(
            Movie(
                1,
                "blah",
                "blah",
                "blah",
                1f,
                "Blah",
                "b",
                "feg",
                true,
                1f,
                1,
                1,
                true,
                false
            )
        )
        MovieList.add(
            Movie(
                1,
                "blah",
                "blah",
                "blah",
                1f,
                "Blah",
                "b",
                "def",
                true,
                1f,
                1,
                0,
                false,
                true
            )
        )
        MovieList.add(
            Movie(
                1,
                "blah",
                "blah",
                "blah",
                1f,
                "Blah",
                "b",
                "abc",
                true,
                1f,
                1,
                0,
                true,
                false
            )
        )
        MovieList.add(
            Movie(
                1,
                "blah",
                "blah",
                "blah",
                1f,
                "Blah",
                "b",
                "bcd",
                true,
                1f,
                1,
                0,
                true,
                true
            )
        )
    }
}