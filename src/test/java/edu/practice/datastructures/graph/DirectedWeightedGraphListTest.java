package edu.practice.datastructures.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DirectedWeightedGraphListTest {

    DirectedWeightedGraphList<String> nameGraph;

    @BeforeEach
    void setup() {
        nameGraph = new DirectedWeightedGraphList<>();
    }

    @Test
    void graphIsInitializedAsEmpty() {
        assertThat(nameGraph.isEmpty()).isTrue();
        assertThat(nameGraph.getVertexCount()).isZero();
    }

    @Test
    void addVerticesToGraph() {
        String[] names = {"AAA", "BBB", "CCC"};

        for (String name : names) {
            nameGraph.addVertex(name);
        }

        assertThat(nameGraph.getVertexCount()).isEqualTo(names.length);

        for (String name : names) {
            assertThat(nameGraph.containsVertex(name)).isTrue();
        }
    }

    @Test
    void createEdgeBetweenVertices() {
        String name1 = "A"; String name2 = "B";
        nameGraph.addVertex(name1);
        nameGraph.addVertex(name2);

        nameGraph.addEdge(name1, name2, 2.5);
        assertThat(nameGraph.getEdge(name1, name2)).isEqualTo(2.5);
        assertThat(nameGraph.getEdge(name2, name1)).isEqualTo(-1);

        assertThatThrownBy(() -> nameGraph.addEdge("CCC", name1, 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Both vertices must be in graph.");
    }

    @Test
    void removeVertex() {
        String[] names = {"AAA", "BBB", "CCC"};

        for (String name : names) {
            nameGraph.addVertex(name);
        }

        assertThat(nameGraph.getVertexCount()).isEqualTo(names.length);

        nameGraph.removeVertex(names[1]);
        assertThat(nameGraph.getVertexCount()).isEqualTo(names.length - 1);
        assertThat(nameGraph.containsVertex(names[1])).isFalse();
    }

    @Test
    void removeEdgeBetweenVertices() {
        String name1 = "A"; String name2 = "B";
        nameGraph.addVertex(name1);
        nameGraph.addVertex(name2);

        nameGraph.addEdge(name1, name2, 2.5);
        assertThat(nameGraph.getEdge(name1, name2)).isEqualTo(2.5);

        nameGraph.removeEdge(name1, name2);
        assertThat(nameGraph.getEdge(name1, name2)).isEqualTo(-1);

        assertThatThrownBy(() -> nameGraph.removeEdge("CCC", name1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Both vertices must be in graph.");
    }
}
