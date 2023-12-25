package com.example.strats.ui

import androidx.annotation.StringRes
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.strats.R
import com.example.strats.data.Datasource

enum class Destinations( @StringRes screenTitle: Int) {
    Homepage(screenTitle = R.string.homepage_title),
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
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Homepage.name
    ) {
        composable(route = Destinations.Homepage.name) {
            EventsHomepage(bowlingEvents = Datasource().bowlingEvents)
        }
    }
}