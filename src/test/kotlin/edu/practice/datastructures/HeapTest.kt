package edu.practice.datastructures

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HeapTest {

    private lateinit var minHeap: Heap<Int>
    private lateinit var maxHeap: Heap<Int>

    @BeforeEach
    fun setup() {
        minHeap = Heap(isMinHeap = true)
        maxHeap = Heap(isMinHeap = false)
    }

    @Test
    fun heapIsInitializedAsEmpty() {
        assertThat(minHeap.size()).isZero()
        assertThat(minHeap.isEmpty()).isTrue()

        assertThat(maxHeap.size()).isZero()
        assertThat(maxHeap.isEmpty()).isTrue()
    }

    @Test
    fun insertElementToHeaps() {
        minHeap.insert(50)
        assertThat(minHeap.isEmpty()).isFalse()
        assertThat(minHeap.size()).isOne()
        assertThat(minHeap.peek()).isEqualTo(50)

        maxHeap.insert(50)
        assertThat(maxHeap.isEmpty()).isFalse()
        assertThat(maxHeap.size()).isOne()
        assertThat(maxHeap.peek()).isEqualTo(50)
    }

    @Test
    fun insertMultipleElementsToHeaps() {
        val items = intArrayOf(50, 60, 10)
        for (item in items) {
            minHeap.insert(item)
            maxHeap.insert(item)
        }

        assertThat(minHeap.isEmpty()).isFalse()
        assertThat(minHeap.size()).isEqualTo(items.size)
        assertThat(minHeap.peek()).isEqualTo(10)

        assertThat(maxHeap.isEmpty()).isFalse()
        assertThat(maxHeap.size()).isEqualTo(items.size)
        assertThat(maxHeap.peek()).isEqualTo(60)
    }

    @Test
    fun removeElementFromHeaps() {
        val items = intArrayOf(50, 60, 10)
        for (item in items) {
            minHeap.insert(item)
            maxHeap.insert(item)
        }

        assertThat(minHeap.remove()).isEqualTo(10)
        assertThat(minHeap.size()).isEqualTo(items.size - 1)

        assertThat(maxHeap.remove()).isEqualTo(60)
        assertThat(maxHeap.size()).isEqualTo(items.size - 1)
    }

    @Test
    fun peekOnEmptyHeap() {
        assertThatThrownBy { minHeap.peek() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("Cannot peek heap when size of Heap is 0")

        assertThatThrownBy { maxHeap.peek() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("Cannot peek heap when size of Heap is 0")
    }

    @Test
    fun removeOnEmptyHeap() {
        assertThatThrownBy { minHeap.remove() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("Cannot remove from heap when size of Heap is 0")

        assertThatThrownBy { maxHeap.remove() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("Cannot remove from heap when size of Heap is 0")
    }
}