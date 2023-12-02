package day1

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.map { retrieveDigits(it) }.map { number(it) }.reduce { acc, i -> acc + i }
    }

    fun part2(input: List<String>): Int {
        return input.map { retrieveDigitsIncludingSpelledOut(it) }.map { number(it) }.reduce { acc, i -> acc + i }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)
    val testInputPart2 = readInput("Day01-2_test")
    check(part2(testInputPart2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

fun retrieveDigits(s: String): Pair<Int, Int> {
    val digits = s.toCharArray()
        .map { it.digitToIntOrNull() }.filterNotNull()
    return Pair(digits.first, digits.last)
}

fun retrieveDigitsIncludingSpelledOut(s: String): Pair<Int, Int> {
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

val replaceMap = mapOf(
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

fun number(p: Pair<Int, Int>): Int = p.first * 10 + p.second

