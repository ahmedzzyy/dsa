package edu.practice.datastructures.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DoublyLinkedListTest {

    DoublyLinkedList<Integer> integerDLL;

    @BeforeEach
    void setup() {
        integerDLL = new DoublyLinkedList<>();
    }

    @Test
    void listIsInitializedAsEmpty() {
        assertThat(integerDLL.isEmpty()).isTrue();
        assertThat(integerDLL.size()).isZero();
    }

    @Test
    void addFirstAnElementToDLL() {
        int item = 50;
        integerDLL.addFirst(item);

        assertThat(integerDLL.isEmpty()).isFalse();
        assertThat(integerDLL.size()).isOne();
        assertThat(integerDLL.getFirst()).isEqualTo(item);
    }

    @Test
    void addFirstMultipleElementsToDLL() {
        int[] item = {50, 60, 70};
        integerDLL.addFirst(item[0]);
        integerDLL.addFirst(item[1]);
        integerDLL.addFirst(item[2]);

        assertThat(integerDLL.isEmpty()).isFalse();
        assertThat(integerDLL.size()).isEqualTo(item.length);
        assertThat(integerDLL.getFirst()).isEqualTo(item[2]);
    }

    @Test
    void addLastAnElementToDLL() {
        int item = 50;
        integerDLL.addLast(item);

        assertThat(integerDLL.isEmpty()).isFalse();
        assertThat(integerDLL.size()).isOne();
        assertThat(integerDLL.getLast()).isEqualTo(item);
    }

    @Test
    void addLastMultipleElementsToDLL() {
        int[] item = {50, 60, 70};
        integerDLL.addLast(item[0]);
        integerDLL.addLast(item[1]);
        integerDLL.addLast(item[2]);

        assertThat(integerDLL.isEmpty()).isFalse();
        assertThat(integerDLL.size()).isEqualTo(item.length);
        assertThat(integerDLL.getLast()).isEqualTo(item[2]);
    }

    @Test
    void removeFirstAnElementFromDLL() {
        int item = 50;
        integerDLL.addFirst(item);

        assertThat(integerDLL.removeFirst()).isEqualTo(item);
        assertThat(integerDLL.size()).isZero();
        assertThat(integerDLL.isEmpty()).isTrue();
    }

    @Test
    void removeFirstMultipleElementsFromDLL() {
        int[] item = {50, 60, 70};
        integerDLL.addFirst(item[0]);
        integerDLL.addFirst(item[1]);
        integerDLL.addFirst(item[2]);

        assertThat(integerDLL.removeFirst()).isEqualTo(item[2]);
        assertThat(integerDLL.size()).isEqualTo((item.length - 1));
        assertThat(integerDLL.isEmpty()).isFalse();
    }

    @Test
    void removeLastAnElementFromDLL() {
        int item = 50;
        integerDLL.addLast(item);

        assertThat(integerDLL.removeLast()).isEqualTo(item);
        assertThat(integerDLL.size()).isZero();
        assertThat(integerDLL.isEmpty()).isTrue();
    }

    @Test
    void removeLastMultipleElementsFromDLL() {
        int[] item = {50, 60, 70};
        integerDLL.addLast(item[0]);
        integerDLL.addLast(item[1]);
        integerDLL.addLast(item[2]);

        assertThat(integerDLL.removeLast()).isEqualTo(item[2]);
        assertThat(integerDLL.size()).isEqualTo((item.length - 1));
        assertThat(integerDLL.isEmpty()).isFalse();
    }

    @Test
    void getFirstLastFromEmptyDLL() {
        assertThatThrownBy(() -> integerDLL.getFirst())
                .isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(() -> integerDLL.getLast())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void checkIfElementExistsInDLL() {
        int[] item = {50, 60, 70};
        integerDLL.addLast(item[0]);
        integerDLL.addLast(item[1]);
        integerDLL.addLast(item[2]);

        assertThat(integerDLL.contains(item[1])).isTrue();
        assertThat(integerDLL.contains(80)).isFalse();
    }
}
