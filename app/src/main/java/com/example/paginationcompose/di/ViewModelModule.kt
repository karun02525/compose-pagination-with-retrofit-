package com.example.paginationcompose.di

import com.example.paginationcompose.ui.screen.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}