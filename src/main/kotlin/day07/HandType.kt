package day07

enum class HandType {
    UNKNOWN, HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND;

    companion object {
        fun defineHandType(s: String): HandType {
            val occurences = s.toCharArray().toList().groupingBy { it }.eachCount()
            val handLst = occurences.values.toList()
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