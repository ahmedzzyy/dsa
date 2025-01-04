package edu.practice.datastructures

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StackTest {

    private lateinit var integerStack: Stack<Int>

    @BeforeEach
    fun setup() {
        integerStack = Stack()
    }

    @Test
    fun stackIsInitializedAsEmpty() {
        assertThat(integerStack.isEmpty()).isTrue()
        assertThat(integerStack.size()).isZero()
    }
    
    @Test
    fun pushAnElementToStack() {
        integerStack.push(50)

        assertThat(integerStack.isEmpty()).isFalse()
        assertThat(integerStack.size()).isOne()
        assertThat(integerStack.peek()).isEqualTo(50)
    }

    @Test
    fun pushMoreThanOneElementToStack() {
        integerStack.push(50)
        integerStack.push(60)
        integerStack.push(70)

        assertThat(integerStack.isEmpty()).isFalse()
        assertThat(integerStack.size()).isEqualTo(3)
        assertThat(integerStack.peek()).isEqualTo(70)
    }

    // Testing "Pop" functionality indirectly tests "Peek"

    @Test
    fun popAnElementFromSingleElementStack() {
        integerStack.push(50)

        assertThat(integerStack.pop()).isEqualTo(50)
        assertThat(integerStack.size()).isZero()
        assertThat(integerStack.isEmpty()).isTrue()
    }

    @Test
    fun popMoreThanOneElementFromStack() {
        integerStack.push(50)
        integerStack.push(60)
        integerStack.push(70)

        assertThat(integerStack.pop()).isEqualTo(70)
        assertThat(integerStack.size()).isEqualTo(3 - 1)
        assertThat(integerStack.isEmpty()).isFalse()
    }
    
    @Test
    fun popEmptyStack() {
        assertThatThrownBy { integerStack.pop() }
            .isInstanceOf(NoSuchElementException::class.java)
    }
}