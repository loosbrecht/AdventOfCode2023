package day07

import kotlin.test.Test
import kotlin.test.assertEquals


class HandTypeTest {
    @Test
    fun testDefineHandType_FiveOfAKind() {
        HandType
        val actual: HandType = HandType.defineHandType("AAAAA")
        assertEquals(HandType.FIVE_OF_A_KIND, actual)
    }

    @Test
    fun testDefineHandType_FourOfAKind() {
        assertEquals(HandType.FOUR_OF_A_KIND, HandType.defineHandType("AA8AA"))

    }

    @Test
    fun testDefineHandType_FullHouse() {
        assertEquals(HandType.FULL_HOUSE, HandType.defineHandType("A2A2A"))

    }

    @Test
    fun testDefineHandType_ThreeOfAKind() {
        assertEquals(HandType.THREE_OF_A_KIND, HandType.defineHandType("A2A3A"))

    }

    @Test
    fun testDefineHandType_TwoPair() {
        assertEquals(HandType.TWO_PAIR, HandType.defineHandType("A2A2B"))

    }

    @Test
    fun testDefineHandType_OnePair() {
        assertEquals(HandType.ONE_PAIR, HandType.defineHandType("ABCBD"))

    }

    @Test
    fun testDefineHandType_HighCard() {
        assertEquals(HandType.HIGH_CARD, HandType.defineHandType("34567"))

    }
}