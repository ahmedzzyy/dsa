package edu.practice.algorithms

/**
 * Selects a maximal set of non-overlapping activities using a greedy algorithm.
 *
 * Developer Note: The start and finish times are currently represented as `Int`.
 * The original intent was to use a native timestamp type, but Kotlin (JVM) doesn’t provide one out of the box.
 *
 * @param activities A list of triples where each entry consists of an activity identifier (`K`),
 *                   a start time (`Int`), and a finish time (`Int`).
 * @param K The type representing the unique identifier for each activity.
 * @return a list of identifiers (`K`) corresponding to the selected non-overlapping activities.
 */
fun <K> selectActivities(activities: List<Triple<K, Int, Int>>): List<K> {
    val sortedActivities = activities.sortedBy { it.third }
    val selected = mutableListOf<K>()

    var lastFinishTime = 0
    for ((id, start, finish) in sortedActivities) {
        if (start >= lastFinishTime) {
            selected.add(id)
            lastFinishTime = finish
        }
    }

    return selected
}