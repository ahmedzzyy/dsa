package edu.practice.datastructures.deque

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DequeListTest {

    private lateinit var intDeque: Deque<Int>

    @BeforeEach
    fun setup() {
        intDeque = DequeList()
    }

    @Test
    fun dequeIsInitializedAsEmpty() {
        assertThat(intDeque.isEmpty()).isTrue()
        assertThat(intDeque.size()).isZero()
    }

    @Test
    fun offerElementToFrontOfDeque() {
        intDeque.offerFirst(10)

        assertThat(intDeque.isEmpty()).isFalse()
        assertThat(intDeque.size()).isOne()
        assertThat(intDeque.peekFirst()).isEqualTo(10)
        assertThat(intDeque.peekLast()).isEqualTo(10)
    }

    @Test
    fun offerElementToBackOfDeque() {
        intDeque.offerLast(20)

        assertThat(intDeque.isEmpty()).isFalse()
        assertThat(intDeque.size()).isOne()
        assertThat(intDeque.peekFirst()).isEqualTo(20)
        assertThat(intDeque.peekLast()).isEqualTo(20)
    }

    @Test
    fun offerElementsToBothEndsOfDeque() {
        intDeque.offerFirst(10)
        intDeque.offerLast(20)

        assertThat(intDeque.size()).isEqualTo(2)
        assertThat(intDeque.peekFirst()).isEqualTo(10)
        assertThat(intDeque.peekLast()).isEqualTo(20)
    }

    @Test
    fun pollElementFromFrontOfDeque() {
        intDeque.offerFirst(10)
        intDeque.offerLast(20)

        assertThat(intDeque.pollFirst()).isEqualTo(10)
        assertThat(intDeque.size()).isEqualTo(1)
        assertThat(intDeque.peekFirst()).isEqualTo(20)
    }

    @Test
    fun pollElementFromBackOfDeque() {
        intDeque.offerFirst(10)
        intDeque.offerLast(20)

        assertThat(intDeque.pollLast()).isEqualTo(20)
        assertThat(intDeque.size()).isEqualTo(1)
        assertThat(intDeque.peekLast()).isEqualTo(10)
    }

    @Test
    fun pollEmptyDequeFromFront() {
        assertThatThrownBy { intDeque.pollFirst() }
            .isInstanceOf(NoSuchElementException::class.java)
            .hasMessage("Cannot remove first element when deque is empty")
    }

    @Test
    fun pollEmptyDequeFromBack() {
        assertThatThrownBy { intDeque.pollLast() }
            .isInstanceOf(NoSuchElementException::class.java)
            .hasMessage("Cannot remove last element when deque is empty")
    }

    @Test
    fun peekEmptyDequeFromFront() {
        assertThatThrownBy { intDeque.peekFirst() }
            .isInstanceOf(NoSuchElementException::class.java)
            .hasMessage("Cannot get first element when deque is empty")
    }

    @Test
    fun peekEmptyDequeFromBack() {
        assertThatThrownBy { intDeque.peekLast() }
            .isInstanceOf(NoSuchElementException::class.java)
            .hasMessage("Cannot get last element when deque is empty")
    }
}