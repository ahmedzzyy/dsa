package edu.practice.algorithms

/**
 * Solves the TSP using Dynamic Programming approach with bit masking.
 *
 * @param cost A 2D array where `cost[i][j]` is the travel cost from city i to j.
 * @return The minimum tour cost starting and ending at city 0.
 */
fun tourCostForTravelingSalesman(cost: Array<IntArray>): Int {
    val n = cost.size
    val dp = Array(1 shl n) { IntArray(n) { -1 } } // (2 ^ n) X n

    fun getTourCost(dp: Array<IntArray>, mask: Int, position: Int): Int {
        // Case: All cities visited
        if (mask == (1 shl n) - 1) return cost[position][0]

        // Already computed i.e. cached
        if (dp[mask][position] != -1) return dp[mask][position]

        var answer = Int.MAX_VALUE
        for (city in 0 until n) {
            if (mask and (1 shl city) == 0) {
                val newCost = cost[position][city] + getTourCost(dp, mask or (1 shl city), city)
                answer = minOf(answer, newCost)
            }
        }

        dp[mask][position] = answer // Updating dp in case we ever return it
        return answer
    }

    // Mask = (1 << 0) as no city visited
    return getTourCost(dp, mask = (1 shl 0), position = 0)
}