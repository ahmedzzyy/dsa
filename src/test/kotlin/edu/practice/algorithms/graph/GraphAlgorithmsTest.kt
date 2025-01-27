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

        //    /--> 0 (5/8) --> 1 (6/7) --> 3 (1/4) --> 5 (2/3)
        // 2 (11/12)
        //    \--> 4 (9/10)

        // starts from 3rd i.e. "HIJ" for normal topological sort
        assertThat(topologicalSort(nameGraph)).containsExactly(
            "EFG", "KLM", "AAA", "BCD", "HIJ", "NOP"
            // These are sorted descending by finishing time
        )

        assertThat(topologicalSortUsingKahnsAlgorithm(nameGraph)).containsExactly(
            "EFG", "AAA", "KLM", "BCD", "HIJ", "NOP"
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
        assertThatThrownBy { topologicalSortUsingKahnsAlgorithm(nameGraph) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Topological sort is only valid for directed acyclic graphs (DAGs). The input graph contains a cycle.")

        val undirectedGraph = Graph.create<Int>()
        assertThatThrownBy { topologicalSort(undirectedGraph) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Topological sort is only valid for directed acyclic graphs (DAGs). The input graph is undirected.")
        assertThatThrownBy { topologicalSortUsingKahnsAlgorithm(undirectedGraph) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Topological sort is only valid for directed acyclic graphs (DAGs). The input graph is undirected.")
    }

    @Test
    fun findStronglyConnectedComponents() {
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
        assertThat(stronglyConnectedComponentsUsingTarjans(nameGraph))
            .containsExactlyInAnyOrder(
                setOf(names[0], names[1], names[2]), setOf(names[3]), setOf(names[4]), setOf(names[5])
            )
    }

    @Test
    fun kosarajuTarjanAlgorithmThrowsErrorForUndirectedGraph() {
        val undirectedGraph = Graph.create<Int>()
        assertThatThrownBy { stronglyConnectedComponentsUsingKosaraju(undirectedGraph) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Kosaraju's algorithm requires a directed graph. Provided graph is not directed.")
        assertThatThrownBy { stronglyConnectedComponentsUsingTarjans(undirectedGraph) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Tarjan's algorithm requires a directed graph. Provided graph is not directed.")
    }
}