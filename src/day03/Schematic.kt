package day03

class Schematic(val input: List<String>) {
    var schema: List<List<Char>> = input.map { it.toCharArray().toList() }

    fun validPartNumbers(): List<Int> {
        val partNumbers = mutableListOf<Int>()
        val digits = mutableListOf<Char>()
        var isPart = false

        for ((i, schemaLine) in schema.withIndex()) {
            for ((j, gearPart) in schemaLine.withIndex()) {
                if (gearPart.isDigit()) {
                    digits.add(gearPart)
                    if (!isPart) {
                        val p = isValidPart(i, j, isSymbol)
                        isPart = p.first
                    }
                } else if (digits.size > 0) {
                    if (isPart) {
                        partNumbers.add(toNumber(digits))
                    }
                    digits.clear()
                    isPart = false
                }

            }
        }
        return partNumbers
    }

//    fun findGearRatios(): List<Int> {
//        val gears = mutableListOf<Gear>()
//        val digits = mutableListOf<Char>()
//        var isPart = false
//        for ((i, schemaLine) in schema.withIndex()) {
//            for ((j, gearPart) in schemaLine.withIndex()) {
//                if (gearPart.isDigit()) {
//                    digits.add(gearPart)
//                    if (!isPart) {
//                        isPart = isValidPart(i, j)
//                    }
//                } else if (digits.size > 0) {
//                    if (isPart) {
//                        gears.add(toNumber(digits))
//                    }
//                    digits.clear()
//                    isPart = false
//                }
//
//            }
//        }
//        return partNumbers
//
//    }

    private fun toNumber(digitList: List<Char>): Int {
        var factor = 1
        var number = 0
        for (c in digitList.reversed()) {
            number += c.digitToInt() * factor
            factor *= 10

        }
        return number
    }

    //isValidPart assumption is that i, j is already a digit
    private fun isValidPart(i: Int, j: Int, validateFn: (Char) -> Boolean): Pair<Boolean, List<Symbol>> {
        val maxX = schema[0].size
        val maxY = schema.size

        val symbolsToBeChecked = mutableListOf<Symbol>()
        //left up
        if (i - 1 >= 0 && j - 1 >= 0) {
            symbolsToBeChecked.add(createSymbol(i - 1, j - 1))
        }
        //left
        if (j - 1 >= 0) {
            symbolsToBeChecked.add(createSymbol(i, j - 1))
        }
        //up
        if (i - 1 >= 0) {
            symbolsToBeChecked.add(createSymbol(i - 1, j))
        }
        //right
        if (j + 1 < maxX) {
            symbolsToBeChecked.add(createSymbol(i, j + 1))
        }
        //left down
        if (i + 1 < maxY && j - 1 >= 0) {
            symbolsToBeChecked.add(createSymbol(i + 1, j - 1))
        }
        //down
        if (i + 1 < maxY) {
            symbolsToBeChecked.add(createSymbol(i + 1, j))
        }
        //right down
        if (i + 1 < maxY && j + 1 < maxX) {
            symbolsToBeChecked.add(createSymbol(i + 1, j + 1))
        }
        // right up
        if (i - 1 >= 0 && j + 1 < maxX) {
            symbolsToBeChecked.add(createSymbol(i - 1, j + 1))
        }

        val symbols = symbolsToBeChecked.filter { validateFn(it.ch) }
        return Pair(symbols.isNotEmpty(), symbols)
    }

    private val isSymbol: (ch: Char) -> Boolean = { ch -> (!ch.isDigit() && ch != '.') }

    private fun createSymbol(x: Int, y: Int): Symbol {
        return Symbol(x, y, schema[x][y])
    }
}

data class Gear(val x: Int, val y: Int, val ratios: MutableList<Int>)

data class Symbol(val x: Int, val y: Int, val ch: Char)