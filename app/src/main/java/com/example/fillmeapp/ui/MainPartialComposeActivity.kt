package com.example.fillmeapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fillmeapp.R
import com.example.fillmeapp.data.UserProfileData
import com.example.fillmeapp.databinding.ActivityMainBinding
import com.example.fillmeapp.databinding.ActivityMainPartialComposeBinding
import com.example.fillmeapp.ui.ui.theme.FillMeAppTheme

class MainPartialComposeActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainPartialComposeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_partial_compose)

        findViewById<ComposeView>(R.id.composeMain).setContent {
            FillMeAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var pacoProfile = UserProfileData()
                    ProfileDataRender(
                        name = pacoProfile.userName,
                        year = pacoProfile.userAge.toString(),
                        city = pacoProfile.userCity,
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}