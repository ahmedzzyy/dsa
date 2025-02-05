package edu.practice.algorithms.sort

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SortingTest {

    private lateinit var array: Array<Int>

    @BeforeEach
    fun setup() {
        array = arrayOf(42, 17, 8, 99, 3, 56, 23, 78, 12, 65)
    }

    // Stability of the algorithm is not checked
    // TODO(Show an example in the README of the directory)
    @Test
    fun bubbleSortAlgorithmCorrectlySortsElement() {
        bubbleSort(array)
        assertThat(array).isSorted
    }

    @Test
    fun selectionSortAlgorithmCorrectlySortsElement() {
        selectionSort(array)
        assertThat(array).isSorted
    }
}