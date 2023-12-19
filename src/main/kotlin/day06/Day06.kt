package day06

import Solve

class Day06 : Solve("288", "71503") {
    override fun solvePartOne(input: List<String>): String {
        val races = Race.createRaces(input)
        val mul = races.map { it.amountOfStrategiesForBeatingBestDistance() }.reduce(Int::times)
        return mul.toString()
    }

    override suspend fun solvePartTwo(input: List<String>): String {
        val newInput = listOf(
                "Time: " + input[0].split(":")[1].replace(" ", ""),
                "Distance: " + input[1].split(":")[1].replace(" ", "")

        )

        val race = Race.createRaces(newInput)[0]
        val amountOfStrategiesForBeatingBestDistance = race.amountOfStrategiesForBeatingBestDistance()
        return amountOfStrategiesForBeatingBestDistance.toString()
    }

    override fun getDay(): String {
        return "day06"
    }
}