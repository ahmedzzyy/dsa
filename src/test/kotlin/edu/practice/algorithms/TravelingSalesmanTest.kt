package edu.practice.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TravelingSalesmanTest {

    @Test
    fun `tspBottomUp returns 0 cost for a single city`() {
        // When there is only one city, there is no travel needed.
        val cost = arrayOf(
            intArrayOf(0)
        )
        val result = tourCostForTravelingSalesman(cost)
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `tspBottomUp returns correct cost for two cities`() {
        // For two cities, the tour is simply 0 -> 1 -> 0.
        // Expected cost = cost[0][1] + cost[1][0]
        val cost = arrayOf(
            intArrayOf(0, 10),
            intArrayOf(10, 0)
        )
        val result = tourCostForTravelingSalesman(cost)
        assertThat(result).isEqualTo(20)
    }

    @Test
    fun `tspBottomUp returns correct tour cost for four cities`() {
        // Tour: 0 -> 1 -> 3 -> 2 -> 0 with cost = 10 + 25 + 30 + 15 = 80.
        val cost = arrayOf(
            intArrayOf(0, 10, 15, 20),
            intArrayOf(10, 0, 35, 25),
            intArrayOf(15, 35, 0, 30),
            intArrayOf(20, 25, 30, 0)
        )
        val result = tourCostForTravelingSalesman(cost)
        assertThat(result).isEqualTo(80)
    }
}