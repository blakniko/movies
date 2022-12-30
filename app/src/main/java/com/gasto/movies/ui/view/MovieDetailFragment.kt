package com.gasto.movies.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gasto.movies.ConstansAguments.KEY_ARGUMENT_MOVIE_ID
import com.gasto.movies.R
import com.gasto.movies.databinding.FragmentMovieDetailBinding
import com.gasto.movies.ui.viewModel.ViewModelMovie
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.movies.commons.utils.ImageUtils.Companion.stringToImage
import com.movies.data.models.Genre
import com.movies.data.models.Movie
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MovieDetailFragment : Fragment() {
    private lateinit var viewBinding: FragmentMovieDetailBinding
    private val viewModelMovie: ViewModelMovie by sharedViewModel()
    private var movieId: String = ""
    private var movie: Movie? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleArguments()
        getMovie()
        bindMovie()
    }


    private fun handleArguments() {
        arguments?.let {
            movieId = it.get(KEY_ARGUMENT_MOVIE_ID).toString()
        }
    }

    private fun getMovie() {
        movie = viewModelMovie.getMovieById(movieId)
    }

    private fun bindMovie() {
        movie?.let {
            it.image.stringToImage(viewBinding.movieImage)

            with(viewBinding) {
                year.text = it.year
                title.text = it.title
                plot.text = it.plot
                duration.text =
                    resources.getString(com.movies.commons.R.string.duration_text, it.duration)
                directors.text =
                    resources.getString(com.movies.commons.R.string.directors, it.directors)
                rating.rating = it.rating.toFloat()
            }
            bindCategories(it.genres)

        }
    }

    private fun bindCategories(movies: List<Genre>) {
        viewBinding.chipGroup.removeAllViews()
        for (i in movies.indices) {

            viewBinding.chipGroup.addView(
                Chip(requireContext()).apply {
                    text = movies[i].key
                    setTextColor(resources.getColor(R.color.white))
                    setChipBackgroundColorResource(com.movies.commons.R.color.colorStar)
                }
            )
        }
    }

}