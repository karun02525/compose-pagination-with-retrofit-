package com.example.paginationcompose.data.network


import com.example.paginationcompose.model.ResponseNotificationModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("notification")
    suspend fun getPopularMovies(@Query("page") pageNumber: Int): ResponseNotificationModel


/*
    @GET("3/movie/popular")
    suspend fun getPopularMovies(@Query("page") pageNumber: Int): MovieListResponse*/
}