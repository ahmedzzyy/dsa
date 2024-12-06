package edu.practice.datastructures.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class BinarySearchTreeTest {

    BinarySearchTree<Integer> integerBinarySearchTree;

    @BeforeEach
    void setup() {
        integerBinarySearchTree = new BinarySearchTree<>();
    }

    @Test
    void treeIsInitializedAsEmpty() {
        assertThat(integerBinarySearchTree.isEmpty()).isTrue();
        assertThat(integerBinarySearchTree.size()).isZero();
    }

    @Test
    void addElementsToTreeAndContains() {
        int[] items = {60, 50, 70};
        integerBinarySearchTree.add(items[0]);

        assertThat(integerBinarySearchTree.isEmpty()).isFalse();

        integerBinarySearchTree.add(items[1]);
        integerBinarySearchTree.add(items[2]);

        assertThat(integerBinarySearchTree.contains(items[1])).isTrue();
        assertThat(integerBinarySearchTree.height()).isEqualTo(1);
    }

    @Test
    void removeElementsToTree() {
        int[] items = {50, 60, 70};
        integerBinarySearchTree.add(items[0]);
        integerBinarySearchTree.add(items[1]);
        integerBinarySearchTree.add(items[2]);

        assertThat(integerBinarySearchTree.contains(items[1])).isTrue();
        integerBinarySearchTree.remove(items[1]);
        assertThat((integerBinarySearchTree.contains(items[1]))).isFalse();
    }

    @Test
    void getsRootOfBinaryTree() {
        int[] items = {50, 60, 70, 80};
        for (int item : items) {
            integerBinarySearchTree.add(item);
        }

        int root = items[0];

        assertThat(integerBinarySearchTree.getRoot()).isEqualTo(root);
    }

    @Test
    void checksIfLeafNode() {
        int[] items = {50, 30, 70, 10, 40, 60, 90};
        for (int item : items) {
            integerBinarySearchTree.add(item);
        }

        int[] leafs = {10, 40, 60, 90};

        for (int leaf : leafs) {
            assertThat(integerBinarySearchTree.isLeaf(leaf)).isTrue();
        }
    }

    @Test
    void getsHeightOfBinaryTree() {
        int[] items = {50, 30, 70, 10, 40, 60, 90};
        for (int item : items) {
            integerBinarySearchTree.add(item);
        }

        int height = 2;

        assertThat(integerBinarySearchTree.height()).isEqualTo(height);
    }

    @Test
    void generateInorderTraversal() {
        int[] items = {50, 30, 70, 10, 40, 60, 90};
        for (int item : items) {
            integerBinarySearchTree.add(item);
        }

        int[] inorderTraversal = {10, 30 , 40, 50, 60, 70, 90};
        ArrayList<Integer> inorderTraversalList = new ArrayList<>();
        for (int integer : inorderTraversal) {
            inorderTraversalList.add(integer);
        }

        assertThat(integerBinarySearchTree.inorderTraversal().equals(inorderTraversalList)).isTrue();
    }

    @Test
    void generatePreorderTraversal() {
        int[] items = {50, 30, 70, 10, 40, 60, 90};
        for (int item : items) {
            integerBinarySearchTree.add(item);
        }

        int[] preorderTraversal = {50, 30, 10, 40, 70, 60, 90};
        ArrayList<Integer> preorderTraversalList = new ArrayList<>();
        for (int integer : preorderTraversal) {
            preorderTraversalList.add(integer);
        }

        assertThat(integerBinarySearchTree.preorderTraversal().equals(preorderTraversalList)).isTrue();
    }

    @Test
    void generatePostorderTraversal() {
        int[] items = {50, 30, 70, 10, 40, 60, 90};
        for (int item : items) {
            integerBinarySearchTree.add(item);
        }

        int[] postorderTraversal = {10, 40, 30, 60, 90, 70, 50};
        ArrayList<Integer> postorderTraversalList = new ArrayList<>();
        for (int integer : postorderTraversal) {
            postorderTraversalList.add(integer);
        }

        System.out.println(integerBinarySearchTree.postorderTraversal());

        assertThat(integerBinarySearchTree.postorderTraversal().equals(postorderTraversalList)).isTrue();
    }

    @Test
    void traversalOnEmptyTreeEdgeCase() {
        assertThat(integerBinarySearchTree.inorderTraversal().isEmpty()).isTrue();
        assertThat(integerBinarySearchTree.preorderTraversal().isEmpty()).isTrue();
        assertThat(integerBinarySearchTree.postorderTraversal().isEmpty()).isTrue();
    }

    @Test
    void treeAfterRemovingRoot() {
        int[] items = {50, 60, 70};
        for (int item : items) {
            integerBinarySearchTree.add(item);
        }

        int root = items[0];
        integerBinarySearchTree.remove(root);

        root = items[1];
        assertThat(integerBinarySearchTree.getRoot()).isEqualTo(root);
    }


}
