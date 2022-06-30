package com.example.paginationcompose.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.paginationcompose.ui.screen.MainScreen
import com.example.paginationcompose.ui.screen.MainViewModel
import com.example.paginationcompose.ui.theme.PagingComposeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		setContent {
			PagingComposeTheme {
				MainScreen(mainViewModel)
			}
		}
	}
}