package edu.practice.algorithms

import edu.practice.datastructures.graph.Graph

/**
 * Attempts to color the provided graph using up to [numberOfColors] colors such that
 * no two adjacent vertices share the same color.
 *
 * @param E Generic Type of the Vertices
 * @param graph [Graph] implementation to be colored
 * @param numberOfColors upper bound of the number of colors that can be used
 * @return `true` if [graph] can be successfully colored with at most [numberOfColors] provided;
 *          otherwise `false`
 */
fun <E> colorGraph(graph: Graph<E>, numberOfColors: Int): Boolean {
    if (graph.isEmpty()) return (numberOfColors == 0)

    val vertices = graph.getVertices().toList()
    val colors = mutableMapOf<Int, Int>() // Vertex Index -> Color ID

    fun isSafe(vertex: E, color: Int): Boolean {
        val neighbors = graph.getNeighbors(vertex)

        for (neighbor in neighbors) {
            val neighborIndex = vertices.indexOf(neighbor)
            if (colors[neighborIndex] == color) return false
        }

        return true
    }

    fun backTrack(currentIndex: Int): Boolean {
        if (colors.size == vertices.size) return true

        for (color in 1..numberOfColors) {
            if (isSafe(vertices[currentIndex], color)) {
                colors[currentIndex] = color

                if (backTrack(currentIndex + 1)) {
                    return true
                }

                colors.remove(key = currentIndex)
            }
        }

        return false
    }

    return backTrack(0)
}