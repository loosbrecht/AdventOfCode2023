package day05


import kotlinx.coroutines.*


class Almanac(val seeds: List<Long>, val convertMaps: List<ConvertMap>) {


    private val orderOfConversions = MapType.entries.drop(1).mapNotNull { mt -> convertMaps.find { mt == it.mapName } }
    private fun findSeedToLocation(seed: Long, orderOfconverLst: List<ConvertMap>): Long {
        var step = seed
        for (orderOfConversion in orderOfconverLst) {
            step = orderOfConversion.getDestination(step)
        }
        return step
    }

    fun findLowestLocation(): Long {
        return seeds.map {
            findSeedToLocation(it, orderOfConversions)
        }.min()
    }

    suspend fun findLowestLocationWhenSeedsArePairs(): Long {

        val result = coroutineScope {
            seeds.chunked(2).map { n ->
                val ooC = orderOfConversions.toList()
                async(start = CoroutineStart.LAZY) { findLowestLocationForRange(n, ooC) }

            }.awaitAll()
        }
        return result.min()
    }


    suspend fun findLowestLocationForRange(pair: List<Long>, orderOfconverLst: List<ConvertMap>): Long {
       // println("Start for ${pair.first()} and ${pair.last()}")

        var minLowestLocation = Long.MAX_VALUE
        var current = pair.first()
        val end = pair.first() + pair.last()
        while (current < end) {
            val location = findSeedToLocation(current, orderOfconverLst)
            if (location < minLowestLocation) {
                minLowestLocation = location
            }
            current++
        }
     //   println("Done for ${pair.first()} and ${pair.last()} with lowest $minLowestLocation")
        return minLowestLocation
    }

    companion object {
        fun create(input: List<String>, seedListIsARange: Boolean): Almanac {
            val seeds = input[0].split(":")[1]
            var seedsNums = seeds.split(" ").filterNot { it == "" }.map { it.toLong() }
            var tmpName = MapType.UNKNOWN
            var tmpRangeNameLst = mutableListOf<RangeLine>()
            var name = false
            val convertMapLst = mutableListOf<ConvertMap>()
            for (s in input.subList(2, input.size)) {
                if (s == "") {
                    convertMapLst.add(ConvertMap(tmpName, tmpRangeNameLst))
                    name = false
                    tmpRangeNameLst = emptyList<RangeLine>().toMutableList()
                    continue
                } else if (!name) {
                    val typeStr = s.split(" ")[0]
                    tmpName = MapType.fromString(typeStr)
                    name = true
                } else {
                    val l = s.split(" ").map { it.toLong() }
                    tmpRangeNameLst.add(RangeLine(l[0], l[1], l[2]))
                }

            }
            convertMapLst.add(ConvertMap(tmpName, tmpRangeNameLst))



            return Almanac(seedsNums, convertMapLst)
        }

        private fun range(start: Long, range: Long): List<Long> {
            val lst = mutableListOf<Long>()
            var current = start
            while (current < (start + range)) {
                lst.add(current)
                current++
            }
            println("I got here")
            return lst
        }
    }
}


class ConvertMap(val mapName: MapType, val rangeLines: List<RangeLine>) {

    fun getDestination(source: Long): Long {
        for (rangeLine in rangeLines) {
            if (rangeLine.inRange(source)) {
                return rangeLine.calculateNewDestination(source)
            }
        }
        return source

    }

    override fun toString(): String {
        return this.mapName.toString()
    }
}

class RangeLine(val destination: Long, val source: Long, val range: Long) {
    fun inRange(value: Long): Boolean {
        return (value >= source) && (value < source + range)
    }

    fun calculateNewDestination(value: Long): Long {
        return value + (destination - source)
    }
}

enum class MapType {
    UNKNOWN, SEEDTOSOIL, SOILTOFERTILIZER, FERTILIZERTOWATER, WATERTOLIGHT, LIGHTTOTEMPERATURE, TEMPERATURETOHUMIDITY, HUMIDITYTOLOCATION;

    companion object {
        fun fromString(s: String): MapType {
            return when (s) {

                "seed-to-soil" -> SEEDTOSOIL
                "soil-to-fertilizer" -> SOILTOFERTILIZER
                "fertilizer-to-water" -> FERTILIZERTOWATER
                "water-to-light" -> WATERTOLIGHT
                "light-to-temperature" -> LIGHTTOTEMPERATURE
                "temperature-to-humidity" -> TEMPERATURETOHUMIDITY
                "humidity-to-location" -> HUMIDITYTOLOCATION
                else -> UNKNOWN

            }
        }
    }

}