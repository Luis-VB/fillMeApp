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
import com.example.fillmeapp.R
import com.example.fillmeapp.data.MovieData
import com.example.fillmeapp.databinding.ActivityThirdBinding

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
        observeMovie()
        searchMovieByTitle("Rambo")
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

    private fun observeMovie() {
        viewModel.movieLiveData.observe(this) {
            (binding.moviesRecyclerView.adapter as ItemAdapter).updateData(listOf(it))
        }
    }
}

class ItemAdapter(var dataSet: List<MovieData>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    fun updateData(movies: List<MovieData>) {
        dataSet = movies
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textTitle: TextView = view.findViewById(R.id.movie_title)
        private val textDirector: TextView = view.findViewById(R.id.movie_director)
        private val textGenera: TextView = view.findViewById(R.id.movie_genera)
        private val textYear: TextView = view.findViewById(R.id.movie_year)
        fun bind(position: Int) {
            textTitle.text = dataSet[position].title
            textDirector.text = dataSet[position].director
            textGenera.text = dataSet[position].genre
            textYear.text = dataSet[position].year
        }
    }
}