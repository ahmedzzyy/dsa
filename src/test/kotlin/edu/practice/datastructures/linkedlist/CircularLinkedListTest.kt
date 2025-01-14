package edu.practice.datastructures.linkedlist

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class CircularLinkedListTest {

    private lateinit var integerCircularSLL: LinkedList<Int>

    @BeforeEach
    fun setup() {
        integerCircularSLL = CircularLinkedList()
    }

    @Test
    fun listIsInitializedAsEmpty() {
        assertThat(integerCircularSLL.isEmpty()).isTrue()
        assertThat(integerCircularSLL.size()).isZero()
    }

    @Test
    fun addFirstAnElementToCircularSLL() {
        val item = 50
        integerCircularSLL.addFirst(item)

        assertThat(integerCircularSLL.isEmpty()).isFalse()
        assertThat(integerCircularSLL.size()).isOne()
        assertThat(integerCircularSLL.getFirst()).isEqualTo(item)
    }

    @Test
    fun addFirstMultipleElementsToCircularSLL() {
        val item = intArrayOf(50, 60, 70)
        integerCircularSLL.addFirst(item[0])
        integerCircularSLL.addFirst(item[1])
        integerCircularSLL.addFirst(item[2])

        assertThat(integerCircularSLL.isEmpty()).isFalse()
        assertThat(integerCircularSLL.size()).isEqualTo(item.size)
        assertThat(integerCircularSLL.getFirst()).isEqualTo(item[2])
    }

    @Test
    fun addLastAnElementToCircularSLL() {
        val item = 50
        integerCircularSLL.addLast(item)

        assertThat(integerCircularSLL.isEmpty()).isFalse()
        assertThat(integerCircularSLL.size()).isOne()
        assertThat(integerCircularSLL.getLast()).isEqualTo(item)
    }

    @Test
    fun addLastMultipleElementsToCircularSLL() {
        val item = intArrayOf(50, 60, 70)
        integerCircularSLL.addLast(item[0])
        integerCircularSLL.addLast(item[1])
        integerCircularSLL.addLast(item[2])

        assertThat(integerCircularSLL.isEmpty()).isFalse()
        assertThat(integerCircularSLL.size()).isEqualTo(item.size)
        assertThat(integerCircularSLL.getLast()).isEqualTo(item[2])
    }

    @Test
    fun removeFirstAnElementFromCircularSLL() {
        val item = 50
        integerCircularSLL.addFirst(item)

        assertThat(integerCircularSLL.removeFirst()).isEqualTo(item)
        assertThat(integerCircularSLL.size()).isZero()
        assertThat(integerCircularSLL.isEmpty()).isTrue()
    }

    @Test
    fun removeFirstMultipleElementsFromCircularSLL() {
        val item = intArrayOf(50, 60, 70)
        integerCircularSLL.addFirst(item[0])
        integerCircularSLL.addFirst(item[1])
        integerCircularSLL.addFirst(item[2])

        assertThat(integerCircularSLL.removeFirst()).isEqualTo(item[2])
        assertThat(integerCircularSLL.size()).isEqualTo((item.size - 1))
        assertThat(integerCircularSLL.isEmpty()).isFalse()
    }

    @Test
    fun removeLastAnElementFromCircularSLL() {
        val item = 50
        integerCircularSLL.addLast(item)

        assertThat(integerCircularSLL.removeLast()).isEqualTo(item)
        assertThat(integerCircularSLL.size()).isZero()
        assertThat(integerCircularSLL.isEmpty()).isTrue()
    }

    @Test
    fun removeLastMultipleElementsFromCircularSLL() {
        val item = intArrayOf(50, 60, 70)
        integerCircularSLL.addLast(item[0])
        integerCircularSLL.addLast(item[1])
        integerCircularSLL.addLast(item[2])

        assertThat(integerCircularSLL.removeLast()).isEqualTo(item[2])
        assertThat(integerCircularSLL.size()).isEqualTo((item.size - 1))
        assertThat(integerCircularSLL.isEmpty()).isFalse()
    }

    @Test
    fun getFirstLastFromEmptyCircularSLL() {
        assertThatThrownBy { integerCircularSLL.getFirst() }
            .isInstanceOf(NoSuchElementException::class.java)
        assertThatThrownBy { integerCircularSLL.getLast() }
            .isInstanceOf(NoSuchElementException::class.java)
    }

    @Test
    fun checkIfElementExistsInCircularSLL() {
        val item = intArrayOf(50, 60, 70)
        integerCircularSLL.addLast(item[0])
        integerCircularSLL.addLast(item[1])
        integerCircularSLL.addLast(item[2])

        assertThat(integerCircularSLL.contains(item[1])).isTrue()
        assertThat(integerCircularSLL.contains(80)).isFalse()
    }
}