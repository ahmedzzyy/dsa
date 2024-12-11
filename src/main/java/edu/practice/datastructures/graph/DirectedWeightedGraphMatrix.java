package edu.practice.datastructures.graph;

import java.util.HashMap;
import java.util.Map;

public class DirectedWeightedGraphMatrix<V> {

    private Map<V, Integer> vertexMap; // Vertex Type -> Integer Index Type
    private double[][] weightedGraph;
    private int vertexCount;

    private static final double INF = Double.POSITIVE_INFINITY;
    private static final double LOAD_FACTOR_THRESHOLD = 0.7;

    DirectedWeightedGraphMatrix(int initialCapacity) {
        vertexMap = new HashMap<>();
        weightedGraph = new double[initialCapacity][initialCapacity];
        vertexCount = 0;

        // Initialize the adjacency matrix to no edges , i.e. all weights are infinity
        for (int i = 0; i < initialCapacity; i++) {
            for (int j = 0; j < initialCapacity; j++) {
                weightedGraph[i][j] = INF;
            }
        }
    }

    DirectedWeightedGraphMatrix() {
        this(5);
    }

    public boolean isEmpty() {
        return vertexCount == 0;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public boolean containsVertex(V v) {
        return vertexMap.containsKey(v);
    }

    private void resize() {
        double[][] newMatrix = new double[vertexCount * 2][vertexCount * 2];

        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix.length; j++) {
                newMatrix[i][j] = INF;
            }
        }

        for (int i = 0; i < vertexCount; i++) {
            System.arraycopy(weightedGraph[i], 0, newMatrix[i], 0, vertexCount);
        }

        weightedGraph = newMatrix;
    }

    public void addVertex(V vertex) {
        if ((double) vertexCount / weightedGraph.length >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        if (vertexMap.containsKey(vertex)) {
            return;
        }

        vertexMap.put(vertex, vertexCount);
        vertexCount++;
    }

    public void removeVertex(V vertex) {
        if (!vertexMap.containsKey(vertex)) {
            return;
        }

        int index = vertexMap.get(vertex);
        vertexMap.remove(vertex);

        // Shift rows and column in the matrix
        for (int i = 0; i < vertexCount; i++) {
            if (i > index) { // Shift rows up
                System.arraycopy(weightedGraph[i], 0, weightedGraph[i - 1], 0, vertexCount);
            }

            for (int j = index; j < vertexCount; j++) { // Shift columns left
                weightedGraph[i][j] = weightedGraph[i][j + 1];
            }
        }

        // Clear last rows and column
        int lastRowColIndex = vertexCount - 1;
        for (int i = 0; i < lastRowColIndex; i++) {
            weightedGraph[lastRowColIndex][i] = INF;
            weightedGraph[i][lastRowColIndex] = INF;
        }

        vertexCount--;

        // Remap the Vertices
        Map<V, Integer> newVertexMap = new HashMap<>();
        int count = 0;
        for (V v : vertexMap.keySet()) {
            newVertexMap.put(v, count++);
        }

        vertexMap = newVertexMap;
    }

    public void addEdge(V u, V v, double weight) {
        if (!vertexMap.containsKey(u) || !vertexMap.containsKey(v)) {
            throw new IllegalArgumentException("Both vertices must be in graph.");
        }

        int uIndex = vertexMap.get(u);
        int vIndex = vertexMap.get(v);

        weightedGraph[uIndex][vIndex] = weight;
    }

    public void removeEdge(V u, V v) {
        if (!vertexMap.containsKey(u) || !vertexMap.containsKey(v)) {
            throw new IllegalArgumentException("Both vertices must be in graph.");
        }

        int uIndex = vertexMap.get(u);
        int vIndex = vertexMap.get(v);

        weightedGraph[uIndex][vIndex] = INF;
    }

    public double getEdge(V u, V v) {
        if (!vertexMap.containsKey(u) || !vertexMap.containsKey(v)) {
            throw new IllegalArgumentException("Both vertices must be in graph.");
        }

        int uIndex = vertexMap.get(u);
        int vIndex = vertexMap.get(v);

        return weightedGraph[uIndex][vIndex] == INF ?
                -1 : weightedGraph[uIndex][vIndex];
    }
}
