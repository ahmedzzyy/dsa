package edu.practice.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BinarySearchTest {

    private lateinit var list: List<Int>

    @BeforeEach
    fun setup() {
        list = listOf(3, 7, 12, 18, 25, 31, 45, 52, 60, 71, 89, 95)
    }

    @Test
    fun iterativeBinarySearchCorrectlyFindsNumbers() {
        assertThat(list).isSorted

        val indicesToCheckFor = setOf(5, 7, 9)
        for (index in indicesToCheckFor) {
            assertThat(binarySearch(list, list[index])).isEqualTo(index)
        }
    }

    @Test
    fun iterativeBinarySearchCorrectlyHandlesNonExistentTargets() {
        assertThat(list).isSorted
        assertThat(binarySearch(list, 27)).isNull()
    }

    @Test
    fun recursiveBinarySearchCorrectlyFindsNumbers() {
        assertThat(list).isSorted

        val indicesToCheckFor = setOf(5, 7, 9)
        for (index in indicesToCheckFor) {
            assertThat(binarySearchRecursive(list, list[index])).isEqualTo(index)
        }
    }

    @Test
    fun recursiveBinarySearchCorrectlyHandlesNonExistentTargets() {
        assertThat(list).isSorted
        assertThat(binarySearchRecursive(list, 27)).isNull()
    }
}