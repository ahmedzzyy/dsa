package edu.practice.datastructures.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class AVLBinarySearchTreeTest {

    AVLBinarySearchTree<Integer> integerAVLTree;

    @BeforeEach
    void setup() {
        integerAVLTree = new AVLBinarySearchTree<>();
    }

    @Test
    void treeIsInitializedAsEmpty() {
        assertThat(integerAVLTree.isEmpty()).isTrue();
        assertThat(integerAVLTree.size()).isZero();
    }

    @Test
    void addElementsToTreeAndContains() {
        int[] items = {60, 50, 70};
        integerAVLTree.add(items[0]);

        assertThat(integerAVLTree.isEmpty()).isFalse();

        integerAVLTree.add(items[1]);
        integerAVLTree.add(items[2]);

        assertThat(integerAVLTree.contains(items[1])).isTrue();
        assertThat(integerAVLTree.height()).isOne();
    }

    @Test
    void treeBalancesOnInsertion() {
        int[] items = {30, 20, 10};  // Should perform right rotation
        for (int item : items) {
            integerAVLTree.add(item);
        }

        assertThat(integerAVLTree.getRoot()).isEqualTo(20);
        assertThat(integerAVLTree.height()).isOne();
    }

    @Test
    void removeElementsToTreeAndReBalance() {
        int[] items = {50, 30, 70, 20, 40};
        for (int item : items) {
            integerAVLTree.add(item);
        }

        integerAVLTree.remove(30);

        assertThat(integerAVLTree.contains(30)).isFalse();
        assertThat(integerAVLTree.height()).isEqualTo(2);
    }

    @Test
    void getsRootOfBinaryTree() {
        int[] items = {50, 30, 70, 20, 40};
        for (int item : items) {
            integerAVLTree.add(item);
        }

        assertThat(integerAVLTree.getRoot()).isEqualTo(50);
    }

    @Test
    void checksIfLeafNode() {
        int[] items = {50, 30, 70, 20, 40};
        for (int item : items) {
            integerAVLTree.add(item);
        }

        int[] leafs = {20, 40, 70};
        for (int leaf : leafs) {
            assertThat(integerAVLTree.isLeaf(leaf)).isTrue();
        }
    }

    @Test
    void getsHeightOfBinaryTree() {
        int[] items = {50, 30, 70, 20, 40};
        for (int item : items) {
            integerAVLTree.add(item);
        }

        assertThat(integerAVLTree.height()).isEqualTo(2);
    }

    @Test
    void generateInorderTraversal() {
        int[] items = {50, 30, 70, 20, 40};
        for (int item : items) {
            integerAVLTree.add(item);
        }

        int[] inorderTraversal = {20, 30, 40, 50, 70};
        ArrayList<Integer> inorderTraversalList = new ArrayList<>();
        for (int integer : inorderTraversal) {
            inorderTraversalList.add(integer);
        }

        assertThat(integerAVLTree.inorderTraversal().equals(inorderTraversalList)).isTrue();
    }

    @Test
    void generatePreorderTraversal() {
        int[] items = {50, 30, 70, 20, 40};
        for (int item : items) {
            integerAVLTree.add(item);
        }

        int[] preorderTraversal = {50, 30, 20, 40, 70};
        ArrayList<Integer> preorderTraversalList = new ArrayList<>();
        for (int integer : preorderTraversal) {
            preorderTraversalList.add(integer);
        }

        assertThat(integerAVLTree.preorderTraversal().equals(preorderTraversalList)).isTrue();
    }

    @Test
    void generatePostorderTraversal() {
        int[] items = {50, 30, 70, 20, 40};
        for (int item : items) {
            integerAVLTree.add(item);
        }

        int[] postorderTraversal = {20, 40, 30, 70, 50};
        ArrayList<Integer> postorderTraversalList = new ArrayList<>();
        for (int integer : postorderTraversal) {
            postorderTraversalList.add(integer);
        }

        assertThat(integerAVLTree.postorderTraversal().equals(postorderTraversalList)).isTrue();
    }

    @Test
    void traversalOnEmptyTreeEdgeCase() {
        assertThat(integerAVLTree.inorderTraversal().isEmpty()).isTrue();
        assertThat(integerAVLTree.preorderTraversal().isEmpty()).isTrue();
        assertThat(integerAVLTree.postorderTraversal().isEmpty()).isTrue();
    }

    @Test
    void treeAfterRemovingRoot() {
        int[] items = {50, 30, 70, 20, 40};
        for (int item : items) {
            integerAVLTree.add(item);
        }

        integerAVLTree.remove(50);

        assertThat(integerAVLTree.getRoot()).isEqualTo(30);
    }

    @Test
    void disallowsDuplicateInserts() {
        integerAVLTree.add(10);
        integerAVLTree.add(10);

        assertThat(integerAVLTree.size()).isOne();
    }

    @Test
    void removingNonExistentElement() {
        integerAVLTree.add(10);
        integerAVLTree.remove(20);

        assertThat(integerAVLTree.size()).isOne();
    }
}
