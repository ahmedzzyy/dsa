package edu.practice.datastructures.graph

interface Graph<V> {

    fun addVertex(vertex: V)
    fun removeVertex(vertex: V)

    fun addEdge(u: V, v: V, weight: Double)
    fun removeEdge(u: V, v: V)
    fun hasEdge(u: V, v: V): Boolean

    fun getNeighbors(vertex: V): Set<V>
    fun getDegree(vertex: V): Int

    fun getVertices(): Set<V>
    fun getEdges(): Set<Pair<V, V>>

    fun containsVertex(vertex: V): Boolean
    fun size(): Int
    fun isEmpty(): Boolean
    fun clear()
}