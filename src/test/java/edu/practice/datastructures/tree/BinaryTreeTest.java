package edu.practice.datastructures.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class BinaryTreeTest {

    BinaryTree<Integer> integerBinaryTree;

    @BeforeEach
    void setup() {
        integerBinaryTree = new BinaryTree<>();
    }

    @Test
    void treeIsInitializedAsEmpty() {
        assertThat(integerBinaryTree.isEmpty()).isTrue();
        assertThat(integerBinaryTree.size()).isZero();
    }

    @Test
    void addElementsToTreeAndContains() {
        int[] items = {50, 60, 70};
        integerBinaryTree.add(items[0]);

        assertThat(integerBinaryTree.isEmpty()).isFalse();

        integerBinaryTree.add(items[1]);
        integerBinaryTree.add(items[2]);

        assertThat(integerBinaryTree.contains(items[1])).isTrue();
        assertThat(integerBinaryTree.height()).isEqualTo(1);
    }

    @Test
    void removeElementsToTree() {
        int[] items = {50, 60, 70};
        integerBinaryTree.add(items[0]);
        integerBinaryTree.add(items[1]);
        integerBinaryTree.add(items[2]);

        assertThat(integerBinaryTree.contains(items[1])).isTrue();
        assertThat(integerBinaryTree.remove(items[1])).isTrue();
        assertThat(integerBinaryTree.contains(items[1])).isFalse();
    }

    @Test
    void getsRootOfBinaryTree() {
        int[] items = {50, 60, 70, 80};
        for (int item : items) {
            integerBinaryTree.add(item);
        }

        int root = items[0];

        assertThat(integerBinaryTree.getRoot()).isEqualTo(root);
    }

    @Test
    void checksIfLeafNode() {
        int[] items = {50, 60, 70, 80, 90};
        for (int item : items) {
            integerBinaryTree.add(item);
        }

        int[] leafs = {80, 90};

        for (int leaf : leafs) {
            assertThat(integerBinaryTree.isLeaf(leaf)).isTrue();
        }
    }

    @Test
    void getsHeightOfBinaryTree() {
        int[] items = {50, 60, 70, 80, 90};
        for (int item : items) {
            integerBinaryTree.add(item);
        }

        int height = 2;

        assertThat(integerBinaryTree.height()).isEqualTo(height);
    }

    @Test
    void generateInorderTraversal() {
        int[] items = {50, 60, 70, 80, 90};
        for (int item : items) {
            integerBinaryTree.add(item);
        }

        int[] inorderTraversal = {80, 60, 90, 50, 70};
        ArrayList<Integer> inorderTraversalList = new ArrayList<>();
        for (int integer : inorderTraversal) {
            inorderTraversalList.add(integer);
        }

        assertThat(integerBinaryTree.inorderTraversal().equals(inorderTraversalList)).isTrue();
    }

    @Test
    void generatePreorderTraversal() {
        int[] items = {50, 60, 70, 80, 90};
        for (int item : items) {
            integerBinaryTree.add(item);
        }

        int[] preorderTraversal = {50, 60, 80, 90, 70};
        ArrayList<Integer> preorderTraversalList = new ArrayList<>();
        for (int integer : preorderTraversal) {
            preorderTraversalList.add(integer);
        }

        assertThat(integerBinaryTree.preorderTraversal().equals(preorderTraversalList)).isTrue();
    }

    @Test
    void generatePostorderTraversal() {
        int[] items = {50, 60, 70, 80, 90};
        for (int item : items) {
            integerBinaryTree.add(item);
        }

        int[] postorderTraversal = {80, 90, 60, 70, 50};
        ArrayList<Integer> postorderTraversalList = new ArrayList<>();
        for (int integer : postorderTraversal) {
            postorderTraversalList.add(integer);
        }

        System.out.println(integerBinaryTree.postorderTraversal());

        assertThat(integerBinaryTree.postorderTraversal().equals(postorderTraversalList)).isTrue();
    }

    @Test
    void traversalOnEmptyTreeEdgeCase() {
        assertThat(integerBinaryTree.inorderTraversal().isEmpty()).isTrue();
        assertThat(integerBinaryTree.preorderTraversal().isEmpty()).isTrue();
        assertThat(integerBinaryTree.postorderTraversal().isEmpty()).isTrue();
    }

    @Test
    void treeAfterRemovingRoot() {
        int[] items = {50, 60, 70};
        for (int item : items) {
            integerBinaryTree.add(item);
        }

        int root = items[0];
        assertThat(integerBinaryTree.remove(root)).isTrue();

        root = items[1];
        assertThat(integerBinaryTree.getRoot()).isEqualTo(root);
    }


}
