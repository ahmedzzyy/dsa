package edu.practice.algorithms.string

fun finiteAutomatonStringMatch(text: String, pattern: String): List<Int> {
    if (pattern.length > text.length) return emptyList()

    val matchIndicesList = mutableListOf<Int>()
    val transitionFunction = buildTransitionFunction(pattern)
    var lengthState = 0

    for (i in 0 until (text.length)) {
        lengthState = transitionFunction(lengthState, text[i])
        if (lengthState == (pattern.length)) {
            matchIndicesList.add(i - lengthState + 1)
        }
    }

    return matchIndicesList
}

private fun buildCharMapping(pattern: String): Map<Char, Int> {
    val uniqueChars = pattern.toSet().toList()

    return uniqueChars.withIndex().associate { (index, char) -> char to index }
}

private fun buildTransitionFunction(pattern: String): (Int, Char) -> Int {
    val patternLength = pattern.length

    val charToIndex = buildCharMapping(pattern)
    val transitionTable: Array<IntArray> = Array(patternLength + 1) {
        IntArray(charToIndex.size) { 0 }
    }

    for (currentState in 0 .. patternLength) {
        for ((currentChar, index) in charToIndex) {
            // Compute next state
            var nextState = currentState

            while (nextState > 0 && (nextState == patternLength || pattern[nextState] != currentChar)) {
                val prevChar = pattern[nextState - 1]
                val nextPossibleState = transitionTable[nextState - 1][charToIndex.getValue(prevChar)]
                if (nextPossibleState == nextState) {
                    nextState = 0
                    break
                }
                nextState = nextPossibleState
            }

            if (nextState < patternLength && pattern[nextState] == currentChar) nextState++

            transitionTable[currentState][index] = nextState
        }
    }

    fun transition(lengthState: Int, nextChar: Char): Int {
        val index = charToIndex[nextChar] ?: 0
        return transitionTable[lengthState][index]
    }

    return ::transition
}