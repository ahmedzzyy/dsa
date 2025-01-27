package edu.practice.algorithms.graph

import edu.practice.datastructures.graph.Graph
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MinimumSpanningTreeTest {

    private lateinit var graph: Graph<Int>

    @BeforeEach
    fun setup() {
        graph = Graph.create()
    }

    @Test
    fun mstGeneratedFromKruskalAlgorithm() {
        for (i in 0..6) {
            graph.addVertex(i)
        }
        graph.addEdge(0, 1, 7.0)
        graph.addEdge(1, 2, 6.0)
        graph.addEdge(2, 3, 5.0)
        graph.addEdge(3, 4, 2.0)
        graph.addEdge(4, 5, 1.0)
        graph.addEdge(5, 6, 1.0)
        graph.addEdge(6, 1, 4.0)
        graph.addEdge(5, 2, 3.0)

        assertThat(minimumSpanningTreeByKruskalAlgorithm(graph).getEdges())
            .containsExactlyInAnyOrder(
                0 to 1, 1 to 6, 5 to 6, 2 to 5, 4 to 5, 3 to 4
            )
    }
}