package com.example.strats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.strats.data.Datasource
import com.example.strats.ui.EventsHomepage
import com.example.strats.ui.STRATSApp
import com.example.strats.ui.theme.STRATSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            STRATSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    STRATSApp()
                }
            }
        }
    }
}

//TODO: Create Lifecycle Methods!!!

@Preview(showBackground = true)
@Composable
fun STRATSHomepagePreview() {
    STRATSTheme {
        STRATSApp()
    }
}