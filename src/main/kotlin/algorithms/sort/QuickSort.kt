package edu.practice.algorithms.sort

fun <E: Comparable<E>> quickSort(array: Array<E>) {
    fun Array<E>.swap(i: Int, j: Int) {
        if (i != j) this[i] = this[j].also { this[j] = this[i] }
    }

    fun partition(start: Int, end: Int): Int {
        val pivot = array[end]
        var i = start - 1 // Highest index on the low side

        for (j in start until end) {
            if (array[j] <= pivot) {
                i++
                array.swap(i, j)
            }
        }

        array.swap(i + 1, end)
        return i + 1
    }

    fun quickSortRecursive(start: Int, end: Int) {
        if (start >= end) return

        val pivot = partition(start, end)
        quickSortRecursive(start, pivot - 1)
        quickSortRecursive(pivot + 1, end)
    }

    quickSortRecursive(0, array.size - 1)
}