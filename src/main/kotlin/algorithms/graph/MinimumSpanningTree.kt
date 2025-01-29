package edu.practice.algorithms.graph

import edu.practice.datastructures.graph.Graph
import edu.practice.datastructures.unionfind.unionFindOf

/**
 * Computes the Minimum Spanning Tree (MST) of a given graph using Kruskal's algorithm.
 *
 * This function uses Kruskal's algorithm to find the subset of edges that forms a tree,
 * connecting all the vertices in the graph with the minimum possible total edge weight.
 *
 * @param E The type of vertices in the graph. Must be reified to allow runtime type checks.
 * @param graph The undirected graph for which the MST is to be calculated.
 * @return A new [Graph] containing the edges of the Minimum Spanning Tree. The resulting graph
 * contains a subset of the input graph's vertices and edges that satisfy the MST properties.
 */
inline fun <reified E> minimumSpanningTreeByKruskalAlgorithm(graph: Graph<E>): Graph<E> {
    val minimumSpanningTree: Graph<E> = Graph.create()
    val vertices = graph.getVertices().toTypedArray()
    val edges = graph.getEdges().sortedBy { (u, v): Pair<E, E> -> graph.getWeight(u, v) }

    val unionFind = unionFindOf(*vertices)
    for ((vertex1, vertex2) in edges) {
        if (unionFind.find(vertex1) != unionFind.find(vertex2)) {
            minimumSpanningTree.addVertex(vertex1)
            minimumSpanningTree.addVertex(vertex2)
            minimumSpanningTree.addEdge(vertex1, vertex2, graph.getWeight(vertex1, vertex2))
            unionFind.union(vertex1, vertex2)
        }
    }

    return minimumSpanningTree
}

/**
 * Computes the Minimum Spanning Tree (MST) of a given graph using Prim's algorithm.
 *
 * This function uses Prim's algorithm to find the subset of edges that forms a tree,
 * connecting all the vertices in the graph with the minimum possible total edge weight.
 *
 * @param E The type of vertices in the graph. Must be reified to allow runtime type checks.
 * @param graph The undirected graph for which the MST is to be calculated.
 * @return A new [Graph] containing the edges of the Minimum Spanning Tree. The resulting graph
 * contains a subset of the input graph's vertices and edges that satisfy the MST properties.
 */
fun <E> minimumSpanningTreeByPrimAlgorithm(graph: Graph<E>): Graph<E> {
    val minimumSpanningTree: Graph<E> = Graph.create()
    val minPriorityQueue = java.util.PriorityQueue<Pair<E, Double>> (compareBy { it.second })
    val minWeightMap = mutableMapOf<E, Double>()
    val visited = mutableSetOf<E>()

    val inf = Double.POSITIVE_INFINITY

    // Initialize keys to infinity and insert all vertices into the priority queue
    for (vertex in graph.getVertices()) {
        minWeightMap[vertex] = inf
        minPriorityQueue.offer(vertex to inf)
    }
    val startVertex = graph.getVertices().firstOrNull() ?: return minimumSpanningTree
    minWeightMap[startVertex] = 0.0
    minPriorityQueue.offer(startVertex to 0.0)

    while (minPriorityQueue.isNotEmpty()) {
        val (currentVertex, _) = minPriorityQueue.poll()

        if (!visited.add(currentVertex)) continue

        if (currentVertex != startVertex) {
            val minEdge = graph.getNeighbors(currentVertex)
                .filter { neighbor -> visited.contains(neighbor) }
                .minByOrNull { neighbor -> graph.getWeight(currentVertex, neighbor) }

            minEdge?.let {
                minimumSpanningTree.addVertex(currentVertex)
                minimumSpanningTree.addVertex(minEdge)
                minimumSpanningTree.addEdge(currentVertex, minEdge, graph.getWeight(currentVertex, minEdge))
            }
        }

        for (neighbor in graph.getNeighbors(currentVertex)) {
            if (!visited.contains(neighbor)) {
                val weight = graph.getWeight(currentVertex, neighbor)
                if (weight < minWeightMap[neighbor]!!) {
                    minWeightMap[neighbor] = weight
                    minPriorityQueue.offer(neighbor to weight)
                }
            }
        }
    }
    return minimumSpanningTree
}