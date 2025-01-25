package edu.practice.algorithms.graph

import edu.practice.datastructures.graph.Graph

/**
 * Identifies and returns the strongly connected components (SCCs) of a directed graph using Kosaraju's algorithm.
 *
 * Kosaraju's algorithm involves two depth-first search (DFS) traversals:
 * 1. The first DFS is performed on the original graph to determine the order of vertices based on their finishing times.
 * 2. The second DFS is performed on the transposed graph, following the order from the first DFS to identify SCCs.
 *
 * @param E The type of vertices in the graph. The vertices should be comparable (or have meaningful equality).
 * @param graph The directed graph for which strongly connected components will be calculated.
 * @return A set of sets, where each inner set represents a strongly connected component of the graph.
 *
 * @throws IllegalArgumentException If the input graph is not a directed graph. Kosaraju's algorithm is defined only for directed graphs.
 */
fun <E> stronglyConnectedComponentsUsingKosaraju(graph: Graph<E>): Set<Set<E>> {
    if (!graph.isDirected) throw IllegalArgumentException(
        "Kosaraju's algorithm requires a directed graph. Provided graph is not directed."
    )

    var visitedSet: MutableSet<E> = mutableSetOf()
    val stronglyConnectedComponents: MutableSet<MutableSet<E>> = mutableSetOf()
    val stack = ArrayDeque<E>()
    val transposedGraph = graph.transpose()

    fun dfsGraphVisitToStack(vertex: E) {
        visitedSet.add(vertex)

        val neighbors = if (graph.isDirected) {
            graph.getChildren(vertex)
        } else {
            graph.getNeighbors(vertex)
        }

        for (neighbor in neighbors) {
            if (!visitedSet.contains(neighbor)) {
                visitedSet.add(neighbor)
                dfsGraphVisitToStack(neighbor)
            }
        }

        stack.addLast(vertex)
    }

    fun dfsTransposeVisitFromStack(vertex: E, currentComponentSet: MutableSet<E>) {
        visitedSet.add(vertex)
        currentComponentSet.add(vertex)

        val neighbors = if (transposedGraph.isDirected) {
            transposedGraph.getChildren(vertex)
        } else {
            transposedGraph.getNeighbors(vertex)
        }

        for (neighbor in neighbors) {
            if (!visitedSet.contains(neighbor)) {
                visitedSet.add(neighbor)
                dfsTransposeVisitFromStack(neighbor, currentComponentSet)
            }
        }
    }

    for (vertex in graph.getVertices()) {
        if (!visitedSet.contains(vertex)) {
            dfsGraphVisitToStack(vertex)
        }
    }

    visitedSet = mutableSetOf() // Clear for next DFS

    while (stack.isNotEmpty()) {
        val vertex = stack.removeLast()
        if (!visitedSet.contains(vertex)) {
            val currentComponentSet = mutableSetOf<E>()
            dfsTransposeVisitFromStack(vertex, currentComponentSet)
            stronglyConnectedComponents.add(currentComponentSet)
        }
    }

    return stronglyConnectedComponents
}