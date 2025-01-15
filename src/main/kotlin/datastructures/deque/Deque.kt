package edu.practice.datastructures.deque

interface Deque<E> {

    fun offerFirst(item: E)
    fun offerLast(item: E)

    fun pollFirst(): E
    fun pollLast(): E

    fun peekFirst(): E
    fun peekLast(): E

    fun size(): Int
    fun isEmpty(): Boolean
}