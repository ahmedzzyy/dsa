package edu.practice.datastructures.unionfind

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StandardUnionFindTest {

    private lateinit var intUnionFind: UnionFind<Int>

    @BeforeEach
    fun setup() {
        intUnionFind = StandardUnionFind()
    }

    // Count Set and size are also tested here
    @Test
    fun ufIsInitialisedWithMultipleElements() {
        intUnionFind.makeSet(1, 2, 3, 4, 5, 6)
        assertThat(intUnionFind.size).isEqualTo(6)
        assertThat(intUnionFind.countSets()).isEqualTo(6)
    }

    @Test
    fun findRootOfAnElement() {
        intUnionFind.makeSet(2, 3)

        intUnionFind.union(3, 2) // 2 -> 3 (Rank Based)
        assertThat(intUnionFind.find(2)).isEqualTo(3)
    }

    @Test
    fun unionSetsAndCheckIfPathsCompressedOneLevel() {
        intUnionFind.makeSet(1, 2, 3, 4, 5, 6)

        intUnionFind.union(2, 3) // 3 -> 2
        intUnionFind.union(4, 5) // 5 -> 4
        intUnionFind.union(5, 6) // 6 -> 5 -> 4, 6 -> 4

        // set containing 2 - (union) - set containing 5
        // 4 -> 2, 5 and 6 still -> 4
        intUnionFind.union(2, 5)

        // When element was in the previous set and new union set
        // Another Level as find called, 5 -> 2
        assertThat(intUnionFind.find(5)).isEqualTo(2)

        // When element was not in the previous set but in new union set
        // 6 still -> 4
        assertThat(intUnionFind.find(3)).isEqualTo(2)
    }

    @Test
    fun checkIfTwoElementsAreConnected() {
        intUnionFind.makeSet(1, 2, 3, 4, 5, 6)

        intUnionFind.union(3, 2)
        intUnionFind.union(5, 4)
        intUnionFind.union(6, 5)

        assertThat(intUnionFind.areConnected(1, 3)).isFalse()

        // set containing 1 - (union) -> set containing 3
        intUnionFind.union(1, 3)

        assertThat(intUnionFind.areConnected(1, 3)).isTrue()
    }

    @Test
    fun handleNoElementInUnionFindException() {
        intUnionFind.makeSet(1, 2)

        assertThatThrownBy { intUnionFind.areConnected(5, 6) }
            .isInstanceOf(NoSuchElementException::class.java)
            .hasMessageContaining("No such element in UnionFind sets")

        assertThatThrownBy { intUnionFind.find(5) }
            .isInstanceOf(NoSuchElementException::class.java)
            .hasMessageContaining("No such element in UnionFind sets")

        assertThatThrownBy { intUnionFind.union(5, 6) }
            .isInstanceOf(NoSuchElementException::class.java)
            .hasMessageContaining("No such element in UnionFind sets")
    }
}