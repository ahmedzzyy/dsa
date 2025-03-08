package edu.practice.algorithms

/**
 * Given a chain of n matrices `<A1, A2, ..., An>`, where matrix `Ai` has dimensions `p[i-1] x p[.i]`,
 * this function computes the minimum number of scalar multiplications needed to multiply the sequence.
 *
 * This algorithm uses bottom-up dynamic programming concept to solve the problem
 *
 * @param dimensions A list of integers representing the sequence of matrix dimensions.
 *                   The list should have size `n + 1`, where `n` is the number of matrices.
 *                   Each matrix `Ai` has dimensions `p[i-1] x p[i]`.
 * @return Pair of minimum number of scalar multiplication for each combination and its split table.
 */
fun matrixChainMultiplication(dimensions: List<Int>): Pair<Array<IntArray>, Array<IntArray>> {
    val n = dimensions.size - 1
    val costTable = Array(n) { IntArray(n) { 0 } }
    val splitTable = Array(n) { IntArray(n) { 0 } }

    for (l in 2..n) { // l -> Chain length
        for (i in 0..(n - l)) { // i -> start index
            val j = i + l - 1 // j -> end index
            costTable[i][j] = Int.MAX_VALUE

            for (k in i until j) {
                val cost = costTable[i][k] + costTable[k + 1][j] +
                        (dimensions[i] * dimensions[k + 1] * dimensions[j + 1])
                if (cost < costTable[i][j]) {
                    costTable[i][j] = cost
                    splitTable[i][j] = k
                }
            }
        }
    }

    return Pair(costTable, splitTable)
}

/**
 * Recursively constructs the optimal parenthesize string using the split table.
 *
 * @param splitTable The table that records the index where the optimal split occurs.
 * @param i Starting index of the matrix chain.
 * @param j Ending index of the matrix chain.
 * @return A string representing the optimal parenthesize with matrix dimensions.
 */
fun buildOptimalParens(
    splitTable: Array<IntArray>,
    i: Int,
    j: Int
) {
    if (i == j) {
        print("[A$i]")
    } else {
        print("(")
        buildOptimalParens(splitTable, i, splitTable[i][j])
        buildOptimalParens(splitTable, splitTable[i][j] + 1, j)
        print(")")
    }
}