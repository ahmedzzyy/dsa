package edu.practice.datastructures.linkedlist

interface LinkedList<E> {

    fun size(): Int
    fun isEmpty(): Boolean

    fun getFirst(): E
    fun getLast(): E

    fun addFirst(item: E)
    fun addLast(item: E)

    fun removeFirst(): E
    fun removeLast(): E

    fun contains(item: E): Boolean
}