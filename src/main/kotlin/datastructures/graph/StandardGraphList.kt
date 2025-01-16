package edu.practice.datastructures.graph

class StandardGraphList<V>(
    initialCapacity: Int = 5,
    private val isDirected: Boolean = false
): Graph<V> {

    private class Edge<T>(val destination: T, val weight: Double)

    private val weightedGraph: MutableMap<V, MutableList<Edge<V>>> = HashMap(initialCapacity)
    private var vertexCount = 0

    override fun addVertex(vertex: V) {
        if (weightedGraph.containsKey(vertex)) {
            return
        }

        weightedGraph[vertex] = ArrayList()
        vertexCount++
    }

    override fun removeVertex(vertex: V) {
        if (!weightedGraph.containsKey(vertex)) {
            return
        }

        weightedGraph.remove(vertex)
        for (edges in weightedGraph.values) {
            edges.removeAll { it.destination == vertex }
        }

        vertexCount--
    }

    override fun addEdge(u: V, v: V, weight: Double) {
        if (!weightedGraph.containsKey(u) || !weightedGraph.containsKey(v)) {
            throw IllegalArgumentException("Both vertices must be in graph.")
        }

        weightedGraph[u]?.add(Edge(v, weight))
        if (!isDirected) weightedGraph[v]?.add(Edge(u, weight))
    }

    override fun removeEdge(u: V, v: V) {
        if (!weightedGraph.containsKey(u) || !weightedGraph.containsKey(v)) {
            throw IllegalArgumentException("Both vertices must be in graph.")
        }

        weightedGraph[u]?.removeAll { it.destination == v }
        if (!isDirected) weightedGraph[v]?.removeAll { it.destination == u }
    }

    override fun hasEdge(u: V, v: V): Boolean {
        if (!weightedGraph.containsKey(u) || !weightedGraph.containsKey(v)) {
            throw IllegalArgumentException("Both vertices must be in graph.")
        }

        val edges = weightedGraph[u] ?: return false

        for (edge in edges) {
            if (edge.destination == v) {
                return true
            }
        }

        return false
    }

    override fun getWeight(u: V, v: V): Double {
        if (!weightedGraph.containsKey(u) || !weightedGraph.containsKey(v)) {
            throw IllegalArgumentException("Both vertices must be in graph.")
        }

        val edges = weightedGraph[u] ?: return (-1).toDouble()

        for (edge in edges) {
            if (edge.destination == v) {
                return edge.weight
            }
        }

        return (-1).toDouble()
    }

    override fun getNeighbors(vertex: V): Set<V> {
        val edges = weightedGraph[vertex] ?: throw IllegalArgumentException("Vertex not found in the graph")
        val neighbors: MutableSet<V> = hashSetOf()

        for (edge in edges) {
            neighbors.add(edge.destination)
        }

        return neighbors
    }

    override fun getInDegree(vertex: V): Int {
        if (!isDirected) return getDegree(vertex) // Constant running time, better efficiency of out degree

        var inDegree = 0

        for ((key, edges) in weightedGraph) {
            if (vertex == key) {
                continue // Skip the same vertex
            }

            if (edges.any { it.destination == vertex }) {
                inDegree++
                continue
            }
        }

        return inDegree
    }

    override fun getOutDegree(vertex: V): Int {
        val inEdges = weightedGraph[vertex] ?: throw IllegalArgumentException("Vertex not found in the graph")

        return inEdges.size
    }

    override fun getDegree(vertex: V): Int = getOutDegree(vertex)

    override fun getVertices(): Set<V> = weightedGraph.keys

    override fun getEdges(): Set<Pair<V, V>> {
        val edges: MutableSet<Pair<V, V>> = hashSetOf()

        for ((vertex, edgeList) in weightedGraph) {
            for (edge in edgeList) {
                if (!isDirected) { // Skip same pair if undirected
                    if (edges.contains(edge.destination to vertex)) {
                        continue
                    }
                }
                edges.add(vertex to edge.destination)
            }
        }

        return edges
    }

    override fun size(): Int = vertexCount

    override fun isEmpty(): Boolean = (vertexCount == 0)

    override fun clear() {
        weightedGraph.clear()
        vertexCount = 0
    }

    override fun containsVertex(vertex: V): Boolean = weightedGraph.containsKey(vertex)
}