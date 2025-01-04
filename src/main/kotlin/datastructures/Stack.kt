package edu.practice.datastructures

class Stack<E> {

    private data class Node<E>(val data: E, var next: Node<E>?)

    private var top: Node<E>? = null
    private var size: Int = 0

    fun size(): Int = size

    fun isEmpty(): Boolean = (size == 0)

    fun peek(): E {
        return top?.data ?: throw NoSuchElementException("Stack is Empty")
    }

    fun push(item: E) {
        top = Node(item, next = top)
        size++
    }

    fun pop(): E {
        val item = peek()
        top = top?.next
        size--
        return item
    }

    override fun toString(): String {
        val sb = StringBuilder("TOP")

        var current = top
        while (current != null) {
            sb.append(" -> ")
            sb.append(current.data)

            current = current.next
        }

        sb.append(" -> NULL")

        return sb.toString()
    }
}