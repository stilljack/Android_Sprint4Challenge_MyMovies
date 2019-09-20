package com.lambdaschool.datapersistencesprintchallenge.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.apiaccess.MovieConstants.API_KEY_PARAM
import com.lambdaschool.datapersistencesprintchallenge.model.Movie
import com.lambdaschool.datapersistencesprintchallenge.model.MovieSearchResult
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.MovieViewModel
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.recycleview.MovieListAdapter
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.retrofit.MovieRetroApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

/// init: yikes first line written an hour late...
/// almost like something went wrong....  hahah here we go
class MainActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupRecyclerView()

        btn_search.setOnClickListener {

        }
    }

    fun setupRecyclerView() {
        rv_movie_list.layoutManager = LinearLayoutManager(this)
        rv_movie_list.setHasFixedSize(false)

        var adapter = MovieListAdapter()

        rv_movie_list.adapter = adapter

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        movieViewModel.getAllMovie().observe(this, Observer<List<Movie>> {
            updateRecyclerView(adapter, it as MutableList<Movie>)
        })

        adapter.setOnItemClickListener(object : MovieListAdapter.OnItemClickListener {
            override fun onItemClick(movie: Movie) {
                val view = et_movie
                view.setText(movie.title)

            }
        })
    }


    //todo::why this?
    fun updateRecyclerView(adapter: MovieListAdapter, pokemonList: MutableList<Movie>) {
        adapter.submitList(pokemonList as List<Movie>)
        adapter.notifyDataSetChanged()
    }


}
