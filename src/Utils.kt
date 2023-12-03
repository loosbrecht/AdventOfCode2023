import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines


abstract class Solve(val testSolution1 :String, val testSolution2 :String) {
   abstract fun solvePartOne(input: List<String>): String
  abstract  fun solvePartTwo(input: List<String>): String

   abstract fun getDay():String

    open fun solveTestInput(){
        val testInput = readInput("${getDay()}_test")
        check(solvePartOne(testInput) == testSolution1)
        check(solvePartTwo(testInput) == testSolution2)
    }
}

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/input/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
