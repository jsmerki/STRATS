package com.example.strats.model

class BowlingEvent (val title: String, val location: String, val startDate: String, val endDate: String, val category: EventCategory) {

    val games = mutableListOf<String>()

    fun addGame(game: String) {
        games.add(game)
    }
}