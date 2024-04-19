package com.example.fillmeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fillmeapp.databinding.ActivitySecondBinding
import com.example.fillmeapp.databinding.ActivityThirdBinding

/*  1. Crear un titulo o encabezado (TextView) para el eqiuipo de Android
    2. Crear un RecyclerView con una lista de strings que contenga los nombres de los companeros de equipo
    3. Llegar a la 3a Activity desde la main con un boton que la lanze (Intent)
* */
class ThirdActivity : AppCompatActivity() {

    lateinit var binding3: ActivityThirdBinding
    fun initViews() {
        binding3.toolBar.title = "PMOS TEAM"
        binding3.txt31.text = intent.getStringExtra("data5")
        binding3.txt32.text = intent.getStringExtra("data6")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding3 = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding3.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
        setRecyclerView1()
        setRecyclerView2()

    }

    private fun setRecyclerView1() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView1)
        val androidTeamList = mutableListOf(
            "Patricia",
            "Arina",
            "Barbora",
            "Attila",
            "Lukas",
            "Jiri",
            "Tomas",
            "Svyatoslav",
            "Luis"
        )
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ThirdActivity)
            adapter = ItemAdapter(androidTeamList.shuffled())
        }
    }

    private fun setRecyclerView2() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView2)
        val iOSTeamList = mutableListOf("Jan", "Helder", "Mohammed", "Myles", "Martin", "Jaime")
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ThirdActivity)
            adapter = ItemAdapter(iOSTeamList.shuffled())
        }
    }

}

class ItemAdapter(val dataSet: List<String>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
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

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textValue: TextView = view.findViewById(R.id.txt_rv)
        fun bind(position: Int) {
            textValue.text = dataSet[position]
        }
    }
}