package edu.practice.datastructures.graph

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DirectedGraphMatrixTest {

    private lateinit var nameGraph: Graph<String>

    @BeforeEach
    fun setup() {
        nameGraph = Graph.create(Graph.Companion.GRAPH.ADJ_MATRIX, directed = true)
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
        assertThat(nameGraph.getWeight(name1, name2)).isEqualTo(2.5)
        assertThat(nameGraph.hasEdge(name2, name1)).isFalse()

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

        nameGraph.removeEdge(name1, name2)
        assertThat(nameGraph.hasEdge(name1, name2)).isFalse()

        assertThatThrownBy { nameGraph.removeEdge("CCC", name1) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Both vertices must be in graph.")
    }

    @Test
    fun graphResizesAfterLoadExceed() {
        val names = arrayOf("A", "B", "C", "D", "E", "F", "G")

        for (name in names) {
            nameGraph.addVertex(name)
        }

        assertThat(nameGraph.size()).isEqualTo(names.size)
        assertThat(nameGraph.containsVertex(names[names.size - 1])).isTrue()
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
    fun getDegreeInAndOutOfTheVertex() {
        val names = arrayOf("AAA", "BCD", "EFG")
        for (name in names) {
            nameGraph.addVertex(name)
        }

        nameGraph.addEdge(names[0], names[1], 3.6)
        nameGraph.addEdge(names[0], names[2], 1.8)
        nameGraph.addEdge(names[2], names[0], 2.5)

        assertThat(nameGraph.getInDegree(names[0])).isEqualTo(1)
        assertThat(nameGraph.getOutDegree(names[0])).isEqualTo(2)
    }
}