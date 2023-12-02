package day1

import Solve
import readInput


class Day01 : Solve {


    override fun solvePartOne(input: List<String>): String {
        return input.map { retrieveDigits(it) }.map { number(it) }.reduce { acc, i -> acc + i }.toString()
    }

    override fun solvePartTwo(input: List<String>): String {
        return input.map { retrieveDigitsIncludingSpelledOut(it) }.map { number(it) }.reduce { acc, i -> acc + i }
            .toString()
    }

    override fun getDay(): String {
        return "Day01"
    }

    override fun solveTestInput() {
        val testInput = readInput("Day01_test")
        check(solvePartOne(testInput) == "142")
        val testInputPart2 = readInput("Day01-2_test")
        check(solvePartTwo(testInputPart2) == "281")
    }


    private fun retrieveDigits(s: String): Pair<Int, Int> {
        val digits = s.toCharArray()
            .map { it.digitToIntOrNull() }.filterNotNull()
        return Pair(digits.first, digits.last)
    }

    private fun retrieveDigitsIncludingSpelledOut(s: String): Pair<Int, Int> {
        var startIndex = 0
        var replacedS = s
        startIndex@ while (startIndex < replacedS.length) {
            if (replacedS[startIndex].isDigit()) {
                startIndex++
                continue@startIndex
            }
            replace@ for (entry in replaceMap) {
                val found = replacedS.startsWith(entry.key, startIndex)
                if (found) {
                    replacedS = replacedS.replace(entry.key, entry.value)
                    break@replace
                }
            }
            startIndex++
        }
        return retrieveDigits(replacedS)
    }

    private val replaceMap = mapOf(
        "one" to "o1e",
        "two" to "tw2o",
        "three" to "thr3ee",
        "four" to "fo4ur",
        "five" to "fi5ve",
        "six" to "si6x",
        "seven" to "se7en",
        "eight" to "eig8ht",
        "nine" to "ni9ne"
    )

    private fun number(p: Pair<Int, Int>): Int = p.first * 10 + p.second

}