package com.lambdaschool.datapersistencesprintchallenge.viewmodel.retrofit

import com.google.gson.Gson
import com.lambdaschool.datapersistencesprintchallenge.apiaccess.MovieConstants.API_KEY_PARAM
import com.lambdaschool.datapersistencesprintchallenge.apiaccess.MovieConstants.FIXED_QUERY_PARAMS
import com.lambdaschool.datapersistencesprintchallenge.apiaccess.MovieConstants.SEARCH_MOVIES_ENDPOINT
import com.lambdaschool.datapersistencesprintchallenge.model.MovieSearchResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/// example string:https://api.themoviedb.org/3/search/movie?language=en-US&page=1&include_adult=false&query=Avengers&api_key=12345
/*const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY_PARAM = "359211348348a13a2b996217f7538f45"
const val FIXED_QUERY_PARAMS = "?language=en-US&page=1&include_adult=false"
const val SEARCH_MOVIES_ENDPOINT = "search/movie$FIXED_QUERY_PARAMS"*/

interface MovieRetroApi {


    // @GET("/friends")
    // Call<ResponseBody> friends(@Query("group") String... groups);
    ///// example string:https://api.themoviedb.org/3/search/movie?language=en-US&page=1&include_adult=false&query=Avengers&api_key=12345
    //Calling with foo.friends("coworker", "bowling") yields /friends?group=coworker&group=bowling.
//    @GET("/friends")
 //     Call<ResponseBody> friends(@QueryName String... filters);
    //
    //Calling with foo.friends("contains(Bob)", "age(42)") yields /friends?contains(Bob)&age(42)./
    //@GET("data/2.5/weather?")
    //    fun getCurrentWeatherData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("APPID") app_id: String)
    @GET("search/movie${FIXED_QUERY_PARAMS}")
    fun getMoviesSearch(@Query("query")query: String,@Query("api_key")api_key:String) : Call<MovieSearchResult>

/*
    @GET("unit/{id}")
    fun getUnit(@Path("id") name: String): Call<Units>
*/

    class Factory {
        companion object {

            val BASE_URL = "https://api.themoviedb.org/3/"
            val gson = Gson()


            fun create(): MovieRetroApi {

                // we don't need this at the moment
                   val logger = HttpLoggingInterceptor()
                   logger.level = HttpLoggingInterceptor.Level.BASIC
                   logger.level = HttpLoggingInterceptor.Level.BODY
                   val okHttpClient = OkHttpClient.Builder()
                       .addInterceptor(logger)
                       .retryOnConnectionFailure(false)
                       .readTimeout(10, TimeUnit.SECONDS)
                       .connectTimeout(15, TimeUnit.SECONDS)
                       .build()
                val retrofit = Retrofit.Builder()
                         .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)) //gson
                    .build()

                return retrofit.create(MovieRetroApi::class.java)
            }
        }
    }

}
