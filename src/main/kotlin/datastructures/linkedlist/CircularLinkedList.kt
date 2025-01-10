package edu.practice.datastructures.linkedlist


class CircularLinkedList<E> {

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

        if (first?.next == null) { // Initially empty before node added
            first?.next = first
            last = first
        }
        
        size++
    }

    fun addLast(item: E) {
        val newNode = Node(item)

        if (last == null) { // If list is empty
            first = newNode
        } else {
            last?.next = newNode
        }
        last = newNode

        newNode.next = first
        size++
    }

    fun removeFirst(): E {
        val item = getFirst()

        if (first == first?.next) {
            last = null
            first = null
        } else {
            first = first?.next
            last?.next = first
        }

        size--
        return item
    }

    fun removeLast(): E {
        val item = getLast()

        if (first == last) { // Only 1 element in the list
            first = null
            last = null
        } else {
            var currentNode = first
            while (currentNode?.next != last) {
                currentNode = currentNode?.next
            }

            currentNode?.next = first
            last = currentNode
        }

        size--
        return item
    }

    fun contains(item: E): Boolean {
        if (first == null) {
            return false
        }

        var currentNode = first

        do {
            if (item == currentNode?.data) {
                return true
            }

            currentNode = currentNode?.next
        } while (currentNode != first)

        return false
    }
}