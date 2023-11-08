package com.example.strats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.strats.data.Datasource
import com.example.strats.model.BowlingEvent
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
                    STRATSEventDisplay(Datasource().bowlingEvents)
                }
            }
        }
    }
}

@Composable
fun STRATSEventDisplay(bowlingEvents: MutableList<BowlingEvent>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = Modifier.padding(24.dp)) {
        items(bowlingEvents) {
            BowlingEventCard(bowlingEvent = it)
        }
    }
}

@Composable
fun BowlingEventCard(bowlingEvent: BowlingEvent, modifier: Modifier = Modifier) {
    Column (modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)){
        
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    STRATSTheme {
        STRATSEventDisplay(Datasource().bowlingEvents)
    }
}