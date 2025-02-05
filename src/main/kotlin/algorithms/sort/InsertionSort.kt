package edu.practice.algorithms.sort

fun <E: Comparable<E>> insertionSort(items: Array<E>) {
    for (i in 1 until items.size) {
        val key = items[i]
        var j = i - 1

        while (j >= 0 && items[j] > key) {
            items[j + 1] = items[j]
            j--
        }

        items[j + 1] = key
    }
}