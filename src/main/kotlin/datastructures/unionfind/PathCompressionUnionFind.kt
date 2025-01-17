package edu.practice.datastructures.unionfind

class PathCompressionUnionFind<E>: UnionFind<E>, BasicUnionFind<E>() {

    override fun findParentIndexByIndex(index: Int): Int {

        var current = index
        while (current != parentByIndex[current]) {
            // Path compression: Point current node directly to its parent’s parent
            parentByIndex[current] = parentByIndex[parentByIndex[current]]
            current = parentByIndex[current]
        }

        return current
    }
}