package com.lambdaschool.datapersistencesprintchallenge.viewmodel.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.datapersistencesprintchallenge.model.Movie
import kotlinx.android.synthetic.main.rv_cv_search_list.view.*
import androidx.recyclerview.widget.DiffUtil


class MovieListAdapter : ListAdapter<Movie,MovieListAdapter.movieholder>(DIFF_CALLBACK) {
// i need to figure out why or if this is necessary
    //look into recycler and livedata intersecting
        companion object {
            private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem.original_title == newItem.original_title
                }

                override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem.id == newItem.id &&
                            oldItem.DBindex == newItem.DBindex &&
                            oldItem.original_title == newItem.original_title &&
                            oldItem.popularity == newItem.popularity
                }
            }
        }

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movieholder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(com.lambdaschool.datapersistencesprintchallenge.R.layout.rv_cv_search_list, parent, false)
        return movieholder(itemView)
    }

    override fun onBindViewHolder(holder: movieholder, position: Int) {
        val currentMovie: Movie = getItem(position)
        holder.tvName.text = currentMovie.title
        holder.tvFavorite.text= currentMovie.isFavorite.toString()
        holder.tvWatched.text=currentMovie.isWatched.toString()
        //todo:ADD POSTERS

        //holder.ivPoster

    }


    fun getMovieAt(position: Int): Movie {
        return getItem(position)
    }

    inner class movieholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvFavorite= itemView.tv_favorite
        var tvName= itemView.tv_movie_name
        var tvWatched =itemView.tv_watched
        var ivPoster = itemView.iv_poster

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
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