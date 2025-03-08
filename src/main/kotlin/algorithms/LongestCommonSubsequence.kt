package edu.practice.algorithms

enum class Direction {
    DIAGONAL, // match
    UP, // value taken from above
    LEFT // value taken from left
}

typealias DirectionTable = Array<Array<Direction?>>

/**
 * Computes the LCS table and backtracking table.
 *
 * @param x First string.
 * @param y Second string.
 * @return A pair where the first element is the cost table and the second element is the direction table.
 */
fun longestCommonSubsequence(x: String, y: String): Pair<Array<IntArray>, DirectionTable>  {
    val xLength = x.length
    val yLength = y.length

    val costTable = Array(xLength + 1) { IntArray(yLength + 1) { 0 } }
    val directionTable = Array(xLength) { arrayOfNulls<Direction>(yLength) }

    for (i in 1..xLength) {
        for (j in 1..yLength) {
            if (x[i - 1] == y[j - 1]) {
                costTable[i][j] = costTable[i - 1][j - 1] + 1
                directionTable[i - 1][j - 1] = Direction.DIAGONAL // Match
            } else if (costTable[i - 1][j] >= costTable[i][j - 1]) {
                costTable[i][j] = costTable[i - 1][j]
                directionTable[i - 1][j - 1] = Direction.UP
            } else {
                costTable[i][j] = costTable[i][j - 1]
                directionTable[i - 1][j - 1] = Direction.LEFT
            }
        }
    }

    return Pair(costTable, directionTable)
}

/**
 * Reconstructs the LCS string by backtracking through the direction table.
 *
 * @param directionTable The direction table from lcsLength.
 * @param x The original string (used to pick matching characters).
 * @param i Current row index.
 * @param j Current column index.
 * @return The longest common subsequence as a string.
 */

fun buildLCS(directionTable: DirectionTable, x: String, i: Int, j: Int): String {
    return if (i == 0 || j == 0) ""
    else when (directionTable[i - 1][j - 1]) {
        Direction.DIAGONAL -> buildLCS(directionTable, x, i - 1, j - 1) + x[i - 1]
        Direction.UP -> buildLCS(directionTable, x, i - 1, j)
        Direction.LEFT -> buildLCS(directionTable, x, i, j - 1)
        null -> ""
    }
}