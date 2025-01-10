package edu.practice.datastructures.linkedlist

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class DoublyLinkedListTest {

    private lateinit var integerDLL: DoublyLinkedList<Int>

    @BeforeEach
    fun setup() {
        integerDLL = DoublyLinkedList()
    }

    @Test
    fun listIsInitializedAsEmpty() {
        assertThat(integerDLL.isEmpty()).isTrue()
        assertThat(integerDLL.size()).isZero()
    }

    @Test
    fun addFirstAnElementToDLL() {
        val item = 50
        integerDLL.addFirst(item)

        assertThat(integerDLL.isEmpty()).isFalse()
        assertThat(integerDLL.size()).isOne()
        assertThat(integerDLL.getFirst()).isEqualTo(item)
    }

    @Test
    fun addFirstMultipleElementsToDLL() {
        val item = intArrayOf(50, 60, 70)
        integerDLL.addFirst(item[0])
        integerDLL.addFirst(item[1])
        integerDLL.addFirst(item[2])

        assertThat(integerDLL.isEmpty()).isFalse()
        assertThat(integerDLL.size()).isEqualTo(item.size)
        assertThat(integerDLL.getFirst()).isEqualTo(item[2])
    }

    @Test
    fun addLastAnElementToDLL() {
        val item = 50
        integerDLL.addLast(item)

        assertThat(integerDLL.isEmpty()).isFalse()
        assertThat(integerDLL.size()).isOne()
        assertThat(integerDLL.getLast()).isEqualTo(item)
    }

    @Test
    fun addLastMultipleElementsToDLL() {
        val item = intArrayOf(50, 60, 70)
        integerDLL.addLast(item[0])
        integerDLL.addLast(item[1])
        integerDLL.addLast(item[2])

        assertThat(integerDLL.isEmpty()).isFalse()
        assertThat(integerDLL.size()).isEqualTo(item.size)
        assertThat(integerDLL.getLast()).isEqualTo(item[2])
    }

    @Test
    fun removeFirstAnElementFromDLL() {
        val item = 50
        integerDLL.addFirst(item)

        assertThat(integerDLL.removeFirst()).isEqualTo(item)
        assertThat(integerDLL.size()).isZero()
        assertThat(integerDLL.isEmpty()).isTrue()
    }

    @Test
    fun removeFirstMultipleElementsFromDLL() {
        val item = intArrayOf(50, 60, 70)
        integerDLL.addFirst(item[0])
        integerDLL.addFirst(item[1])
        integerDLL.addFirst(item[2])

        assertThat(integerDLL.removeFirst()).isEqualTo(item[2])
        assertThat(integerDLL.size()).isEqualTo((item.size - 1))
        assertThat(integerDLL.isEmpty()).isFalse()
    }

    @Test
    fun removeLastAnElementFromDLL() {
        val item = 50
        integerDLL.addLast(item)

        assertThat(integerDLL.removeLast()).isEqualTo(item)
        assertThat(integerDLL.size()).isZero()
        assertThat(integerDLL.isEmpty()).isTrue()
    }

    @Test
    fun removeLastMultipleElementsFromDLL() {
        val item = intArrayOf(50, 60, 70)
        integerDLL.addLast(item[0])
        integerDLL.addLast(item[1])
        integerDLL.addLast(item[2])

        assertThat(integerDLL.removeLast()).isEqualTo(item[2])
        assertThat(integerDLL.size()).isEqualTo((item.size - 1))
        assertThat(integerDLL.isEmpty()).isFalse()
    }

    @Test
    fun getFirstLastFromEmptyDLL() {
        assertThatThrownBy { integerDLL.getFirst() }
            .isInstanceOf(NoSuchElementException::class.java)
        assertThatThrownBy { integerDLL.getLast() }
            .isInstanceOf(NoSuchElementException::class.java)
    }

    @Test
    fun checkIfElementExistsInDLL() {
        val item = intArrayOf(50, 60, 70)
        integerDLL.addLast(item[0])
        integerDLL.addLast(item[1])
        integerDLL.addLast(item[2])

        assertThat(integerDLL.contains(item[1])).isTrue()
        assertThat(integerDLL.contains(80)).isFalse()
    }
}