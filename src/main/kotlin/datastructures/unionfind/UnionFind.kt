package edu.practice.datastructures.unionfind

interface UnionFind<E> {

    val size: Int

    fun makeSet(vararg items: E)

    fun find(element: E): E

    fun union(element1: E, element2: E)

    fun areConnected(element1: E, element2: E): Boolean

    fun countSets(): Int
}

fun <E> unionFindOf(vararg items: E): UnionFind<E> {
    val unionFind: UnionFind<E> = StandardUnionFind()
    unionFind.makeSet(*items)
    return unionFind
}