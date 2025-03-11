package edu.practice.algorithms

import edu.practice.datastructures.graph.Graph
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GraphColoringTest {

    @Test
    fun `colorGraph returns true for an empty graph when numberOfColors is 0`() {
        val graph: Graph<Int> = Graph.create()
        assertThat(colorGraph(graph, 0)).isTrue()
    }

    @Test
    fun `colorGraph returns false for an empty graph when numberOfColors is greater than 0`() {
        val graph: Graph<Int> = Graph.create()
        assertThat(colorGraph(graph, 1)).isFalse()
    }

    @Test
    fun `colorGraph returns true for a graph with a single vertex`() {
        val graph: Graph<String> = Graph.create()
        graph.addVertex("A")
        assertThat(colorGraph(graph, 1)).isTrue()
    }

    @Test
    fun `colorGraph returns false for a two-vertex graph with an edge and one color`() {
        val graph: Graph<String> = Graph.create()
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addEdge("A", "B")

        assertThat(colorGraph(graph, 1)).isFalse()
    }

    @Test
    fun `colorGraph returns true for a two-vertex graph with an edge and two colors`() {
        val graph: Graph<String> = Graph.create()
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addEdge("A", "B")

        assertThat(colorGraph(graph, 2)).isTrue()
    }

    @Test
    fun `colorGraph returns false for a triangle graph with two colors`() {
        val graph: Graph<String> = Graph.create()
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addVertex("C")
        graph.addEdge("A", "B")
        graph.addEdge("B", "C")
        graph.addEdge("C", "A")

        assertThat(colorGraph(graph, 2)).isFalse()
    }

    @Test
    fun `colorGraph returns true for a triangle graph with three colors`() {
        val graph: Graph<String> = Graph.create()
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addVertex("C")
        graph.addEdge("A", "B")
        graph.addEdge("B", "C")
        graph.addEdge("C", "A")

        assertThat(colorGraph(graph, 3)).isTrue()
    }
}