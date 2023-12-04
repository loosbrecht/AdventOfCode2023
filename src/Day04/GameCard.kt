package Day04

import kotlin.math.pow

class GameCard(val id: Int, private val winningNumbers: Set<Int>, private val gameNumbers: Set<Int>) {


    fun getActualWinningsSet(): Set<Int> {
        return winningNumbers intersect gameNumbers
    }

    fun calculatePoints(): Int {
        val actualWinnings = getActualWinningsSet()
        val score = calculateScore(actualWinnings.size)
        return score
    }

    private fun calculateScore(size: Int): Int {
        return when (size) {
            0 -> 0
            1 -> 1
            else -> 2 * calculateScore(size - 1)
        }
    }

    companion object {
        fun create(input: String): GameCard {
            //Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            //println(input)
            val cardSplit = input.split(":")
            val id = cardSplit[0].split(" ").last()

            val numberLst = cardSplit[1].split("|")
            return GameCard(
                id.toInt(),
                parseNumbers(numberLst[0]),
                parseNumbers(numberLst[1])
            )
        }

        private fun parseNumbers(numList: String): Set<Int> {
            return numList.split(" ").filterNot { it == "" }.map { it.toInt() }.toSet()
        }
    }

    override fun toString(): String {
        return "$id"
    }

    fun copy(): GameCard {
        return GameCard(this.id, this.winningNumbers, this.gameNumbers)
    }
}