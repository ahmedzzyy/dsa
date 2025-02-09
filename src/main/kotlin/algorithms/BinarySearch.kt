package edu.practice.algorithms

fun <T : Comparable<T>> binarySearch(list: List<T>, target: T): Int? {
    var left = 0
    var right = list.lastIndex

    while (left <= right) {
        val mid = left + (right - left) / 2
        val midValue = list[mid]

        when {
            midValue == target -> return mid
            midValue < target -> left = mid + 1
            else -> right = mid - 1
        }
    }

    return null
}

fun <T : Comparable<T>> binarySearchRecursive(list: List<T>, target: T): Int? {
    fun search(left: Int, right: Int): Int? {
        if (left > right) return null

        val mid = left + (right - left) / 2
        val midValue = list[mid]

        return when {
            midValue == target -> mid
            midValue < target -> search(mid + 1, right)
            else -> search(left, mid - 1)
        }
    }

    return search(0, list.size)
}