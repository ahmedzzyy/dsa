package edu.practice.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.assertj.core.api.Assertions.*;

public class StackTest {

    Stack<Integer> integerStack;

    @BeforeEach
    void setup() {
        integerStack = new Stack<>();
    }

    @Test
    void stackIsInitializedAsEmpty() {
        assertThat(integerStack.isEmpty()).isTrue();
        assertThat(integerStack.size()).isZero();
    }

    @Test
    void pushAnElementToStack() {
        int item = 50;
        integerStack.push(item);

        assertThat(integerStack.isEmpty()).isFalse();
        assertThat(integerStack.size()).isOne();
        assertThat(integerStack.peek()).isEqualTo(item);
    }

    @Test
    void pushMoreThanOneElementToStack() {
        int[] item = {50, 60, 70};
        integerStack.push(item[0]);
        integerStack.push(item[1]);
        integerStack.push(item[2]);

        assertThat(integerStack.isEmpty()).isFalse();
        assertThat(integerStack.size()).isEqualTo(item.length);
        assertThat(integerStack.peek()).isEqualTo(item[2]);
    }

    // Testing "Pop" functionality indirectly tests "Peek"

    @Test
    void popAnElementFromSingleElementStack() {
        int item = 50;
        integerStack.push(item);

        assertThat(integerStack.pop()).isEqualTo(item);
        assertThat(integerStack.size()).isZero();
        assertThat(integerStack.isEmpty()).isTrue();
    }

    @Test
    void popMoreThanOneElementFromStack() {
        int[] item = {50, 60, 70};
        integerStack.push(item[0]);
        integerStack.push(item[1]);
        integerStack.push(item[2]);

        assertThat(integerStack.pop()).isEqualTo(item[2]);
        assertThat(integerStack.size()).isEqualTo((item.length - 1));
        assertThat(integerStack.isEmpty()).isFalse();
    }

    @Test
    void popEmptyStack() {
        assertThatThrownBy(() -> integerStack.pop())
                .isInstanceOf(EmptyStackException.class);
    }
}
