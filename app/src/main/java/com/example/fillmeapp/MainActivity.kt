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
    lateinit var userName: TextView
    lateinit var userAge: TextView
    lateinit var userCity: TextView
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var eText: EditText
    lateinit var colorButtonStatus: ColorButtonStatus
    lateinit var binding: ActivityMainBinding
    lateinit var spinner: Spinner
    fun initViews() {

        userName = findViewById(R.id.txt1)
        userAge = findViewById(R.id.txt2)
        userCity = findViewById(R.id.txt3)
        button1 = findViewById(R.id.myButtonId1)
        button2 = findViewById(R.id.myButtonId2)
        button3 = findViewById(R.id.myButtonId3)
        eText = findViewById(R.id.edit_text_id)
    }

    fun initTextViews() {
        // Take user profile data and displays in the TextView
        userName.text = pacoProfile.userName
        userAge.text = pacoProfile.userAge.toString()
        userCity.text = pacoProfile.userCity
    }

    fun initButtonListeners() {
        button1.setOnClickListener {
            button1.text = eText.getText()
        }
        button2.setOnClickListener {
            userName.setTextColor(parseColor(colorButtonStatus.color))
            userAge.setTextColor(parseColor(colorButtonStatus.color))
            userCity.setTextColor(parseColor(colorButtonStatus.color))
        }
        button3.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("data", "- Hi, mi dog doesn't have a nose.")
            intent.putExtra("data1", "- Aha, and how does he smells?.")
            intent.putExtra("data2", "- Terrible!")
            startActivity(intent)
        }
    }

    fun initSpinner() {
        // Spinner element
        spinner = findViewById(R.id.color_spinner)

        // Creating adapter for spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.spinner_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            //Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinner.adapter = adapter
        }
        // Spinner onItemSelectedListener listener
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
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

        initViews()
        initTextViews()
        initButtonListeners()
        initSpinner()

        Log.e("Color", spinner.selectedItem.toString())
        getColor()
//        getColor2()

    }
    fun getColor() {
        if(spinner.selectedItem == ColorButtonStatus.BUTTON_STATE_BLACK) {
            colorButtonStatus = ColorButtonStatus.BUTTON_STATE_BLACK
        }
        else if(spinner.selectedItem == ColorButtonStatus.BUTTON_STATE_BLUE) {
            colorButtonStatus = ColorButtonStatus.BUTTON_STATE_BLUE
        }
        else {
            colorButtonStatus = ColorButtonStatus.BUTTON_STATE_PURPLE
        }
    }

//    fun getColor2() {
//        when (spinner.selectedItem) {
//            ColorButtonStatus.BUTTON_STATE_BLACK -> ColorButtonStatus.BUTTON_STATE_BLACK
//            ColorButtonStatus.BUTTON_STATE_BLUE -> ColorButtonStatus.BUTTON_STATE_BLUE
//            ColorButtonStatus.BUTTON_STATE_PURPLE -> ColorButtonStatus.BUTTON_STATE_PURPLE
//        }
//    }
}











