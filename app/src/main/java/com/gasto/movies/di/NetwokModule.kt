package com.gasto.movies.di

import com.gasto.movies.Constanst
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Nicolas Pino on 28,diciembre,2022
 */

val networkModule = module {

    single {
        Retrofit.Builder().baseUrl(Constanst.BASE_URL).client(get())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    single {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(get<Interceptor>())
        return@single httpClient.build()
    }

    single<Interceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}