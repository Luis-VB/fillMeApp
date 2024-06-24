package com.example.fillmeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.fillmeapp.R
import com.example.fillmeapp.data.Movie
import com.example.fillmeapp.data.MovieList
import com.example.fillmeapp.ui.ui.theme.ProvideColorScheme

class SearchMovieActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProvideColorScheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "searchMovieText") {
                    composable("searchMovieText") {
                        val viewModel: MovieViewModel = viewModel()
                        SearchMovieBar(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchMovieBar(viewModel: MovieViewModel) {
    var searchText by remember { mutableStateOf("") }

    // Observe changes in the ViewModel
    val movies by viewModel.movieLiveDataByInputText.observeAsState(MovieList(listOf()))

    Column(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                viewModel.searchMovieByInputText(it)
            },
            label = { Text("Search Movie") },
            leadingIcon = {
                Icon(Icons.Filled.Search, contentDescription = "Search Icon")
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
        )
//        Button(onClick = { viewModel.searchMovieByInputText(searchText) }) {
//            Icon(Icons.Filled.Search, contentDescription = "Search Icon")
//        }

        LazyColumn {
            items(movies.movies) { movie ->
                MovieCard(movie)
            }
        }
    }
}

@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()

    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            val moviePoster = rememberImagePainter(data = movie.poster)
            Box(
                modifier = Modifier
                    .height(500.dp)
                    .width(400.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = moviePoster,
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                )
            }
            Column {
                Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = movie.year, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = movie.Type, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
