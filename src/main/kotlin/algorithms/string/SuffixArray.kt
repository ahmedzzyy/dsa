package edu.practice.algorithms.string

private data class Suffix(var leftRank: Int, var rightRank: Int, var index: Int)

fun buildSuffixArray(text: String): IntArray {
    val textLength = text.length
    if (textLength == 0) return IntArray(0)

    val suffixes = Array(textLength) { i ->
        Suffix(
            leftRank = text[i].code,
            rightRank = if (i + 1 < textLength) text[i + 1].code else 0,
            index = i
        )
    }

    suffixes.sortWith(compareBy({ it.leftRank }, { it.rightRank }))

    var substringLength = 1
    val rank = IntArray(textLength)
    while (substringLength < textLength) {
        makeRanks(suffixes, rank)

        for (i in 0 until textLength) {
            suffixes[i].leftRank = rank[suffixes[i].index]
            suffixes[i].rightRank =
                if (suffixes[i].index + substringLength < textLength) rank[suffixes[i].index + substringLength] else 0
        }

        suffixes.sortWith(compareBy({ it.leftRank }, { it.rightRank }))

        substringLength *= 2
    }

    val suffixArray = IntArray(textLength) { i -> suffixes[i].index }
    return suffixArray
}

/**
 * Computes the LCP (Longest Common Prefix) array for the given text and its suffix array.
 *
 * @param text The input string.
 * @param suffixArray An array of starting indices representing the suffix array (sorted lexicographically).
 * @return An array LCP where `LCP[i]` is the length of the longest common prefix between
 *         the suffixes starting at `suffixArray[i]` and `suffixArray[i-1]`. `LCP[0]` is defined to be 0.
 */
fun computeLCP(text: String, suffixArray: IntArray): IntArray {
    val textLength = text.length
    require(suffixArray.size == textLength) { "Suffix array size must match text length." }

    val rank = IntArray(textLength)
    val lcp = IntArray(textLength)

    for (currentIndex in 0 until textLength) {
        rank[suffixArray[currentIndex]] = currentIndex
    }

    var commonPrefixLength = 0

    for (currentIndex in 0 until textLength) {
        if (rank[currentIndex] > 0) {
            val previousSuffixStart = suffixArray[rank[currentIndex] - 1]

            while (currentIndex + commonPrefixLength < textLength &&
                previousSuffixStart + commonPrefixLength < textLength &&
                text[currentIndex + commonPrefixLength] == text[previousSuffixStart + commonPrefixLength]) {
                // Increase the length of common prefix while the characters match.
                commonPrefixLength++
            }

            lcp[rank[currentIndex]] = commonPrefixLength

            if (commonPrefixLength > 0) commonPrefixLength--
        }
    }

    return lcp
}

private fun makeRanks(suffixes: Array<Suffix>, rank: IntArray) {
    var r = 0
    rank[suffixes[0].index] = 0

    for (i in 1 until suffixes.size) {
        // If the current suffix's left or right rank is different from the previous suffix, then increase the rank.
        if (suffixes[i].leftRank != suffixes[i - 1].leftRank ||
            suffixes[i].rightRank != suffixes[i - 1].rightRank) {
            r++
        }

        rank[suffixes[i].index] = r
    }
}