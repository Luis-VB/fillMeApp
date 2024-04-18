package com.example.fillmeapp

import android.content.Intent
import android.graphics.Color.parseColor
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fillmeapp.databinding.ActivityMainBinding
/*
         Tarea1: Tomar el profile del usuario y escribirlo en textos dieferentes e inicializarlos
         al presionar el boton

        Tarea2:
        Cuando el usuario hace 'click' sobre el boton
        -> Tomar el texto que nuestro EditView y escribirlo en el boton que ya tenemos inicializado

        Tarea3:
        Aniade un segundo boton a la vista a la derecha del anterior que tenga un backgroundColor
        rojo con el texto "cambiar color"
        Cuando el usuario haga click sobre este segundo boton,
        cambia el color a azul de los textos de los TextViews para el nombre, la edad y la ciudad

        Tarea 4:
        Anade un drop list para elegir los colores (Spinner)

        Tarea 5:
        Anade el color correspondiente del Spinner
        */
class MainActivity : AppCompatActivity() {
    var pacoProfile = UserProfileData()
    var colorButtonStatus: ColorButtonStatus = ColorButtonStatus.BUTTON_STATE_BLACK
    lateinit var binding: ActivityMainBinding
    fun initTextViews() {
        // Take user profile data and displays in the TextView
        binding.txt1.text = pacoProfile.userName
        binding.txt2.text = pacoProfile.userAge.toString()
        binding.txt3.text = pacoProfile.userCity
    }
    fun initButtonListeners() {
        binding.myButtonId1.setOnClickListener {
            binding.myButtonId1.text = binding.editTextId.getText()
        }
        binding.myButtonId2.setOnClickListener {
            getColor()
            binding.txt1.setTextColor(parseColor(colorButtonStatus.color))
            binding.txt2.setTextColor(parseColor(colorButtonStatus.color))
            binding.txt3.setTextColor(parseColor(colorButtonStatus.color))
        }
        binding.myButtonId3.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("data", "- Hi, mi dog doesn't have a nose.")
            intent.putExtra("data1", "- Aha, and how does he smells?.")
            intent.putExtra("data2", "- Terrible!")
            intent.putExtra("data3", "- And how old is he?")
            intent.putExtra("data4", 45)
            startActivity(intent)
        }
        binding.myButtonId4.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
    fun initSpinner() {
        // Spinner element
        // Creating adapter for spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.spinner_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            //Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            binding.colorSpinner.adapter = adapter
        }
        // Spinner onItemSelectedListener listener
        binding.colorSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected (parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.selectedItem
                //val selectedItem = parent.getItemAtPosition(position)
                Toast.makeText(this@MainActivity, "Selected item: $selectedItem", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected (parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("prueba", pacoProfile.userName)
        Log.e("prueba", pacoProfile.userAge.toString())
        Log.e("prueba", pacoProfile.userCity)

        initTextViews()
        initButtonListeners()
        initSpinner()

        Log.e("Color", binding.colorSpinner.selectedItem.toString())

        getColor()
    }

    fun getColor() {
        colorButtonStatus = when (binding.colorSpinner.selectedItem) {
            ColorButtonStatus.BUTTON_STATE_BLACK.nameColor -> ColorButtonStatus.BUTTON_STATE_BLACK
            ColorButtonStatus.BUTTON_STATE_BLUE.nameColor -> ColorButtonStatus.BUTTON_STATE_BLUE
            else -> ColorButtonStatus.BUTTON_STATE_PURPLE
        }
    }
}











