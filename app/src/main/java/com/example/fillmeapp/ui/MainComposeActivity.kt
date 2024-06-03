package com.example.fillmeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fillmeapp.data.UserProfileData
import com.example.fillmeapp.ui.ui.theme.FillMeAppTheme

class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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

@Composable
fun ProfileDataRender(name: String, year: String, city: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        content = {
            Text(text = name)
            Text(text = year)
            Text(text = city)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileDataRenderPreview() {
    FillMeAppTheme {
        ProfileDataRender("Ana", "12", "Madrid")
    }
}