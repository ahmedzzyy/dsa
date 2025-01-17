package edu.practice.datastructures.unionfind

interface UnionFind<E> {

    /**
     * Initialises the Union-Find structure with the provided sets.
     *
     * This function accepts pre-made sets of elements and treats each set as a distinct group.
     * Each element in a set will belong to the same group initially.
     *
     * @param items Vararg parameter representing multiple sets of elements.
     */
    fun makeSet(vararg items: Set<E>)

    /**
     * Initialises the Union-Find structure with each element as a disjoint set
     */
    fun makeSet(vararg items: E)

    /**
     * Finds the root element of E
     */
    fun find(element: E): E // TODO(Maybe return index)

    /**
     * Union the sets containing the elements provided
     */
    fun union(element1: E, element2: E)

    /**
     * Check if both elements provided are in the same set
     */
    fun areConnected(element1: E, element2: E): Boolean

    /**
     * Count number of disjoint sets currently present
     */
    fun countSets(): Int
}