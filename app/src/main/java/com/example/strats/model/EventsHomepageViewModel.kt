package com.example.strats.model

import android.media.metrics.Event
import com.example.strats.data.EventsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EventsHomepageViewModel {

    private val _uiState = MutableStateFlow(EventsUiState())
    val uiState: StateFlow<EventsUiState> = _uiState.asStateFlow()

    fun addEvent(newEvent: BowlingEvent) {
        //TODO: Should there be checking logic?
        _uiState.value.eventsList.add(newEvent)
    }
}