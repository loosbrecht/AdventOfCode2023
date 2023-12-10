package day03

import Solve

class Day03 : Solve("4361", "467835") {
    override fun solvePartOne(input: List<String>): String {
        val schematic = Schematic(input)
      return  schematic.validPartNumbers().sum().toString()
    }

    override suspend fun solvePartTwo(input: List<String>): String {
       val schematic= Schematic(input)
        return  schematic.validGears().sum().toString()
    }

    override fun getDay(): String {
        return "Day03"
    }
}