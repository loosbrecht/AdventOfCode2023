package day07

class CamelPoker(val cards: List<CardHand>) {
    fun totalWinnings(): Long {
        val sortedList = cards.sorted()
        return sortedList
                .asSequence()
                .mapIndexed { i, card -> (card.score * (i + 1)).toLong() }
                .sum()
    }
}


