package com.gasto.movies.di

import com.gasto.movies.ui.viewModel.ViewModelMovie
import com.movies.data.repository.MoviesRepository
import com.movies.data.repository.MoviesRepositoryImpl
import com.movies.domain.GetAllMoviesUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Nicolas Pino on 28,diciembre,2022
 */

val movieModule = module {

    factory<MoviesRepository> {
        MoviesRepositoryImpl(get())
    }

    factory {
        GetAllMoviesUseCase(get())
    }

    viewModel {
        ViewModelMovie(get())
    }
}