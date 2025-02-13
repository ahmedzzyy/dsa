package edu.practice.algorithms.string

fun knuthMorrisPrattStringMatch(text: String, pattern: String): List<Int> {
    if (pattern.length > text.length) return emptyList()

    fun computeLongestPrefixSuffix(): IntArray {
        if (pattern.isEmpty()) return IntArray(0)

        val lps = IntArray(pattern.length) { 0 }
        var prefixLength = 0

        for (q in 1 until pattern.length) {
            while (prefixLength > 0 && pattern[prefixLength] != pattern[q]) {
                prefixLength = lps[prefixLength]
            }

            if (pattern[prefixLength] == pattern[q]) prefixLength++
            lps[q] = prefixLength
        }

        return lps
    }

    val matchIndicesList = mutableListOf<Int>()
    val lps = computeLongestPrefixSuffix()

    var matchedLength = 0
    for (i in text.indices) {
        while (matchedLength > 0 && pattern[matchedLength] != text[i]) {
            // next character does not match
            matchedLength = lps[matchedLength - 1]
        }

        // next character matches
        if (pattern[matchedLength] == text[i]) matchedLength++

        // are all characters in the Pattern matched?
        if (matchedLength == pattern.length) {
            matchIndicesList.add(i + 1 - pattern.length)
            // look for the next match
            matchedLength = lps[matchedLength - 1]
        }
    }
    return matchIndicesList
}