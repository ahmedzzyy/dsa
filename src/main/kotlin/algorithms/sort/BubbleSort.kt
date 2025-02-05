package edu.practice.algorithms.sort

fun <E: Comparable<E>> bubbleSort(items: Array<E>) {
    val limit = items.size - 1

    for (i in 0..limit) {
        for (j in limit downTo (i + 1)) {
            if (items[j] < items[j - 1]) {
                items[j] = items[j - 1].also {
                    items[j - 1] = items[j]
                }
            }
        }
    }
}