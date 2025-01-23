package edu.practice.datastructures.tree

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RedBlackTreeTest {

    private lateinit var integerRedBlackTree: Tree<Int>

    @BeforeEach
    fun setup() {
        integerRedBlackTree = RedBlackTree()
    }

    @Test
    fun treeIsInitializedAsEmpty() {
        assertThat(integerRedBlackTree.isEmpty()).isTrue()
        assertThat(integerRedBlackTree.size()).isZero()
    }

    @Test
    fun addElementsToTreeAndContains() {
        val items = intArrayOf(60, 50, 70)
        integerRedBlackTree.insert(items[0])

        assertThat(integerRedBlackTree.isEmpty()).isFalse()

        integerRedBlackTree.insert(items[1])
        integerRedBlackTree.insert(items[2])

        assertThat(integerRedBlackTree.contains(items[1])).isTrue()
        assertThat(integerRedBlackTree.height()).isEqualTo(1)
    }

    @Test
    fun removeElementsToTree() {
        val items = intArrayOf(50, 60, 70)
        integerRedBlackTree.insert(items[0])
        integerRedBlackTree.insert(items[1])
        integerRedBlackTree.insert(items[2])

        assertThat(integerRedBlackTree.contains(items[1])).isTrue()
        assertThat(integerRedBlackTree.delete(items[1])).isTrue()
        assertThat(integerRedBlackTree.contains(items[1])).isFalse()
    }

    @Test
    fun getsRootOfBinaryTree() {
        val items = intArrayOf(50, 60, 70, 80)
        for (item in items) {
            integerRedBlackTree.insert(item)
        }

        assertThat(integerRedBlackTree.root()).isEqualTo(60)
    }

    @Test
    fun checksIfLeafNode() {
        val items = intArrayOf(50, 30, 70, 10, 40, 60, 90)
        for (item in items) {
            integerRedBlackTree.insert(item)
        }

        val leafs = intArrayOf(10, 40, 60, 90)

        for (leaf in leafs) {
            assertThat(integerRedBlackTree.isLeaf(leaf)).isTrue()
        }
    }

    @Test
    fun getsHeightOfBinaryTree() {
        val items = intArrayOf(50, 30, 70, 10, 40, 60, 90)
        for (item in items) {
            integerRedBlackTree.insert(item)
        }

        val height = 2

        assertThat(integerRedBlackTree.height()).isEqualTo(height)
    }

    @Test
    fun generateInorderTraversal() {
        val items = intArrayOf(50, 30, 70, 10, 40, 60, 90)
        for (item in items) {
            integerRedBlackTree.insert(item)
        }

        val inorderTraversal = intArrayOf(10, 30 , 40, 50, 60, 70, 90)
        val inorderTraversalList = ArrayList<Int>()
        for (integer in inorderTraversal) {
            inorderTraversalList.add(integer)
        }

        assertThat(integerRedBlackTree.inOrderTraversal() == inorderTraversalList).isTrue()
    }

    @Test
    fun generatePreorderTraversal() {
        val items = intArrayOf(50, 30, 70, 10, 40, 60, 90)
        for (item in items) {
            integerRedBlackTree.insert(item)
        }

        val preorderTraversal = intArrayOf(50, 30, 10, 40, 70, 60, 90)
        val preorderTraversalList = ArrayList<Int>()
        for (integer in preorderTraversal) {
            preorderTraversalList.add(integer)
        }

        assertThat(integerRedBlackTree.preOrderTraversal() == preorderTraversalList).isTrue()
    }

    @Test
    fun generatePostorderTraversal() {
        val items = intArrayOf(50, 30, 70, 10, 40, 60, 90)
        for (item in items) {
            integerRedBlackTree.insert(item)
        }

        val postorderTraversal = intArrayOf(10, 40, 30, 60, 90, 70, 50)
        val postorderTraversalList = ArrayList<Int>()
        for (integer in postorderTraversal) {
            postorderTraversalList.add(integer)
        }

        assertThat(integerRedBlackTree.postOrderTraversal() == postorderTraversalList).isTrue()
    }

    @Test
    fun traversalOnEmptyTreeEdgeCase() {
        assertThat(integerRedBlackTree.inOrderTraversal().isEmpty()).isTrue()
        assertThat(integerRedBlackTree.preOrderTraversal().isEmpty()).isTrue()
        assertThat(integerRedBlackTree.postOrderTraversal().isEmpty()).isTrue()
    }

    @Test
    fun treeAfterRemovingRoot() {
        val items = intArrayOf(50, 60, 70)
        for (item in items) {
            integerRedBlackTree.insert(item)
        }

        var root = items[0]
        assertThat(integerRedBlackTree.delete(root)).isTrue()

        root = items[1]
        assertThat(integerRedBlackTree.root()).isEqualTo(root)
    }
}