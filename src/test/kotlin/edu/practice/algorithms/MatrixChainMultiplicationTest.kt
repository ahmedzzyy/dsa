package edu.practice.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MatrixChainMultiplicationTest {

    @Test
    fun `matrixChainMultiplication returns zero cost for single matrix`() {
        val dimensions = listOf(10, 20)
        val (costTable, _) = matrixChainMultiplication(dimensions)
        assertThat(costTable[0][0]).isEqualTo(0)
    }

    @Test
    fun `matrixChainMultiplication computes correct cost for two matrices`() {
        val dimensions = listOf(10, 20, 30)
        val (costTable, _) = matrixChainMultiplication(dimensions)
        assertThat(costTable[0][1]).isEqualTo(10 * 20 * 30) // 6000
    }

    @Test
    fun `matrixChainMultiplication computes correct cost for three matrices`() {
        val dimensions = listOf(10, 20, 30, 40)
        val (costTable, _) = matrixChainMultiplication(dimensions)
        // Optimal order: (A1(A2A3)) or ((A1A2)A3)
        assertThat(costTable[0][2]).isEqualTo(10 * 20 * 30 + 10 * 30 * 40) // 18000
    }

    @Test
    fun `matrixChainMultiplication finds correct split positions`() {
        val dimensions = listOf(30, 35, 15, 5, 10, 20, 25)
        val (_, splitTable) = matrixChainMultiplication(dimensions)
        assertThat(splitTable[1][5]).isEqualTo(2) // Optimal split for chain from A2 to A6
    }

    @Test
    fun `matrixChainMultiplication handles larger sequences`() {
        val dimensions = listOf(5, 10, 3, 12, 5, 50, 6)
        val (costTable, _) = matrixChainMultiplication(dimensions)
        assertThat(costTable[0][5]).isEqualTo(2010) // Known optimal result for this sequence
    }
}