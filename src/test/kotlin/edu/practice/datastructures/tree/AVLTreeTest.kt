package edu.practice.datastructures.tree

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class AVLTreeTest {

    private lateinit var integerAVLTree: Tree<Int>

    @BeforeEach
    fun setup() {
        integerAVLTree = AVLTree()
    }

    @Test
    fun treeIsInitializedAsEmpty() {
        assertThat(integerAVLTree.isEmpty()).isTrue()
        assertThat(integerAVLTree.size()).isZero()
    }

    @Test
    fun addElementsToTreeAndContains() {
        val items = intArrayOf(60, 50, 70)
        integerAVLTree.insert(items[0])

        assertThat(integerAVLTree.isEmpty()).isFalse()

        integerAVLTree.insert(items[1])
        integerAVLTree.insert(items[2])

        assertThat(integerAVLTree.contains(items[1])).isTrue()
        assertThat(integerAVLTree.height()).isOne()
    }

    @Test
    fun treeBalancesOnInsertion() {
        val items = intArrayOf(30, 20, 10) // Should perform right rotation
        for (item in items) {
            integerAVLTree.insert(item)
        }

        assertThat(integerAVLTree.root()).isEqualTo(20)
        assertThat(integerAVLTree.height()).isOne()
    }

    @Test
    fun removeElementsToTreeAndReBalance() {
        val items = intArrayOf(50, 30, 70, 20, 40)
        for (item in items) {
            integerAVLTree.insert(item)
        }

        integerAVLTree.delete(30)

        assertThat(integerAVLTree.contains(30)).isFalse()
        assertThat(integerAVLTree.height()).isEqualTo(2)
    }

    @Test
    fun getsRootOfBinaryTree() {
        val items = intArrayOf(50, 30, 70, 20, 40)
        for (item in items) {
            integerAVLTree.insert(item)
        }

        assertThat(integerAVLTree.root()).isEqualTo(50)
    }

    @Test
    fun checksIfLeafNode() {
        val items = intArrayOf(50, 30, 70, 20, 40)
        for (item in items) {
            integerAVLTree.insert(item)
        }

        val leafs = intArrayOf(20, 40, 70)
        for (leaf in leafs) {
            assertThat(integerAVLTree.isLeaf(leaf)).isTrue()
        }
    }

    @Test
    fun getsHeightOfBinaryTree() {
        val items = intArrayOf(50, 30, 70, 20, 40)
        for (item in items) {
            integerAVLTree.insert(item)
        }

        assertThat(integerAVLTree.height()).isEqualTo(2)
    }

    @Test
    fun generateInorderTraversal() {
        val items = intArrayOf(50, 30, 70, 20, 40)
        for (item in items) {
            integerAVLTree.insert(item)
        }

        val inorderTraversal = intArrayOf(20, 30, 40, 50, 70)
        val inorderTraversalList = ArrayList<Int>()
        for (integer in inorderTraversal) {
            inorderTraversalList.add(integer)
        }

        assertThat(integerAVLTree.inOrderTraversal() == inorderTraversalList).isTrue()
    }

    @Test
    fun generatePreorderTraversal() {
        val items = intArrayOf(50, 30, 70, 20, 40)
        for (item in items) {
            integerAVLTree.insert(item)
        }

        val preorderTraversal = intArrayOf(50, 30, 20, 40, 70)
        val preorderTraversalList = ArrayList<Int>()
        for (integer in preorderTraversal) {
            preorderTraversalList.add(integer)
        }

        assertThat(integerAVLTree.preOrderTraversal() == preorderTraversalList).isTrue()
    }

    @Test
    fun generatePostorderTraversal() {
        val items = intArrayOf(50, 30, 70, 20, 40)
        for (item in items) {
            integerAVLTree.insert(item)
        }

        val postorderTraversal = intArrayOf(20, 40, 30, 70, 50)
        val postorderTraversalList = ArrayList<Int>()
        for (integer in postorderTraversal) {
            postorderTraversalList.add(integer)
        }

        assertThat(integerAVLTree.postOrderTraversal() == postorderTraversalList).isTrue()
    }

    @Test
    fun traversalOnEmptyTreeEdgeCase() {
        assertThat(integerAVLTree.inOrderTraversal().isEmpty()).isTrue()
        assertThat(integerAVLTree.preOrderTraversal().isEmpty()).isTrue()
        assertThat(integerAVLTree.postOrderTraversal().isEmpty()).isTrue()
    }

    @Test
    fun treeAfterRemovingRoot() {
        val items = intArrayOf(50, 30, 70, 20, 40)
        for (item in items) {
            integerAVLTree.insert(item)
        }

        integerAVLTree.delete(50)

        assertThat(integerAVLTree.root()).isEqualTo(30)
    }

    @Test
    fun disallowsDuplicateInserts() {
        integerAVLTree.insert(10)
        integerAVLTree.insert(10)

        assertThat(integerAVLTree.size()).isOne()
    }

    @Test
    fun removingNonExistentElement() {
        integerAVLTree.insert(10)
        integerAVLTree.delete(20)

        assertThat(integerAVLTree.size()).isOne()
    }
}