package edu.practice.algorithms.graph

import edu.practice.datastructures.graph.Graph
import edu.practice.datastructures.graph.GraphType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AllPairShortestPathTest {

    private lateinit var intGraph: Graph<Int>

    @BeforeEach
    fun setup() {
        intGraph = Graph.create(type = GraphType.ADJ_MATRIX, isDirected = true)
    }

    @Test
    fun shortestPathsGeneratedByFloydWarshallAlgorithm() {
        for (i in 0 until 5) {
            intGraph.addVertex(i)
        }

        intGraph.addEdge(0, 1, 1.0)
        intGraph.addEdge(1, 2, 1.0)
        intGraph.addEdge(2, 4, 1.0)
        intGraph.addEdge(3, 2, 1.0)

        assertThat(shortestPathUsingFloydWarshallAlgorithm(intGraph)).isEqualTo(expectedResultMap())
    }

    @Test
    fun negativeEdgeHandledByFloydWarshallAlgorithm() {
        for (i in 0 until 5) {
            intGraph.addVertex(i)
        }

        intGraph.addEdge(0, 1, 1.0)
        intGraph.addEdge(1, 3, -5.0)
        intGraph.addEdge(2, 1, 3.0)
        intGraph.addEdge(3, 2, 1.0)
        intGraph.addEdge(4, 0, 3.0)

        val shortestDistanceMap = shortestPathUsingFloydWarshallAlgorithm(intGraph)
        val verticesInCycle = setOf(1, 2, 3)
        for ((edge, pathWeight) in shortestDistanceMap) {
            val (vertexFrom, vertexTo) = edge
            if (vertexFrom in verticesInCycle || vertexTo in verticesInCycle) {
                assertThat(pathWeight).isEqualTo(Double.NEGATIVE_INFINITY)
            }
        }
    }

    private fun expectedResultMap(): Map<Pair<Int, Int>, Double> {
        val inf = Double.POSITIVE_INFINITY
        val distances = listOf(
            listOf(0.0, 1.0, 2.0, inf, 3.0),
            listOf(inf, 0.0, 1.0, inf, 2.0),
            listOf(inf, inf, 0.0, inf, 1.0),
            listOf(inf, inf, 1.0, 0.0, 2.0),
            listOf(inf, inf, inf, inf, 0.0)
        )

        return buildMap {
            for (i in distances.indices) {
                for (j in distances[i].indices) {
                    put(i to j, distances[i][j])
                }
            }
        }
    }
}