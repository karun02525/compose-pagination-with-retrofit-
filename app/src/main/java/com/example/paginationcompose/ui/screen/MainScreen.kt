package com.example.paginationcompose.ui.screen

import android.text.format.DateUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.paginationcompose.ui.state.ErrorItem
import com.example.paginationcompose.ui.state.LoadingItem
import com.example.paginationcompose.ui.state.LoadingView
import kotlinx.coroutines.flow.Flow
import com.example.paginationcompose.R
import com.example.paginationcompose.model.NotificationList
import java.util.*
import kotlin.random.Random


@Composable
fun MainScreen(mainViewModel: MainViewModel) {
	Scaffold(
		topBar = {
			TopAppBar(
				title = { Text(text = "Notification", style = TextStyle(textAlign= TextAlign.Center)) }
			)
		}
	) { paddingValues ->
		Column(
			modifier = Modifier
				.padding(bottom = paddingValues.calculateBottomPadding())
		)
		{ MovieList(movies = mainViewModel.movies) }
	}
}

@Composable
fun MovieList(movies: Flow<PagingData<NotificationList>>) {
	val lazyMovieItems = movies.collectAsLazyPagingItems()
	
	LazyColumn {
		
		items(lazyMovieItems) { movie ->
			ItemUI(item = movie!!)
		}
		
		lazyMovieItems.apply {
			when {
				loadState.refresh is LoadState.Loading -> {
					item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
				}
				loadState.append is LoadState.Loading -> {
					item { LoadingItem() }
				}
				loadState.refresh is LoadState.Error -> {
					val e = lazyMovieItems.loadState.refresh as LoadState.Error
					item {
						ErrorItem(
							message = e.error.localizedMessage!!,
							modifier = Modifier.fillParentMaxSize(),
							onClickRetry = { retry() }
						)
					}
				}
				loadState.append is LoadState.Error -> {
					val e = lazyMovieItems.loadState.append as LoadState.Error
					item {
						ErrorItem(
							message = e.error.localizedMessage!!,
							onClickRetry = { retry() }
						)
					}
				}
			}
		}
	}
}


@Composable
fun ItemUI(item: NotificationList) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(10.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	) {

		if (item.hasSenderProfilePicture()) {
			AsyncImage(
				model = ImageRequest.Builder(LocalContext.current)
					.data("base url" + item.senderProPic)
					.crossfade(true).build(),
				placeholder = painterResource(R.drawable.ab_progress),
				contentDescription = "",
				modifier = Modifier
					.width(60.dp)
					.height(60.dp)
					.clip(CircleShape),
				contentScale = ContentScale.Crop
			)
		} else {
			TextCircle(item.receiverMessage.english)
		}


		Column(modifier = Modifier.padding(start = 10.dp)) {
			TextUI(
				str = item.receiverMessage.english,
				fontSize = 14.sp,
				modifier = Modifier.padding(top = 5.dp)
			)
			TextUI(
				str = getTimeLabel(item.created_at),
				fontSize = 12.sp,
				color = Color(0xFFDFB1EF),
				modifier = Modifier.padding(top = 5.dp)
			)
		}

		if(!item.notifyType.equals("2")) {
			if(item.postImage !=null) {
				AsyncImage(
					model = ImageRequest.Builder(LocalContext.current)
						.data("base url" + item.postImage)
						.crossfade(true).build(),
					placeholder = painterResource(R.drawable.ab_progress),
					contentDescription = "",
					modifier = Modifier
						.width(60.dp)
						.height(60.dp)
						.clip(RoundedCornerShape(10)),
					contentScale = ContentScale.Crop
				)
			}else{

			}
		}

	}
}
















@Composable
fun MovieItem(movie: NotificationList) {
	Row(
		modifier = Modifier
			.padding(start = 16.dp, top = 16.dp, end = 16.dp)
			.fillMaxWidth(),
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically
	) {
		MovieTitle(
			movie.senderName,
			modifier = Modifier.weight(1f)
		)
		MovieImage(
			"" + movie.receiverName,
			modifier = Modifier
				.padding(start = 16.dp)
				.size(90.dp)
		)
	}
}


@Composable
fun TextUI(
	modifier: Modifier = Modifier,
	str: String,
	color: Color = Color.White,
	fontSize: TextUnit = TextUnit.Unspecified,
	fontWeight: FontWeight = FontWeight.Light,

	) {
	Text(
		text = str, style = TextStyle(
			color = color,
			fontSize = fontSize,
			fontWeight = fontWeight,
			fontFamily = FontFamily.Default
		), modifier = modifier
	)
}

fun Color.Companion.random() : Color {
	val red = Random.nextInt(256)
	val green = Random.nextInt(256)
	val blue = Random.nextInt(256)
	return Color(red, green, blue)
}

@Composable
fun TextCircle(str:String) {
	Box(modifier = Modifier
		.width(60.dp)
		.height(60.dp)
		.fillMaxSize()
		.aspectRatio(1f)
		.background(Color.Companion.random(), shape = CircleShape),
		contentAlignment = Alignment.Center
	) {
		Text(
			text = str[0].toString()
				.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString() },
			color = Color.White,
			textAlign = TextAlign.Center,
			fontSize = 25.sp,
			fontWeight = FontWeight.Bold
		)
	}
}


private fun getTimeLabel(created_at: String): String {
	return "\u0020\u2022 " + created_at.toLong().let {
		DateUtils.getRelativeTimeSpanString(it)
	}
}




@Composable
fun MovieImage(
	imageUrl: String,
	modifier: Modifier = Modifier
) {
	AsyncImage(
		model = ImageRequest.Builder(LocalContext.current)
			.data(imageUrl)
			.crossfade(true)
			.build(),
		placeholder = painterResource(R.drawable.ic_launcher_background), alpha = 0.45f,
		contentDescription = null,
		contentScale = ContentScale.Crop,
		modifier = modifier
	)
	
}

@Composable
fun MovieTitle(
	title: String,
	modifier: Modifier = Modifier
) {
	Text(
		modifier = modifier,
		text = title,
		maxLines = 2,
		style = MaterialTheme.typography.h6,
		overflow = TextOverflow.Ellipsis
	)
}