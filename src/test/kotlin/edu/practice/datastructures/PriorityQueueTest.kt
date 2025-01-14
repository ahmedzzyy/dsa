package edu.practice.datastructures

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PriorityQueueTest {

    private lateinit var minPriorityQueue: PriorityQueue<Int>
    private lateinit var maxPriorityQueue: PriorityQueue<Int>

    @BeforeEach
    fun setup() {
        minPriorityQueue = PriorityQueue(isMinHeap = true)
        maxPriorityQueue = PriorityQueue(isMinHeap = false)
    }

    @Test
    fun priorityQueueIsInitializedAsEmpty() {
        assertThat(minPriorityQueue.size()).isZero()
        assertThat(minPriorityQueue.isEmpty()).isTrue()

        assertThat(maxPriorityQueue.size()).isZero()
        assertThat(maxPriorityQueue.isEmpty()).isTrue()
    }

    @Test
    fun offerElementToPriorityQueue() {
        minPriorityQueue.offer(50)
        assertThat(minPriorityQueue.isEmpty()).isFalse()
        assertThat(minPriorityQueue.size()).isOne()
        assertThat(minPriorityQueue.peek()).isEqualTo(50)

        maxPriorityQueue.offer(50)
        assertThat(maxPriorityQueue.isEmpty()).isFalse()
        assertThat(maxPriorityQueue.size()).isOne()
        assertThat(maxPriorityQueue.peek()).isEqualTo(50)
    }

    @Test
    fun offerMultipleElementsToPriorityQueue() {
        val items = intArrayOf(50, 60, 10)
        for (item in items) {
            minPriorityQueue.offer(item)
            maxPriorityQueue.offer(item)
        }

        assertThat(minPriorityQueue.isEmpty()).isFalse()
        assertThat(minPriorityQueue.size()).isEqualTo(items.size)
        assertThat(minPriorityQueue.peek()).isEqualTo(10)

        assertThat(maxPriorityQueue.isEmpty()).isFalse()
        assertThat(maxPriorityQueue.size()).isEqualTo(items.size)
        assertThat(maxPriorityQueue.peek()).isEqualTo(60)
    }

    @Test
    fun pollElementFromPriorityQueue() {
        val items = intArrayOf(50, 60, 10)
        for (item in items) {
            minPriorityQueue.offer(item)
            maxPriorityQueue.offer(item)
        }

        assertThat(minPriorityQueue.poll()).isEqualTo(10)
        assertThat(minPriorityQueue.size()).isEqualTo(items.size - 1)

        assertThat(maxPriorityQueue.poll()).isEqualTo(60)
        assertThat(maxPriorityQueue.size()).isEqualTo(items.size - 1)
    }

    @Test
    fun peekOnEmptyPriorityQueue() {
        assertThatThrownBy { minPriorityQueue.peek() }
            .isInstanceOf(IllegalStateException::class.java)
        assertThatThrownBy { maxPriorityQueue.peek() }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun pollOnEmptyPriorityQueue() {
        assertThatThrownBy { minPriorityQueue.poll() }
            .isInstanceOf(IllegalStateException::class.java)
        assertThatThrownBy { maxPriorityQueue.poll() }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun offerNullElementToPriorityQueue() {
        assertThat(minPriorityQueue.offer(null)).isFalse()
        assertThat(maxPriorityQueue.offer(null)).isFalse()

        assertThat(minPriorityQueue.size()).isZero()
        assertThat(maxPriorityQueue.size()).isZero()
    }
}