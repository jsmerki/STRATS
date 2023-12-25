package com.example.strats.model

import org.junit.Test

import org.junit.Assert.*

class GameTest {

    val testGame: Game = Game()

    @Test
    fun game_GetNextThrow_NextFrameExists() {
        testGame.addFrame(9, 1) //Next throw of index 0 is 10
        testGame.addFrame(10, 0)

        assertEquals(10, testGame.getNextThrow(0))
    }

    @Test
    fun game_GetNextThrow_NextFrameDoesntExist() {
        testGame.addFrame(6, 2) //Next throw doesn't exist yet

        assertEquals(0, testGame.getNextThrow(0))
    }

    @Test
    fun game_GetNextThrow_GameIsEmpty() {
        assertEquals(0, testGame.getNextThrow(0))
    }

    @Test
    fun game_GetNextThrow_RequestedIndexDoesntExist() {
        testGame.addFrame(6, 2)
        testGame.addFrame(9, 1)

        assertEquals(0, testGame.getNextThrow(3)) //Frame 3 doesn't exist yet
        assertEquals(0, testGame.getNextThrow(11)) //Frame 11 would never exist
    }

    @Test
    fun game_GetNextTwoThrows_NextFramesExist() {
        testGame.addFrame(10, 0)
        testGame.addFrame(10, 0)
        testGame.addFrame(8, 1)
        testGame.addFrame(10, 0)

        //Both frames exist, second frame only has first throw so first throw from third is needed
        assertEquals(18, testGame.getNextTwoThrows(0))
        //Both frames exist, third frame has both necessary throws so fourth frame is ignored
        assertEquals(9, testGame.getNextTwoThrows(1))
    }

    @Test
    fun game_GetNextTwoThrows_OnlyNextFrameExists() {
        testGame.addFrame(10, 0)
        testGame.addFrame(9, 0)

        assertEquals(9, testGame.getNextTwoThrows(0))
    }

    @Test
    fun game_GetNextTwoThrows_NeitherFramesExist() {
        testGame.addFrame(10, 0)

        assertEquals(0, testGame.getNextTwoThrows(0))
    }

    @Test
    fun game_GetNextTwoThrows_RequestedIndexDoesntExist() {
        testGame.addFrame(10, 0)
        testGame.addFrame(10, 0)

        assertEquals(0, testGame.getNextTwoThrows(3)) //Frame 3 doesn't exist yet
        assertEquals(0, testGame.getNextTwoThrows(11)) //Frame 11 would never exist
    }

    @Test
    fun game_CalculateScore_FirstFrame() {
        testGame.addFrame(7, 3) //Spare

        assertEquals(10, testGame.frames[0].frameScore)
    }

    @Test
    fun game_CalculateScore_AfterSpareConditions() {
        testGame.addFrame(7, 3) //0 + 7 + 3 + 9 = 19 frame 1
        testGame.addFrame(9, 1) //19 + 9 + 1 + 10 = 39 frame 2
        testGame.addFrame(10, 0) //39 + 10 + 0 + 0 = 49 frame 3

        assertEquals(19, testGame.frames[0].frameScore)
        assertEquals(39, testGame.frames[1].frameScore)
        assertEquals(49, testGame.frames[2].frameScore)
    }

    @Test
    fun game_CalculateScore_AfterStrikeConditions() {
        testGame.addFrame(6, 4) //0 + 6 + 4 + 10 = 20
        testGame.addFrame(10, 0) //20 + 10 + 9 + 1 = 40
        testGame.addFrame(9, 1) //40 + 9 + 1 + 10 = 60
        testGame.addFrame(10, 0) //60 + 10 + 8 + 1 = 79
        testGame.addFrame(8, 1) //69 + 8 + 1 = 78

        assertEquals(40, testGame.frames[1].frameScore)
        assertEquals(79, testGame.frames[3].frameScore)
    }

    @Test
    fun game_CalculateScore_AfterDoubleConditions() {
        testGame.addFrame(10, 0) //0 + 10 + 10 + 10 = 30
        testGame.addFrame(10, 0) //30 + 10 + 10 + 9 = 59
        testGame.addFrame(10, 0) //59 + 10 + 9 + 1 = 79
        testGame.addFrame(9, 1) //79 + 9 + 1 + 0 = 89

        assertEquals(30, testGame.frames[0].frameScore)
        assertEquals(59, testGame.frames[1].frameScore)
        assertEquals(79, testGame.frames[2].frameScore)
        assertEquals(89, testGame.frames[3].frameScore)
    }

}