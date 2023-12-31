package com.example.strats.data

import com.example.strats.model.BowlingEvent
import com.example.strats.model.EventCategory

data class Datasource(val bowlingEvents: MutableList<BowlingEvent> = mutableListOf(
    BowlingEvent("Mega Cash Trios","Bowlero Avondale", "11-7-2023", "11-7-2023", EventCategory.LEAGUE),
    BowlingEvent("USBC Nationals Team","South Point Bowling Plaza", "7-12-2024", "7-12-2024", EventCategory.TOURNAMENT),
    BowlingEvent("USBC Nationals Doubles/Singles", "South Point Bowling Plaza", "7-13-2024", "7-13-2024", EventCategory.TOURNAMENT),
))
