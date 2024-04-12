package com.example.strats.data

import com.example.strats.model.BowlingEvent
import com.example.strats.model.EventCategory

data class Datasource(val bowlingEvents: MutableList<BowlingEvent> = mutableListOf(
    BowlingEvent("Saturday Night Live","Fiesta Lanes", "11-7-2023", "11-7-2023", EventCategory.LEAGUE),
    BowlingEvent("Tucson City Tournament","Fiesta Lanes", "7-12-2024", "7-12-2024", EventCategory.TOURNAMENT),
    BowlingEvent("Arizona State Tournament", "South Point Bowling Plaza", "7-13-2024", "7-13-2024", EventCategory.TOURNAMENT),
    BowlingEvent("Mega Cash Trios","Bowlero Avondale", "11-7-2023", "11-7-2023", EventCategory.LEAGUE),
    BowlingEvent("USBC Nationals","South Point Bowling Plaza", "7-12-2024", "7-12-2024", EventCategory.TOURNAMENT),
    BowlingEvent("USBC Nationals Practice", "South Point Bowling Plaza", "7-13-2024", "7-13-2024", EventCategory.PRACTICE),
))
