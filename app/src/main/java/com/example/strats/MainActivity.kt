package com.example.strats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
            BowlingEventCard(
                bowlingEvent = it,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun BowlingEventCard(bowlingEvent: BowlingEvent, modifier: Modifier = Modifier) {
    Card (modifier = modifier){
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(
                    text = bowlingEvent.title,
                    modifier = Modifier.weight(0.65F),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = bowlingEvent.category.toString().lowercase().capitalize(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.35F),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = bowlingEvent.location,
                    style = MaterialTheme.typography.bodyMedium)
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = bowlingEvent.date,
                    style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = bowlingEvent.games.size.toString() + stringResource(id = R.string.games_suffix),
                    style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = "--- Avg.",
                    style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    STRATSTheme {
        STRATSEventDisplay(Datasource().bowlingEvents)
    }
}