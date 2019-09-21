package com.lambdaschool.datapersistencesprintchallenge.viewmodel.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lambdaschool.datapersistencesprintchallenge.model.Movie

@Dao
interface MovieDBDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    //this is probably not actually going to be useful but whatever
    @Query(value = "SELECT * FROM movie_table WHERE id = :id")
    fun getMovieById(id:Long): LiveData<Movie>

    @Query(value = "SELECT * FROM movie_table WHERE isFavorite = 1")
    fun getFavMovies(): LiveData<List<Movie>>

    @Query(value = "SELECT * FROM movie_table WHERE isWatched = 1")
    fun getWatchedMovie(): LiveData<List<Movie>>

    @Query("DELETE FROM movie_table")
    fun deleteAllMovies()

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie_table WHERE title like :title")
    fun getLastSearchMovies(title:String): List<Movie>
}