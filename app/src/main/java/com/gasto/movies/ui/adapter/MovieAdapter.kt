package com.gasto.movies.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gasto.movies.R
import com.movies.commons.utils.ImageUtils.Companion.stringToImage
import com.movies.data.models.Movie

/**
 * Created by Nicolas Pino on 28,diciembre,2022
 */
class MovieAdapter : RecyclerView.Adapter<ViewHolderMovie>() {
    private val dataSource = mutableListOf<Movie>()
    private var listener: ((id: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovie {
        return ViewHolderMovie(
            LayoutInflater.from(parent.context).inflate(R.layout.items_movies, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderMovie, position: Int) {
        holder.bind(dataSource[position], listener)
    }

    override fun getItemCount(): Int = dataSource.size

    fun loadMovies(movies: List<Movie>) {
        dataSource.clear()
        dataSource.addAll(movies)
        notifyDataSetChanged()
    }

    fun setListener(listener: (id: String) -> Unit) {
        this.listener = listener
    }
}

class ViewHolderMovie(val view: View) : RecyclerView.ViewHolder(view) {

    val title: TextView = view.findViewById(R.id.title)
    private val year = view.findViewById<TextView>(R.id.year)
    private val image: ImageView = view.findViewById(R.id.imageMovie)
    private val rating: RatingBar = view.findViewById(R.id.ratingItem)

    fun bind(movie: Movie, listener: ((id: String) -> Unit)?) {
        title.text = movie.title
        this.year.text = movie.year
        bindImage(image, movie.image)
        bindStars(movie.rating.toFloat())

        view.rootView.setOnClickListener {
            listener?.invoke(movie.id)
        }
    }

    private fun bindStars(rating: Float) {
        this.rating.rating = rating.getRating()
    }

    private fun bindImage(image: ImageView, url: String) {
        url.stringToImage(image)
    }

    fun Float.getRating(): Float {
        return when {
            this > 7 -> {
                (7).toFloat()
            }
            else -> {
                this
            }
        }
    }
}