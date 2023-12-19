package day08

class NodeMap(val stepList: List<Char>, val mapWithNodes: Map<String, Pair<String, String>>) {

    fun findEnd(start: String, end: (String) -> Boolean): Int {
        var step = start
        var stepCount = 0
        while (!end(step)) {
            for (lr in stepList) {
                step = doOneStep(step, lr)
                stepCount++
                if (end(step)) {
                    return stepCount
                }
            }
        }
        return stepCount
    }

    fun goThroughMapPartOne(): Int {
        return findEnd(START) { e -> e == END }
    }

    fun goThroughMapPartTwo(): Long {
        val stepNodes = mapWithNodes.keys.filter { it.last() == 'A' }
        val endFn = { e: String -> e.last() == 'Z' }
        val stepCount = stepNodes.map { findEnd(it, endFn) }

        val lcm = stepCount.map { it.toLong() }.reduce { acc, i -> lcm(acc, i) }
        return lcm
    }


    fun doOneStep(key: String, dir: Char): String {
        return when (dir) {
            'L' -> mapWithNodes[key]!!.first
            'R' -> mapWithNodes[key]!!.second
            else -> ""
        }
    }


    companion object {

        fun lcm(a: Long, b: Long): Long {
            var gcd = 1
            var i = 1
            while (i <= a && i <= b) {
                if ((a % i == 0L) && (b % i == 0L))
                    gcd = i
                i++
            }

            return (a * b) / gcd

        }

        fun create(input: List<String>): NodeMap {
            val stepList = input[0].toCharArray().asList()
            val mapWithNodes: MutableMap<String, Pair<String, String>> = mutableMapOf()
            for (s in input.drop(2)) {
                val sNoSpaces = s.replace(" ", "")
                val parts = sNoSpaces.split("=")
                val nodes = parts[1].split(",")

                mapWithNodes[parts[0]] = Pair(nodes[0].drop(1), nodes[1].dropLast(1))

            }
            return NodeMap(stepList, mapWithNodes)
        }

        const val START = "AAA"
        const val END = "ZZZ"
    }
}

