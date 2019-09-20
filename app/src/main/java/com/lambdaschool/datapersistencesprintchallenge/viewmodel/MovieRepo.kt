package com.lambdaschool.datapersistencesprintchallenge.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.lambdaschool.datapersistencesprintchallenge.model.Movie
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.db.MovieDBDao
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.db.MovieDatabase
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.retrofit.MovieRetroDao


class MovieRepo(context: Context) {
    var contxt = context
    companion object {
        var tempMovieList:MutableList<Movie> = mutableListOf<Movie>()
    }
    private var MovieDBdao: MovieDBDao

    private var allMovies: LiveData<List<Movie>>

    init {
        //I think we get a singleton out of this move, although I'm a little sketched out by it
        val database: MovieDatabase = MovieDatabase.getInstance(
            context
        )!!



        MovieDBdao = database.movieDBDao()
        allMovies = MovieDBdao.getAllMovies()


    }

    fun getSearchMovies(search:String,api_key:String) :MutableList<Movie>{

        return MovieRetroDao().makeMovieSearchList(search,contxt)
    }
    fun getAllMovies(): LiveData<List<Movie>> {
        return allMovies
    }

    fun insert(movie: Movie) {

    }

    fun update(movie: Movie) {

    }

    fun delete(movie: Movie) {

    }

    fun deleteAllMovies() {
    }

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