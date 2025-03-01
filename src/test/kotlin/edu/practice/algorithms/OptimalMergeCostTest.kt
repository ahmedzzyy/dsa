package edu.practice.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OptimalMergeCostTest {

    @Test
    fun `optimalMergeCost returns 0 when input is empty`() {
        val weights = emptyList<Int>()
        val result = determineOptimalMergeCost(weights)
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `optimalMergeCost returns 0 when only one weight is provided`() {
        val weights = listOf(10)
        val result = determineOptimalMergeCost(weights)
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `optimalMergeCost returns correct cost for two weights`() {
        val weights = listOf(5, 3)
        // Merging 5 and 3 -> 5 + 3 = 8.
        val result = determineOptimalMergeCost(weights)
        assertThat(result).isEqualTo(8)
    }

    @Test
    fun `optimalMergeCost returns correct cost for multiple weights`() {
        val weights = listOf(5, 3, 8, 2)
        // Merge 2 and 3 => cost 5 [5, 5, 8]
        // Merge 5 and 5 => cost 10 [8, 10]
        // Merge 8 and 10 => cost 18,  5 + 10 + 18 = 33.
        val result = determineOptimalMergeCost(weights)
        assertThat(result).isEqualTo(33)
    }

    @Test
    fun `optimalMergeCost handles unsorted input correctly`() {
        val weights = listOf(8, 2, 5, 3)
        val result = determineOptimalMergeCost(weights)
        assertThat(result).isEqualTo(33)
    }
}