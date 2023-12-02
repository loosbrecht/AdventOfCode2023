package Day02

import Solve
import println
import readInput

class Day02 : Solve {
    override fun solvePartOne(input: List<String>): String {
        val maxSet = Set(12, 13, 14)
        val sum = input.map { Game.create(it) }.filter { it.possible(maxSet) }.sumOf { it.id }
        return sum.toString()
    }

    override fun solvePartTwo(input: List<String>): String {
        val sum = input.map { Game.create(it) }.sumOf { it.leastPossibleGameSet().power() }
        return sum.toString()
    }

    override fun getDay(): String {
        return "Day02"
    }

    override fun solveTestInput() {
        val testInput = readInput("Day02_test")
        check(solvePartOne(testInput) == "8")
        check(solvePartTwo(testInput) == "2286")
    }
}