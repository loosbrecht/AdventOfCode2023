import Day02.Day02
import Day04.Day04
import Day05.Day05
import day03.Day03
import day1.Day01
import kotlin.system.measureTimeMillis


fun main() {


    val days: List<Solve> = mutableListOf(
//            Day01(),
//            Day02(),
//            Day03(),
//            Day04(),
            Day05()
    )
    days.forEach(::solveDay)
}


fun solveDay(s: Solve) {
    s.solveTestInput()
    val input = readInput(s.getDay())
    println("--- Solution for ${s.getDay()} ---")
    val elapsedPart1 = measureTimeMillis {
        println("Part 1\t: ${s.solvePartOne(input)}")
    }
    val elapsedPart2 = measureTimeMillis {
        println("Part 2\t: ${s.solvePartTwo(input)}")
    }
    println("Elapsed time in ms\n Part 1\t: ${elapsedPart1}\n  Part 2\t: ${elapsedPart2}\n")
}