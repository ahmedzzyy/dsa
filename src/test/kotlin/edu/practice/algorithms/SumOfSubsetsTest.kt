package edu.practice.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SumOfSubsetsTest {

    @Test
    fun `sumOfSubsets returns empty list when input is empty and target is non-zero`() {
        val numbers = listOf<Int>()
        val target = 5
        val result = subsetsWithSum(target, numbers)
        assertThat(result).isEmpty()
    }

    @Test
    fun `sumOfSubsets returns list with empty subset when input is empty and target is zero`() {
        val numbers = listOf<Int>()
        val target = 0
        val result = subsetsWithSum(target, numbers)
        assertThat(result).hasSize(0)
        assertThat(result).isEmpty()
    }

    @Test
    fun `sumOfSubsets returns single subset when only one element equals target`() {
        val numbers = listOf(5)
        val target = 5
        val result = subsetsWithSum(target, numbers)
        assertThat(result).containsExactly(listOf(5))
    }

    @Test
    fun `sumOfSubsets finds all subsets that sum to target`() {
        val numbers = listOf(1, 2, 3, 4, 5)
        val target = 5
        val result = subsetsWithSum(target, numbers)
        // [1, 4], [2, 3], and [5]
        assertThat(result).containsExactlyInAnyOrder(
            listOf(1, 4),
            listOf(2, 3),
            listOf(5)
        )
    }

    @Test
    fun `sumOfSubsets handles multiple possible combinations`() {
        // [2, 3, 5], [2, 8], and [10]
        val numbers = listOf(2, 3, 5, 6, 8, 10)
        val target = 10
        val result = subsetsWithSum(target, numbers)
        assertThat(result).containsExactlyInAnyOrder(
            listOf(2, 3, 5),
            listOf(2, 8),
            listOf(10)
        )
    }
}