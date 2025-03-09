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
fun binaryKnapsack(maxWeight: Int, weights: List<Int>, profits: List<Int>): Pair<Int, List<Int>> {
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

/**
 * This function solves Fractional Knapsack Problem by using greedy approach.
 *
 * Given a set of items, each with a weight and a profit, this function determines the maximum profit
 * each with a weight and profit that can be obtained while ensuring that the total weight does not exceed `maxWeight`,
 * where fractions of items can be taken.
 *
 * The function returns a pair:
 * - The first element is the maximum profit achievable as a `Double`.
 * - The second element is a list of fractions representing the portion of each item taken,
 *   in the same order as the input lists. A value of `1.0` indicates the full item was taken,
 *   whereas a value between `0.0` and `1.0` indicates a fraction of the item.
 *
 * @param maxWeight The maximum weight capacity of the knapsack.
 * @param weights A list of item weights.
 * @param profits A list of item profits corresponding to the weights.
 *
 * @return A `Pair` where:
 *   - The first element is the maximum profit that can be obtained.
 *   - The second element is a list of fractions for the selected items.
 */
fun fractionalKnapsack(maxWeight: Int, weights: List<Int>, profits: List<Int>): Pair<Double, List<Double>> {
    val n = weights.size
    if (n == 0 || maxWeight <= 0) return Pair((0).toDouble(), List(n) { (0).toDouble() })

    data class Item(val index: Int, val weight: Int, val profit: Int, val ratio: Double)

    val items = List(n) { index: Int ->
        Item(index, weights[index], profits[index], profits[index].toDouble() / weights[index])
    }

    val sortedItems = items.sortedByDescending { it.ratio }
    var remainingWeight = maxWeight
    var totalProfit = (0).toDouble()
    val fractions = MutableList(n) { (0).toDouble() }

    for (item in sortedItems) {
        if (remainingWeight == 0) break

        if (item.weight <= remainingWeight) {
            fractions[item.index] = (1).toDouble()
            remainingWeight -= item.weight
            totalProfit += item.profit
        } else {
            val fraction = remainingWeight.toDouble() / item.weight
            fractions[item.index] = fraction
            remainingWeight = 0
            totalProfit += fraction * item.profit
        }
    }

    return Pair(totalProfit, fractions)
}