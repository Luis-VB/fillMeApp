package com.example.fillmeapp


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
import com.example.fillmeapp.databinding.ActivityThirdBinding

/*  1. Crear un titulo o encabezado (TextView) para el eqiuipo de Android
    2. Crear un RecyclerView con una lista de strings que contenga los nombres de los companeros de equipo
    3. Llegar a la 3a Activity desde la main con un boton que la lanze (Intent)
* */
class ThirdActivity : AppCompatActivity() {

    lateinit var binding3: ActivityThirdBinding

    fun initViews() {
//        binding3.toolBar.title = "PMOS TEAM"
        binding3.txt31.text = intent.getStringExtra("data5")
        binding3.txt32.text = intent.getStringExtra("data6")
    }
//    fun getSubtitle(): CharSequence {
//        binding3.toolBar.subtitle = "An awesome team"
//        return (binding3.toolBar.subtitle)
//    }

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
//        getSubtitle()
        setRecyclerView1()
        setRecyclerView2()
    }

    private fun setRecyclerView1() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView1)
        val androidTeamList = mapOf(
            "Patricia" to 26,
            "Arina" to 25,
            "Barbora" to 29,
            "Attila" to 44,
            "Lukas" to 31,
            "Jiri" to 30,
            "Tomas" to 32,
            "Svyatoslav" to 29,
            "Luis" to 22
        )
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ThirdActivity)
            adapter = ItemAdapter(androidTeamList.keys, androidTeamList.values)
        }
    }

    private fun setRecyclerView2() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView2)
        val iOSTeamList = mapOf(
            "Jan" to 44,
            "Helder" to 35,
            "Mohammed" to 31,
            "Myles" to 34,
            "Martin" to 33,
            "Jaime" to 29
        )
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ThirdActivity)
            adapter = ItemAdapter(iOSTeamList.keys, iOSTeamList.values)
        }
    }

}

class ItemAdapter(val nameList: Set<String>, val ageList: Collection<Int>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textValue1: TextView = view.findViewById(R.id.txt_rv)
        private val textValue2: TextView = view.findViewById(R.id.txt_rv2)
        fun bind(position: Int) {
            textValue1.text = nameList.toString()
            textValue2.text = ageList.toString()
        }
    }
}