package edu.practice.algorithms.sort

fun <E: Comparable<E>> selectionSort(items: Array<E>) {
    val limit = items.size - 1

    for (i in 0..limit) {
        var minIndex = i

        for (j in limit downTo (i + 1)) {
            if (items[j] < items[minIndex]) {
                minIndex = j
            }
        }

        if (minIndex != i) {
            items[i] = items[minIndex].also {
                items[minIndex] = items[i]
            }
        }
    }
}