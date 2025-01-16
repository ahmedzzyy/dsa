package edu.practice.datastructures.graph

interface Graph<V> {

    companion object {
        enum class GRAPH {
            ADJ_MATRIX, ADJ_LIST
        }

        fun <V> create(
            type: GRAPH = GRAPH.ADJ_LIST,
            initialCapacity: Int = 5,
            directed: Boolean = false
        ): Graph<V> {
            return when (type) {
                GRAPH.ADJ_LIST -> StandardGraphList(initialCapacity, directed)
                GRAPH.ADJ_MATRIX -> StandardGraphMatrix(initialCapacity, directed)
            }
        }
    }

    fun addVertex(vertex: V)
    fun removeVertex(vertex: V)

    fun addEdge(u: V, v: V, weight: Double = 1.0)
    fun removeEdge(u: V, v: V)
    fun hasEdge(u: V, v: V): Boolean

    fun getNeighbors(vertex: V): Set<V>
    fun getWeight(u: V, v: V): Double
    fun getDegree(vertex: V): Int
    fun getInDegree(vertex: V): Int
    fun getOutDegree(vertex: V): Int

    fun getVertices(): Set<V>
    fun getEdges(): Set<Pair<V, V>>

    fun containsVertex(vertex: V): Boolean
    fun size(): Int
    fun isEmpty(): Boolean
    fun clear()
}