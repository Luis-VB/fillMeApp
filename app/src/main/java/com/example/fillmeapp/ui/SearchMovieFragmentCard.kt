package com.example.fillmeapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.fillmeapp.data.MovieList
import androidx.compose.runtime.livedata.observeAsState
import coil.compose.rememberImagePainter
import com.example.fillmeapp.data.Movie

@Composable
fun SearchMovieCard(
    viewModel: MovieViewModel,
    movie: Movie,
    Title: String,
    Year: String,
    Genre: String,
    modifier: Modifier = Modifier,
    onClose: (() -> Unit)? = null,
) {
    val movies by viewModel.movieLiveDataByInputText.observeAsState(MovieList(listOf()))

    LazyColumn(
        modifier = modifier) {

        items(movies.movies) { movie ->

            Card {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = movie.title)
                    Text(text = movie.year)
                    Text(text = movie.genre)
                }

                val moviePoster = rememberImagePainter(data = movie.poster)
                Image(painter = moviePoster, contentDescription = "Movie Poster")
            }
        }
    }
}