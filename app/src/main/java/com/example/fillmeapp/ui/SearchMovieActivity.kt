package com.example.fillmeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
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
                        SearchMovieText(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchMovieText(viewModel: MovieViewModel) {
    var searchText by remember { mutableStateOf("") }

    // Observe changes in the ViewModel
    val movies by viewModel.movieLiveDataByInputText.observeAsState(MovieList(listOf()))

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search Movie") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { viewModel.searchMovieByInputText(searchText) }) {
            Icon(Icons.Filled.Search, contentDescription = "Search Icon")
        }

        LazyColumn {
            items(movies.movies) { movie ->
                MovieCard(movie)
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = movie.title)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = movie.year)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = movie.genre)
            Spacer(modifier = Modifier.height(8.dp))
            val moviePoster = rememberImagePainter(data = movie.poster)
            Image(painter = moviePoster, contentDescription = "Movie Poster",modifier = Modifier.height(180.dp))
        }
    }
}