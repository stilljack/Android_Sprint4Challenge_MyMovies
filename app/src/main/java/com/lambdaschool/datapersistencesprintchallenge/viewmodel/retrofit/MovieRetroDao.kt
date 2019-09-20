package com.lambdaschool.datapersistencesprintchallenge.viewmodel.retrofit

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.lambdaschool.datapersistencesprintchallenge.apiaccess.MovieConstants
import com.lambdaschool.datapersistencesprintchallenge.model.Movie
import com.lambdaschool.datapersistencesprintchallenge.model.MovieSearchResult
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.MovieRepo.Companion.tempMovieList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRetroDao {


fun makeMovieSearchList(search:String, context: Context): MutableList<Movie> {

    var apiInterface = MovieRetroApi.Factory.create()
    var mutlist= mutableListOf<Movie>()
    apiInterface.getMoviesSearch(search, MovieConstants.API_KEY_PARAM)
        .enqueue(object : Callback<MovieSearchResult> {
            override fun onFailure(call: Call<MovieSearchResult>, t: Throwable) {
                t.printStackTrace()
                val response = "faliure; ${t.message}"
                Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<MovieSearchResult>,
                response: Response<MovieSearchResult>
            ) {
                val newMovList: MovieSearchResult? = response.body() as MovieSearchResult
                newMovList?.results?.forEach {

                    mutlist.add(it)
                    Toast.makeText(context, "success! $response", Toast.LENGTH_SHORT).show()
                }
            }
        })
    return mutlist
}

}


