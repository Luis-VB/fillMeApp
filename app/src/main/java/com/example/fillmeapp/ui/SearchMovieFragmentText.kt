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
import com.example.fillmeapp.data.MovieList
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.example.fillmeapp.data.Movie
import coil.compose.rememberImagePainter

@Composable
fun SearchMovieText(
    viewModel: MovieViewModel,
    navController: NavHostController

) {
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
        Button(onClick = {
            viewModel.searchMovieByInputText(searchText)
            navController.navigate("searchMovieScreenCards")
        }) {
            Icon(Icons.Filled.Search, contentDescription = "Search Icon")
        }
    }
}

