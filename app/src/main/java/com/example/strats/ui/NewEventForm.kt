package com.example.strats.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.strats.R
import com.example.strats.data.EventsUiState
import com.example.strats.model.EventCategory
import com.example.strats.model.EventsHomepageViewModel
import com.example.strats.ui.theme.STRATSTheme

@Composable
fun NewEventForm(
    newEventTitle: String,
    newEventLocation: String,
    newEventCategory: EventCategory,
    newEventStartDate: String,
    newEventEndDate: String,
    onNewEventTitleChanged: (String) -> Unit,
    onNewEventLocationChanged: (String) -> Unit,
    onNewEventCategoryChanged: (EventCategory) -> Unit,
    onNewEventStartDateChanged: (String) -> Unit,
    onNewEventEndDateChanged: (String) -> Unit,
    onCreateEventClicked: () -> Unit,
    formUiState: EventsUiState,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(dimensionResource(R.dimen.padding_large))) {
        Row(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))) {
            Text(
                text = stringResource(R.string.new_event_form_heading),
                style = MaterialTheme.typography.titleLarge
            )
        }
        //TODO: Pass in isError values from UI State
        Row {
            OutlinedTextField(
                value = newEventTitle,
                onValueChange = onNewEventTitleChanged,
                label = { Text(text = stringResource(R.string.input_prompt_event_title)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row {
            OutlinedTextField(
                value = newEventLocation,
                onValueChange = onNewEventLocationChanged,
                label = { Text(text = stringResource(R.string.input_prompt_event_location)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row {
            OutlinedTextField(
                value = newEventStartDate,
                onValueChange = onNewEventStartDateChanged,
                label = { Text(text = stringResource(R.string.input_prompt_start_date)) },
                placeholder = { Text(text = "mm/dd/yyyy" )},
                singleLine = true,
                modifier = Modifier
                    .weight(0.5F)
                    .padding(end = dimensionResource(id = R.dimen.padding_xsmall))
            )
            OutlinedTextField(
                value = newEventEndDate,
                onValueChange = onNewEventEndDateChanged,
                label = { Text(text = stringResource(R.string.input_prompt_end_date)) },
                placeholder = { Text(text = "mm/dd/yyyy" )},
                singleLine = true,
                modifier = Modifier
                    .weight(0.5F)
                    .padding(start = dimensionResource(id = R.dimen.padding_xsmall))
            )
        }
        Row {
            OutlinedTextField(
                value = stringResource(newEventCategory.plaintext),
                onValueChange = {},
                label = { Text(stringResource(R.string.input_prompt_event_category)) },
                readOnly = true,
                trailingIcon = { 
                    IconButton(onClick = {
                        expanded = true
                    }){
                        Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                    }
                    EventCategoryDropdown(
                        expanded = expanded,
                        onDropdownItemClicked = onNewEventCategoryChanged,
                        onDismissRequest = { expanded = false }
                    )
                }
            )
        }
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onCreateEventClicked,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_medium))
            ) {
                Text(text = "Create")
            }
        }
    }
}

@Composable
fun EventCategoryDropdown(
    expanded: Boolean,
    onDropdownItemClicked: (EventCategory) -> Unit,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(R.string.event_category_league)
                )
            },
            onClick = {
                onDropdownItemClicked(EventCategory.LEAGUE)
                onDismissRequest()
            }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(R.string.event_category_tournament)
                )
            },
            onClick = {
                onDropdownItemClicked(EventCategory.TOURNAMENT)
                onDismissRequest()
            }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(R.string.event_category_practice)
                )
            },
            onClick = {
                onDropdownItemClicked(EventCategory.PRACTICE)
                onDismissRequest()
            }
        )
    }
}

@Preview(showBackground = true,
    showSystemUi = true,)
@Composable
fun NewEventFormPreview() {
    STRATSTheme {
        val viewModel: EventsHomepageViewModel = viewModel()
        NewEventForm(
            newEventTitle = viewModel.newEventTitle,
            newEventLocation = viewModel.newEventLocation,
            newEventCategory = viewModel.newEventCategory,
            newEventStartDate = viewModel.newEventStartDate,
            newEventEndDate = viewModel.newEventEndDate,
            onNewEventTitleChanged = { viewModel.updateNewEventTitle(it) },
            onNewEventLocationChanged = { viewModel.updateNewEventLocation(it) },
            onNewEventCategoryChanged = { viewModel.updateNewEventCategory(it) },
            onNewEventEndDateChanged = { viewModel.updateNewEventEndDate(it) },
            onNewEventStartDateChanged = { viewModel.updateNewEventStartDate(it) },
            onCreateEventClicked = {},
            formUiState = viewModel.uiState.collectAsState().value
        )
    }
}