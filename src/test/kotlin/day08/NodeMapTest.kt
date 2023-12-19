package day08

import kotlin.test.Test
import kotlin.test.assertEquals

class NodeMapTest {
    @Test
    fun testLcm() {
        val exp: Long = 360L
        val act = NodeMap.lcm(72, 120)
        assertEquals(exp, act)
    }
}