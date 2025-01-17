package edu.practice.datastructures.unionfind

class BasicUnionFind<E>: UnionFind<E> {

    private val indexOfElementLookup: MutableMap<E, Int> = HashMap()
    private lateinit var parentByIndex: Array<Int>
    private var _size: Int = 0

    override fun makeSet(vararg items: E) {
        parentByIndex = Array(items.size) { it }

        items.forEachIndexed { index, item ->
            if (!indexOfElementLookup.containsKey(item)) {
                indexOfElementLookup[item] = index
            }
        }

        _size = items.size
    }

    private fun findParentIndex(element: E): Int {
        if (!indexOfElementLookup.containsKey(element)) {
            throw NoSuchElementException("No such element in UnionFind sets")
        }

        var parentIndex = indexOfElementLookup[element] ?: 0
        while (parentIndex != parentByIndex[parentIndex]) {
            parentIndex = parentByIndex[parentIndex]
        }

        return parentIndex
    }

    override fun find(element: E): E {
        val parentIndex = findParentIndex(element)
        val root = indexOfElementLookup.entries.find { parentIndex == it.value }?.key ?: element
        return root
    }

    override fun union(element1: E, element2: E) {
        val rootIndex1 = findParentIndex(element1)
        val rootIndex2 = findParentIndex(element2)

        if (rootIndex1 != rootIndex2) {
            parentByIndex[rootIndex1] = rootIndex2
        }
    }

    override fun areConnected(element1: E, element2: E): Boolean {
        return findParentIndex(element1) == findParentIndex(element2)
    }

    override fun countSets(): Int {
        return indexOfElementLookup.keys.map { findParentIndex(it) }.distinct().size
    }

    override val size: Int
        get() = _size
}