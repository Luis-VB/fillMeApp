package com.example.fillmeapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fillmeapp.databinding.ActivitySecondBinding
import com.example.fillmeapp.databinding.ActivityThirdBinding

/*  1. Crear un titulo o encabezado (TextView) para el eqiuipo de Android
    2. Crear un RecyclerView con una lista de strings que contenga los nombres de los companeros de equipo
    3. Llegar a la 3a Activity desde la main con un boton que la lanze (Intent)
* */
class ThirdActivity: AppCompatActivity() {

    lateinit var binding3: ActivityThirdBinding
    fun initViews() {

        binding3.txt31.text = intent.getStringExtra("data5")
    }

//    fun printListOfTeamMembers(mutableList: MutableList<String>) {
//        var androidTeamList = mutableListOf.suffle("Patricia")
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
    }
}