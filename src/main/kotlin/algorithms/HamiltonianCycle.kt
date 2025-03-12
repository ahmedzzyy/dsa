package edu.practice.algorithms

import edu.practice.datastructures.graph.Graph

/**
 * Finds all Hamiltonian cycles in the given graph
 *
 * A Hamiltonian cycle is a closed path that visits every vertex exactly once and returns to the starting vertex.
 * This function fixes the starting vertex to avoid duplicate cycles that are merely rotations of one another.
 * It recursively explores possible paths while tracking visited vertices, and adds a cycle to the result list
 * only when the last vertex is connected back to the starting vertex.
 *
 * @param E Data type of the vertices in the graph
 * @param graph graph in which to find hamiltonian cycles.
 *
 * @return a list of hamiltonian cycles. Where each cycle is represented as a list of vertices in the
 *         order they are visited.
 *         If the graph is empty, an empty list is returned.
 */
fun <E> findHamiltonianCycles(graph: Graph<E>): List<List<E>> {
    if (graph.isEmpty()) return emptyList()

    val vertices = graph.getVertices().toList()
    val startVertex = vertices.first()
    val visited = mutableSetOf<E>()

    val finalPaths = mutableListOf<List<E>>()
    val currentPath = mutableListOf<E>()

    fun backTrack() {
        if (currentPath.size == vertices.size) {
            if (graph.hasEdge(currentPath.last(), startVertex)) {
                finalPaths.add(currentPath.toList())
            }
            return
        }

        val neighbors = graph.getNeighbors(currentPath.last()) // neighbors of last vertex
        for (vertex in neighbors) {
            if (vertex !in visited) {
                visited.add(vertex)
                currentPath.add(vertex)
                backTrack()
                currentPath.removeAt(currentPath.lastIndex)
                visited.remove(vertex)
            }
        }
    }

    visited.add(startVertex)
    currentPath.add(startVertex)
    backTrack()

    return finalPaths
}