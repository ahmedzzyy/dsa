package edu.practice.algorithms

import kotlin.math.abs

/**
 * Solves the N-Queens problem by placing `n` queens on an `n x n` chessboard such that
 * no two queens threaten each other.
 *
 * Each solution is represented as a list of strings, where each string corresponds to a row
 * of the chessboard. In the representation, a 'Q' indicates the presence of a queen and a '.'
 * indicates an empty cell.
 *
 * @param n the number of queens to place as well as the dimensions of the chessboard.
 * @return a list containing all valid board configurations. Each configuration is a list of strings.
 */
fun placeNQueens(n: Int): List<List<String>> {
    if (n <= 0) return emptyList()

    val solutions = mutableListOf<List<String>>()
    val board = IntArray(n) { -1 }

    fun isSafe(row: Int, col: Int): Boolean {
        for (i in 0 until row) {
            if (board[i] == col || abs(board[i] - col) == row - i) {
                return false
            }
        }
        return true
    }

    fun backTrack(row: Int) {
        if (row == n) {
            solutions.add(buildBoard(board, n))
            return
        }

        for (col in 0 until n) {
            if (isSafe(row, col)) {
                board[row] = col // Queen place
                backTrack(row + 1)
                board[row] = -1 // BackTrack by removing queen
            }
        }
    }

    backTrack(0)

    return solutions
}

private fun buildBoard(board: IntArray, n: Int): List<String> {
    return board.map { column: Int ->
        CharArray(n) { if (it == column) 'Q' else '.' }.concatToString()
    }
}