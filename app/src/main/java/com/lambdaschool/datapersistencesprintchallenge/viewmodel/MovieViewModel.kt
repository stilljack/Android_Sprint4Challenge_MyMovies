package com.lambdaschool.datapersistencesprintchallenge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.lambdaschool.datapersistencesprintchallenge.model.Movie
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.retrofit.MovieRetroDao

//viewmodel modifys dao modifys database
class MovieViewModel (application: Application) : AndroidViewModel(application){
    private var repository: MovieRepo =
        MovieRepo(application)
    private var allMovies: LiveData<List<Movie>> = repository.getAllMovies()

    fun SearchMovies(search:String, api_key:String):MutableList<Movie>{

        return repository.getSearchMovies(search,api_key)
    }

    fun getLastSearchMovies(search:String):MutableList<Movie> {
        return repository.getLastSearchMovies(search) as MutableList<Movie>
    }
    fun getAllMovie(): LiveData<List<Movie>> {
        return allMovies
    }
    fun insert(movie: Movie) {
        repository.insert(movie)
    }

    fun update(movie: Movie) {
        repository.update(movie)
    }

    fun delete(movie: Movie) {
        repository.delete(movie)
    }

    fun deleteAllMovies() {
        repository.deleteAllMovies()
    }

/*
    fun pokemonByNumber(id:Int):Pokemon{
        return repository.deleteMovie(id)

    }
*/

    /*    fun getPokemonById(id:Int):LiveData<Pokemon>{
          //  repository.
        }*/

}