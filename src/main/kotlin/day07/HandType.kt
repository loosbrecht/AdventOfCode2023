package day07

enum class HandType {
    UNKNOWN, HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND;

    companion object {
        fun defineHandType(s: String, joker: Boolean): HandType {
            val occurrences = s.toCharArray().toList().groupingBy { it }.eachCount()
            val handLst = occurrences.values.toList()
            return if (joker && occurrences.getOrDefault('J', 0) > 0)
                defineWithJoker(handLst, occurrences)
            else {
                defineWithoutJoker(handLst)
            }
        }

        private fun defineWithJoker(handLst: List<Int>, occurrences: Map<Char, Int>): HandType {
            val amountJ = occurrences['J']
            return if (handLst.contains(5)) {
                FIVE_OF_A_KIND
            } else if (handLst.contains(4)) {
                if (amountJ == 1) {
                    FIVE_OF_A_KIND
                } else {
                    FOUR_OF_A_KIND
                }
            } else if (handLst.contains(3) && handLst.contains(2)) {
                if (amountJ == 2 || amountJ == 3) {
                    FIVE_OF_A_KIND
                } else {
                    FULL_HOUSE
                }
            } else if (handLst.contains(3)) {
                when (amountJ) {
                    3 -> FOUR_OF_A_KIND
                    2 -> FIVE_OF_A_KIND
                    1 -> FOUR_OF_A_KIND
                    else -> THREE_OF_A_KIND
                }

            } else if (handLst.count { it == 2 } == 2) {
                when (amountJ) {
                    2 -> FOUR_OF_A_KIND
                    1 -> FULL_HOUSE
                    else -> TWO_PAIR
                }
            } else if (handLst.count { it == 2 } == 1) { //AAJ23
                when (amountJ) {
                    2 -> THREE_OF_A_KIND
                    1 -> THREE_OF_A_KIND
                    else -> ONE_PAIR
                }
            } else {
                when (amountJ) {
                    1 -> ONE_PAIR
                    else -> HIGH_CARD
                }
            }
        }

        private fun defineWithoutJoker(handLst: List<Int>): HandType {
            if (handLst.contains(5)) {
                return FIVE_OF_A_KIND
            } else if (handLst.contains(4)) {
                return FOUR_OF_A_KIND
            } else if (handLst.contains(3) && handLst.contains(2)) {
                return FULL_HOUSE
            } else if (handLst.contains(3)) {
                return THREE_OF_A_KIND
            } else if (handLst.count { it == 2 } == 2) {
                return TWO_PAIR
            } else if (handLst.count { it == 2 } == 1) {
                return ONE_PAIR
            } else {
                return HIGH_CARD
            }
        }
    }
}