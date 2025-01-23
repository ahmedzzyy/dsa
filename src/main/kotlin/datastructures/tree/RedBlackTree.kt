package edu.practice.datastructures.tree

class RedBlackTree<E: Comparable<E>>: Tree<E> {

    private enum class NodeColor { RED, BLACK }

    private data class Node<E>(
        var data: E? = null,
        var color: NodeColor = NodeColor.RED,
        var parent: Node<E>? = null,
        var left: Node<E>? = null,
        var right: Node<E>? = null,
    )

    private val nil: Node<E> = Node(color = NodeColor.BLACK)

    private var root: Node<E>? = nil
    private var size = 0

    private fun createNode(data: E) = Node(
        data = data,
        parent = nil,
        left = nil,
        right = nil
    )

    override fun insert(item: E) {
        val z = createNode(item)

        var y = nil
        var x = root

        while (x != null && x != nil) {
            y = x
            x = if ((z.data ?: continue) < (x.data ?: continue)) {
                x.left
            } else {
                x.right
            }
        }

        z.parent = y
        if (y == nil) {
            root = z
        } else if (z.data!! < y.data!!) {
            y.left = z
        } else {
            y.right = z
        }

        insertFixUp(z)
        size++
    }

    private fun insertFixUp(z: Node<E>) {
        var currentNode: Node<E>? = z

        while (currentNode?.parent?.color == NodeColor.RED) {
            if (currentNode.parent == currentNode.parent?.parent?.left) {
                val y = currentNode.parent?.parent?.right

                if (y?.color == NodeColor.RED) { // Case 1
                    currentNode.parent?.color = NodeColor.BLACK
                    y.color = NodeColor.BLACK
                    currentNode.parent?.parent?.color = NodeColor.RED
                    currentNode = currentNode.parent?.parent
                } else {
                    if (currentNode == currentNode.parent?.right) { // Case 2
                        currentNode = currentNode.parent
                        currentNode?.let { leftRotate(it) }
                    }
                    // Case 3
                    currentNode?.parent?.color = NodeColor.BLACK
                    currentNode?.parent?.parent?.color = NodeColor.RED
                    currentNode?.parent?.parent?.let { rightRotate(it) }
                }
            } else {
                val y = currentNode.parent?.parent?.left

                if (y?.color == NodeColor.RED) { // Case 1
                    currentNode.parent?.color = NodeColor.BLACK
                    y.color = NodeColor.BLACK
                    currentNode.parent?.parent?.color = NodeColor.RED
                    currentNode = currentNode.parent?.parent
                } else {
                    if (currentNode == currentNode.parent?.left) { // Case 2
                        currentNode = currentNode.parent
                        currentNode?.let { rightRotate(it) }
                    }
                    // Case 3
                    currentNode?.parent?.color = NodeColor.BLACK
                    currentNode?.parent?.parent?.color = NodeColor.RED
                    currentNode?.parent?.parent?.let { leftRotate(it) }
                }
            }
        }

        root?.color = NodeColor.BLACK
    }

    override fun delete(item: E): Boolean {
        if (root == null) return false

        val z = searchNodeWithData(item)

        if (z == nil) return false

        var y: Node<E>? = z
        var yOriginalColor = y?.color
        val x: Node<E>?

        when {
            z.left == nil -> {
                x = z.right
                z.right?.let { transplant(z, it) }
            }
            z.right == nil -> {
                x = z.left
                z.left?.let { transplant(z, it) }
            }
            else -> {
                y = z.right?.let { minimumOfTreeFromNode(it) }
                yOriginalColor = y?.color
                x = y?.right

                if (y?.parent != z) {
                    y?.let { yNode -> y.right?.let { yRight -> transplant(yNode, yRight)
                    } }

                    y?.right = z.right
                    y?.right?.parent = y
                } else {
                    x?.parent = y
                }

                y?.let { transplant(z, it) }
                y?.left = z.left
                y?.left?.parent = y
                y?.color = z.color
            }
        }

        if (yOriginalColor == NodeColor.BLACK) deleteFixUp(x)

        size--
        return true
    }

