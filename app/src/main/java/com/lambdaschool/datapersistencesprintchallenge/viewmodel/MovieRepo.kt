package com.lambdaschool.datapersistencesprintchallenge.viewmodel

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.lambdaschool.datapersistencesprintchallenge.apiaccess.MovieConstants
import com.lambdaschool.datapersistencesprintchallenge.model.Movie
import com.lambdaschool.datapersistencesprintchallenge.model.MovieSearchResult
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.db.MovieDBDao
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.db.MovieDatabase
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.retrofit.MovieRetroApi
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.retrofit.MovieRetroDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieRepo(context: Context) {
    var contxt = context
    companion object {
        var tempMovieList:MutableList<Movie> = mutableListOf<Movie>()
    }
    private var movieDBdao: MovieDBDao

    private var allMovies: LiveData<List<Movie>>
    private var workinStorage:MutableList<Movie> = mutableListOf()


    init {
        //I think we get a singleton out of this move, although I'm a little sketched out by it
        val database: MovieDatabase = MovieDatabase.getInstance(
            context
        )!!
        movieDBdao = database.movieDBDao()
        allMovies = movieDBdao.getAllMovies()


    }

    fun getSearchMovies(search:String,api_key:String):MutableList<Movie> {
        tempMovieList= mutableListOf<Movie>()


            val apiInterface = MovieRetroApi.Factory.create()
            val mutlist= mutableListOf<Movie>()
            apiInterface.getMoviesSearch(search, api_key)
                .enqueue(object : Callback<MovieSearchResult> {
                    override fun onFailure(call: Call<MovieSearchResult>, t: Throwable) {
                        t.printStackTrace()
                        val response = "faliure; ${t.message}"

                    }

                    override fun onResponse(
                        call: Call<MovieSearchResult>,
                        response: Response<MovieSearchResult>
                    ) {
                        val newMovList: MovieSearchResult? = response.body() as MovieSearchResult
                        newMovList?.results?.forEach {
                            tempMovieList.add(it)
                            mutlist.add(it)
                            insert(it)
                        }
                    }
                })


        return tempMovieList
    }
    fun getAllMovies(): LiveData<List<Movie>> {
        return allMovies
    }

    fun getLastSearchMovies(search:String): List<Movie> {
        val searchAsyncTask = getSearchedAsyncTask(
            movieDBdao
        ).execute(search).get()
        return searchAsyncTask
    }

    fun insert(movie:Movie) {
        val insertMovieAsyncTask = insertMovieAsyncTask(
            movieDBdao
        ).execute(movie)
    }

    fun update(movie: Movie) {

    }

    fun delete(movie: Movie) {

    }

    fun deleteAllMovies() {
    }

    private class getSearchedAsyncTask (movieDao:MovieDBDao):
        AsyncTask<String, Unit, List<Movie>>() {
        val movieDBdao = movieDao

        override fun doInBackground(vararg p0: String?): List<Movie> {
            var searchedMovies= movieDBdao.getLastSearchMovies(p0[0]!!)
            return searchedMovies
        }




    }

    private class insertMovieAsyncTask(movieDao: MovieDBDao) :
        AsyncTask<Movie, Unit, Unit>() {
        val movieDBdao = movieDao

        override fun doInBackground(vararg p0: Movie?) {
            movieDBdao.insert(p0[0]!!)
        }
    }


/*    private class InsertPokemonAsyncTask(pokemonDao: PokemonDao) :
        AsyncTask<Movie, Unit, Unit>() {
        val PokemonDao = pokemonDao

        override fun doInBackground(vararg p0: Pokemon?) {
            PokemonDao.insert(p0[0]!!)
        }
    }*/
}
   /* fun pokemonByNumber(id:Int):Pokemon {
        val allRealPokemon =allPokemon.value
        val was = allPokemon.value?.forEachIndexed {i, pokemon->
            if (allRealPokemon?.get(i)?.number == null) {
                return allRealPokemon!!.get(i) as Pokemon
            }
        }
        return was as Pokemon
    }


    fun insert(pokemon: Pokemon) {
        val insertPokemonAsyncTask = InsertPokemonAsyncTask(
            pokemonDao
        ).execute(pokemon)
    }

    fun update(pokemon: Pokemon) {
        val updatePokemonAsyncTask = UpdatePokemonAsyncTask(
            pokemonDao
        ).execute(pokemon)
    }


    fun delete(pokemon: Pokemon) {
        val deletePokemonAsyncTask = DeletePokemonAsyncTask(
            pokemonDao
        ).execute(pokemon)
    }

    fun deleteAllPokemon() {
        val deleteAllPokemonAsyncTask = DeleteAllPokemonAsyncTask(
            pokemonDao
        ).execute()
    }

    fun getAllPokemon(): LiveData<List<Pokemon>> {
        return allPokemon
    }


    companion object {

        //Pokemon
        private class InsertPokemonAsyncTask(pokemonDao: PokemonDao) :
            AsyncTask<Pokemon, Unit, Unit>() {
            val PokemonDao = pokemonDao

            override fun doInBackground(vararg p0: Pokemon?) {
                PokemonDao.insert(p0[0]!!)
            }
        }

        private class UpdatePokemonAsyncTask(pokemonDao: PokemonDao) :
            AsyncTask<Pokemon, Unit, Unit>() {
            val PokemonDao = pokemonDao

            override fun doInBackground(vararg p0: Pokemon?) {
                PokemonDao.update(p0[0]!!)
            }
        }

        private class DeletePokemonAsyncTask(pokemonDao: PokemonDao) :
            AsyncTask<Pokemon, Unit, Unit>() {
            val PokemonDao = pokemonDao

            override fun doInBackground(vararg p0: Pokemon?) {
                PokemonDao.delete(p0[0]!!)
            }
        }

        private class DeleteAllPokemonAsyncTask(pokemonDao: PokemonDao) :
            AsyncTask<Unit, Unit, Unit>() {
            val PokemonDao = pokemonDao

            override fun doInBackground(vararg p0: Unit?) {
                PokemonDao.deleteAllPokemon()
            }
        }

    }
}
*/