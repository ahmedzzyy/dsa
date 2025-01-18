package edu.practice.datastructures.deque

class DequeArray<E>(initialCapacity: Int = DEFAULT_INITIAL_CAPACITY): Deque<E> {

    // Initially planned to use `Array()` instead of `ArrayList`,
    // but switched to `ArrayList` due to the need for reification.

    companion object {
        private const val DEFAULT_INITIAL_CAPACITY = 11
    }

    private var deque: ArrayList<E>
    private var size = 0

    init {
        if (initialCapacity < 1) {
            throw IllegalArgumentException("Initial Capacity of Queue should be at least 1")
        }

        deque = ArrayList(initialCapacity)
    }

    override fun offerFirst(item: E) {
        deque.addFirst(item)
        size++
    }

    override fun offerLast(item: E) {
        deque.addLast(item)
        size++
    }

    override fun pollFirst(): E {
        val item: E
        try {
            item = deque.removeFirst()
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException("Cannot remove first element when deque is empty")
        }
        size--
        return item
    }

    override fun pollLast(): E {
        val item: E
        try {
            item = deque.removeLast()
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException("Cannot remove last element when deque is empty")
        }
        size--
        return item
    }

    override fun peekFirst(): E {
        val item: E
        try {
            item = deque.first()
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException("Cannot get first element when deque is empty")
        }

        return item
    }

    override fun peekLast(): E {
        val item: E
        try {
            item = deque.last()
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException("Cannot get last element when deque is empty")
        }

        return item
    }

    override fun size(): Int = size

    override fun isEmpty(): Boolean = (size == 0)
}