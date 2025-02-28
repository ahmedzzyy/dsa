package edu.practice.algorithms

/**
 * Data class representing a job with an id, deadline, and profit.
 *
 * @property id a unique identifier for the job.
 * @property deadline the last time slot by which the job must be completed.
 * @property profit the profit earned by completing the job.
 */
data class Job(val id: String, val deadline: Int, val profit: Int)

/**
 * Schedules a set of jobs to maximize total profit using a greedy algorithm.
 *
 * @param jobs a list of [Job] objects, each containing an identifier, deadline, and profit.
 * @return a [Pair] where the first element is the count of jobs successfully scheduled and the second element is the
 * total profit obtained from these jobs.
 *
 * @sample
 * ```
 * val (count, profit) = jobSequencing(jobs)
 * ```
 */
fun jobSequencing(jobs: List<Job>): Pair<Int, Int> {
    if (jobs.isEmpty()) return Pair(0, 0)

    val sortedJobs = jobs.sortedByDescending { it.profit }

    val maxDeadline = sortedJobs.maxOf { it.deadline }

    val slots = BooleanArray(maxDeadline + 1)

    var count = 0
    var totalProfit = 0

    for (job in sortedJobs) {
        for (j in job.deadline downTo 1) {
            if (!slots[j]) {
                slots[j] = true
                count++
                totalProfit += job.profit
                break
            }
        }
    }

    return Pair(count, totalProfit)
}