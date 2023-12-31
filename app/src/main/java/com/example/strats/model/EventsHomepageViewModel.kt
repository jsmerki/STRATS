package com.example.strats.model

import android.media.metrics.Event
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.strats.data.EventsUiState
import com.example.strats.ui.Destinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EventsHomepageViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(EventsUiState())
    val uiState: StateFlow<EventsUiState> = _uiState.asStateFlow()

    val dateStringTemplate: String = "mm/dd/yyyy"

    //Variables to hold changes in New Event form text fields
    var newEventTitle: String by mutableStateOf("")
        private set
    var newEventLocation: String by mutableStateOf("")
        private set
    var newEventStartDate: String by mutableStateOf(dateStringTemplate)
        private set
    var newEventEndDate: String by mutableStateOf(dateStringTemplate)
        private set
    var newEventCategory: EventCategory by mutableStateOf(EventCategory.LEAGUE)
        private set

    fun addEvent() {
        //TODO: Should there be checking logic? YES! Check for errors in inputs and update accordingly!
        val newEvent: BowlingEvent = BowlingEvent(
            title = newEventTitle,
            location =  newEventLocation,
            startDate = newEventStartDate,
            endDate =  newEventEndDate,
            category = newEventCategory
        )
        _uiState.value.eventsList.add(newEvent)
        reset()
    }

    fun checkInputErrors() {
        //CHECK all 5 inputs for errors such as format and being empty
    }

    fun updateNewEventTitle(title: String) {
        newEventTitle = title
    }

    fun updateNewEventLocation(location: String) {
        newEventLocation = location
    }

    fun updateNewEventStartDate(startDate: String) {
        newEventStartDate = startDate
    }

    fun updateNewEventEndDate(endDate: String) {
        newEventEndDate = endDate
    }

    fun updateNewEventCategory(category: EventCategory) {
        newEventCategory = category
    }

    fun inputIsEmpty(value: String): Boolean {
        return value == ""
    }
    fun reset() {
        newEventTitle = ""
        newEventLocation = ""
        newEventCategory = EventCategory.LEAGUE
        newEventStartDate = dateStringTemplate
        newEventEndDate = dateStringTemplate
    }
}