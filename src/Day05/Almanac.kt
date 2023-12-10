package Day05

class Almanac(val seeds: List<Long>, val convertMaps: List<ConvertMap>) {


    private val orderOfConversions = MapType.entries.drop(1).mapNotNull { mt -> convertMaps.find { mt == it.mapName } }
    private fun findSeedToLocation(seed: Long): Long {
        var step = seed
        for (orderOfConversion in orderOfConversions) {
            step = orderOfConversion.getDestination(step)
        }
        return step
    }

    fun findLowestLocation(): Long {
        return seeds.map {
            findSeedToLocation(it)
        }.min()

    }

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