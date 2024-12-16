package edu.practice.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PriorityQueueTest {

    private PriorityQueue<Integer> minPriorityQueue;
    private PriorityQueue<Integer> maxPriorityQueue;

    @BeforeEach
    void setup() {
        minPriorityQueue = new PriorityQueue<>();
        maxPriorityQueue = new PriorityQueue<>(false);
    }

    @Test
    void priorityQueueIsInitializedAsEmpty() {
        assertThat(minPriorityQueue.size()).isZero();
        assertThat(minPriorityQueue.isEmpty()).isTrue();

        assertThat(maxPriorityQueue.size()).isZero();
        assertThat(maxPriorityQueue.isEmpty()).isTrue();
    }

    @Test
    void offerElementToPriorityQueue() {
        minPriorityQueue.offer(50);
        assertThat(minPriorityQueue.isEmpty()).isFalse();
        assertThat(minPriorityQueue.size()).isOne();
        assertThat(minPriorityQueue.peek()).isEqualTo(50);

        maxPriorityQueue.offer(50);
        assertThat(maxPriorityQueue.isEmpty()).isFalse();
        assertThat(maxPriorityQueue.size()).isOne();
        assertThat(maxPriorityQueue.peek()).isEqualTo(50);
    }

    @Test
    void offerMultipleElementsToPriorityQueue() {
        int[] items = {50, 60, 10};
        for (int item : items) {
            minPriorityQueue.offer(item);
            maxPriorityQueue.offer(item);
        }

        assertThat(minPriorityQueue.isEmpty()).isFalse();
        assertThat(minPriorityQueue.size()).isEqualTo(items.length);
        assertThat(minPriorityQueue.peek()).isEqualTo(10);

        assertThat(maxPriorityQueue.isEmpty()).isFalse();
        assertThat(maxPriorityQueue.size()).isEqualTo(items.length);
        assertThat(maxPriorityQueue.peek()).isEqualTo(60);
    }

    @Test
    void pollElementFromPriorityQueue() {
        int[] items = {50, 60, 10};
        for (int item : items) {
            minPriorityQueue.offer(item);
            maxPriorityQueue.offer(item);
        }

        assertThat(minPriorityQueue.poll()).isEqualTo(10);
        assertThat(minPriorityQueue.size()).isEqualTo(items.length - 1);

        assertThat(maxPriorityQueue.poll()).isEqualTo(60);
        assertThat(maxPriorityQueue.size()).isEqualTo(items.length - 1);
    }

    @Test
    void peekOnEmptyPriorityQueue() {
        assertThat(minPriorityQueue.peek()).isNull();
        assertThat(maxPriorityQueue.peek()).isNull();
    }

    @Test
    void pollOnEmptyPriorityQueue() {
        assertThat(minPriorityQueue.poll()).isNull();
        assertThat(maxPriorityQueue.poll()).isNull();
    }

    @Test
    void offerNullElementToPriorityQueue() {
        assertThat(minPriorityQueue.offer(null)).isFalse();
        assertThat(maxPriorityQueue.offer(null)).isFalse();

        assertThat(minPriorityQueue.size()).isZero();
        assertThat(maxPriorityQueue.size()).isZero();
    }
}
