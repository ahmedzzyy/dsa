package edu.practice.algorithms.graph

import edu.practice.datastructures.graph.Graph
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ShortestPathMultiStageGraphTest {

    @Test
    fun `multiStageGraphDP returns optimal path for a valid multi-stage graph`() {
        // Stages: 0 -> {1, 2} -> {3, 4} -> 5
        val stages = listOf(
            listOf(0),
            listOf(1, 2),
            listOf(3, 4),
            listOf(5)
        )

        val graph = Graph.create<Int>(isDirected = true)
        for (i in 0..5) graph.addVertex(i)
        // Graph definition:
        // Stage 1:
        graph.addEdge(0, 1, 2.0) // 0 -> 1 (cost=2)
        graph.addEdge(0, 2, 3.0) // 0 -> 2 (cost=3)
        // Stage 2:
        graph.addEdge(1, 3, 1.0) // 1 -> 3 (cost=1)
        graph.addEdge(1, 4, 2.0) // 1 -> 4 (cost=2)
        graph.addEdge(2, 3, 1.0) // 2 -> 3 (cost=1)
        graph.addEdge(2, 4, 3.0) // 2 -> 4 (cost=3)
        // Stage 3:
        graph.addEdge(3, 5, 4.0) // 3 -> 5 (cost=4)
        graph.addEdge(4, 5, 2.0) // 4 -> 5 (cost=2)

        val start = 0
        val end = 5

        // Expected path: 0 -> 1 -> 4 -> 5
        val (cost, path) = shortestPathOfMultiStageGraph(stages, graph, start, end)
        println("$cost -> $path")
        assertThat(cost).isEqualTo(6)
        assertThat(path).isEqualTo(listOf(0, 1, 4, 5))
    }

    @Test
    fun `multiStageGraphDP returns -1 when no path exists`() {
        val stages = listOf(
            listOf(0),
            listOf(1),
            listOf(2)
        )

        val graph = Graph.create<Int>(isDirected = true)
        for (i in 0..2) graph.addVertex(i)
        graph.addEdge(0, 1, 2.0)
        val start = 0
        val end = 2

        val (cost, path) = shortestPathOfMultiStageGraph(stages, graph, start, end)
        assertThat(cost).isEqualTo(-1)
        assertThat(path).isEqualTo(listOf(0))
    }

    @Test
    fun `multiStageGraphDP returns trivial path when start equals end`() {
        val stages = listOf(
            listOf(0)
        )
        val graph = Graph.create<Int>(isDirected = true)
        graph.addVertex(0)
        val start = 0
        val end = 0

        // The cost from the node to itself is 0.
        val (cost, path) = shortestPathOfMultiStageGraph(stages, graph, start, end)
        assertThat(cost).isEqualTo(0)
        assertThat(path).isEqualTo(listOf(0))
    }
}