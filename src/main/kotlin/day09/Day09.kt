package day09

import Solve

class Day09 : Solve("114", "2") {
    override fun solvePartOne(input: List<String>): String {
        val sequences = parseInput(input)
        val sum = sequences.sumOf { it.nextSequenceValue() }
        return sum.toString()
    }

    private fun parseInput(input: List<String>): List<Sequence> {
        return input.map { it.split(" ") }
            .map { str -> str.map { it.toInt() } }
            .map { Sequence(it) }
    }

    override suspend fun solvePartTwo(input: List<String>): String {
        val sequences = parseInput(input)
        val sum = sequences.sumOf { it.precedingSequenceValue() }
        return sum.toString()
    }

    override fun getDay(): String {
        return "Day09"
    }
}