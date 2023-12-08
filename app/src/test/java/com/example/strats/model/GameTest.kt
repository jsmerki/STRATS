package com.example.strats.model

import org.junit.Test

import org.junit.Assert.*

class GameTest {

    val testGame: Game = Game()

    @Test
    fun game_CalculateScore_FirstFrame() {
        testGame.addFrame(7, 3) //Spare

        assertEquals(10, testGame.frames[0].frameScore)
    }

}