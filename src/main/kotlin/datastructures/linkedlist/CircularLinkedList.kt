package edu.practice.datastructures.linkedlist


class CircularLinkedList<E>: LinkedList<E> {

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

        if (first?.next == null) { // Initially empty before node added
            first?.next = first
            last = first
        }
        
        size++
    }

    override fun addLast(item: E) {
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

    override fun removeFirst(): E {
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

    override fun removeLast(): E {
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

    override fun contains(item: E): Boolean {
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