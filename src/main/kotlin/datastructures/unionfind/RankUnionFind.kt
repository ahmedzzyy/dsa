package edu.practice.datastructures.unionfind

class RankUnionFind<E>: UnionFind<E>, BasicUnionFind<E>() {

    private lateinit var rankByIndex: Array<Int>

    override fun makeSet(vararg items: E) {
        super.makeSet(*items)

        rankByIndex = Array(items.size) { 1 }
    }

    override fun union(element1: E, element2: E) {
        val rootIndex1 = findParentIndex(element1)
        val rootIndex2 = findParentIndex(element2)

        if (rootIndex1 != rootIndex2) {
            when {
                rankByIndex[rootIndex1] < rankByIndex[rootIndex2] -> { // 1 -> 2
                    parentByIndex[rootIndex1] = rootIndex2
                }

                rankByIndex[rootIndex1] > rankByIndex[rootIndex2] -> { // 2 -> 1
                    parentByIndex[rootIndex2] = rootIndex1
                }

                else -> {
                    parentByIndex[rootIndex2] = rootIndex1 // 2 -> 1
                    rankByIndex[rootIndex1]++
                }
            }
        }
    }
}