    private fun deleteFixUp(node: Node<E>?) {
        var x = node
        while (x != root && x?.color == NodeColor.BLACK) {
            if (x == x.parent?.left) {
                var w = x.parent?.right

                // ---- TYPE 1 ---- //
                if (w?.color == NodeColor.RED) {
                    w.color = NodeColor.BLACK
                    x.parent?.color = NodeColor.RED
                    x.parent?.let { leftRotate(it) }
                    w = x.parent?.right
                }

                // ---- TYPE 2 ---- //
                when {
                    w?.left?.color == NodeColor.BLACK && w.right?.color == NodeColor.BLACK -> {
                        w.color = NodeColor.RED
                        x = x.parent
                    }
                    else -> {
                        // ---- TYPE 3 ---- //
                        if (w?.right?.color == NodeColor.BLACK) {
                            w.left?.color = NodeColor.BLACK
                            w.color = NodeColor.RED
                            rightRotate(w)
                            w = x.parent?.right
                        }

                        // ---- TYPE 4 ---- //
                        x.parent?.let { w?.color = it.color }
                        x.parent?.color = NodeColor.BLACK
                        w?.right?.color = NodeColor.BLACK
                        x.parent?.let { leftRotate(it) }
                        x = root
                    }
                }
            } else {
                var w = x.parent?.left

                // ---- TYPE 1 ---- //
                if (w?.color == NodeColor.RED) {
                    w.color = NodeColor.BLACK
                    x.parent?.color = NodeColor.RED
                    x.parent?.let { rightRotate(it) }
                    w = x.parent?.left
                }

                // ---- TYPE 2 ---- //
                when {
                    w?.left?.color == NodeColor.BLACK && w.right?.color == NodeColor.BLACK -> {
                        w.color = NodeColor.RED
                        x = x.parent
                    }
                    else -> {
                        // ---- TYPE 3 ---- //
                        if (w?.left?.color == NodeColor.BLACK) {
                            w.right?.color = NodeColor.BLACK
                            w.color = NodeColor.RED
                            leftRotate(w)
                            w = x.parent?.left
                        }

                        // ---- TYPE 4 ---- //
                        x.parent?.let { w?.color = it.color }
                        x.parent?.color = NodeColor.BLACK
                        w?.left?.color = NodeColor.BLACK
                        x.parent?.let { rightRotate(it) }
                        x = root
                    }
                }
            }
        }
    }

    private fun minimumOfTreeFromNode(node: Node<E>): Node<E> {
        var currentNode = node
        while (currentNode.left != nil) {
            currentNode = currentNode.left!!
        }
        return currentNode
    }

    private fun searchNodeWithData(item: E): Node<E> {
        var currentNode = root

        while (currentNode != nil && item != currentNode?.data) {
            if (currentNode != null) {
                currentNode = if (item < (currentNode.data ?: return nil)) {
                    currentNode.left
                } else {
                    currentNode.right
                }
            }
        }

        return currentNode
    }

    private fun transplant(u: Node<E>, v: Node<E>) {
        if (u.parent == nil) {
            root = v
        } else if (u == u.parent?.left) {
            u.parent?.left = v
        } else {
            u.parent?.right = v
        }

        v.parent = u.parent
    }

    private fun leftRotate(x: Node<E>) {
        val y = x.right
        x.right = y?.left

        if (y?.left != nil) {
            y?.left?.parent = x
        }
        y?.parent = x.parent

        if (x.parent == nil) {
            root = y
        } else if (x == x.parent?.left) {
            x.parent?.left = y
        } else {
            x.parent?.right = y
        }

        y?.left = x
        x.parent = y
    }

    private fun rightRotate(x: Node<E>) {
        val y = x.left
        x.left = y?.right

        if (y?.right != nil) {
            y?.right?.parent = x
        }
        y?.parent = x.parent

        if (x.parent == nil) {
            root = y
        } else if (x == x.parent?.right) {
            x.parent?.right = y
        } else {
            x.parent?.left = y
        }

        y?.right = x
        x.parent = y
    }

    override fun root(): E? = root?.data

    override fun preOrderTraversal(): List<E> {
        val result: MutableList<E> = mutableListOf()

        fun preorder(currentNode: Node<E>?) {
            if (currentNode == null || currentNode == nil) return

            currentNode.data?.let { result.add(it) }
            preorder(currentNode.left)
            preorder(currentNode.right)
        }
        preorder(root)

        return result
    }

    override fun inOrderTraversal(): List<E> {
        val result: MutableList<E> = mutableListOf()

        fun inorder(currentNode: Node<E>?) {
            if (currentNode == null || currentNode == nil) return

            inorder(currentNode.left)
            currentNode.data?.let { result.add(it) }
            inorder(currentNode.right)
        }
        inorder(root)
        println(result)

        return result
    }

    override fun postOrderTraversal(): List<E> {
        val result: MutableList<E> = mutableListOf()

        fun postorder(currentNode: Node<E>?) {
            if (currentNode == null || currentNode == nil) return

            postorder(currentNode.left)
            postorder(currentNode.right)
            currentNode.data?.let { result.add(it) }
        }
        postorder(root)

        return result
    }

    override fun height(): Int = calculateHeightOfNode(root)

    private fun calculateHeightOfNode(node: Node<E>?): Int {
        if (node == null || node == nil) { // Base Condition
            return -1
        }

        val leftSubTreeHeight = calculateHeightOfNode(node.left)
        val rightSubTreeHeight = calculateHeightOfNode(node.right)

        return maxOf(leftSubTreeHeight, rightSubTreeHeight) + 1 // 1 for current node
    }

    override fun contains(item: E): Boolean {
        return findNodeAndApply(item, root) {
                node -> node != nil && node?.data == item
        }
    }

    override fun isLeaf(item: E): Boolean {
        return findNodeAndApply(item, root) {
                node -> node?.left == nil && node.right == nil
        }
    }

    private fun <T> findNodeAndApply(item: E, currentNode: Node<E>?, action: (Node<E>?) -> T): T {
        currentNode?.let { node ->
            node.data?.let {
                when {
                    item < it -> return findNodeAndApply(item, node.left, action)
                    item > it -> return findNodeAndApply(item, node.right, action)
                    else -> return action(node) // Node to apply on
                }
            } ?: return action(null)
        } ?: return action(null)
    }

    override fun size(): Int = size

    override fun isEmpty(): Boolean  = (size == 0)
}