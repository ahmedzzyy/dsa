package edu.practice.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class KnapsackSelectionTest {


    @Test
    fun `binaryKnapsack returns (0, empty list) when no items provided`() {
        val maxWeight = 50
        val weights = emptyList<Int>()
        val profits = emptyList<Int>()
        val (maxProfit, selectedItems) = binaryKnapsack(maxWeight, weights, profits)
        assertThat(maxProfit).isEqualTo(0)
        assertThat(selectedItems).isEmpty()
    }

    @Test
    fun `binaryKnapsack returns single item when one fits`() {
        val maxWeight = 10
        val weights = listOf(5)
        val profits = listOf(50)
        val (maxProfit, selectedItems) = binaryKnapsack(maxWeight, weights, profits)
        assertThat(maxProfit).isEqualTo(50)
        assertThat(selectedItems).containsExactly(0)
    }

    @Test
    fun `binaryKnapsack returns (0, empty list) when one item does not fit`() {
        val maxWeight = 5
        val weights = listOf(10)
        val profits = listOf(50)
        val (maxProfit, selectedItems) = binaryKnapsack(maxWeight, weights, profits)
        assertThat(maxProfit).isEqualTo(0)
        assertThat(selectedItems).isEmpty()
    }

    @Test
    fun `binaryKnapsack returns optimal selection for multiple items`() {
        // Example: items with weights [10, 20, 30] and profits [60, 100, 120], maxWeight = 50.
        // The optimal solution is to take items 1 and 2 (0-based indices) with total profit = 220.
        val maxWeight = 50
        val weights = listOf(10, 20, 30)
        val profits = listOf(60, 100, 120)
        val (maxProfit, selectedItems) = binaryKnapsack(maxWeight, weights, profits)
        assertThat(maxProfit).isEqualTo(220)
        assertThat(selectedItems).containsExactly(1, 2)
    }

    @Test
    fun `binaryKnapsack returns optimal selection with multiple possible combinations`() {
        // Example: items with weights [1, 3, 4, 5] and profits [10, 40, 50, 70], maxWeight = 8.
        // The optimal solution is to select items 1 and 3 with total profit = 40 + 70 = 110.
        val maxWeight = 8
        val weights = listOf(1, 3, 4, 5)
        val profits = listOf(10, 40, 50, 70)
        val (maxProfit, selectedItems) = binaryKnapsack(maxWeight, weights, profits)
        assertThat(maxProfit).isEqualTo(110)
        assertThat(selectedItems).containsExactly(1, 3)
    }
}