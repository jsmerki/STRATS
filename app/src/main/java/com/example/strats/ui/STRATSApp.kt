package com.example.strats.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.strats.R
import com.example.strats.data.Datasource
import com.example.strats.data.EventsUiState
import com.example.strats.model.EventsHomepageViewModel

enum class Destinations( @StringRes screenTitle: Int) {
    Homepage(screenTitle = R.string.homepage_title),
    NewEvent(screenTitle = R.string.new_event_form_title),
    Event(screenTitle = R.string.event_page_title),
    Practice(screenTitle = R.string.practice_page_title),
    Statistics(screenTitle = R.string.statistics_page_title)
}
@Composable
fun STRATSAppBar(

) {

}
@Composable
fun STRATSApp(
    navController : NavHostController = rememberNavController(),
    eventsHomepageViewModel: EventsHomepageViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {STRATSAppBar()},
        floatingActionButton = {
            NewEventFAB(newEventsFABOnClick = {navController.navigate(Destinations.NewEvent.name)})
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->

        val eventsUiState by eventsHomepageViewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = Destinations.Homepage.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Destinations.Homepage.name) {
                EventsHomepage(bowlingEvents = eventsUiState.eventsList)
            }

            composable(route = Destinations.NewEvent.name) {
                NewEventForm(
                    newEventTitle = eventsHomepageViewModel.newEventTitle,
                    newEventLocation = eventsHomepageViewModel.newEventLocation,
                    newEventStartDate = eventsHomepageViewModel.newEventStartDate,
                    newEventEndDate = eventsHomepageViewModel.newEventEndDate,
                    newEventCategory = eventsHomepageViewModel.newEventCategory,
                    onNewEventTitleChanged = { eventsHomepageViewModel.updateNewEventTitle(it) },
                    onNewEventLocationChanged = { eventsHomepageViewModel.updateNewEventLocation(it) },
                    onNewEventStartDateChanged = { eventsHomepageViewModel.updateNewEventStartDate(it) },
                    onNewEventEndDateChanged = { eventsHomepageViewModel.updateNewEventEndDate(it) },
                    onNewEventCategoryChanged = { eventsHomepageViewModel.updateNewEventCategory(it) },
                    onCreateEventClicked = {
                        //Try to add the event but only navigate if no errors exist in the form
                        //Must call addEvent first since check for errors occurs inside it
                        eventsHomepageViewModel.addEvent()
                        if(!eventsHomepageViewModel.errorsExist()) navController.navigate(Destinations.Homepage.name)
                    },
                    formUiState = eventsUiState
                )
            }
        }
    }

}