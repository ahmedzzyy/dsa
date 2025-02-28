package edu.practice.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JobSequencingTest {

    @Test
    fun `jobSequencing returns (0, 0) when input is empty`() {
        val jobs = emptyList<Job>()
        val (count, totalProfit) = jobSequencing(jobs)
        assertThat(count).isEqualTo(0)
        assertThat(totalProfit).isEqualTo(0)
    }

    @Test
    fun `jobSequencing returns single job when only one is provided`() {
        val jobs = listOf(Job("A", 2, 100))
        val (count, totalProfit) = jobSequencing(jobs)
        assertThat(count).isEqualTo(1)
        assertThat(totalProfit).isEqualTo(100)
    }

    @Test
    fun `jobSequencing schedules jobs`() {
        val jobs = listOf(
            Job("A", 1, 50),
            Job("B", 2, 20),
            Job("C", 3, 30)
        )
        // Sorted order by profit: A (50), C (30), B (20)
        // A -> slot 1, C -> slot 3, B -> slot 2.
        // Total profit: 50 + 30 + 20 = 100
        val (count, totalProfit) = jobSequencing(jobs)
        assertThat(count).isEqualTo(3)
        assertThat(totalProfit).isEqualTo(100)
    }

    @Test
    fun `jobSequencing selection for overlapping jobs`() {
        val jobs = listOf(
            Job("A", 2, 100),
            Job("B", 1, 19),
            Job("C", 2, 27),
            Job("D", 1, 25),
            Job("E", 3, 15)
        )
        // Sorted order by profit: A (100), C (27), D (25), B (19), E (15)
        // Scheduling:
        // - "A" takes slot 2.
        // - "C" checks slot 2 (taken), then slot 1 (free) → scheduled.
        // - "D" and "B" cannot be scheduled (slot 1 is taken).
        // - "E" takes slot 3.
        // Expected: 3 jobs scheduled with total profit 100 + 27 + 15 = 142.
        val (count, totalProfit) = jobSequencing(jobs)
        assertThat(count).isEqualTo(3)
        assertThat(totalProfit).isEqualTo(142)
    }

    @Test
    fun `jobSequencing with same deadlines`() {
        val jobs = listOf(
            Job("A", 2, 50),
            Job("B", 2, 60),
            Job("C", 1, 20)
        )
        // Sorted order by profit: B (60), A (50), C (20)
        // Scheduling:
        // - "B" takes slot 2.
        // - "A" checks slot 2 (taken), then slot 1 (free) → scheduled.
        // - "C" cannot be scheduled (slot 1 is taken).
        // Expected: 2 jobs scheduled with total profit 60 + 50 = 110.
        val (count, totalProfit) = jobSequencing(jobs)
        assertThat(count).isEqualTo(2)
        assertThat(totalProfit).isEqualTo(110)
    }
}