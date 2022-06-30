package com.example.paginationcompose.di


import com.example.paginationcompose.data.network.MovieApi
import com.example.paginationcompose.data.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { createRepository(get()) }
}

fun createRepository(movieApi: MovieApi) : MovieRepository = MovieRepository(movieApi)