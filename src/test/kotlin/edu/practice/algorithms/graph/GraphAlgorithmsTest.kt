package edu.practice.algorithms.graph

import edu.practice.datastructures.graph.Graph
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GraphAlgorithmsTest {

    private lateinit var nameGraph: Graph<String>

    @BeforeEach
    fun setup() {
        nameGraph = Graph.create(isDirected = true)
    }

    @Test
    fun findStronglyConnectedComponentsByKosarajuAlgorithm() {
        val names = arrayOf("AAA", "BCD", "EFG", "HIJ", "KLM", "NOP")
        for (name in names) {
            nameGraph.addVertex(name)
        }

        nameGraph.addEdge(names[1], names[0])
        nameGraph.addEdge(names[0], names[2])
        nameGraph.addEdge(names[2], names[1])
        nameGraph.addEdge(names[0], names[3])
        nameGraph.addEdge(names[3], names[4])

        assertThat(stronglyConnectedComponentsUsingKosaraju(nameGraph))
            .containsExactlyInAnyOrder(
                setOf(names[0], names[1], names[2]), setOf(names[3]), setOf(names[4]), setOf(names[5])
            )
    }

    @Test
    fun kosarajuAlgorithmThrowsErrorForUndirectedGraph() {
        val undirectedGraph = Graph.create<Int>()
        assertThatThrownBy { stronglyConnectedComponentsUsingKosaraju(undirectedGraph) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Kosaraju's algorithm requires a directed graph. Provided graph is not directed.")
    }
}