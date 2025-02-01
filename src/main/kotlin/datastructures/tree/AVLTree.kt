package edu.practice.datastructures.tree

class AVLTree<E: Comparable<E>>: Tree<E> {

    private data class Node<E>(
        var data: E,
        var left: Node<E>? = null,
        var right: Node<E>? = null,
        var height: Int = 0
    )

    private var root: Node<E>? = null
    private var size = 0

    override fun insert(item: E) {
        fun addItemToNode(item: E, currentNode: Node<E>?): Node<E> {
            currentNode?.let {
                when {
                    item < it.data -> it.left = addItemToNode(item, it.left)
                    item > it.data -> it.right = addItemToNode(item, it.right)
                    else -> return it // Duplicate Values disallowed
                }
            } ?: return Node(item).also { size++ }

            updateHeight(currentNode)
            return reBalance(currentNode)
        }

        root = addItemToNode(item, root)
    }

    override fun delete(item: E): Boolean {
        if (root == null) return false

        val originalSize = size
        root = removeNodeWithItem(item, root)

        return originalSize != size
    }

    private fun findSmallestNode(node: Node<E>?): Node<E>? {
        return node?.left?.let { findSmallestNode(it) } ?: node
    }

    private fun removeNodeWithItem(item: E, currentNode: Node<E>?): Node<E>? {
        currentNode ?: return null

        when {
            item < currentNode.data -> currentNode.left = removeNodeWithItem(item, currentNode.left)

            item > currentNode.data -> currentNode.right = removeNodeWithItem(item, currentNode.right)

            else -> { // Node's data == item to remove
                when {
                    // No children or one child
                    currentNode.left == null || currentNode.right == null -> {
                        size--
                        return currentNode.left ?: currentNode.right
                    }
                    else -> { // Both Children
                        val successorNode = findSmallestNode(currentNode.right)
                        currentNode.data = successorNode?.data ?: return null
                        currentNode.right = removeNodeWithItem(successorNode.data, currentNode.right)
                    }
                }
            }
        }

        updateHeight(currentNode)
        return reBalance(currentNode)
    }

    private fun reBalance(node: Node<E>): Node<E> {
        val balanceFactor = balanceFactor(node)

        val newRoot = when {
            // Left-Right Case
            balanceFactor > 1 && (node.left?.let { balanceFactor(it) } ?: 0) < 0 -> leftRightRotate(node)
            // Left-Left Case
            balanceFactor > 1 -> rightRotate(node)
            // Right-Left Case
            balanceFactor < -1 && (node.right?.let { balanceFactor(it) } ?: 0) > 0 -> rightLeftRotate(node)
            // Left-Right Case
            balanceFactor < -1 -> leftRotate(node)
            else -> node // No rotation needed
        }

        updateHeight(newRoot)
        return newRoot
    }

    private fun leftRightRotate(node: Node<E>): Node<E> {
        node.left = node.left?.let { leftRotate(it) }
        return rightRotate(node)
    }

    private fun rightLeftRotate(node: Node<E>): Node<E> {
        node.right = node.right?.let { rightRotate(it) }
        return leftRotate(node)
    }

    private fun leftRotate(node: Node<E>): Node<E> {
        val newRoot = node.right ?: return node
        val newRootLeftChild = newRoot.left

        newRoot.left = node
        node.right = newRootLeftChild

        updateHeight(node)
        updateHeight(newRoot)

        return newRoot
    }

    private fun rightRotate(node: Node<E>): Node<E> {
        val newRoot = node.left ?: return node
        val newRootRightChild = newRoot.right

        newRoot.right = node
        node.left = newRootRightChild

        updateHeight(node)
        updateHeight(newRoot)

        return newRoot
    }

    override fun root(): E? = root?.data

    override fun preOrderTraversal(): List<E> {
        val result: MutableList<E> = mutableListOf()

        fun preorder(currentNode: Node<E>?) {
            if (currentNode == null) return

            result.add(currentNode.data)
            preorder(currentNode.left)
            preorder(currentNode.right)
        }
        preorder(root)

        return result
    }

    override fun inOrderTraversal(): List<E> {
        val result: MutableList<E> = mutableListOf()

        fun inorder(currentNode: Node<E>?) {
            if (currentNode == null) return

            inorder(currentNode.left)
            result.add(currentNode.data)
            inorder(currentNode.right)
        }
        inorder(root)
        println(result)

        return result
    }

    override fun postOrderTraversal(): List<E> {
        val result: MutableList<E> = mutableListOf()

        fun postorder(currentNode: Node<E>?) {
            if (currentNode == null) return

            postorder(currentNode.left)
            postorder(currentNode.right)
            result.add(currentNode.data)
        }
        postorder(root)

        return result
    }

    override fun height(): Int = root?.height ?: -1

    private fun <T> findNodeAndApply(item: E, currentNode: Node<E>?, action: (Node<E>?) -> T): T {
        currentNode?.let {
            when {
                item < it.data -> return findNodeAndApply(item, it.left, action)
                item > it.data -> return findNodeAndApply(item, it.right, action)
                else -> return action(it) // Node to apply on
            }
        } ?: return action(null)
    }

    override fun contains(item: E): Boolean {
        return findNodeAndApply(item, root) {
                node -> node != null && node.data == item
        }
    }

    override fun isLeaf(item: E): Boolean {
        return findNodeAndApply(item, root) {
                node -> node?.left == null && node?.right == null
        }
    }

    private fun balanceFactor(node: Node<E>): Int = (node.left?.height ?: -1) - (node.right?.height ?: -1)
    
    private fun updateHeight(node: Node<E>) {
        node.height = 1 + maxOf(node.left?.height ?: -1, node.right?.height ?: -1)
    }

    override fun size(): Int = size

    override fun isEmpty(): Boolean = (size == 0)
}