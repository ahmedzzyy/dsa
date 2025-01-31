package edu.practice.algorithms.graph

import edu.practice.datastructures.graph.Graph
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SingleSourceShortestPathTest {

    private lateinit var intGraph: Graph<Int>

    @BeforeEach
    fun setup() {
        intGraph = Graph.create(isDirected = true)
    }

    @Test
    fun shortestPathsGeneratedByBellmanFordAlgorithm() {
        for (i in 0 until 9) {
            intGraph.addVertex(i)
        }

        intGraph.addEdge(0, 1, 1.0)
        intGraph.addEdge(1, 2, 1.0)
        intGraph.addEdge(2, 4, 1.0)
        intGraph.addEdge(4, 3, -3.0)
        intGraph.addEdge(3, 2, 1.0)
        intGraph.addEdge(1, 5, 4.0)
        intGraph.addEdge(1, 6, 4.0)
        intGraph.addEdge(5, 6, 5.0)
        intGraph.addEdge(6, 7, 4.0)
        intGraph.addEdge(5, 7, 3.0)

        val nInf = Double.NEGATIVE_INFINITY

        val resultPathMap = mapOf(
            0 to 0.0,
            1 to 1.0,
            2 to nInf,
            3 to nInf,
            4 to nInf,
            5 to 5.0,
            6 to 5.0,
            7 to 8.0,
            8 to Double.POSITIVE_INFINITY
        )

        assertThat(shortestPathUsingBellmanFordAlgorithm(intGraph, 0)).isEqualTo(resultPathMap)
    }

    @Test
    fun shortestPathsGeneratedByDijkstraAlgorithm() {
        for (i in 0 until 9) {
            intGraph.addVertex(i)
        }

        intGraph.addEdge(0, 1, 1.0)
        intGraph.addEdge(1, 2, 1.0)
        intGraph.addEdge(2, 4, 1.0)
        intGraph.addEdge(3, 2, 1.0)
        intGraph.addEdge(1, 5, 4.0)
        intGraph.addEdge(1, 6, 4.0)
        intGraph.addEdge(5, 6, 5.0)
        intGraph.addEdge(6, 7, 4.0)
        intGraph.addEdge(5, 7, 3.0)

        val resultPathMap = mapOf(
            0 to 0.0,
            1 to 1.0,
            2 to 2.0,
            3 to Double.POSITIVE_INFINITY,
            4 to 3.0,
            5 to 5.0,
            6 to 5.0,
            7 to 8.0,
            8 to Double.POSITIVE_INFINITY
        )

        assertThat(shortestPathUsingDijkstraAlgorithm(intGraph, 0)).isEqualTo(resultPathMap)
    }

    @Test
    fun errorThrownWhenSourceVertexAbsentInBellmanFordAndDijkstraAlgorithm() {
        assertThatThrownBy { shortestPathUsingBellmanFordAlgorithm(intGraph, 0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Source vertex not present in the given graph.")

        assertThatThrownBy { shortestPathUsingDijkstraAlgorithm(intGraph, 0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Source vertex not present in the given graph.")
    }
}