package edu.practice.algorithms

/**
 * Computes the optimal binary search tree (Optimal BST) using dynamic programming.
 *
 * Given an array `p` of probabilities for keys (indexed 1 through n, with index 0 unused)
 * and an array `q` of probabilities for dummy keys (indexed 0 through n), this function
 * calculates the minimum expected search cost and builds a table of roots to reconstruct the tree.
 *
 * @param keyProbability A [List] of key probabilities (`p[1]` to `p[n]` are used).
 * @param dummyProbability An [List] of dummy key probabilities (`q[0]` to `q[n]` are used).
 * @return A pair where:
 *         - The first element is a 2D array `costTable` of expected search costs.
 *         - The second element is a 2D array `root` used to reconstruct the optimal BST.
 */
fun buildOptimalBinarySearchTree(
    keyProbability: List<Double>, dummyProbability: List<Double>
): Pair<Array<DoubleArray>, Array<IntArray>> {
    val n = keyProbability.size - 1
    val costTable = Array(n + 2) { DoubleArray(n + 1) { 0.0 } }
    val probabilityTable = Array(n + 2) { DoubleArray(n + 1) { 0.0 } }
    val root = Array(n + 1) { IntArray(n + 1) { 0 } }

    for (i in 1..(n + 1)) {
        costTable[i][i - 1] = dummyProbability[i - 1]
        probabilityTable[i][i - 1] = dummyProbability[i - 1]
    }

    for (l in 1..n) { // Chain length
        for (i in 1..(n - l + 1)) {
            val j = i + l - 1
            // Subtree from i -> j
            costTable[i][j] = Double.POSITIVE_INFINITY
            probabilityTable[i][j] = probabilityTable[i][j - 1] + keyProbability[j - 1] + dummyProbability[j]

            for (rootIndex in i..j) {
                val candidate = costTable[i][rootIndex - 1] + costTable[rootIndex + 1][j] + probabilityTable[i][j]

                if (candidate < costTable[i][j]) {
                    costTable[i][j] = candidate
                    root[i][j] = rootIndex
                }
            }
        }
    }

    return Pair(costTable, root)
}