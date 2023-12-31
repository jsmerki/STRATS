package com.example.strats.model

import androidx.annotation.StringRes
import com.example.strats.R

enum class EventCategory(@StringRes val plaintext: Int) {
    TOURNAMENT(plaintext = R.string.event_category_tournament),
    LEAGUE(plaintext = R.string.event_category_league),
    PRACTICE(plaintext = R.string.event_category_practice)
}