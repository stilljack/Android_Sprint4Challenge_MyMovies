package com.lambdaschool.datapersistencesprintchallenge.viewmodel.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.model.Movie
import kotlinx.android.synthetic.main.rv_cv_search_list.view.*
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.MovieRepo.Companion.tempMovieList

class FavListAdapter : RecyclerView.Adapter<FavListAdapter.movieholder>() {


    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movieholder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_cv_fav_list, parent, false)
        return movieholder(itemView)
    }
    override fun getItemCount() = tempMovieList.size
    override fun onBindViewHolder(holder: movieholder, position: Int) {
        val currentMovie: Movie = tempMovieList[position]
        holder.tvName.text = currentMovie.title
        holder.tvFavorite.text= currentMovie.isFavorite.toString()
        holder.tvWatched.text=currentMovie.isWatched.toString()
        //todo:ADD POSTERS

        //holder.ivPoster

    }


    fun getMovieAt(position: Int): Movie {
        return tempMovieList[position]
    }

    inner class movieholder(view: View) : RecyclerView.ViewHolder(view) {
        var tvFavorite= itemView.tv_favorite
        var tvName= itemView.tv_movie_name
        var tvWatched =itemView.tv_watched
        var ivPoster = itemView.iv_poster

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(tempMovieList[position])
                }
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}