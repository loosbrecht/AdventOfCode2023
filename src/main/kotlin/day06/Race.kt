package day06

class Race(private val totalRaceTime: Long, private val bestDistanceEver: Long) {

    fun amountOfStrategiesForBeatingBestDistance(): Int {
        return calculateAllDistances().count { it > bestDistanceEver }

    }

    private fun calculateAllDistances(): List<Long> {
        return (1..<totalRaceTime).map { calculateDistanceByTimeOnStop(it, totalRaceTime) }
    }

    private fun calculateDistanceByTimeOnStop(timeOnStopButton: Long, totalRaceTime: Long): Long {
        return (totalRaceTime - timeOnStopButton) * timeOnStopButton

    }

    companion object {
        fun createRaces(input: List<String>): List<Race> {
//            Time:      7  15   30
//            Distance:  9  40  200
            val times = extractValues(input[0])
            val distance = extractValues(input[1])
            return times.zip(distance).map { (t, d) -> Race(t, d) }
        }

        val extractValues: (String) -> List<Long> = { s ->
            s.split(" ").drop(1).filterNot { it == "" }.map { it.toLong() }
        }
    }
}