package edu.practice.datastructures;

import java.util.NoSuchElementException;

public class Queue<E> {

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> front;
    private Node<E> rear;
    private int size;

    public Queue() { // Initializes empty Queue
        front = null;
        rear = null;
        size = 0;
    }

    public int size() { return size; }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean offer(E item) { // Add element to the rear of stack
        rear = new Node<>(item, rear);

        if (front == null) { // If no element in Queue
            front = rear;
        }

        size++;

        return true;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return front.data;
    }

    public E poll() {
        E item;
        item = peek();

        front = front.next;
        size--;

        if (isEmpty()) {
            rear = null;
        }

        return item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FRONT -> ");

        Node<E> current = front;

        if (current == null) {
            sb.append(" NULL ");
        }

        while (current != null) {
            sb.append(current.data);

            if (current.next != null) {
                sb.append(" -> ");
            }

            current = current.next;
        }

        sb.append(" <- REAR");

        return sb.toString();
    }
}
