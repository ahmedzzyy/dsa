package edu.practice.algorithms

fun subsetsWithSum(sum: Int, numbers: List<Int>): List<List<Int>> {
    if (numbers.isEmpty()) return emptyList()

    val solutions = mutableListOf<List<Int>>()
    val subset = mutableListOf<Int>()

    fun backTrack(currentIndex: Int, currentSum: Int = 0) {
        if (currentSum == sum) {
            solutions.add(subset.toList())
            return
        }

        for (index in currentIndex until numbers.size) {
            val nextChoice = numbers[index]
            if (currentSum + nextChoice <= sum) {
                subset.add(nextChoice)
                backTrack(index + 1, currentSum + nextChoice)
                subset.removeAt(subset.lastIndex) // Remove after backtracked
            }
        }
    }

    backTrack(0)

    return solutions
}