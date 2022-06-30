package com.example.paginationcompose.data.repository

import com.example.paginationcompose.data.network.MovieApi


class MovieRepository(private val movieApi: MovieApi) {
    suspend fun getPopularMovies(pageNumber: Int) = movieApi.getPopularMovies(pageNumber)
}