package edu.practice.datastructures.graph

interface DirectedGraph<V>: Graph<V> {

    fun getInDegree(vertex: V): Int
    fun getOutDegree(vertex: V): Int

}