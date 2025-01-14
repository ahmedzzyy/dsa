package edu.practice.datastructures.graph

interface DirectedWeightedGraph<V> {

    fun addVertex(vertex: V)
    fun removeVertex(vertex: V)

    fun addEdge(u: V, v: V, weight: Double)
    fun removeEdge(u: V, v: V)
    fun getEdge(u: V, v: V): Double

    fun getNeighbors(vertex: V): Set<V>
    fun getInDegree(vertex: V): Int
    fun getOutDegree(vertex: V): Int

    fun getVertices(): Set<V>
    fun getEdges(): Set<Pair<V, V>>

    fun containsVertex(vertex: V): Boolean
    fun size(): Int
    fun isEmpty(): Boolean
    fun clear()
}