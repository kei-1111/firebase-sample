package com.example.firebasesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.firebasesample.ui.navigation.FirebaseSampleNavHost
import com.example.firebasesample.ui.navigation.Screen
import com.example.firebasesample.ui.theme.FirebaseSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirebaseSampleTheme {
                FirebaseSampleNavHost(
                    navController = rememberNavController(),
                    startDestination = Screen.Login,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
