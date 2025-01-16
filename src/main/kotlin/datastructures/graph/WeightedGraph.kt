package edu.practice.datastructures.graph

interface WeightedGraph<V>: Graph<V> {

    fun getWeight(u: V, v: V): Double

}