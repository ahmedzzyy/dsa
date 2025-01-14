package edu.practice.datastructures.tree

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class BinaryTreeTest {

    private lateinit var integerBinaryTree: Tree<Int>

    @BeforeEach
    fun setup() {
        integerBinaryTree = BinaryTree()
    }

    @Test
    fun treeIsInitializedAsEmpty() {
        assertThat(integerBinaryTree.isEmpty()).isTrue()
        assertThat(integerBinaryTree.size()).isZero()
    }

    @Test
    fun addElementsToTreeAndContains() {
        val items = intArrayOf(50, 60, 70)
        integerBinaryTree.insert(items[0])

        assertThat(integerBinaryTree.isEmpty()).isFalse()

        integerBinaryTree.insert(items[1])
        integerBinaryTree.insert(items[2])

        assertThat(integerBinaryTree.contains(items[1])).isTrue()
        assertThat(integerBinaryTree.height()).isEqualTo(1)
    }

    @Test
    fun removeElementsToTree() {
        val items = intArrayOf(50, 60, 70)
        integerBinaryTree.insert(items[0])
        integerBinaryTree.insert(items[1])
        integerBinaryTree.insert(items[2])

        assertThat(integerBinaryTree.contains(items[1])).isTrue()
        assertThat(integerBinaryTree.delete(items[1])).isTrue()
        assertThat(integerBinaryTree.contains(items[1])).isFalse()
    }

    @Test
    fun getsRootOfBinaryTree() {
        val items = intArrayOf(50, 60, 70, 80)
        for (item in items) {
            integerBinaryTree.insert(item)
        }

        val root = items[0]

        assertThat(integerBinaryTree.root()).isEqualTo(root)
    }

    @Test
    fun checksIfLeafNode() {
        val items = intArrayOf(50, 60, 70, 80, 90)
        for (item in items) {
            integerBinaryTree.insert(item)
        }

        val leafs = intArrayOf(80, 90)

        for (leaf in leafs) {
            assertThat(integerBinaryTree.isLeaf(leaf)).isTrue()
        }
    }

    @Test
    fun getsHeightOfBinaryTree() {
        val items = intArrayOf(50, 60, 70, 80, 90)
        for (item in items) {
            integerBinaryTree.insert(item)
        }

        val height = 2

        assertThat(integerBinaryTree.height()).isEqualTo(height)
    }

    @Test
    fun generateInorderTraversal() {
        val items = intArrayOf(50, 60, 70, 80, 90)
        for (item in items) {
            integerBinaryTree.insert(item)
        }

        val inorderTraversal = intArrayOf(80, 60, 90, 50, 70)
        val inorderTraversalList = ArrayList<Int>()
        for (integer in inorderTraversal) {
            inorderTraversalList.add(integer)
        }

        assertThat(integerBinaryTree.inOrderTraversal() == inorderTraversalList).isTrue()
    }

    @Test
    fun generatePreorderTraversal() {
        val items = intArrayOf(50, 60, 70, 80, 90)
        for (item in items) {
            integerBinaryTree.insert(item)
        }

        val preorderTraversal = intArrayOf(50, 60, 80, 90, 70)
        val preorderTraversalList = ArrayList<Int>()
        for (integer in preorderTraversal) {
            preorderTraversalList.add(integer)
        }

        assertThat(integerBinaryTree.preOrderTraversal() == preorderTraversalList).isTrue()
    }

    @Test
    fun generatePostorderTraversal() {
        val items = intArrayOf(50, 60, 70, 80, 90)
        for (item in items) {
            integerBinaryTree.insert(item)
        }

        val postorderTraversal = intArrayOf(80, 90, 60, 70, 50)
        val postorderTraversalList = ArrayList<Int>()
        for (integer in postorderTraversal) {
            postorderTraversalList.add(integer)
        }

        assertThat(integerBinaryTree.postOrderTraversal() == postorderTraversalList).isTrue()
    }

    @Test
    fun traversalOnEmptyTreeEdgeCase() {
        assertThat(integerBinaryTree.inOrderTraversal().isEmpty()).isTrue()
        assertThat(integerBinaryTree.preOrderTraversal().isEmpty()).isTrue()
        assertThat(integerBinaryTree.postOrderTraversal().isEmpty()).isTrue()
    }

    @Test
    fun treeAfterRemovingRoot() {
        val items = intArrayOf(50, 60, 70)
        for (item in items) {
            integerBinaryTree.insert(item)
        }

        var root = items[0]
        assertThat(integerBinaryTree.delete(root)).isTrue()

        root = items[1]
        assertThat(integerBinaryTree.root()).isEqualTo(root)
    }
}