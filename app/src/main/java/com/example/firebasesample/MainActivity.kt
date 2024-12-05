package com.example.firebasesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.firebasesample.ui.navigation.FirebaseSampleNavHost
import com.example.firebasesample.ui.theme.FirebaseSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val splashScreen = installSplashScreen()

        setContent {
            val startDestination by mainViewModel.startDestination.collectAsStateWithLifecycle()

            splashScreen.setKeepOnScreenCondition {
                startDestination == null
            }

            FirebaseSampleTheme {
                startDestination?.let {
                    FirebaseSampleNavHost(
                        navController = rememberNavController(),
                        startDestination = startDestination!!,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}
