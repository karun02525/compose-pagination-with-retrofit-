package com.example.paginationcompose.data.repository.paged


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginationcompose.data.repository.MovieRepository
import com.example.paginationcompose.model.NotificationList

class MovieSource(private val movieRepository: MovieRepository) : PagingSource<Int, NotificationList>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NotificationList> {
        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = movieRepository.getPopularMovies(nextPage)

            LoadResult.Page(
                data = movieListResponse.data.notifications,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage+1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    
    override fun getRefreshKey(state: PagingState<Int, NotificationList>): Int? {
        TODO("Not yet implemented")
    }
}