package day08

import Solve
import readInput

class Day08 : Solve("", "") {
    override fun solvePartOne(input: List<String>): String {
        val nodeMap = NodeMap.create(input)
        val stepCount = nodeMap.goThroughMapPartOne()
        return stepCount.toString()
    }

    override suspend fun solvePartTwo(input: List<String>): String {
        val nodeMap = NodeMap.create(input)
        val stepCount = nodeMap.goThroughMapPartTwo()
        return stepCount.toString()
    }

    override fun getDay(): String {
        return "Day08"
    }

    override suspend fun solveTestInput() {
        val testInput = readInput("Day08_test")
        check(solvePartOne(testInput) == "2")
        val testInputTest2 = readInput("Day08_test2")
        check(solvePartOne(testInputTest2) == "6")
        val testInputPartTwo = readInput("Day08_testPart2")
        check(solvePartTwo(testInputPartTwo) == "6")
    }
}