package edu.practice.algorithms

import edu.practice.datastructures.graph.Graph
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HamiltonianCycleTest {

    @Test
    fun `findHamiltonianCycles returns empty list for an empty graph`() {
        val graph = Graph.create<Int>()
        val cycles = findHamiltonianCycles(graph)
        assertThat(cycles).isEmpty()
    }

    @Test
    fun `findHamiltonianCycles returns single cycle for graph with one vertex`() {
        val graph = Graph.create<Int>()
        graph.addVertex(1)
        graph.addEdge(1, 1)
        val cycles = findHamiltonianCycles(graph)

        assertThat(cycles).hasSize(1)
        assertThat(cycles[0]).containsExactly(1)
    }

    @Test
    fun `findHamiltonianCycles returns valid path for a two-vertex graph with one directed edge`() {
        val vertices = listOf(1, 2)
        val edges = setOf(Pair(1, 2))
        val graph = Graph.create<Int>()

        for (vertex in vertices) graph.addVertex(vertex)
        for ((u, v) in edges) graph.addEdge(u, v)

        val cycles = findHamiltonianCycles(graph)
        assertThat(cycles).containsExactly(listOf(1, 2))
    }

    @Test
    fun `findHamiltonianCycles returns empty path for a non-complete graph`() {
        val vertices = listOf("A", "B", "C")
        val edges = setOf(Pair("A", "B"), Pair("B", "C"))
        val graph = Graph.create<String>()

        for (vertex in vertices) graph.addVertex(vertex)
        for ((u, v) in edges) graph.addEdge(u, v)

        val cycles = findHamiltonianCycles(graph)
        assertThat(cycles).isEmpty()
    }

    @Test
    fun `findHamiltonianCycles returns all valid Hamiltonian paths for a complete graph`() {
        val vertices = listOf(1, 2, 3)
        val edges = mutableSetOf<Pair<Int, Int>>()
        for (u in vertices) {
            for (v in vertices) {
                if (u != v) {
                    edges.add(Pair(u, v))
                }
            }
        }

        val graph = Graph.create<Int>()
        for (vertex in vertices) graph.addVertex(vertex)
        for ((u, v) in edges) graph.addEdge(u, v)

        val cycles = findHamiltonianCycles(graph)
        // excluding duplicate cycles
        // number of cycles = (number of vertices - 1)!
        // = (3-1)! = 2 Hamiltonian cycles
        assertThat(cycles).hasSize(2)
        cycles.forEach { path ->
            assertThat(path).containsExactlyInAnyOrderElementsOf(vertices)
        }
    }
}