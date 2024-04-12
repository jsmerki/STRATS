package com.example.strats.ui

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.strats.R
import com.example.strats.model.EventCategory
import com.example.strats.model.EventsHomepageViewModel

enum class Destinations( @StringRes screenTitle: Int) {
    Homepage(screenTitle = R.string.homepage_title),
    NewEvent(screenTitle = R.string.new_event_form_title),
    Event(screenTitle = R.string.event_page_title),
    Practice(screenTitle = R.string.practice_page_title),
    Statistics(screenTitle = R.string.statistics_page_title)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun STRATSAppBar(changeEventCategoryFilter: (EventCategory) -> Unit, showTabs: Boolean) {

    //Placeholder coming soon toast for when certain features haven't been made yet
    val context = LocalContext.current
    val comingSoonText = stringResource(R.string.toast_coming_soon)
    val comingSoonToast = {Toast.makeText(context, comingSoonText, Toast.LENGTH_SHORT).show()}

    //State variable to keep track of selected tabs, as well as tab titles and categories for each tab
    var selectedTab by remember{ mutableStateOf(0) }
    val tabTitles = listOf(stringResource(R.string.tab_title_leagues), stringResource(R.string.tab_title_tournaments), stringResource(R.string.tab_title_practices))
    val tabCategories = listOf(EventCategory.LEAGUE, EventCategory.TOURNAMENT, EventCategory.PRACTICE)

    val smallPadding = dimensionResource(R.dimen.padding_small)

    Column {
        TopAppBar(
            title = { Text(stringResource(R.string.app_name)) },
            navigationIcon = {
                IconButton(
                    onClick = comingSoonToast,
                    modifier = Modifier.padding(end = smallPadding)
                ) {
                    Icon(Icons.Filled.Menu, stringResource(R.string.content_menu_icon))
                }
            },
            actions = {
                IconButton(onClick = comingSoonToast) {
                    Icon(Icons.Filled.Search, stringResource(R.string.content_search_icon))
                }
                IconButton(onClick = comingSoonToast) {
                    Icon(Icons.Filled.MoreVert, stringResource(R.string.content_more_icon))
                }
            },
            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState()),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
        if (showTabs) {
            PrimaryTabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        text = {
                            Text(
                                text = title,
                                maxLines = 1,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        onClick = {
                            selectedTab = index
                            changeEventCategoryFilter(tabCategories[index])
                        }
                    )
                }
            }
        }
    }
}
@Composable
fun STRATSApp(
    navController : NavHostController = rememberNavController(),
    eventsHomepageViewModel: EventsHomepageViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    var showNewEventsFAB: Boolean by remember{ mutableStateOf(true) }
    var showCategoryTabs: Boolean by remember{ mutableStateOf(true)}

    Scaffold(
        modifier = modifier,
        topBar = {
            STRATSAppBar(
                changeEventCategoryFilter = { eventsHomepageViewModel.changeEventCategoryFilter(it) },
                showTabs = showCategoryTabs
            )
        },
        floatingActionButton = {
            if(showNewEventsFAB){
                NewEventFAB(newEventsFABOnClick = {
                    navController.navigate(Destinations.NewEvent.name)
                    showNewEventsFAB = false
                    showCategoryTabs = false
                })
            }
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
                EventsHomepage(
                    bowlingEvents = eventsUiState.eventsList.filter {event ->
                        event.category == eventsUiState.eventsCategoryFilter
                    }
                )
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
                        if(!eventsHomepageViewModel.errorsExist()){
                            navController.navigate(Destinations.Homepage.name)
                            showNewEventsFAB = true
                            showCategoryTabs = true
                        }
                    },
                    formUiState = eventsUiState
                )
            }
        }
    }
}