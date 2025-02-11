package edu.practice.algorithms.string

fun naiveStringMatch(text: String, pattern: String): List<Int> {
    if (pattern.length > text.length) return emptyList()

    val matchIndicesList = mutableListOf<Int>()

    for (shift in 0..(text.length - pattern.length)) {
        if (pattern == text.substring(shift, shift + pattern.length)) {
            matchIndicesList.add(shift)
        }
    }

    return matchIndicesList
}