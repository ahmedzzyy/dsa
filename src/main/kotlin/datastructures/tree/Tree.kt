package edu.practice.datastructures.tree

interface Tree<E> {

    fun insert(item: E)
    fun delete(item: E): Boolean

    fun root(): E
    fun isLeaf(item: E): Boolean

    fun preOrderTraversal(): List<E>
    fun inOrderTraversal(): List<E>
    fun postOrderTraversal(): List<E>

    fun height(): Int
    fun contains(item: E): Boolean
    fun size(): Int
    fun isEmpty(): Boolean
}