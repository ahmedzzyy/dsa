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
    fun topologicalSortGeneratedForGraph() {
        val names = arrayOf("AAA", "BCD", "EFG", "HIJ", "KLM", "NOP")
        for (name in names) {
            nameGraph.addVertex(name)
        }

        nameGraph.addEdge(names[0], names[1])
        nameGraph.addEdge(names[2], names[0])
        nameGraph.addEdge(names[3], names[5])
        nameGraph.addEdge(names[1], names[3])
        nameGraph.addEdge(names[2], names[4])

//        starts from 3rd  i.e. "HIJ"
//        names[3] to (1 to 4),
//        names[5] to (2 to 3), // 3 -> 5
//        names[0] to (5 to 8), // standalone 0 from `for`
//        names[1] to (6 to 7), // 0 -> 1
//        names[4] to (9 to 10), // standalone 4 from `for` (if 2 occurred earlier, it would be different)
//        names[2] to (11 to 12) // standalone 2 from `for`

        assertThat(topologicalSort(nameGraph)).containsExactly(
            "EFG", "KLM", "AAA", "BCD", "HIJ", "NOP"
            // These are sorted descending by finishing time
        )
    }

    @Test
    fun topologicalSortThrowsErrorForUnDirectedOrCyclicGraph() {
        val names = arrayOf("AAA", "BCD", "EFG", "HIJ", "KLM", "NOP")
        for (name in names) {
            nameGraph.addVertex(name)
        }

        nameGraph.addEdge(names[1], names[0])
        nameGraph.addEdge(names[0], names[2])
        nameGraph.addEdge(names[2], names[1])
        nameGraph.addEdge(names[0], names[3])
        nameGraph.addEdge(names[3], names[4])

        assertThatThrownBy { topologicalSort(nameGraph) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Topological sort is only valid for directed acyclic graphs (DAGs). The input graph contains a cycle.")

        val undirectedGraph = Graph.create<Int>()
        assertThatThrownBy { topologicalSort(undirectedGraph) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Topological sort is only valid for directed acyclic graphs (DAGs). The input graph is undirected.")
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