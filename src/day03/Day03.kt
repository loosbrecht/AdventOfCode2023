package day03

import Solve

class Day03 : Solve("4361", "467835") {
    override fun solvePartOne(input: List<String>): String {
        val schematic = Schematic(input)
      return  schematic.validPartNumbers().sum().toString()
    }

    override fun solvePartTwo(input: List<String>): String {
       return ""
    }

    override fun getDay(): String {
        return "Day03"
    }
}