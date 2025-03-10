package edu.practice.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NQueensTest {
    @Test
    fun `solveNQueens returns empty list when n is less than or equal to zero`() {
        assertThat(placeNQueens(0)).isEmpty()
        assertThat(placeNQueens(-1)).isEmpty()
    }

    @Test
    fun `solveNQueens returns single solution for n = 1`() {
        val result = placeNQueens(1)
        val expected = listOf(listOf("Q"))
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `solveNQueens returns empty list for n = 2 and n = 3`() {
        assertThat(placeNQueens(2)).isEmpty()
        assertThat(placeNQueens(3)).isEmpty()
    }

    @Test
    fun `solveNQueens returns two solutions for n = 4`() {
        val result = placeNQueens(4)
        val expectedSolution1 = listOf(
            ".Q..",
            "...Q",
            "Q...",
            "..Q."
        )
        val expectedSolution2 = listOf(
            "..Q.",
            "Q...",
            "...Q",
            ".Q.."
        )

        assertThat(result).hasSize(2)
        assertThat(result).containsExactlyInAnyOrder(expectedSolution1, expectedSolution2)
    }
}