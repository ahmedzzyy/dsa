package edu.practice.algorithms.sort

enum class MergeSortVariant { RECURSIVE, ITERATIVE }

fun <E: Comparable<E>> mergeSort(array: Array<E>, variant: MergeSortVariant = MergeSortVariant.RECURSIVE) {
    when (variant) {
        MergeSortVariant.RECURSIVE -> mergeSortRecursive(array, 0, array.size - 1)
        MergeSortVariant.ITERATIVE -> mergeSortIterative(array)
    }
}

private fun <E: Comparable<E>> mergeSortRecursive(array: Array<E>, start: Int, end: Int) {
    if (start >= end) return

    val mid = (start + end) / 2
    mergeSortRecursive(array, start, mid)
    mergeSortRecursive(array, mid + 1, end)

    merge(array, start, mid, end)
}

private fun <E: Comparable<E>> mergeSortIterative(array: Array<E>) {
    val size = array.size
    var currentSize = 1

    while (currentSize < size) {
        var leftStart = 0
        while (leftStart < (size - 1)) {
            val mid = minOf(leftStart + currentSize - 1, size - 1)
            val rightEnd = minOf(leftStart + 2 * currentSize - 1, size - 1)

            merge(array, leftStart, mid, rightEnd)
            leftStart += (2 * currentSize)
        }

        currentSize *= 2
    }
}

private fun <E: Comparable<E>> merge(array: Array<E>, left: Int, mid: Int, right: Int) {
    val leftArray = array.copyOfRange(left, mid + 1)
    val rightArray = array.copyOfRange(mid + 1, right + 1)

    var i = 0 // Index for Left Array
    var j = 0 // Index for Right Array
    var k = left // Index for Original Array

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