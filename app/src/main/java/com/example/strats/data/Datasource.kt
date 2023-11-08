package com.example.strats.data

import com.example.strats.model.BowlingEvent
import com.example.strats.model.EventCategory

data class Datasource(val bowlingEvents: MutableList<BowlingEvent> = mutableListOf(
    BowlingEvent("Mega Cash Trios","11-7-2023", "Bowlero Avondale", EventCategory.LEAGUE),
    BowlingEvent("USBC Nationals Team","7-12-2024", "South Point Bowling Plaza", EventCategory.TOURNAMENT),
    BowlingEvent("USBC Nationals Doubles/Singles", "7-13-2024", "South Point Bowling Plaza", EventCategory.TOURNAMENT),
))
