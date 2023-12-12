package day07

import Solve

class Day07 : Solve("6440", "5905") {
    override fun solvePartOne(input: List<String>): String {
        return solve(input, false)
    }

    private fun solve(input: List<String>, joker: Boolean): String {
        val cards = input.map { it.split(" ") }.map { CardHand(it[0], it[1].toInt(), joker) }
        val camelPoker = CamelPoker(cards)
        if (joker) cards.filter { it.hand.contains("J") }.forEach(::println)
        val totalWinnings = camelPoker.totalWinnings()
        return totalWinnings.toString()
    }

    override suspend fun solvePartTwo(input: List<String>): String {
        return solve(input, true)
    }

    override fun getDay(): String {
        return "day07"
    }
}