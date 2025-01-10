package edu.practice.datastructures.linkedlist

class SinglyLinkedList<E> {

    private data class Node<E>(val data: E, var next: Node<E>? = null)

    private var size = 0

    private var first: Node<E>? = null
    private var last: Node<E>? = null

    fun size(): Int = size

    fun isEmpty(): Boolean = (size == 0)

    fun getFirst(): E {
        return first?.data ?: throw NoSuchElementException("Linked List is Empty")
    }

    fun getLast(): E {
        return last?.data ?: throw NoSuchElementException("Linked List is Empty")
    }

    fun addFirst(item: E) {
        val newNode = Node(item, first)
        first = newNode

        if (last == null) {
            last = newNode
        }

        size++
    }

    fun addLast(item: E) {
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

    fun removeFirst(): E {
        val item = getFirst()

        first = first?.next
        if (first == null) {
            last = null
        }

        size--
        return item
    }

    fun removeLast(): E {
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

    fun contains(item: E): Boolean {
        return existsInList(item)
    }
}