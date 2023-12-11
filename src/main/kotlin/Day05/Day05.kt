package Day05

import Solve

class Day05 : Solve("35", "46") {
    override fun solvePartOne(input: List<String>): String {
        val almanac = Almanac.create(input, false)
        return almanac.findLowestLocation().toString()
    }

    override suspend fun solvePartTwo(input: List<String>): String {
        val almanac = Almanac.create(input, true)
        return almanac.findLowestLocationWhenSeedsArePairs().toString()
    }

    override fun getDay(): String {
        return "Day05"
    }
}