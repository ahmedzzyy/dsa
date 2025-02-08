package edu.practice.algorithms.sort

fun <E: Comparable<E>> heapSort(array: Array<E>) {
    fun swap(i: Int, j: Int) {
        array[i] = array[j].also { array[j] = array[i] }
    }

    fun maxHeapify(size: Int, index: Int) {
        val left = (2 * index) + 1
        val right = (2 * index) + 2
        var largest = index

        if (left < size && array[left] > array[largest]) largest = left
        if (right < size && array[right] > array[largest]) largest = right

        if (largest != index) {
            swap(index, largest)
            maxHeapify(size, largest)
        }
    }

    for (i in ((array.size / 2) - 1) downTo 0) {
        maxHeapify(array.size, i)
    }

    for (i in (array.size - 1) downTo 1) {
        swap(0, i)
        maxHeapify(i, 0)
    }
}