package com.example.strats.model

import kotlin.math.max

class Game () {

    val frames: MutableList<Frame> = mutableListOf()

    fun getGameScore(): Int {
        return frames[frames.lastIndex].frameScore
    }

    fun addFrame(firstThrow: Int, secondThrow: Int = 0, thirdThrow: Int = 0) {
        //Add the new frame to the list, then update scores of any unfilled frames
        //TODO: consider where checks should happen with handling 10th frames and third throws
        frames.add(Frame(firstThrow = firstThrow, secondThrow = secondThrow, thirdThrow = thirdThrow))
        calculateScore()
    }

    fun calculateScore() {
        //We only need to go as far back as two frames
        for(i in max(frames.lastIndex - 2, 0) .. frames.lastIndex) {
            frames[i].frameScore = getPreviousFrameScore(i) + frames[i].firstThrow +
                    frames[i].secondThrow + frames[i].thirdThrow
            if(frames[i].firstThrow == 10) frames[i].frameScore += getNextTwoThrows(i)
            else if((frames[i].firstThrow + frames[i].secondThrow) == 10) frames[i].frameScore += getNextThrow(i)
        }
    }

    fun getPreviousFrameScore(index: Int): Int {
        return if(index == 0) 0 else frames[index - 1].frameScore
    }

    //A function most likely used to get the value that needs to be added to a spare frame
    fun getNextThrow(index: Int): Int {
        //If the frame exists then its first throw will be returned, otherwise the default empty frame
        //will return 0
        return frames.getOrElse(index + 1) { Frame(0, 0, 0)}.firstThrow
    }

    //A function most likely used to get the values that need to be added to a strike frame
    fun getNextTwoThrows(index: Int): Int {
        //If the frame doesn't exist yet treat it as an empty frame for the calculation
        val nextFrame = frames.getOrElse(index + 1) { Frame(0, 0, 0) }
        val secondNextFrame = frames.getOrElse(index + 2) { Frame(0, 0, 0) }

        //Return either the next frame's first and second throws combine if the next frame isn't a strike,
        //or the first throws of the next and second next frames if the next frame is a strike
        return if(nextFrame.secondThrow == 0) nextFrame.firstThrow + secondNextFrame.firstThrow else
            nextFrame.firstThrow + nextFrame.secondThrow
    }
}