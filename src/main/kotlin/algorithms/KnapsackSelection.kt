package edu.practice.algorithms

/**
 * This function solves 0/1 Knapsack Problem by using dynamic programming approach.
 *
 * Given a set of items, each with a weight and a profit, this function determines the maximum profit
 * that can be obtained while ensuring that the total weight does not exceed `maxWeight`. It also
 * returns the list of selected item indices that contribute to this maximum profit.
 *
 * @param maxWeight The maximum weight capacity of the knapsack.
 * @param weights A list of item weights.
 * @param profits A list of item profits corresponding to the weights.
 *
 * @return A pair where:
 *   - The first element is the maximum profit that can be obtained.
 *   - The second element is a list of indices of the selected items.
 */
fun binaryKnapsack(maxWeight: Int, weights: List<Int>, profits: List<Int>): Pair<Int, MutableList<Int>> {
    val n = weights.size
    val dp = Array(n + 1) { IntArray(maxWeight + 1) { 0 } }

    for (i in 0..n) {
        for (w in 0 .. maxWeight) {
            if (i == 0 || w == 0) {
                dp[i][w] = 0
            } else if (weights[i - 1] <= w) {
                dp[i][w] = maxOf(
                    profits[i - 1] + dp[i - 1][w - weights[i - 1]]
                    , dp[i - 1][w]
                )
            } else {
                dp[i][w] = dp[i - 1][w]
            }
        }
    }

    var currentWeight = maxWeight
    val selectedItems = mutableListOf<Int>()

    for (i in n downTo 1) {
        if (currentWeight == 0) break

        if (dp[i][currentWeight] != dp[i - 1][currentWeight]) {
            selectedItems.add(i - 1)
            currentWeight -= weights[i - 1]
        }
    }

    selectedItems.reverse()

    return Pair(dp[n][maxWeight], selectedItems)
}