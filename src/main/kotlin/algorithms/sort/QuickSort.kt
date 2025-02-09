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
        var low = start
        var high = end

        // Manual Tail Recursion
        while (low < high) {
            val pivot = partition(low, high)

            if (pivot - low < high - pivot) {
                quickSortRecursive(low, pivot - 1)
                low = pivot + 1
            } else {
                quickSortRecursive(pivot + 1, high)
                high = pivot - 1
            }
        }
    }

    quickSortRecursive(0, array.size - 1)
}