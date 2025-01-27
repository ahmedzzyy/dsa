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

/**
 * Identifies and returns the strongly connected components (SCCs) of a directed graph using Tarjan's algorithm.
 *
 * Tarjan's algorithm is a depth-first search (DFS)-based algorithm that finds SCCs in a single traversal.
 * It assigns each vertex:
 * 1. A discovery time (the time when the vertex is first visited).
 * 2. A low-link value (the smallest discovery time reachable from the vertex).
 *
 * The algorithm maintains a stack to track the vertices currently in the current DFS path.
 * When a vertex's low-link value matches its discovery time, it is identified as the root of a new SCC.
 * All nodes in the stack, up to this root vertex, form the SCC.
 *
 * @throws IllegalArgumentException If the input graph is not a directed graph. Kosaraju's algorithm is defined only for directed graphs.
 */
fun <E> stronglyConnectedComponentsUsingTarjans(graph: Graph<E>): Set<Set<E>> {
    if (!graph.isDirected) throw IllegalArgumentException(
        "Tarjan's algorithm requires a directed graph. Provided graph is not directed."
    )

    val stronglyConnectedComponents: MutableSet<MutableSet<E>> = mutableSetOf()

    val visitedSet: MutableSet<E> = mutableSetOf()
    val vertexDiscoveryTime = mutableMapOf<E, Int>()
    val vertexLowLink = mutableMapOf<E, Int>()
    val stack = ArrayDeque<E>()

    var time = 0

    fun dfsVisit(vertex: E) {
        visitedSet.add(vertex)
        vertexDiscoveryTime[vertex] = time
        vertexLowLink[vertex] = time

        time++
        stack.addLast(vertex)

        for (neighbor in graph.getChildren(vertex)) {
            when {
                !visitedSet.contains(neighbor) -> {
                    dfsVisit(neighbor)
                    val currentLowLink = vertexLowLink[vertex]
                    val neighborLowLink = vertexLowLink[neighbor]
                    if (currentLowLink != null && neighborLowLink != null) {
                        vertexLowLink[vertex] = minOf(currentLowLink, neighborLowLink)
                    }
                }
                stack.contains(neighbor) -> {
                    val currentLowLink = vertexLowLink[vertex]
                    val neighborDiscoveryTime = vertexDiscoveryTime[neighbor]
                    if (currentLowLink != null && neighborDiscoveryTime != null) {
                        vertexLowLink[vertex] = minOf(currentLowLink, neighborDiscoveryTime)
                    }
                }
            }
        }

        if (vertexLowLink[vertex] == vertexDiscoveryTime[vertex]) {
            val currentComponent = mutableSetOf<E>()
            do {
                val top = stack.removeLast()
                currentComponent.add(top)
            } while (top != vertex)
            stronglyConnectedComponents.add(currentComponent)
        }
    }

    for (vertex in graph.getVertices()) {
        if (!visitedSet.contains(vertex)) {
            dfsVisit(vertex)
        }
    }

    return stronglyConnectedComponents
}