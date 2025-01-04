package edu.practice.datastructures

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class QueueTest {

    private lateinit var integerQueue: Queue<Int>

    @BeforeEach
    fun setup() {
        integerQueue = Queue()
    }

    @Test
    fun queueIsInitializedAsEmpty() {
        assertThat(integerQueue.isEmpty()).isTrue()
        assertThat(integerQueue.size()).isZero()
    }

    @Test
    fun offerAnElementToQueue() {
        integerQueue.offer(50)

        assertThat(integerQueue.isEmpty()).isFalse()
        assertThat(integerQueue.size()).isOne()
        assertThat(integerQueue.peek()).isEqualTo(50)
    }

    @Test
    fun offerMoreThanOneElementToQueue() {
        integerQueue.offer(50)
        integerQueue.offer(60)
        integerQueue.offer(70)

        assertThat(integerQueue.isEmpty()).isFalse()
        assertThat(integerQueue.size()).isEqualTo(3)
        assertThat(integerQueue.peek()).isEqualTo(50)
    }


    // Testing "Poll" functionality indirectly tests "Peek"
    @Test
    fun pollAnElementFromSingleElementQueue() {
        integerQueue.offer(50)

        assertThat(integerQueue.poll()).isEqualTo(50)
        assertThat(integerQueue.size()).isZero()
        assertThat(integerQueue.isEmpty()).isTrue()
    }

    @Test
    fun pollMoreThanOneElementFromQueue() {
        integerQueue.offer(50)
        integerQueue.offer(60)
        integerQueue.offer(70)

        assertThat(integerQueue.poll()).isEqualTo(50)
        assertThat(integerQueue.size()).isEqualTo(3 - 1)
        assertThat(integerQueue.isEmpty()).isFalse()
    }

    @Test
    fun pollEmptyQueue() {
        assertThatThrownBy { integerQueue.poll() }
            .isInstanceOf(NoSuchElementException::class.java)
    }
}