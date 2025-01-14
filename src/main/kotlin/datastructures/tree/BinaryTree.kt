package edu.practice.datastructures.tree

class BinaryTree<E>: Tree<E> {

    private val tree: MutableList<E> = mutableListOf()
    private var size: Int = 0

    override fun insert(item: E) {
        tree.add(item)
        size++
    }

    override fun delete(item: E): Boolean {
        val deleted = tree.remove(item)
        size--
        return deleted
    }

    override fun root(): E {
        return tree.first()
    }

    override fun isLeaf(item: E): Boolean {
        val index = tree.indexOf(item)
        if (index == -1) {
            return false
        }

        val hasNoLeftChild = getLeftChildIndex(index) > size
        val hasNoRightChild = getRightChildIndex(index) > size

        return (hasNoLeftChild && hasNoRightChild)
    }

    override fun preOrderTraversal(): List<E> {
        val result: MutableList<E> = mutableListOf()

        fun preorder(indexFrom: Int) {
            if (indexFrom >= size) return

            result.add(tree[indexFrom])
            preorder(getLeftChildIndex(indexFrom))
            preorder(getRightChildIndex(indexFrom))
        }
        preorder(0)

        return result
    }

    override fun inOrderTraversal(): List<E> {
        val result: MutableList<E> = mutableListOf()

        fun inorder(indexFrom: Int) {
            if (indexFrom >= size) return

            inorder(getLeftChildIndex(indexFrom))
            result.add(tree[indexFrom])
            inorder(getRightChildIndex(indexFrom))
        }
        inorder(0)

        return result
    }

    override fun postOrderTraversal(): List<E> {
        val result: MutableList<E> = mutableListOf()

        fun postorder(indexFrom: Int) {
            if (indexFrom >= size) return

            postorder(getLeftChildIndex(indexFrom))
            postorder(getRightChildIndex(indexFrom))
            result.add(tree[indexFrom])
        }
        postorder(0)

        return result
    }

    override fun height(): Int {
        fun heightFromIndex(index: Int): Int {
            if (index >= size) return -1

            val leftHeight = heightFromIndex(getLeftChildIndex(index))
            val rightHeight = heightFromIndex(getRightChildIndex(index))

            return maxOf(leftHeight, rightHeight) + 1
        }

        return heightFromIndex(0)
    }

    private fun getLeftChildIndex(parentIndex: Int): Int = ((2 * parentIndex) + 1)
    private fun getRightChildIndex(parentIndex: Int): Int = ((2 * parentIndex) + 2)

    override fun size(): Int = size

    override fun isEmpty(): Boolean = (size == 0)

    override fun contains(item: E): Boolean = tree.contains(item)
}