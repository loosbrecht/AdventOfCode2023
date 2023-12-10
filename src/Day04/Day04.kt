package Day04

import Solve

class Day04 : Solve("13", "30") {
    override fun solvePartOne(input: List<String>): String {
        return input.map { GameCard.create(it) }.sumOf { it.calculatePoints() }.toString()
    }

    override suspend fun solvePartTwo(input: List<String>): String {
        val gameCardsOriginal = input.map { GameCard.create(it) }.toList()
        val game = Game(gameCardsOriginal)
        return game.playGame().toString()

    }

    override fun getDay(): String {
        return "Day04"
    }
}