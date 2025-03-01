package edu.practice.algorithms

/**
 * Computes the optimal merge cost for a given list of costs to merge
 *
 * @param costs a list of positive integers representing weights or costs.
 * @return the total cost of merging the items optimally.
 */
fun determineOptimalMergeCost(costs: List<Int>): Int {
    val queue = java.util.PriorityQueue<Int>()
    queue.addAll(costs)
    var totalCost = 0

    while (queue.size > 1) {
        val first = queue.poll()
        val second = queue.poll()

        val merged = first + second
        totalCost += merged
        queue.offer(merged)
    }

    return totalCost
}