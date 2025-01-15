package edu.practice.datastructures.linkedlist

open class SinglyLinkedList<E>: LinkedList<E> {

    private data class Node<E>(val data: E, var next: Node<E>? = null)

    private var size = 0

    private var first: Node<E>? = null
    private var last: Node<E>? = null

    override fun size(): Int = size

    override fun isEmpty(): Boolean = (size == 0)

    override fun getFirst(): E {
        return first?.data ?: throw NoSuchElementException("Linked List is Empty")
    }

    override fun getLast(): E {
        return last?.data ?: throw NoSuchElementException("Linked List is Empty")
    }

    override fun addFirst(item: E) {
        val newNode = Node(item, first)
        first = newNode

        if (last == null) {
            last = newNode
        }

        size++
    }

    override fun addLast(item: E) {
        val newNode = Node(item)

        if (last == null) {
            first = newNode
            last = newNode
        } else {
            last?.next = newNode
            last = newNode
        }

        size++
    }

    override fun removeFirst(): E {
        val item = getFirst()

        first = first?.next
        if (first == null) {
            last = null
        }

        size--
        return item
    }

    override fun removeLast(): E {
        val item = getLast()

        if (first != last) {
            // > 1 element in the list
            var currentNode = first
            while (currentNode?.next != last) {
                currentNode = currentNode?.next
            }

            currentNode?.next = null
            last = currentNode
        } else {
            first = null
            last = null
        }

        size--
        return item
    }

    private fun existsInList(item: E, firstNode: Node<E>? = first): Boolean {
        return firstNode?.let {
            it.data == item || existsInList(item, it.next)
        } ?: false
    }

    override fun contains(item: E): Boolean {
        return existsInList(item)
    }
}