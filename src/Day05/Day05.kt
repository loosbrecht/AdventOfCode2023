package Day05

import Solve

class Day05 : Solve("35", "") {
    override fun solvePartOne(input: List<String>): String {

        val almanac = Almanac.create(input)
        return almanac.findLowestLocation().toString()

    }

    override fun solvePartTwo(input: List<String>): String {
        return ""
    }

    override fun getDay(): String {
        return "Day05"
    }
}