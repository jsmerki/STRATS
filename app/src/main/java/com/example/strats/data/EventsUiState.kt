package com.example.strats.data

import com.example.strats.model.BowlingEvent
import com.example.strats.model.EventCategory

data class EventsUiState(
   val eventsList : MutableList<BowlingEvent> = Datasource().bowlingEvents,
   val eventsCategoryFilter: EventCategory = EventCategory.LEAGUE,
   val newEventTitleError: Boolean = false,
   val newEventLocationError: Boolean = false,
   val newEventStartDateError: Boolean = false,
   val newEventEndDateError: Boolean = false,
   val newEventCategoryError: Boolean = false
)
