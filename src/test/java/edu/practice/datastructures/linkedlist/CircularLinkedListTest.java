package edu.practice.datastructures.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CircularLinkedListTest {

    CircularLinkedList<Integer> integerCircularSLL;

    @BeforeEach
    void setup() {
        integerCircularSLL = new CircularLinkedList<>();
    }

    @Test
    void listIsInitializedAsEmpty() {
        assertThat(integerCircularSLL.isEmpty()).isTrue();
        assertThat(integerCircularSLL.size()).isZero();
    }

    @Test
    void addFirstAnElementToCircularSLL() {
        int item = 50;
        integerCircularSLL.addFirst(item);

        assertThat(integerCircularSLL.isEmpty()).isFalse();
        assertThat(integerCircularSLL.size()).isOne();
        assertThat(integerCircularSLL.getFirst()).isEqualTo(item);
    }

    @Test
    void addFirstMultipleElementsToCircularSLL() {
        int[] item = {50, 60, 70};
        integerCircularSLL.addFirst(item[0]);
        integerCircularSLL.addFirst(item[1]);
        integerCircularSLL.addFirst(item[2]);

        assertThat(integerCircularSLL.isEmpty()).isFalse();
        assertThat(integerCircularSLL.size()).isEqualTo(item.length);
        assertThat(integerCircularSLL.getFirst()).isEqualTo(item[2]);
    }

    @Test
    void addLastAnElementToCircularSLL() {
        int item = 50;
        integerCircularSLL.addLast(item);

        assertThat(integerCircularSLL.isEmpty()).isFalse();
        assertThat(integerCircularSLL.size()).isOne();
        assertThat(integerCircularSLL.getLast()).isEqualTo(item);
    }

    @Test
    void addLastMultipleElementsToCircularSLL() {
        int[] item = {50, 60, 70};
        integerCircularSLL.addLast(item[0]);
        integerCircularSLL.addLast(item[1]);
        integerCircularSLL.addLast(item[2]);

        assertThat(integerCircularSLL.isEmpty()).isFalse();
        assertThat(integerCircularSLL.size()).isEqualTo(item.length);
        assertThat(integerCircularSLL.getLast()).isEqualTo(item[2]);
    }

    @Test
    void removeFirstAnElementFromCircularSLL() {
        int item = 50;
        integerCircularSLL.addFirst(item);

        assertThat(integerCircularSLL.removeFirst()).isEqualTo(item);
        assertThat(integerCircularSLL.size()).isZero();
        assertThat(integerCircularSLL.isEmpty()).isTrue();
    }

    @Test
    void removeFirstMultipleElementsFromCircularSLL() {
        int[] item = {50, 60, 70};
        integerCircularSLL.addFirst(item[0]);
        integerCircularSLL.addFirst(item[1]);
        integerCircularSLL.addFirst(item[2]);

        assertThat(integerCircularSLL.removeFirst()).isEqualTo(item[2]);
        assertThat(integerCircularSLL.size()).isEqualTo((item.length - 1));
        assertThat(integerCircularSLL.isEmpty()).isFalse();
    }

    @Test
    void removeLastAnElementFromCircularSLL() {
        int item = 50;
        integerCircularSLL.addLast(item);

        assertThat(integerCircularSLL.removeLast()).isEqualTo(item);
        assertThat(integerCircularSLL.size()).isZero();
        assertThat(integerCircularSLL.isEmpty()).isTrue();
    }

    @Test
    void removeLastMultipleElementsFromCircularSLL() {
        int[] item = {50, 60, 70};
        integerCircularSLL.addLast(item[0]);
        integerCircularSLL.addLast(item[1]);
        integerCircularSLL.addLast(item[2]);

        assertThat(integerCircularSLL.removeLast()).isEqualTo(item[2]);
        assertThat(integerCircularSLL.size()).isEqualTo((item.length - 1));
        assertThat(integerCircularSLL.isEmpty()).isFalse();
    }

    @Test
    void getFirstLastFromEmptyCircularSLL() {
        assertThatThrownBy(() -> integerCircularSLL.getFirst())
                .isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(() -> integerCircularSLL.getLast())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void checkIfElementExistsInCircularSLL() {
        int[] item = {50, 60, 70};
        integerCircularSLL.addLast(item[0]);
        integerCircularSLL.addLast(item[1]);
        integerCircularSLL.addLast(item[2]);

        assertThat(integerCircularSLL.contains(item[1])).isTrue();
        assertThat(integerCircularSLL.contains(80)).isFalse();
    }
}
