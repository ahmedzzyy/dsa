package edu.practice.algorithms

/**
 * Computes all subsets of the given list of integers that sum exactly to the specified target.
 *
 * @param sum The total sum the subset should amount to
 * @param numbers [List] of [Int] to form subsets from
 * @return List of subsets which amount to [sum]
 */
// "form" subsets "from" He he :-)
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