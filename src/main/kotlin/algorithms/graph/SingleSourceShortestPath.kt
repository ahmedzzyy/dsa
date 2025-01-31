package edu.practice.algorithms.graph

import edu.practice.datastructures.graph.Graph

/**
 * Computes the shortest path from a given source vertex to all other vertices in a weighted graph
 * using the **Bellman-Ford Algorithm**.
 *
 * Unlike Dijkstra's algorithm, Bellman-Ford can handle graphs with negative weight edges
 * and even detect negative weight cycles.
 *
 * @param E The type of vertices in the graph.
 * @param graph The graph containing vertices and weighted edges.
 * @param source The starting vertex from which shortest paths are calculated.
 * @return A map where each vertex is associated with the shortest distance from the source.
 * If a vertex is affected by a negative weight cycle, its distance is marked as `Double.NEGATIVE_INFINITY`.
 * If a vertex is unreachable from source vertex, its distance is marked as `Double.POSITIVE_INFINITY`.
 *
 * @throws IllegalArgumentException If the given source vertex is not present in the graph.
 */
fun <E> shortestPathUsingBellmanFordAlgorithm(graph: Graph<E>, source: E): Map<E, Double> {
    if (!graph.containsVertex(source)) {
        throw IllegalArgumentException("Source vertex not present in the given graph.")
    }

    val shortestPathMap = mutableMapOf<E, Double>()

    fun initializeSingleSource(sourceVertex: E) {
        for (vertex in graph.getVertices()) {
            shortestPathMap[vertex] = Double.POSITIVE_INFINITY
        }
        shortestPathMap[sourceVertex] = 0.0
    }

    fun relaxEdge(vertexFrom: E, vertexTo: E) {
        val shortestFrom = shortestPathMap[vertexFrom] ?: return
        val shortestTo = shortestPathMap[vertexTo] ?: return
        if (shortestTo > shortestFrom + graph.getWeight(vertexFrom, vertexTo)) {
            shortestPathMap[vertexTo] = shortestFrom + graph.getWeight(vertexFrom, vertexTo)
        }
    }

    fun relaxEdgeForNegativeCycle(vertexFrom: E, vertexTo: E) {
        val shortestFrom = shortestPathMap[vertexFrom] ?: return
        val shortestTo = shortestPathMap[vertexTo] ?: return
        if (shortestTo > shortestFrom + graph.getWeight(vertexFrom, vertexTo)) {
            shortestPathMap[vertexTo] = Double.NEGATIVE_INFINITY
        }
    }

    initializeSingleSource(source)

    val limit = graph.size() - 1
    for (i in 0 until limit) {
        for ((vertexFrom, vertexTo) in graph.getEdges()) {
            relaxEdge(vertexFrom, vertexTo)
        }
    }

    for (i in 0 until limit) {
        for ((vertexFrom, vertexTo) in graph.getEdges()) {
            relaxEdgeForNegativeCycle(vertexFrom, vertexTo)
        }
    }

    return shortestPathMap
}

/**
 * Computes the shortest path from a given source vertex to all other vertices in a weighted graph
 * using **Dijkstra's Algorithm**.
 *
 * Dijkstra's algorithm is more efficient but does not handle graphs with negative weight edges.
 * It finds the shortest path from the source vertex to all other reachable vertices in a non-negative weighted graph.
 *
 * @param E The type of vertices in the graph.
 * @param graph The graph containing vertices and weighted edges.
 * @param source The starting vertex from which shortest paths are calculated.
 * @return A map where each vertex is associated with the shortest distance from the source.
 * If a vertex is unreachable from the source vertex, its distance is marked as `Double.POSITIVE_INFINITY`.
 *
 * @throws IllegalArgumentException If the given source vertex is not present in the graph.
 */
fun <E> shortestPathUsingDijkstraAlgorithm(graph: Graph<E>, source: E): Map<E, Double> {
    if (!graph.containsVertex(source)) {
        throw IllegalArgumentException("Source vertex not present in the given graph.")
    }

    val shortestPathMap = mutableMapOf<E, Double>()
    val minPriorityQueue = java.util.PriorityQueue<Pair<E, Double>> (compareBy { it.second })

    fun initializeSingleSource(sourceVertex: E) {
        for (vertex in graph.getVertices()) {
            shortestPathMap[vertex] = Double.POSITIVE_INFINITY
            minPriorityQueue.offer(vertex to Double.POSITIVE_INFINITY)
        }
        shortestPathMap[sourceVertex] = 0.0
    }

    fun relaxEdge(vertexFrom: E, vertexTo: E) {
        val shortestFrom = shortestPathMap[vertexFrom] ?: return
        val shortestTo = shortestPathMap[vertexTo] ?: return
        if (shortestTo > shortestFrom + graph.getWeight(vertexFrom, vertexTo)) {
            minPriorityQueue.remove(vertexTo to shortestPathMap[vertexTo])
            shortestPathMap[vertexTo] = shortestFrom + graph.getWeight(vertexFrom, vertexTo)
            minPriorityQueue.offer(vertexTo to shortestPathMap[vertexTo]!!)
        }
    }

    initializeSingleSource(source)

    while (minPriorityQueue.isNotEmpty()) {
        val (vertexFrom, _) = minPriorityQueue.poll()
        val neighbors = if (graph.isDirected) {
            graph.getChildren(vertexFrom)
        } else {
            graph.getNeighbors(vertexFrom)
        }
        for (neighbor in neighbors) {
            relaxEdge(vertexFrom, neighbor)
        }
    }

    return shortestPathMap
}