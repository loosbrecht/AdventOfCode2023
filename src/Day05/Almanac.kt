package Day05

import kotlin.system.measureTimeMillis

class Almanac(val seeds: List<Long>, val convertMaps: List<ConvertMap>) {

    //drop unknown
    private val orderOfConversions = MapType.entries.drop(1).mapNotNull { mt -> convertMaps.find { mt == it.mapName } }
    private fun findSeedToLocation(seed: Long): Long {
        var step = seed
        for (orderOfConversion in orderOfConversions) {
            step = orderOfConversion.getDestination(step)
//            println("$orderOfConversion $step -> $nxtStep")
//            step = nxtStep
        }
        return step
    }

    fun findLowestLocation(): Long {
        return seeds.map {
            findSeedToLocation(it)
        }.min()

    }

//    private fun process(it: Long): Long {
//        var location: Long
//        val elapsedPart1 = measureTimeMillis {
//            location = findSeedToLocation(it)
//        }
//        println("$it elapsed time $elapsedPart1")
//        return location
//    }

    companion object {
        fun create(input: List<String>): Almanac {
            val seeds = input[0].split(":")[1]
            val seedsNums = seeds.split(" ").filterNot { it == "" }.map { it.toLong() }
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
    }
}


class ConvertMap(val mapName: MapType, val rangeLines: List<RangeLine>) {
    private val rangeMap: MutableMap<Long, Long> = mutableMapOf()

    init {

        //TODO change this from a map to a calculation which should be doable
        for (rangeLine in rangeLines) {
            for (i in 0..<rangeLine.range) {
                rangeMap[rangeLine.source + i] = rangeLine.destination + i
            }
        }
    }

    fun getDestination(source: Long): Long {
        return rangeMap.getOrElse(source) { source }


    }

    override fun toString(): String {
        return this.mapName.toString()
    }
}

data class RangeLine(val destination: Long, val source: Long, val range: Long)

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