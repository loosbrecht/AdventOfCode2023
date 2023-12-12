import Day06.Day06
import day07.Day07
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


fun main() = runBlocking {

    val days: List<Solve> = mutableListOf(
//            Day01(),
//            Day02(),
//            Day03(),
//            Day04(),
            //   Day05()
            Day06(),
            Day07(),
    )

    for (day in days) {
        solveDay(day)
    }


}


suspend fun solveDay(s: Solve) {
    s.solveTestInput()
    val input = readInput(s.getDay())
    println("--- Solution for ${s.getDay()} ---")
    val elapsedPart1 = measureTimeMillis {
        println("Part 1\t: ${s.solvePartOne(input)}")
    }
    val elapsedPart2 = measureTimeMillis {
        println("Part 2\t: ${s.solvePartTwo(input)}")
    }
    println("Elapsed time in ms\n Part 1\t: ${elapsedPart1}\nPart 2\t: ${elapsedPart2}\n")
}