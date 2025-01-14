package edu.practice.datastructures

class PriorityQueue<E: Comparable<E>>(isMinHeap: Boolean = true) {

    // In previous Java implementations, `offer` included a null check and returned `false` for null inputs.
    // Kotlin's null safety and type checks make this unnecessary.
    // As a result, `peek` no longer returns `null` as in the Java implementation;
    // instead, it throws an error originating from the Heap Kotlin class.

    private val heap: Heap<E> = Heap(isMinHeap = isMinHeap)

    fun offer(data: E?): Boolean {
        if (data == null) {
            return false
        }
        heap.insert(data)
        return true
    }

    fun poll(): E {
        return heap.remove()
    }

    fun peek(): E {
        return heap.peek()
    }

    fun size(): Int {
        return heap.size()
    }

    fun isEmpty(): Boolean {
        return heap.isEmpty()
    }
}