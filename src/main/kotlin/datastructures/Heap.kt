package edu.practice.datastructures

class Heap<E: Comparable<E>>(
    initialCapacity: Int = DEFAULT_INITIAL_CAPACITY,
    val isMinHeap: Boolean = true
) {

    companion object {
        private const val DEFAULT_INITIAL_CAPACITY = 11
    }

    private var heap: ArrayList<E>
    private var size = 0

    init {
        if (initialCapacity < 1) {
            throw IllegalArgumentException("Initial Capacity of Heap should be at least 1")
        }

        heap = ArrayList(DEFAULT_INITIAL_CAPACITY)
    }

    private fun heapifyUp(startIndex: Int) {
        var index = startIndex

        while (index > 0) {
            val parentIndex = (index - 1) / 2

            if (!compare(heap[index], heap[parentIndex])) {
                break
            }

            swap(index, parentIndex)
            index = parentIndex
        }
    }

    private fun heapifyDown(startIndex: Int) {
        val leftChild = 2 * startIndex + 1
        val rightChild = 2 * startIndex + 2
        var smallestOrLargest = startIndex

        if (leftChild < size && compare(heap[leftChild], heap[smallestOrLargest])) {
            smallestOrLargest = leftChild
        }
        if (rightChild < size  && compare(heap[rightChild], heap[smallestOrLargest])) {
            smallestOrLargest = rightChild
        }
        if (smallestOrLargest != startIndex) {
            swap(startIndex, smallestOrLargest)
            heapifyDown(smallestOrLargest)
        }
    }

    fun insert(data: E) {
        heap.addLast(data)
        heapifyUp(size)
        size++
    }

    fun remove(): E {
        val item: E?
        try {
            item = peek()
        } catch (exception: IllegalStateException) {
            throw IllegalStateException("Cannot remove from heap when size of Heap is 0")
        }

        heap[0] = heap[size - 1]
        heapifyDown(0)

        size--
        return item
    }

    private fun swap(i: Int, j: Int) {
        val temp = heap[i]
        heap[i] = heap[j]
        heap[j] = temp
    }

    private fun compare(a: E, b: E): Boolean {
        return if (isMinHeap) a < b else a > b // Overloaded operator by Comparable
    }

    fun peek(): E {
        if (!isEmpty()) {
            return heap[0]
        } else {
            throw IllegalStateException("Cannot peek heap when size of Heap is 0")
        }
    }

    fun size(): Int = size

    fun isEmpty(): Boolean = (size == 0)
}