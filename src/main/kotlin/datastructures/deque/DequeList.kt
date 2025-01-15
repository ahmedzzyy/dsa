package edu.practice.datastructures.deque

import edu.practice.datastructures.linkedlist.SinglyLinkedList

class DequeList<E>: SinglyLinkedList<E>(), Deque<E> {
    override fun offerFirst(item: E) {
        super.addFirst(item)
    }

    override fun offerLast(item: E) {
        super.addLast(item)
    }

    override fun pollFirst(): E {
        val item: E
        try {
            item = super.removeFirst()
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException("Cannot remove first element when deque is empty")
        }

        return item
    }

    override fun pollLast(): E {
        val item: E
        try {
            item = super.removeLast()
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException("Cannot remove last element when deque is empty")
        }

        return item
    }

    override fun peekFirst(): E {
        val item: E
        try {
            item = super.getFirst()
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException("Cannot get first element when deque is empty")
        }

        return item
    }

    override fun peekLast(): E {
        val item: E
        try {
            item = super.getLast()
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException("Cannot get last element when deque is empty")
        }

        return item
    }
}