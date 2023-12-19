package day02

class Game(val id: Int, val sets: List<Set>) {


    fun possible(maxSet: Set): Boolean {
        return sets.count { it.possible(maxSet) } == sets.size
    }

    fun leastPossibleGameSet(): Set {
        return Set(sets.maxOf { it.red }, sets.maxOf { it.green }, sets.maxOf { it.blue })
    }


    companion object {
        fun create(input: String): Game {
            val gamePart = input.split(":")
            val gameIdStr = gamePart[0].split(" ")[1]
            val gameId = gameIdStr.toInt()

            val sets = gamePart[1].split(";").map { Set.create(it) }
            return Game(gameId, sets)
        }
    }


}

data class Set(val red: Int, val green: Int, val blue: Int) {
    companion object {
        fun create(input: String): Set {
            val inputs = input.split(",")
            var red = 0
            var blue = 0
            var green = 0
            for (inp in inputs) {
                val setInp = inp.split(" ").filterNot { it == "" }
                val amount = setInp[0].toInt()
                when (setInp[1]) {
                    "blue" -> blue = amount
                    "red" -> red = amount
                    "green" -> green = amount
                }
            }
            return Set(red, green, blue)
        }
    }

    fun possible(other: Set): Boolean {
        return red <= other.red && green <= other.green && blue <= other.blue
    }

    fun power(): Int {
        return red * green * blue
    }
}