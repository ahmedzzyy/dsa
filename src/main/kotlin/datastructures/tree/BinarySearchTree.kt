package edu.practice.datastructures.tree

class BinarySearchTree<E: Comparable<E>>: Tree<E> {

    private data class Node<E>(var data: E, var left: Node<E>? = null, var right: Node<E>? = null)

    private var root: Node<E>? = null
    private var size = 0

    override fun insert(item: E) {
        fun addItemToNode(item: E, currentNode: Node<E>?): Node<E> {
            currentNode?.let {
                when {
                    item < it.data -> it.left = addItemToNode(item, it.left)
                    else -> it.right = addItemToNode(item, it.right)
                }
                return it
            } ?: return Node(item)
        }

        root = addItemToNode(item, root)
        size++
    }

    override fun delete(item: E): Boolean {
        if (root == null) return false

        root = removeNodeWithItem(item, root)
        size--
        return true
    }

    private fun findSmallestNode(node: Node<E>?): Node<E>? {
        return node?.left?.let { findSmallestNode(it) } ?: node
    }

    private fun removeNodeWithItem(item: E, currentNode: Node<E>?): Node<E>? {
        currentNode ?: return null

        when {
            item < currentNode.data -> {
                currentNode.left = removeNodeWithItem(item, currentNode.left)
                return currentNode
            }

            item > currentNode.data -> {
                currentNode.right = removeNodeWithItem(item, currentNode.right)
                return currentNode
            }

            else -> { // Node's data == item to remove
                when {
                    currentNode.left == null && currentNode.right == null -> return null // No children
                    currentNode.left == null -> return currentNode.right // Only Right Child
                    currentNode.right == null -> return currentNode.left // Only Left Child

                    else -> { // Both Children
                        val successorNode = findSmallestNode(currentNode.right)
                        currentNode.data = successorNode?.data ?: return null
                        currentNode.right = removeNodeWithItem(successorNode.data, currentNode.right)
                        return currentNode
                    }
                }
            }
        }
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

    override fun height(): Int = calculateHeightOfNode(root)

    private fun calculateHeightOfNode(node: Node<E>?): Int {
        if (node == null) { // Base Condition
            return -1
        }

        val leftSubTreeHeight = calculateHeightOfNode(node.left)
        val rightSubTreeHeight = calculateHeightOfNode(node.right)

        return maxOf(leftSubTreeHeight, rightSubTreeHeight) + 1 // 1 for current node
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

    private fun <T> findNodeAndApply(item: E, currentNode: Node<E>?, action: (Node<E>?) -> T): T {
        currentNode?.let {
            when {
                item < it.data -> return findNodeAndApply(item, it.left, action)
                item > it.data -> return findNodeAndApply(item, it.right, action)
                else -> return action(it) // Node to apply on
            }
        } ?: return action(null)
    }

    override fun size(): Int = size

    override fun isEmpty(): Boolean = (size == 0)
}