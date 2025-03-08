package edu.practice.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LongestCommonSubsequenceTest {

    @Test
    fun `longestCommonSubsequence returns empty string when both inputs are empty`() {
        val (_, directionTable) = longestCommonSubsequence("", "")
        val lcs = buildLCS(directionTable, "", "".length, "".length)
        assertThat(lcs).isEmpty()
    }

    @Test
    fun `longestCommonSubsequence returns empty string when one input is empty`() {
        val x = "ABC"
        val y = ""
        val (_, directionTable) = longestCommonSubsequence(x, y)
        val lcs = buildLCS(directionTable, x, x.length, y.length)
        assertThat(lcs).isEmpty()
    }

    @Test
    fun `longestCommonSubsequence returns the full string when both inputs are identical`() {
        val x = "ABC"
        val (_, directionTable) = longestCommonSubsequence(x, x)
        val lcs = buildLCS(directionTable, x, x.length, x.length)
        assertThat(lcs).isEqualTo("ABC")
    }

    @Test
    fun `longestCommonSubsequence returns expected subsequence for partial match`() {
        val x = "AGGTAB"
        val y = "GXTXAYB"
        val (_, directionTable) = longestCommonSubsequence(x, y)
        val lcs = buildLCS(directionTable, x, x.length, y.length)
        assertThat(lcs).isEqualTo("GTAB")
    }

    @Test
    fun `longestCommonSubsequence returns empty string when no common characters`() {
        val x = "ABC"
        val y = "DEF"
        val (_, directionTable) = longestCommonSubsequence(x, y)
        val lcs = buildLCS(directionTable, x, x.length, y.length)
        assertThat(lcs).isEmpty()
    }
}