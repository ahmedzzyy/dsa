package edu.practice.datastructures.linkedlist

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class SinglyLinkedListTest {

    private lateinit var integerSLL: SinglyLinkedList<Int>

    @BeforeEach
    fun setup() {
        integerSLL = SinglyLinkedList()
    }

    @Test
    fun listIsInitializedAsEmpty() {
        assertThat(integerSLL.isEmpty()).isTrue()
        assertThat(integerSLL.size()).isZero()
    }

    @Test
    fun addFirstAnElementToSLL() {
        val item = 50
        integerSLL.addFirst(item)

        assertThat(integerSLL.isEmpty()).isFalse()
        assertThat(integerSLL.size()).isOne()
        assertThat(integerSLL.getFirst()).isEqualTo(item)
    }

    @Test
    fun addFirstMultipleElementsToSLL() {
        val item = intArrayOf(50, 60, 70)
        integerSLL.addFirst(item[0])
        integerSLL.addFirst(item[1])
        integerSLL.addFirst(item[2])

        assertThat(integerSLL.isEmpty()).isFalse()
        assertThat(integerSLL.size()).isEqualTo(item.size)
        assertThat(integerSLL.getFirst()).isEqualTo(item[2])
    }

    @Test
    fun addLastAnElementToSLL() {
        val item = 50
        integerSLL.addLast(item)

        assertThat(integerSLL.isEmpty()).isFalse()
        assertThat(integerSLL.size()).isOne()
        assertThat(integerSLL.getLast()).isEqualTo(item)
    }

    @Test
    fun addLastMultipleElementsToSLL() {
        val item = intArrayOf(50, 60, 70)
        integerSLL.addLast(item[0])
        integerSLL.addLast(item[1])
        integerSLL.addLast(item[2])

        assertThat(integerSLL.isEmpty()).isFalse()
        assertThat(integerSLL.size()).isEqualTo(item.size)
        assertThat(integerSLL.getLast()).isEqualTo(item[2])
    }

    @Test
    fun removeFirstAnElementFromSLL() {
        val item = 50
        integerSLL.addFirst(item)

        assertThat(integerSLL.removeFirst()).isEqualTo(item)
        assertThat(integerSLL.size()).isZero()
        assertThat(integerSLL.isEmpty()).isTrue()
    }

    @Test
    fun removeFirstMultipleElementsFromSLL() {
        val item = intArrayOf(50, 60, 70)
        integerSLL.addFirst(item[0])
        integerSLL.addFirst(item[1])
        integerSLL.addFirst(item[2])

        assertThat(integerSLL.removeFirst()).isEqualTo(item[2])
        assertThat(integerSLL.size()).isEqualTo((item.size - 1))
        assertThat(integerSLL.isEmpty()).isFalse()
    }

    @Test
    fun removeLastAnElementFromSLL() {
        val item = 50
        integerSLL.addLast(item)

        assertThat(integerSLL.removeLast()).isEqualTo(item)
        assertThat(integerSLL.size()).isZero()
        assertThat(integerSLL.isEmpty()).isTrue()
    }

    @Test
    fun removeLastMultipleElementsFromSLL() {
        val item = intArrayOf(50, 60, 70)
        integerSLL.addLast(item[0])
        integerSLL.addLast(item[1])
        integerSLL.addLast(item[2])

        assertThat(integerSLL.removeLast()).isEqualTo(item[2])
        assertThat(integerSLL.size()).isEqualTo((item.size - 1))
        assertThat(integerSLL.isEmpty()).isFalse()
    }

    @Test
    fun getFirstLastFromEmptySLL() {
        assertThatThrownBy { integerSLL.getFirst() }
            .isInstanceOf(NoSuchElementException::class.java)
        assertThatThrownBy { integerSLL.getLast() }
            .isInstanceOf(NoSuchElementException::class.java)
    }

    @Test
    fun checkIfElementExistsInSLL() {
        val item = intArrayOf(50, 60, 70)
        integerSLL.addLast(item[0])
        integerSLL.addLast(item[1])
        integerSLL.addLast(item[2])

        assertThat(integerSLL.contains(item[1])).isTrue()
        assertThat(integerSLL.contains(80)).isFalse()
    }
}