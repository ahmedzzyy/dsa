package edu.practice.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.within
import org.junit.jupiter.api.Test

class OptimalBinarySearchTreeTest {

    @Test
    fun `optimalBST computes correct base cases for n=3`() {
        val p = listOf(0.0, 0.15, 0.10, 0.05)  // p[0] is unused.
        val q = listOf(0.0, 0.05, 0.10, 0.05, 0.05) // q[0] is unused.

        val (e, _) = buildOptimalBinarySearchTree(p, q)

        for (i in 1..p.size) {
            assertThat(e[i][i - 1]).isCloseTo(q[i - 1], within(0.0001))
        }
    }

    @Test
    fun `optimalBST computes optimal cost for n=3`() {
        val p = listOf(0.0, 0.15, 0.10, 0.05)
        val q = listOf(0.0, 0.05, 0.10, 0.05, 0.05)

        val (e, _) = buildOptimalBinarySearchTree(p, q)

        assertThat(e[1][3]).isCloseTo(0.95, within(0.0001))
    }

    @Test
    fun `optimalBST computes correct root structure for n=3`() {
        val p = listOf(0.0, 0.15, 0.10, 0.05)
        val q = listOf(0.0, 0.05, 0.10, 0.05, 0.05)

        val (_, root) = buildOptimalBinarySearchTree(p, q)

        // root[1][1] = 1, root[2][2] = 2, root[3][3] = 3.
        // root[1][2] = 1, root[2][3] = 2.
        // root[1][3] = 2.
        assertThat(root[1][1]).isEqualTo(1)
        assertThat(root[2][2]).isEqualTo(2)
        assertThat(root[3][3]).isEqualTo(3)
        assertThat(root[1][2]).isEqualTo(2)
        assertThat(root[2][3]).isEqualTo(2)
        assertThat(root[1][3]).isEqualTo(2)
    }
}