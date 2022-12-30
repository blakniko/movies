package com.gasto.movies.application

import android.app.Application
import com.gasto.movies.di.movieModule
import com.gasto.movies.di.networkModule
import org.koin.core.context.startKoin

/**
 * Created by Nicolas Pino on 28,diciembre,2022
 */
class ApplicationMovies:Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin(){
        startKoin {
            modules(listOf(networkModule,movieModule))
        }
    }
}