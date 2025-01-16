package edu.practice.datastructures.graph

class StandardGraphMatrix<V>(
    initialCapacity: Int = 5,
    private val isDirected: Boolean = false
): Graph<V> {

    companion object {
        private const val INF = Double.POSITIVE_INFINITY
        private const val LOAD_FACTOR_THRESHOLD = 0.7
    }

    private var vertexMap: MutableMap<V, Int> = hashMapOf()
    private var weightedGraph: Array<DoubleArray>
    private var vertexCount = 0

    init {
        if (initialCapacity < 1) {
            throw IllegalArgumentException("Initial Capacity of Graph should be at least 1")
        }

        weightedGraph = Array(initialCapacity) { DoubleArray(initialCapacity) { INF } }
    }

    override fun addVertex(vertex: V) {
        if (vertexCount.toDouble() / weightedGraph.size >= LOAD_FACTOR_THRESHOLD) {
            resize()
        }

        if (vertexMap.containsKey(vertex)) {
            return
        }

        vertexMap[vertex] = vertexCount
        vertexCount++
    }

    override fun removeVertex(vertex: V) {
        if (!vertexMap.containsKey(vertex)) {
            return
        }

        val index: Int = vertexMap[vertex] ?: return
        vertexMap.remove(vertex)

        // Shift rows and column in the matrix
        for (i in 0 until vertexCount) {
            if (i > index) {
                weightedGraph[i].copyInto(weightedGraph[i - 1])
            }

            for (j in index until vertexCount) { // Shift columns left
                weightedGraph[i][j] = weightedGraph[i][j + 1]
            }
        }

        // Clear last rows and column
        val lastRowColIndex = vertexCount - 1
        for (i in 0 until lastRowColIndex) {
            weightedGraph[lastRowColIndex][i] = INF
            weightedGraph[i][lastRowColIndex] = INF
        }

        vertexCount--

        // Remap the Vertices
        val oldMap = vertexMap
        vertexMap = hashMapOf()
        var count = 0

        for (v in oldMap.keys) {
            vertexMap[v] = count++
        }
    }

    override fun addEdge(u: V, v: V, weight: Double) {
        if (!vertexMap.containsKey(u) || !vertexMap.containsKey(v)) {
            throw IllegalArgumentException("Both vertices must be in graph.")
        }

        val uIndex = vertexMap[u]
        val vIndex = vertexMap[v]

        if (uIndex != null && vIndex != null) { // Get returns Null - Int
            weightedGraph[uIndex][vIndex] = weight
            if (!isDirected) weightedGraph[vIndex][uIndex] = weight
        }
    }

    override fun removeEdge(u: V, v: V) {
        if (!vertexMap.containsKey(u) || !vertexMap.containsKey(v)) {
            throw IllegalArgumentException("Both vertices must be in graph.")
        }

        val uIndex = vertexMap[u]
        val vIndex = vertexMap[v]

        if (uIndex != null && vIndex != null) { // Get returns Null - Int
            weightedGraph[uIndex][vIndex] = INF
            if (!isDirected) weightedGraph[vIndex][uIndex] = INF
        }
    }

    override fun hasEdge(u: V, v: V): Boolean {
        if (!vertexMap.containsKey(u) || !vertexMap.containsKey(v)) {
            throw IllegalArgumentException("Both vertices must be in graph")
        }

        val uIndex = vertexMap[u]
        val vIndex = vertexMap[v]

        if (uIndex != null && vIndex != null) { // Get returns Null - Int
            if (weightedGraph[uIndex][vIndex] != INF) {
                return true
            }
        }

        return false
    }

    override fun getWeight(u: V, v: V): Double {
        if (!vertexMap.containsKey(u) || !vertexMap.containsKey(v)) {
            throw IllegalArgumentException("Both vertices must be in graph")
        }

        val uIndex = vertexMap[u]
        val vIndex = vertexMap[v]

        if (uIndex != null && vIndex != null) { // Get returns Null - Int
            if (weightedGraph[uIndex][vIndex] != INF) {
                return weightedGraph[uIndex][vIndex]
            }
        }

        return (-1).toDouble()
    }

    override fun getNeighbors(vertex: V): Set<V> {
        val vertexIndex = vertexMap[vertex] ?: throw IllegalArgumentException("Vertex not found in the graph")
        val neighbors: MutableSet<V> = hashSetOf()

        for (i in 0 until vertexCount) {
            if (weightedGraph[vertexIndex][i] != INF) {
                // Add vertex to set after referencing the object from the map
                vertexMap.entries.find { it.value == i }?.key?.let { neighbors.add(it) }
            }
        }

        return neighbors
    }

    override fun getVertices(): Set<V> = vertexMap.keys

    override fun getEdges(): Set<Pair<V, V>> {
        val edges: MutableSet<Pair<V, V>> = hashSetOf()

        for (i in 0 until vertexCount) {
            for (j in 0 until vertexCount) {
                if (weightedGraph[i][j] != INF) {
                    val u = vertexMap.entries.find { it.value == i }?.key
                    val v = vertexMap.entries.find { it.value == j }?.key

                    if (u != null && v != null) {
                        if (edges.contains(v to u)) {
                            continue
                        }
                        edges.add(u to v)
                    }
                }
            }
        }

        return edges
    }

    override fun getOutDegree(vertex: V): Int {
        val vertexIndex = vertexMap[vertex] ?: throw IllegalArgumentException("Vertex not found in the graph")
        var outDegree = 0

        for (i in 0 until vertexCount) {
            if (weightedGraph[vertexIndex][i] != INF) {
                outDegree++
            }
        }

        return outDegree
    }

    override fun getInDegree(vertex: V): Int {
        val vertexIndex = vertexMap[vertex] ?: throw IllegalArgumentException("Vertex not found in the graph")
        var inDegree = 0

        for (i in 0 until vertexCount) {
            if (weightedGraph[i][vertexIndex] != INF) {
                inDegree++
            }
        }

        return inDegree
    }

    override fun getDegree(vertex: V): Int = getOutDegree(vertex)

    private fun resize() {
        val newCapacity = weightedGraph.size * 2
        val oldMatrix = weightedGraph

        weightedGraph = Array(newCapacity) { DoubleArray(newCapacity) { INF } }

        for (i in 0 until vertexCount) {
            weightedGraph[i] = oldMatrix[i].copyOf(vertexCount)
        }
    }

    override fun containsVertex(vertex: V): Boolean = vertexMap.containsKey(vertex)

    override fun size(): Int = vertexCount

    override fun isEmpty(): Boolean = (vertexCount == 0)

    override fun clear() {
        vertexMap.clear()
        vertexCount = 0

        weightedGraph = Array(weightedGraph.size) { DoubleArray(weightedGraph.size) { INF } }
    }
}