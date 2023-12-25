package com.example.strats.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.strats.R
import com.example.strats.model.BowlingEvent
import com.example.strats.model.EventCategory

@Composable
fun EventsHomepage(bowlingEvents: MutableList<BowlingEvent>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = Modifier.padding(24.dp)) {
        items(bowlingEvents) {
            BowlingEventListItem(
                bowlingEvent = it,
                //modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun BowlingEventListItem(bowlingEvent: BowlingEvent, modifier: Modifier = Modifier) {
    val categoryStrings: Map<EventCategory, String> = mapOf<EventCategory, String>(
        EventCategory.LEAGUE to stringResource(id = R.string.event_category_league),
        EventCategory.TOURNAMENT to stringResource(id = R.string.event_category_tournament),
        EventCategory.PRACTICE to stringResource(id = R.string.event_category_practice)
    )

    Column(modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        ) {
            Text(
                text = bowlingEvent.title,
                modifier = Modifier.weight(0.65F),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = categoryStrings.getOrDefault(bowlingEvent.category, ""),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.35F),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(modifier = Modifier.padding(start = 16.dp, bottom = 12.dp)) {
            Text(
                text = bowlingEvent.location,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.weight(1.0F))
            Icon(
                Icons.Rounded.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = bowlingEvent.date,
                style = MaterialTheme.typography.bodyMedium)
            Text(
                text = bowlingEvent.games.size.toString() + " " + stringResource(id = R.string.games_suffix),
                style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "--- Avg.",
                style = MaterialTheme.typography.bodyMedium)
        }
        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}