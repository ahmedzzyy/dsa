package edu.practice.datastructures.graph

class StandardGraphList<V>(
    initialCapacity: Int = 5,
    val directed: Boolean = false
): Graph<V> {

    private class Edge<T>(val destination: T, val weight: Double)

    private val weightedGraph: MutableMap<V, MutableList<Edge<V>>> = HashMap(initialCapacity)
    private var vertexCount = 0

    override val isDirected: Boolean
        get() = directed

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
        if (!directed) weightedGraph[v]?.add(Edge(u, weight))
    }

    override fun removeEdge(u: V, v: V) {
        if (!weightedGraph.containsKey(u) || !weightedGraph.containsKey(v)) {
            throw IllegalArgumentException("Both vertices must be in graph.")
        }

        weightedGraph[u]?.removeAll { it.destination == v }
        if (!directed) weightedGraph[v]?.removeAll { it.destination == u }
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

    override fun bfsTraversal(source: V): List<Pair<V, Double>> {
        if (!weightedGraph.containsKey(source)) {
            throw IllegalArgumentException("Source vertex must be in the graph")
        }

        val visitedSet: MutableSet<V> = mutableSetOf()
        val result: MutableList<Pair<V, Double>> = mutableListOf()
        val visitingQueue: ArrayDeque<Pair<V, Double>> = ArrayDeque()

        visitingQueue.addLast(source to (0).toDouble())
        visitedSet.add(source)

        while (visitingQueue.isNotEmpty()) {
            val (currentVertex, distance) = visitingQueue.removeFirst()
            result.add(currentVertex to distance)

            val neighbors = if (directed) {
                getChildren(currentVertex)
            } else {
                getNeighbors(currentVertex)
            }
            for (neighbor in neighbors) {
                if (!visitedSet.contains(neighbor)) {
                    visitedSet.add(neighbor)
                    visitingQueue.addLast(neighbor to (distance + getWeight(currentVertex, neighbor)))
                }
            }
        }

        return result
    }

    override fun dfsTraversal(): Map<V, Pair<Int, Int>> {
        val visitedSet: MutableSet<V> = mutableSetOf()
        val result: MutableMap<V, Pair<Int, Int>> = mutableMapOf()
        var time = 0

        fun dfsVisit(vertex: V) {
            time += 1
            val discoveryTime = time
            result[vertex] = discoveryTime to 0
            visitedSet.add(vertex)

            val neighbors = if (directed) {
                getChildren(vertex)
            } else {
                getNeighbors(vertex)
            }

            for (neighbor in neighbors) {
                if (!visitedSet.contains(neighbor)) {
                    visitedSet.add(neighbor)
                    dfsVisit(neighbor)
                }
            }

            time += 1
            val finishTime = time
            result[vertex] = discoveryTime to finishTime
        }

        for ((vertex, _) in weightedGraph) {
            if (!visitedSet.contains(vertex)) {
                dfsVisit(vertex)
            }
        }

        return result
    }

    override fun transpose(): Graph<V> {
        val transposedGraph: Graph<V> = StandardGraphList(
            initialCapacity = this.weightedGraph.size,
            directed = this.directed
        )

        for (vertex in getVertices()) {
            transposedGraph.addVertex(vertex)
        }

        if (!directed) { // Undirected Graph - Cloning optimization
            for ((vertex, edges) in weightedGraph) {
                for (edge in edges) {
                    transposedGraph.addEdge(vertex, edge.destination, edge.weight)
                }
            }
        } else { // Directed Graph
            for ((vertex, edges) in weightedGraph) {
                for (edge in edges) {
                    transposedGraph.addEdge(edge.destination, vertex, edge.weight)
                }
            }
        }

        return transposedGraph
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

        for ((v, e) in weightedGraph) {
            if (vertex == v) {
                continue // Skip the same vertex
            }

            if (e.any { it.destination == vertex }) {
                if (neighbors.contains(v)) continue
                neighbors.add(v)
            }
        }

        return neighbors
    }

    override fun getParents(vertex: V): Set<V> {
        val parents: MutableSet<V> = hashSetOf()

        for ((v, edges) in weightedGraph) {
            if (vertex == v) {
                continue // Skip the same vertex
            }

            if (edges.any { it.destination == vertex }) {
                parents.add(v)
            }
        }

        return parents
    }

    override fun getChildren(vertex: V): Set<V> {
        val edges = weightedGraph[vertex] ?: throw IllegalArgumentException("Vertex not found in the graph")
        val children: MutableSet<V> = hashSetOf()

        for (edge in edges) {
            children.add(edge.destination)
        }

        return children
    }

    override fun getInDegree(vertex: V): Int {
        if (!directed) return getDegree(vertex) // Constant running time, better efficiency of out degree

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
                if (!directed) { // Skip same pair if undirected
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