import Day02.Day02
import day1.Day01


fun main() {

    val days: List<Solve> = mutableListOf(
        Day01(),
        Day02()
    )
    days.forEach(::solveDay)
}


fun solveDay(s: Solve) {
    s.solveTestInput()
    val input = readInput(s.getDay())
    println("--- Solution for ${s.getDay()} ---")
    println("Part 1\t: ${s.solvePartOne(input)}")
    println("Part 2\t: ${s.solvePartTwo(input)}")
    println()
}