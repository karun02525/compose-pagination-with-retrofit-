package com.example.paginationcompose.ui.screen

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paginationcompose.data.repository.MovieRepository
import com.example.paginationcompose.data.repository.paged.MovieSource
import com.example.paginationcompose.model.NotificationList
import com.example.paginationcompose.model.ResponseNotificationModel
import kotlinx.coroutines.flow.Flow

class MainViewModel(movieRepository: MovieRepository) : ViewModel() {

    val movies: Flow<PagingData<NotificationList>> = Pager(PagingConfig(pageSize = 10)) {
        MovieSource(movieRepository)
    }.flow
}