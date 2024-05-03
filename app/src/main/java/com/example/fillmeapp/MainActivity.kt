package com.example.fillmeapp

import android.content.Intent
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
            userName.setTextColor(getColor(R.color.purple_200))
            userAge.setTextColor(getColor(R.color.purple_200))
            userCity.setTextColor(getColor(R.color.purple_200))
        }
        button3.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
<<<<<<< Updated upstream
            intent.putExtra("data", "Hola, como estas?")
            intent.putExtra("data1", "Muy bien, y tu?")
=======
            intent.putExtra("data", "- Hi, mi dog doesn't have a nose.")
            intent.putExtra("data1", "- Aha, and how does he smells?.")
            intent.putExtra("data2", "- Terrible!")
            intent.putExtra("data3", "- And how old is he?")
            intent.putExtra("data4", 45)
            startActivity(intent)
        }
        binding.myButtonId4.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("data5","PMOS Android Team")
            intent.putExtra("data6","PMOS iOS Team")
>>>>>>> Stashed changes
            startActivity(intent)
        }
    }

    fun initSpinner() {
        colorButtonStatus = ColorButtonStatus.BUTTON_STATE_BLUE
        // Spinner element
        val spinner: Spinner = findViewById(R.id.color_spinner)

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
  /*  fun getColor (selectedItem: ColorButtonStatus) {
        return getColor(colorButtonStatus)
    }*/
    }
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("prueba", pacoProfile.userName)
        Log.e("prueba", pacoProfile.userAge.toString())
        Log.e("prueba", pacoProfile.userCity)

        initViews()
        initTextViews()
        initButtonListeners()
        initSpinner()
        /*getColor()*/
    }

    /*override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }*/
}











