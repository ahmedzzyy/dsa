package edu.practice.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HeapTest {

    Heap<Integer> minHeap;
    Heap<Integer> maxHeap;

    @BeforeEach
    void setup() {
        minHeap = new Heap<>(true);
        maxHeap = new Heap<>(false);
    }

    @Test
    void heapIsInitializedAsEmpty() {
        assertThat(minHeap.size()).isZero();
        assertThat(minHeap.isEmpty()).isTrue();

        assertThat(maxHeap.size()).isZero();
        assertThat(maxHeap.isEmpty()).isTrue();
    }

    @Test
    void insertElementToHeaps() {
        minHeap.insert(50);
        assertThat(minHeap.isEmpty()).isFalse();
        assertThat(minHeap.size()).isOne();
        assertThat(minHeap.peek()).isEqualTo(50);

        maxHeap.insert(50);
        assertThat(maxHeap.isEmpty()).isFalse();
        assertThat(maxHeap.size()).isOne();
        assertThat(maxHeap.peek()).isEqualTo(50);
    }

    @Test
    void insertMultipleElementsToHeaps() {
        int[] items = {50, 60, 10};
        for (int item : items) {
            minHeap.insert(item);
            maxHeap.insert(item);
        }

        assertThat(minHeap.isEmpty()).isFalse();
        assertThat(minHeap.size()).isEqualTo(items.length);
        assertThat(minHeap.peek()).isEqualTo(10);

        assertThat(maxHeap.isEmpty()).isFalse();
        assertThat(maxHeap.size()).isEqualTo(items.length);
        assertThat(maxHeap.peek()).isEqualTo(60);
    }

    @Test
    void removeElementFromHeaps() {
        int[] items = {50, 60, 10};
        for (int item : items) {
            minHeap.insert(item);
            maxHeap.insert(item);
        }

        assertThat(minHeap.remove()).isEqualTo(10);
        assertThat(minHeap.size()).isEqualTo(items.length - 1);

        assertThat(maxHeap.remove()).isEqualTo(60);
        assertThat(maxHeap.size()).isEqualTo(items.length - 1);
    }

    @Test
    void peekOnEmptyHeap() {
        assertThatThrownBy(() -> minHeap.peek())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Heap is Empty.");

        assertThatThrownBy(() -> maxHeap.peek())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Heap is Empty.");
    }

    @Test
    void removeOnEmptyHeap() {
        assertThatThrownBy(() -> minHeap.remove())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Heap is Empty.");

        assertThatThrownBy(() -> maxHeap.remove())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Heap is Empty.");
    }
}
