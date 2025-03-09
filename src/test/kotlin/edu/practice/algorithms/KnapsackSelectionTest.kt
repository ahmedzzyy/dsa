package edu.practice.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.within
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
        // Example: weights = [10, 20, 30] and profits = [60, 100, 120], maxWeight = 50.
        // items 1 and 2 (0-based indices) with total profit = 220.
        val maxWeight = 50
        val weights = listOf(10, 20, 30)
        val profits = listOf(60, 100, 120)
        val (maxProfit, selectedItems) = binaryKnapsack(maxWeight, weights, profits)
        assertThat(maxProfit).isEqualTo(220)
        assertThat(selectedItems).containsExactly(1, 2)
    }

    @Test
    fun `binaryKnapsack returns optimal selection with multiple possible combinations`() {
        // Example: weights = [1, 3, 4, 5] and profits = [10, 40, 50, 70], maxWeight = 8.
        // items 1 and 3 with total profit = 40 + 70 = 110.
        val maxWeight = 8
        val weights = listOf(1, 3, 4, 5)
        val profits = listOf(10, 40, 50, 70)
        val (maxProfit, selectedItems) = binaryKnapsack(maxWeight, weights, profits)
        assertThat(maxProfit).isEqualTo(110)
        assertThat(selectedItems).containsExactly(1, 3)
    }

    @Test
    fun `fractionalKnapsack returns (0, empty list) when no items provided`() {
        val maxWeight = 50
        val weights = emptyList<Int>()
        val profits = emptyList<Int>()
        val (totalProfit, fractions) = fractionalKnapsack(maxWeight, weights, profits)
        assertThat(totalProfit).isEqualTo(0.0)
        assertThat(fractions).isEmpty()
    }

    @Test
    fun `fractionalKnapsack returns (0, fractions list of zeros) when capacity is zero`() {
        val maxWeight = 0
        val weights = listOf(10, 20, 30)
        val profits = listOf(60, 100, 120)
        val (totalProfit, fractions) = fractionalKnapsack(maxWeight, weights, profits)
        assertThat(totalProfit).isEqualTo(0.0)
        assertThat(fractions).containsExactly(0.0, 0.0, 0.0)
    }

    @Test
    fun `fractionalKnapsack returns full item when one fits completely`() {
        val maxWeight = 10
        val weights = listOf(5)
        val profits = listOf(50)
        val (totalProfit, fractions) = fractionalKnapsack(maxWeight, weights, profits)
        assertThat(totalProfit).isEqualTo(50.0)
        assertThat(fractions).containsExactly(1.0)
    }

    @Test
    fun `fractionalKnapsack returns partial item when item partially fits`() {
        val maxWeight = 5
        val weights = listOf(10)
        val profits = listOf(50)
        val (totalProfit, fractions) = fractionalKnapsack(maxWeight, weights, profits)
        assertThat(totalProfit).isCloseTo(25.0, within(0.0001))
        assertThat(fractions).containsExactly(0.5)
    }

    @Test
    fun `fractionalKnapsack returns optimal profit and fractions for multiple items`() {
        // weights [10, 20, 30], profits [60, 100, 120] and maxWeight = 50.
        // Ratios: item0 = 6.0, item1 = 5.0, item2 = 4.0.
        //
        // - Take item0 fully (profit = 60, remaining weight = 40)
        // - Take item1 fully (profit = 100, remaining weight = 20)
        // - Take 20/30 of item2 (profit = 20/30 * 120 = 80)
        // Total profit = 60 + 100 + 80 = 240.
        // Fractions in original order: [1.0, 1.0, 0.66667]
        val maxWeight = 50
        val weights = listOf(10, 20, 30)
        val profits = listOf(60, 100, 120)
        val (totalProfit, fractions) = fractionalKnapsack(maxWeight, weights, profits)
        println(totalProfit.toString())
        println(fractions.toString())
        assertThat(totalProfit).isCloseTo(240.0, within(0.0001))
        assertThat(fractions[0]).isEqualTo(1.0)
        assertThat(fractions[1]).isEqualTo(1.0)
        assertThat(fractions[2]).isCloseTo(0.66667, within(0.0001))
    }
}