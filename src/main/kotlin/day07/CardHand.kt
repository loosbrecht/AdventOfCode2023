package day07

class CardHand(val hand: String, val score: Int, val joker: Boolean) : Comparable<CardHand> {
    val handType: HandType

    init {
        handType = HandType.defineHandType(hand, joker)
    }

    override fun compareTo(other: CardHand): Int {
        if (handType > other.handType) {
            return 1
        } else if (handType < other.handType) {
            return -1
        }
        return orderByCard(hand, other.hand)
    }

    private fun orderByCard(hand: String, other: String): Int {
        if (hand.isEmpty() || other.isEmpty()) {
            return 0
        }
        if (hand[0] == other[0]) {
            return orderByCard(hand.drop(1), other.drop(1))
        }
        if (joker) {
            val h = CardWithJoker.create(hand[0])
            val o = CardWithJoker.create(other[0])
            return if (h > o) {
                -1
            } else {
                1
            }
        } else {
            val h = Card.create(hand[0])
            val o = Card.create(other[0])
            return if (h > o) {
                -1
            } else {
                1
            }
        }
    }

    override fun toString(): String {
        return "${handType.name} $hand $score"
    }
}

enum class Card {
    A, K, Q, J, T, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO, UNKNOWN;

    companion object {
        fun create(c: Char): Card {
            return when (c) {
                'A' -> A
                'K' -> K
                'Q' -> Q
                'J' -> J
                'T' -> T
                '9' -> NINE
                '8' -> EIGHT
                '7' -> SEVEN
                '6' -> SIX
                '5' -> FIVE
                '4' -> FOUR
                '3' -> THREE
                '2' -> TWO
                else -> UNKNOWN
            }
        }
    }
}

enum class CardWithJoker {
    A, K, Q, T, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO, J, UNKNOWN;

    companion object {
        fun create(c: Char): CardWithJoker {
            return when (c) {
                'A' -> A
                'K' -> K
                'Q' -> Q
                'J' -> J
                'T' -> T
                '9' -> NINE
                '8' -> EIGHT
                '7' -> SEVEN
                '6' -> SIX
                '5' -> FIVE
                '4' -> FOUR
                '3' -> THREE
                '2' -> TWO
                else -> UNKNOWN
            }
        }
    }
}