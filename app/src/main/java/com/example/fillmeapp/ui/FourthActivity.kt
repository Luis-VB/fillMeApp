package com.example.fillmeapp.ui


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fillmeapp.data.MovieList
import com.example.fillmeapp.databinding.ActivityFourthBinding
class FourthActivity : BaseActivity() {

    private lateinit var binding: ActivityFourthBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = MovieViewModel()

        val movieTitle = intent.getStringExtra("MOVIE_TITLE")
        if (!movieTitle.isNullOrEmpty()) {
            viewModel.searchMovieByInputText(movieTitle)
            viewModel.movieLiveDataByInputText.observe(this, Observer { movieData: MovieList ->
                val movies = movieData.movies
                binding.moviesRecyclerView.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@FourthActivity)
                    adapter = ItemAdapter(movies)
                }
            })
        } else {
            Toast.makeText(this, "Movie title is missing", Toast.LENGTH_SHORT).show()
        }
    }
}