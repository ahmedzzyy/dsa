package edu.practice.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

public class QueueTest {

    Queue<Integer> integerQueue;

    @BeforeEach
    void setup() {
        integerQueue = new Queue<>();
    }

    @Test
    void queueIsInitializedAsEmpty() {
        assertThat(integerQueue.isEmpty()).isTrue();
        assertThat(integerQueue.size()).isZero();
    }

    @Test
    void offerAnElementToQueue() {
        int item = 50;
        integerQueue.offer(item);

        assertThat(integerQueue.isEmpty()).isFalse();
        assertThat(integerQueue.size()).isOne();
        assertThat(integerQueue.peek()).isEqualTo(item);
    }

    @Test
    void offerMoreThanOneElementToQueue() {
        int[] item = {50, 60, 70};
        integerQueue.offer(item[0]);
        integerQueue.offer(item[1]);
        integerQueue.offer(item[2]);

        assertThat(integerQueue.isEmpty()).isFalse();
        assertThat(integerQueue.size()).isEqualTo(item.length);
        assertThat(integerQueue.peek()).isEqualTo(item[0]);
    }

    // Testing "Poll" functionality indirectly tests "Peek"

    @Test
    void pollAnElementFromSingleElementQueue() {
        int item = 50;
        integerQueue.offer(item);

        assertThat(integerQueue.poll()).isEqualTo(item);
        assertThat(integerQueue.size()).isZero();
        assertThat(integerQueue.isEmpty()).isTrue();
    }

    @Test
    void pollMoreThanOneElementFromQueue() {
        int[] item = {50, 60, 70};
        integerQueue.offer(item[0]);
        integerQueue.offer(item[1]);
        integerQueue.offer(item[2]);

        assertThat(integerQueue.poll()).isEqualTo(item[0]);
        assertThat(integerQueue.size()).isEqualTo((item.length - 1));
        assertThat(integerQueue.isEmpty()).isFalse();
    }

    @Test
    void pollEmptyQueue() {
        assertThatThrownBy(() -> integerQueue.poll())
                .isInstanceOf(NoSuchElementException.class);
    }
}
