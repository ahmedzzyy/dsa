package edu.practice.datastructures.graph

interface Graph<V> {

    companion object {
        fun <V> create(
            type: GraphType = GraphType.ADJ_LIST,
            initialCapacity: Int = 5,
            isDirected: Boolean = false
        ): Graph<V> {
            return when (type) {
                GraphType.ADJ_LIST -> StandardGraphList(initialCapacity, isDirected)
                GraphType.ADJ_MATRIX -> StandardGraphMatrix(initialCapacity, isDirected)
            }
        }
    }

    fun addVertex(vertex: V)
    fun removeVertex(vertex: V)

    fun addEdge(u: V, v: V, weight: Double = 1.0)
    fun removeEdge(u: V, v: V)
    fun hasEdge(u: V, v: V): Boolean

    /**
     * @return returns a list of all the connected vertices with their its distance ( including weights )
     * from source vertex
     */
    fun bfsTraversal(source: V): List<Pair<V, Double>>

    fun getNeighbors(vertex: V): Set<V>
    fun getParents(vertex: V): Set<V>
    fun getChildren(vertex: V): Set<V>

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