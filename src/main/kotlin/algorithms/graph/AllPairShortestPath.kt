package edu.practice.algorithms.graph

import edu.practice.datastructures.graph.Graph

/**
 * Computes the shortest paths between all pairs of vertices in a weighted graph using the **Floyd-Warshall Algorithm**.
 *
 * This algorithm finds the shortest distances between every pair of vertices,
 * even if the graph contains negative edge weights.
 *
 *
 *
 * @param E The type of vertices in the graph.
 * @param graph The graph containing vertices and weighted edges.
 * @return A map where each pair of vertices `(u, v)` is associated with the shortest distance between them.
 * - If two vertices are **connected**, the value represents the shortest path distance.
 * - If there is **no path** between two vertices, the distance is `Double.POSITIVE_INFINITY`.
 * - If a vertex is affected by a **negative weight cycle**, all paths involving it are marked as `Double.NEGATIVE_INFINITY`.
 */
fun <E> shortestPathUsingFloydWarshallAlgorithm(graph: Graph<E>): Map<Pair<E, E>, Double> {
    if (graph.isEmpty()) return emptyMap()

    val vertices = graph.getVertices().toList()
    val vertexCount = graph.size()
    val inf = Double.POSITIVE_INFINITY

    val indexedVertexMap = vertices.withIndex().associate { it.value to it.index }
    val dist = Array(vertexCount) { DoubleArray(vertexCount) { inf } }

    for (i in 0 until vertexCount) {
        dist[i][i] = 0.0
    }

    for ((u, v) in graph.getEdges()) {
        val uIndex = indexedVertexMap[u] ?: continue
        val vIndex = indexedVertexMap[v] ?: continue
        dist[uIndex][vIndex] = graph.getWeight(u, v)
    }

    // ---- FLOYD-WARSHALL ALGORITHM ----
    for (k in 0 until vertexCount) {
        for (i in 0 until vertexCount) {
            for (j in 0 until vertexCount) {
                if (dist[i][k] != inf && dist[k][j] != inf) {
                    dist[i][j] = minOf(dist[i][j], dist[i][k] + dist[k][j])
                }
            }
        }
    }

    // Detect Negative Weight Cycles
    val affectedVertices = mutableSetOf<E>()
    for (i in 0 until vertexCount) {
        if (dist[i][i] < 0) {
            affectedVertices.add(vertices[i])
        }
    }

    if (affectedVertices.isNotEmpty()) {
        for (vertex in affectedVertices) {
            val vIndex = indexedVertexMap[vertex] ?: continue
            for (i in 0 until vertexCount) {
                dist[vIndex][i] = Double.NEGATIVE_INFINITY
                dist[i][vIndex] = Double.NEGATIVE_INFINITY
            }
        }
    }

    // Convert result back to a map
    val result = mutableMapOf<Pair<E, E>, Double>()
    for (i in 0 until vertexCount) {
        for (j in 0 until vertexCount) {
            result[vertices[i] to vertices[j]] = dist[i][j]
        }
    }

    return result
}