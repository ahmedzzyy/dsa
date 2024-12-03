package edu.practice.datastructures;

import java.util.EmptyStackException;

public class Stack<E> {

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> top;
    private int size;

    public Stack() { // Initializes empty stack
        top = null;
        size = 0;
    }

    public int size() { return size; }

    public void push(E item) {
        top = new Node<>(item, top);
        size++;
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    public E pop() {
        E item;
        item = peek();

        top = top.next;
        size--;

        return item;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TOP -> ");

        Node<E> current = top;
        while (current != null) {
            sb.append(current.data);

            if (current.next != null) {
                sb.append(" -> ");
            }

            current = current.next;
        }

        sb.append(" NULL");

        return sb.toString();
    }
}
