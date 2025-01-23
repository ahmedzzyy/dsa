package edu.practice.datastructures.graph

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UndirectedGraphListTest {

    private lateinit var nameGraph: Graph<String>

    @BeforeEach
    fun setup() {
        nameGraph = Graph.create() // Default is an undirected graph with adjacency list implementation
    }

    @Test
    fun graphIsInitializedAsEmpty() {
        assertThat(nameGraph.isEmpty()).isTrue()
        assertThat(nameGraph.size()).isZero()
    }

    @Test
    fun addVerticesToGraph() {
        val names = arrayOf("AAA", "BBB", "CCC")

        for (name in names) {
            nameGraph.addVertex(name)
        }

        assertThat(nameGraph.size()).isEqualTo(names.size)

        for (name in names) {
            assertThat(nameGraph.containsVertex(name)).isTrue()
        }
    }

    @Test
    fun createEdgeBetweenVertices() {
        val name1 = "A"
        val name2 = "B"
        nameGraph.addVertex(name1)
        nameGraph.addVertex(name2)

        nameGraph.addEdge(name1, name2, 2.5)
        assertThat(nameGraph.hasEdge(name1, name2)).isTrue()
        assertThat(nameGraph.hasEdge(name2, name1)).isTrue()

        assertThatThrownBy { nameGraph.addEdge("CCC", name1, 5.0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Both vertices must be in graph.")
    }

    @Test
    fun removeVertex() {
        val names = arrayOf("AAA", "BBB", "CCC")

        for (name in names) {
            nameGraph.addVertex(name)
        }

        assertThat(nameGraph.size()).isEqualTo(names.size)

        nameGraph.removeVertex(names[1])
        assertThat(nameGraph.size()).isEqualTo(names.size - 1)
        assertThat(nameGraph.containsVertex(names[1])).isFalse()
    }

    @Test
    fun removeEdgeBetweenVertices() {
        val name1 = "A"
        val name2 = "B"
        nameGraph.addVertex(name1)
        nameGraph.addVertex(name2)

        nameGraph.addEdge(name1, name2, 2.5)
        assertThat(nameGraph.getWeight(name1, name2)).isEqualTo(2.5)
        assertThat(nameGraph.getWeight(name2, name1)).isEqualTo(2.5)

        nameGraph.removeEdge(name1, name2)
        assertThat(nameGraph.hasEdge(name1, name2)).isFalse()
        assertThat(nameGraph.hasEdge(name2, name1)).isFalse()

        assertThatThrownBy { nameGraph.removeEdge("CCC", name1) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Both vertices must be in graph.")
    }
    
    @Test
    fun getNeighborsOfVertex() {
        val names = arrayOf("AAA", "BCD", "EFG")
        for (name in names) {
            nameGraph.addVertex(name)
        }

        nameGraph.addEdge(names[0], names[1], 2.5)
        nameGraph.addEdge(names[0], names[2], 2.5)
        assertThat(nameGraph.getNeighbors(names[0])).containsExactlyInAnyOrder(names[1], names[2])
    }

    @Test
    fun clearResetsGraph() {
        val names = arrayOf("AAA", "BCD", "EFG")
        for (name in names) {
            nameGraph.addVertex(name)
        }

        nameGraph.clear()

        assertThat(nameGraph.isEmpty()).isTrue()
        assertThat(nameGraph.size()).isZero()
    }

    @Test
    fun getEdgesVerticesOfGraph() {
        val names = arrayOf("AAA", "BCD", "EFG")
        for (name in names) {
            nameGraph.addVertex(name)
        }

        nameGraph.addEdge(names[0], names[1], 2.5)
        nameGraph.addEdge(names[1], names[2], 2.2)

        assertThat(nameGraph.getVertices()).containsExactlyInAnyOrder(names[0], names[1], names[2])
        assertThat(nameGraph.getEdges()).containsExactlyInAnyOrder(
            Pair(names[0], names[1]), Pair(names[1], names[2])
        )
    }

    @Test
    fun getDegreeOfTheVertex() {
        val names = arrayOf("AAA", "BCD", "EFG")
        for (name in names) {
            nameGraph.addVertex(name)
        }

        nameGraph.addEdge(names[0], names[1], 3.6)
        nameGraph.addEdge(names[2], names[0], 2.5)

        assertThat(nameGraph.getDegree(names[0])).isEqualTo(2)
    }

    @Test
    fun generateBFSTraversal() {
        val names = arrayOf("AAA", "BCD", "EFG", "HIJ", "KLM", "NOP")
        for (name in names) {
            nameGraph.addVertex(name)
        }

        nameGraph.addEdge(names[0], names[1]) // 1 w.r.t. 0th
        nameGraph.addEdge(names[2], names[0]) // 2 to 0 - 1 w.r.t. 0th
        nameGraph.addEdge(names[3], names[5]) // initially not connected with 0 later 3 w.r.t. 0th
        nameGraph.addEdge(names[1], names[3]) // 2 w.r.t. 0th
        nameGraph.addEdge(names[2], names[4]) // 2 w.r.t. 0th

        assertThat(nameGraph.bfsTraversal(names[0])).containsExactlyInAnyOrder(
            names[0] to 0.0,
            names[1] to 1.0,
            names[2] to 1.0,
            names[3] to 2.0,
            names[4] to 2.0,
            names[5] to 3.0
        )
    }

    @Test
    fun generateDFSTraversal() {
        val names = arrayOf("AAA", "BCD", "EFG", "HIJ", "KLM", "NOP")
        for (name in names) {
            nameGraph.addVertex(name)
        }

        nameGraph.addEdge(names[0], names[1])
        nameGraph.addEdge(names[2], names[0])
        nameGraph.addEdge(names[3], names[5])
        nameGraph.addEdge(names[1], names[3])
        nameGraph.addEdge(names[2], names[4])

        val resultMap = mapOf(
            // starts from 3rd  i.e. "HIJ"
            names[3] to (1 to 12),
            names[1] to (2 to 9), // 3 <-> 1
            names[0] to (3 to 8), // 1 <-> 0
            names[2] to (4 to 7), // 0 <-> 2
            names[4] to (5 to 6), // 2 <-> 4
            names[5] to (10 to 11) // 3 <-> 5
        )

        assertThat(nameGraph.dfsTraversal()).isEqualTo(resultMap)
    }
}