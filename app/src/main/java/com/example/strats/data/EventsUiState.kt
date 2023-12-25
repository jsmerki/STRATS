package com.example.strats.data

import com.example.strats.model.BowlingEvent

data class EventsUiState(
   val eventsList : MutableList<BowlingEvent> = Datasource().bowlingEvents
)
