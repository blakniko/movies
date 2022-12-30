package com.gasto.movies.ui.view

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.gasto.movies.Constanst

import com.gasto.movies.R
import com.gasto.movies.databinding.FragmentMoviesBinding
import com.gasto.movies.ui.adapter.MovieAdapter
import com.gasto.movies.ui.state.MovieState
import com.gasto.movies.ui.viewModel.ViewModelMovie
import com.movies.commons.BaseState
import com.movies.data.models.Movie
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MoviesFragment : Fragment() {

    private val viewModelMovie: ViewModelMovie by sharedViewModel()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewBinding: FragmentMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelMovie.loadMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMoviesBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()

    }

    private fun setupView() {
        movieAdapter = MovieAdapter()

        with(viewBinding) {
            recycler.apply {
                adapter = movieAdapter.apply {
                    setListener { id ->
                        goToDetail(id)
                    }
                }
                layoutManager = GridLayoutManager(requireContext(), 2)
            }

            toolbar.apply {
                title = resources.getString(com.movies.commons.R.string.title_movies)
            }

            refresh.setOnRefreshListener {
                refresh.isRefreshing = false
                viewModelMovie.loadMovies()
            }

            error.setListener {
                viewModelMovie.loadMovies()
            }
        }
    }

    private fun setupObservers() {
        viewModelMovie.state.observe(viewLifecycleOwner) { handleState(it) }
    }

    private fun handleState(state: BaseState) {

        when (state) {
            is MovieState.LoadingMovies -> showLoading()
            is MovieState.LoadedMovies -> showMovies(state.movies)
            is MovieState.ErrorLoadingMovies -> showErrorMovies()
        }
    }

    private fun showLoading() {
        viewBinding.error.visibility=View.GONE
        viewBinding.recycler.visibility=View.GONE
        viewBinding.progress.visibility=View.VISIBLE
    }

    private fun goToDetail(movieId:String) {
        findNavController().navigate(Constanst.getMovieDetailUri(movieId))
    }

    private fun showMovies(movies: List<Movie>) {
        viewBinding.error.visibility=View.GONE
        viewBinding.recycler.visibility=View.VISIBLE
        viewBinding.progress.visibility=View.GONE
        movieAdapter.loadMovies(movies)
    }

    private fun showErrorMovies() {
        viewBinding.error.visibility=View.VISIBLE
        viewBinding.recycler.visibility=View.GONE
        viewBinding.progress.visibility=View.GONE

    }



}