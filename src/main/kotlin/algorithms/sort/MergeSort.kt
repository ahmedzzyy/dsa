package edu.practice.algorithms.sort

fun <E: Comparable<E>> mergeSort(array: Array<E>) {
    fun merge(start: Int, end: Int) {
        val mid = (start + end) / 2

        // Take care of inclusivity of `copyOfRange` function
        val leftArray = array.copyOfRange(start, mid + 1)
        val rightArray = array.copyOfRange(mid + 1, end + 1)

        var i = 0 // Tracks left array
        var j = 0 // Tracks right array
        var k = start // Tracks location to fill

        while (i < leftArray.size && j < rightArray.size) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i]
                i++
            } else {
                array[k] = rightArray[j]
                j++
            }
            k++
        }

        while (i < leftArray.size) {
            array[k] = leftArray[i]
            i++
            k++
        }

        while (j < rightArray.size) {
            array[k] = rightArray[j]
            j++
            k++
        }
    }

    fun mergeSortRecursive(array: Array<E>, start: Int, end: Int) {
        if (start >= end) return

        val mid = (start + end) / 2
        mergeSortRecursive(array, start, mid)
        mergeSortRecursive(array, mid + 1, end)

        merge(start, end)
    }

    mergeSortRecursive(array, 0, array.size - 1)
}