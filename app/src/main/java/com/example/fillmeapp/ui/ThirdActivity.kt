package com.example.fillmeapp.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fillmeapp.R
import com.example.fillmeapp.databinding.ActivityThirdBinding
import android.widget.ImageView
import com.example.fillmeapp.data.Movie


/*  1. Crear un titulo o encabezado (TextView) para el eqiuipo de Android
    2. Crear un RecyclerView con una lista de strings que contenga los nombres de los companeros de equipo
    3. Llegar a la 3a Activity desde la main con un boton que la lanze (Intent)
* */
class ThirdActivity : AppCompatActivity() {

    lateinit var binding: ActivityThirdBinding
    private lateinit var viewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = MovieViewModel()
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setRecyclerView()
        observeMovies()
        searchMovieByID("tt1285016")
    }

    private fun setRecyclerView() {
        binding.moviesRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ThirdActivity)
            adapter = ItemAdapter(mutableListOf())
        }
    }

    private fun searchMovieByTitle(title: String) {
        viewModel.searchMovie(title)
    }

    private fun searchMovieByID(id: String) {
        viewModel.searchMovieByID(id)
    }

    private fun observeMovies() {
        viewModel.movieLiveDataByTitle.observe(this) {
            (binding.moviesRecyclerView.adapter as ItemAdapter).updateData((it))
        }
        viewModel.movieLiveDataByID.observe(this) {
            (binding.moviesRecyclerView.adapter as ItemAdapter).updateData((it))
        }
    }
}

class ItemAdapter(var dataSet: List<Movie>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activity_fourth_card, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    fun updateData(movie: Movie) {
        dataSet = listOf(movie)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textTitle: TextView = itemView.findViewById(R.id.movie_card_title)
        private val textDirector: TextView = itemView.findViewById(R.id.movie_card_director)
        private val textGenera: TextView = itemView.findViewById(R.id.movie_card_genre)
        private val textYear: TextView = itemView.findViewById(R.id.movie_card_year)
        private val imageView: ImageView = itemView.findViewById(R.id.movie_poster)
        fun bind(position: Int) {
            Glide.with(itemView.context)
                .load(dataSet[position].poster)
                .fitCenter()
                .into(imageView)
            textTitle.text = dataSet[position].title
            textDirector.text = dataSet[position].director
            textGenera.text = dataSet[position].genre
            textYear.text = dataSet[position].year
        }
    }
}