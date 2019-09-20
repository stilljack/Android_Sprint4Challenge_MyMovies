package com.lambdaschool.datapersistencesprintchallenge.viewmodel.retrofit

import android.content.Context
import android.widget.Toast
import com.lambdaschool.datapersistencesprintchallenge.apiaccess.MovieConstants
import com.lambdaschool.datapersistencesprintchallenge.model.MovieSearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRetroDao {


fun makeMovieSearchList(search:String, context: Context) {

    var apiInterface = MovieRetroApi.Factory.create()

    apiInterface.getMoviesSearch(et_movie.text.toString(),
        MovieConstants.API_KEY_PARAM
    )
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
                val newMovList: MovieSearchResult? = response.body()

                    newMovList
                    Toast.makeText(context, "success! $response", Toast.LENGTH_SHORT).show()
                }
            }
        })

}
makeMovieSearchList(et_movie.text.toString(),this)
}
}