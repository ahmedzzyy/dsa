package edu.practice.algorithms.graph

import edu.practice.datastructures.graph.Graph

/**
 * Performs a topological sort on a directed acyclic graph (DAG).
 *
 *  Topological sorting is an ordering of the vertices in a directed graph such that for every directed edge (u, v),
 *  vertex `u` comes before vertex `v` in the ordering. This function assumes the input graph is directed and acyclic.
 *
 * @param E The type of vertices in the graph.
 * @param graph The directed acyclic graph (DAG) for which the topological sort will be performed
 * @return A list of vertices in topological order.
 *
 * @throws IllegalArgumentException If the input graph contains a cycle or is not directed. Topological sorting is
 * defined only for directed acyclic graphs.
 */
fun <E> topologicalSort(graph: Graph<E>): List<E> {
    if (!graph.isDirected) throw IllegalArgumentException(
        "Topological sort is only valid for directed acyclic graphs (DAGs). The input graph is undirected."
    )
    if (isCyclicGraph(graph)) throw IllegalArgumentException(
        "Topological sort is only valid for directed acyclic graphs (DAGs). The input graph contains a cycle."
    )

    val visitedSet: MutableSet<E> = mutableSetOf()
    val topSort = ArrayDeque<E>()

    fun dfsVisit(vertex: E) {
        visitedSet.add(vertex)

        // Graph is Directed, so get children only not all neighbors
        val neighbors = graph.getChildren(vertex)

        for (neighbor in neighbors) {
            if (!visitedSet.contains(neighbor)) {
                visitedSet.add(neighbor)
                dfsVisit(neighbor)
            }
        }

        topSort.addFirst(vertex)
    }

    for (vertex in graph.getVertices()) {
        if (!visitedSet.contains(vertex)) {
            dfsVisit(vertex)
        }
    }

    return topSort
}

/**
 * Performs a topological sort on a directed acyclic graph (DAG) using Kahn's Algorithm.
 *
 *  Topological sorting is an ordering of the vertices in a directed graph such that for every directed edge (u, v),
 *  vertex `u` comes before vertex `v` in the ordering. This function assumes the input graph is directed and acyclic.
 *
 * @param E The type of vertices in the graph.
 * @param graph The directed acyclic graph (DAG) for which the topological sort will be performed
 * @return A list of vertices in topological order.
 *
 * @throws IllegalArgumentException If the input graph contains a cycle or is not directed. Topological sorting is
 * defined only for directed acyclic graphs.
 */
fun <E> topologicalSortUsingKahnsAlgorithm(graph: Graph<E>): List<E> {
    if (!graph.isDirected) throw IllegalArgumentException(
        "Topological sort is only valid for directed acyclic graphs (DAGs). The input graph is undirected."
    )

    val topSort = arrayListOf<E>()
    val inDegreeMap = mutableMapOf<E, Int>()
    val visitingQueue = ArrayDeque<E>()

    for (vertex in graph.getVertices()) {
        val inDegree = graph.getInDegree(vertex)
        inDegreeMap[vertex] = inDegree
        if (inDegree == 0) {
            visitingQueue.addLast(vertex)
        }
    }

    var index = 0
    while (visitingQueue.isNotEmpty()) {
        val currentElement = visitingQueue.removeFirst()
        topSort.add(currentElement)
        index++

        for (destination in graph.getNeighbors(currentElement)) {
            val inDegree = inDegreeMap[destination]
            inDegree ?: continue
            inDegreeMap[destination] = inDegree - 1
            if ((inDegree - 1) == 0) {
                visitingQueue.addLast(destination)
            }
        }
    }

    if (index != graph.size()) throw IllegalArgumentException(
        "Topological sort is only valid for directed acyclic graphs (DAGs). The input graph contains a cycle."
    )

    return topSort
}

private fun <E> isCyclicGraph(graph: Graph<E>): Boolean {
    val visitedSet: MutableSet<E> = mutableSetOf()
    val currentPathStack = ArrayDeque<E>()

    fun dfsVisit(vertex: E): Boolean {
        // If the vertex is already in the recursion stack, a cycle is detected
        if (currentPathStack.contains(vertex)) return true

        // If the vertex is already visited, no cycle from this vertex
        if (visitedSet.contains(vertex)) return false

        visitedSet.add(vertex)
        currentPathStack.addLast(vertex)

        val neighbors = if (graph.isDirected) {
            graph.getChildren(vertex)
        } else {
            graph.getNeighbors(vertex)
        }

        for (neighbor in neighbors) {
            if (dfsVisit(neighbor)) return true
        }

        currentPathStack.removeLast()
        return false
    }

    for (vertex in graph.getVertices()) {
        if (!visitedSet.contains(vertex)) {
            if (dfsVisit(vertex)) return true
        }
    }

    return false
}