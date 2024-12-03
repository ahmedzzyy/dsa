package edu.practice.datastructures.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SinglyLinkedListTest {

    SinglyLinkedList<Integer> integerSLL;

    @BeforeEach
    void setup() {
        integerSLL = new SinglyLinkedList<>();
    }

    @Test
    void listIsInitializedAsEmpty() {
        assertThat(integerSLL.isEmpty()).isTrue();
        assertThat(integerSLL.size()).isZero();
    }

    @Test
    void addFirstAnElementToSLL() {
        int item = 50;
        integerSLL.addFirst(item);

        assertThat(integerSLL.isEmpty()).isFalse();
        assertThat(integerSLL.size()).isOne();
        assertThat(integerSLL.getFirst()).isEqualTo(item);
    }

    @Test
    void addFirstMultipleElementsToSLL() {
        int[] item = {50, 60, 70};
        integerSLL.addFirst(item[0]);
        integerSLL.addFirst(item[1]);
        integerSLL.addFirst(item[2]);

        assertThat(integerSLL.isEmpty()).isFalse();
        assertThat(integerSLL.size()).isEqualTo(item.length);
        assertThat(integerSLL.getFirst()).isEqualTo(item[2]);
    }

    @Test
    void addLastAnElementToSLL() {
        int item = 50;
        integerSLL.addLast(item);

        assertThat(integerSLL.isEmpty()).isFalse();
        assertThat(integerSLL.size()).isOne();
        assertThat(integerSLL.getLast()).isEqualTo(item);
    }

    @Test
    void addLastMultipleElementsToSLL() {
        int[] item = {50, 60, 70};
        integerSLL.addLast(item[0]);
        integerSLL.addLast(item[1]);
        integerSLL.addLast(item[2]);

        assertThat(integerSLL.isEmpty()).isFalse();
        assertThat(integerSLL.size()).isEqualTo(item.length);
        assertThat(integerSLL.getLast()).isEqualTo(item[2]);
    }

    @Test
    void removeFirstAnElementFromSLL() {
        int item = 50;
        integerSLL.addFirst(item);

        assertThat(integerSLL.removeFirst()).isEqualTo(item);
        assertThat(integerSLL.size()).isZero();
        assertThat(integerSLL.isEmpty()).isTrue();
    }

    @Test
    void removeFirstMultipleElementsFromSLL() {
        int[] item = {50, 60, 70};
        integerSLL.addFirst(item[0]);
        integerSLL.addFirst(item[1]);
        integerSLL.addFirst(item[2]);

        assertThat(integerSLL.removeFirst()).isEqualTo(item[2]);
        assertThat(integerSLL.size()).isEqualTo((item.length - 1));
        assertThat(integerSLL.isEmpty()).isFalse();
    }

    @Test
    void removeLastAnElementFromSLL() {
        int item = 50;
        integerSLL.addLast(item);

        assertThat(integerSLL.removeLast()).isEqualTo(item);
        assertThat(integerSLL.size()).isZero();
        assertThat(integerSLL.isEmpty()).isTrue();
    }

    @Test
    void removeLastMultipleElementsFromSLL() {
        int[] item = {50, 60, 70};
        integerSLL.addLast(item[0]);
        integerSLL.addLast(item[1]);
        integerSLL.addLast(item[2]);

        assertThat(integerSLL.removeLast()).isEqualTo(item[2]);
        assertThat(integerSLL.size()).isEqualTo((item.length - 1));
        assertThat(integerSLL.isEmpty()).isFalse();
    }

    @Test
    void getFirstLastFromEmptySLL() {
        assertThatThrownBy(() -> integerSLL.getFirst())
                .isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(() -> integerSLL.getLast())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void checkIfElementExistsInSLL() {
        int[] item = {50, 60, 70};
        integerSLL.addLast(item[0]);
        integerSLL.addLast(item[1]);
        integerSLL.addLast(item[2]);

        assertThat(integerSLL.contains(item[1])).isTrue();
        assertThat(integerSLL.contains(80)).isFalse();
    }
}
