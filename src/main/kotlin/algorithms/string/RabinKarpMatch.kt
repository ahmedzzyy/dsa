package edu.practice.algorithms.string

fun rabinKarpMatch(text: String, pattern: String): List<Int> {
    if (pattern.length > text.length) return emptyList()

    val matchIndicesList = mutableListOf<Int>()

    // ---- Developer NOTE ----
    // Values will differ from language to language.
    // These values are just what I used for a basic implementation.
    // CONSTANTS
    val radix = 65536L // 2 ^ 16 : Covers full UTF-16 range
    val primeMod = 1000000007L // 10^9 + 7 : Large prime to reduce hash collisions
    var highestPlaceValue = 1L
    for (i in 1 until pattern.length) {
        highestPlaceValue = (highestPlaceValue * radix) % primeMod
    }
    highestPlaceValue %= primeMod

    var patternHash = 0L
    var currentHash = 0L

    for (i in 0 until (pattern.length)) {
        patternHash = ((radix * patternHash) + pattern[i].code) % primeMod
        currentHash = ((radix * currentHash) + text[i].code) % primeMod
    }

    for (shift in 0..(text.length - pattern.length)) {
        if (patternHash == currentHash) {
            if (pattern == text.substring(shift, shift + pattern.length)) {
                matchIndicesList.add(shift)
            }
        }

        // Rolling Hash Forward (if not at the last window)
        if (shift < (text.length - pattern.length)) {
            currentHash = ((
                    radix * (currentHash - text[shift].code * highestPlaceValue)
                            + text[shift + pattern.length].code
                    ) % primeMod)
        }
        if (currentHash < 0) {  // Ensure positive hash
            currentHash += primeMod
        }
    }

    return matchIndicesList
}