package edu.practice.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ActivitySelectionTest {

    @Test
    fun `selectActivities returns empty list when input is empty`() {
        val activities = emptyList<Triple<String, Int, Int>>()
        val result = selectActivities(activities)
        assertThat(result).isEmpty()
    }

    @Test
    fun `selectActivities returns single activity when only one is provided`() {
        val activities = listOf(Triple("A", 1, 3))
        val result = selectActivities(activities)
        assertThat(result).containsExactly("A")
    }

    @Test
    fun `selectActivities returns all activities when none overlap`() {
        val activities = listOf(
            Triple("A", 1, 2),
            Triple("B", 3, 4),
            Triple("C", 5, 6)
        )

        val result = selectActivities(activities)
        assertThat(result).containsExactly("A", "B", "C")
    }

    @Test
    fun `selectActivities selects maximal set for overlapping activities`() {
        val activities = listOf(
            Triple("A", 1, 4),
            Triple("B", 3, 5),
            Triple("C", 0, 6),
            Triple("D", 5, 7),
            Triple("E", 3, 8),
            Triple("F", 5, 9),
            Triple("G", 6, 10),
            Triple("H", 8, 11),
            Triple("I", 8, 12),
            Triple("J", 2, 13),
            Triple("K", 12, 14)
        )

        val result = selectActivities(activities)
        assertThat(result).containsExactly("A", "D", "H", "K")
    }

    @Test
    fun `selectActivities handles activities with same finish times`() {
        val activities = listOf(
            Triple("A", 1, 4),
            Triple("B", 2, 4),
            Triple("C", 3, 4),
            Triple("D", 4, 5)
        )
        val result = selectActivities(activities)
        // Only one among A, B, C should be selected, followed by "D"
        assertThat(result.size).isEqualTo(2)
        assertThat(result).contains("D")
    }

    @Test
    fun `selectActivities sorts unsorted input and selects correctly`() {
        val activities = listOf(
            Triple("B", 3, 5),
            Triple("A", 1, 4),
            Triple("D", 5, 7),
            Triple("C", 0, 6)
        )
        val result = selectActivities(activities)
        assertThat(result).containsExactly("A", "D")
    }
}