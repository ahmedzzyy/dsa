package edu.practice.algorithms.graph

import edu.practice.datastructures.graph.Graph

/**
 * This function solves the multi-stage graph problem using dynamic programming.
 *
 * In a multi-stage graph the nodes are arranged in stages and edges exist only between consecutive stages.
 * This function finds the minimum cost path from the [start] node to the [end] node.
 *
 * @param stages A list of stages, each stage being a list of node IDs. The stages must be in order,
 *               with the first stage containing the start node and the last stage containing the end node.
 * @param graph An instance of [Graph] representing the multi-stage graph
 * @param start The starting node ID.
 * @param end The ending node ID.
 * @return A pair where:
 *   - The first element is the minimum cost (an [Int]) from [start] to [end]. If no path exists, it returns -1.
 *   - The second element is the list of node IDs representing the optimal path from [start] to [end].
 */
fun shortestPathOfMultiStageGraph(
    stages: List<List<Int>>,
    graph: Graph<Int>,
    start: Int,
    end: Int
): Pair<Int, List<Int>> {
    val dp = mutableMapOf<Int, Int>()
    val nextChoice = mutableMapOf<Int, Int>()

    dp[end] = 0

    for (stage in stages.reversed()) {
        for (node in stage) {
            if (node == end) continue

            val children = graph.getChildren(node)
            var bestCost = Int.MAX_VALUE
            var bestNext = -1

            for (vertex in children) {
                val costToEnd = dp[vertex] ?: Int.MAX_VALUE
                val candidateCost = graph.getWeight(node, vertex) + costToEnd

                if (candidateCost < bestCost) {
                    println("Vertex $vertex -> candidate cost = $candidateCost")
                    bestCost = candidateCost.toInt()
                    bestNext = vertex
                }
            }

            if (bestCost < Int.MAX_VALUE) {
                dp[node] = bestCost
                nextChoice[node] = bestNext
            }
        }
    }

    val path = mutableListOf<Int>()
    var current = start
    while (true) {
        path.add(current)
        if (current == end) break
        val next = nextChoice[current] ?: break
        current = next
    }

    return Pair(dp[start] ?: -1, path)
}