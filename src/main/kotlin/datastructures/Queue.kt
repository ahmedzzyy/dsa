package edu.practice.datastructures

class Queue<E> {

    private data class Node<E>(val data: E, var next: Node<E>? = null)

    private var front: Node<E>? = null
    private var rear: Node<E>? = null

    private var size: Int = 0

    fun size(): Int = size

    fun isEmpty(): Boolean = (size == 0)

    fun peek(): E {
        return front?.data ?: throw NoSuchElementException("Queue is Empty.")
    }

    fun offer(item: E) {
        val newNode = Node(item)

        if (rear == null) { // Empty Queue Condition
            front = newNode
        } else {
            rear?.next = newNode
        }

        rear = newNode
        size++
    }

    fun poll(): E {
        val item = peek()
        front = front?.next
        size--
        return item
    }

    override fun toString(): String {
        val sb = StringBuilder("FRONT -> ")

        var current = front

        if (current == null) {
            sb.append("NULL ")
        }

        while (current != null) {
            sb.append(current.data)

            if (current.next != null) {
                sb.append(" -> ")
            }

            current = current.next
        }

        sb.append(" <- REAR")

        return sb.toString()
    }
}