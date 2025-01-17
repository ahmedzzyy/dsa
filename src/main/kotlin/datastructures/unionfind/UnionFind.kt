package edu.practice.datastructures.unionfind

interface UnionFind<E> {

    val size: Int

    fun makeSet(vararg items: E)

    fun find(element: E): E // TODO(Maybe return index)

    fun union(element1: E, element2: E)

    fun areConnected(element1: E, element2: E): Boolean

    fun countSets(): Int
}