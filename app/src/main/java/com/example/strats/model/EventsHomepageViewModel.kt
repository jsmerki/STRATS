package com.example.strats.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.strats.data.Datasource
import com.example.strats.data.EventsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EventsHomepageViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(EventsUiState())
    val uiState: StateFlow<EventsUiState> = _uiState.asStateFlow()

    //Variables to hold changes in New Event form text fields
    var newEventTitle: String by mutableStateOf("")
        private set
    var newEventLocation: String by mutableStateOf("")
        private set
    //In order to add / for date format and control cursor position, dates will be managed with TextFieldValue
    var newEventStartDate: TextFieldValue by mutableStateOf(TextFieldValue(""))
        private set
    var newEventEndDate: TextFieldValue by mutableStateOf(TextFieldValue(""))
        private set
    var newEventCategory: EventCategory by mutableStateOf(EventCategory.LEAGUE)
        private set

    fun addEvent() {
        checkInputErrors()

        if(!errorsExist()) {
            val newEvent: BowlingEvent = BowlingEvent(
                title = newEventTitle,
                location =  newEventLocation,
                startDate = newEventStartDate.text,
                endDate =  newEventEndDate.text,
                category = newEventCategory
            )

            _uiState.value.eventsList.add(newEvent)
            reset()
        }
    }

    private fun checkInputErrors() {
        //CHECK all 5 inputs for errors such as format and being empty
        //TODO: RegEx still doesn't account for technically incorrect days like Feb 30th or September 31st
        val dateRegex: Regex = """(0[1-9]|1[0-2])/([0-2][0-9]|3[0-1])/[1-2][0-9]{3}""".toRegex()

        //List to easily track which fields have errors
        val errors: Array<Boolean> = arrayOf<Boolean>(false, false, false, false, false)

        //Check all inputs individually to know which flags to set
        //TODO: Work on category field??
        if(inputIsEmpty(newEventTitle)) errors[0] = true
        if(inputIsEmpty(newEventLocation)) errors[1] = true
        //if check for category
        if(!dateRegex.matches(newEventStartDate.text)) errors[3] = true
        if(!dateRegex.matches(newEventEndDate.text)) errors[4] = true

        //Update _uiState with error flags
        _uiState.update { currentState ->
            currentState.copy(
                newEventTitleError = errors[0],
                newEventLocationError = errors[1],
                newEventCategoryError = errors[2],
                newEventStartDateError = errors[3],
                newEventEndDateError = errors[4]
            )
        }
    }

    fun errorsExist(): Boolean {
        return _uiState.value.newEventTitleError || _uiState.value.newEventLocationError ||
                _uiState.value.newEventCategoryError || _uiState.value.newEventStartDateError ||
                _uiState.value.newEventEndDateError
    }

    fun changeEventCategoryFilter(categoryToFilter: EventCategory) {
        /*_uiState.update { currentState ->
            currentState.copy(
                filteredEventsList = currentState.eventsList.filter{event ->
                    event.category == categoryToFilter
                }
            )
        }*/
        _uiState.update { currentState ->
            currentState.copy(
                eventsCategoryFilter = categoryToFilter
            )
        }
    }

    fun updateNewEventTitle(title: String) {
        newEventTitle = title
    }

    fun updateNewEventLocation(location: String) {
        newEventLocation = location
    }

    fun updateNewEventStartDate(startDate: TextFieldValue) {
        //This needs to use technically incorrect RegEx templates, otherwise slashes won't get added
        //for users when they attempt to enter incorrect dates
        //TODO: consider consolidating new date functions by moving regex checks to one function
        val months = """[0-9]{2}""".toRegex()
        val monthsAndDays = """[0-9]{2}/[0-9]{2}""".toRegex()

        newEventStartDate = if(months.matches(startDate.text) || monthsAndDays.matches(startDate.text)) {
            val newText = startDate.text + "/"
            startDate.copy(
                text = newText,
                selection = TextRange(newText.length)
            )
        } else startDate
    }

    fun updateNewEventEndDate(endDate: TextFieldValue) {
        val months = """[0-9]{2}""".toRegex()
        val monthsAndDays = """[0-9]{2}/[0-9]{2}""".toRegex()

        newEventEndDate = if(months.matches(endDate.text) || monthsAndDays.matches(endDate.text)) {
            val newText = endDate.text + "/"
            endDate.copy(
                text = newText,
                selection = TextRange(newText.length)
            )
        } else endDate
    }

    fun updateNewEventCategory(category: EventCategory) {
        newEventCategory = category
    }

    private fun inputIsEmpty(value: String): Boolean {
        return value == ""
    }
    private fun reset() {
        //Reset form text field values
        newEventTitle = ""
        newEventLocation = ""
        newEventCategory = EventCategory.LEAGUE
        newEventStartDate = newEventStartDate.copy(text = "", selection = TextRange.Zero)
        newEventEndDate = newEventEndDate.copy(text = "", selection = TextRange.Zero)

        //Reset all error flags
        _uiState.update { currentState ->
            currentState.copy(
                newEventTitleError = false,
                newEventLocationError = false,
                newEventStartDateError = false,
                newEventEndDateError = false,
                newEventCategoryError = false
            )
        }
    }
}