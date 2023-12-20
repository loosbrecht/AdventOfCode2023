package day09

class Sequence(private val sequence: List<Int>) {
    fun nextSequenceValue(): Int {
        return nextStep(sequence)
    }

    fun precedingSequenceValue(): Int {
        return previousStep(sequence)
    }

    private fun previousStep(seq: List<Int>): Int {
        if (seq.all { it == 0 }) return 0

        val newSeqs = seq.zipWithNext().map { it.second - it.first }
        val toBeAdded = previousStep(newSeqs)
        val first = seq.first()
        // toBeAdded=0  first = 2 => prev 2   toBeAdded + first
        // toBeAdded=2  first = 0 => prev=-2 first - toBeAdded
        // toBeAdded=-2 first = 3 => prev = 5   first - toBeAdded
        return if (toBeAdded == 0) first else first - toBeAdded
    }

    private fun nextStep(seq: List<Int>): Int {
        if (seq.all { it == 0 }) return 0

        val newSeqs = seq.zipWithNext().map { it.second - it.first }
        val toBeAdded = nextStep(newSeqs)
        return seq.last() + toBeAdded
    }


}