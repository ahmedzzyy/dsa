package edu.practice.datastructures.linkedlist;

import java.util.NoSuchElementException;
import java.util.Objects;

public class CircularLinkedList<E> {

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private int size;
    private Node<E> first;
    private Node<E> last;

    public CircularLinkedList() {// Initialize empty Linked List
    }

    public int size() { return size; }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void addFirst(E item) {
        first = new Node<>(item, first);

        if (first.next == null) { // If the added node is the only node, its next should point to itself
            first.next = first;
        }

        if (last == null) {
            last = first;
        }

        size++;
    }

    public void addLast(E item) {
        final Node<E> newNode = new Node<>(item, null);

        if (last == null) {
            newNode.next = newNode;
            last = newNode;
            first = newNode;
        } else {
            newNode.next = first;
            last.next = newNode;
            last = last.next;
        }

        size++;
    }

    public E getFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }

        return first.data;
    }

    public E getLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }

        return last.data;
    }

    public E removeFirst() {
        final E item = first.data;

        if (first == first.next) { // 1 element in List
            first = null;
            last = null;
        } else {
            first = first.next;
            last.next = first;
        }

        size--;

        return item;
    }

    public E removeLast() {
        final E item = last.data;

        if (first != last) { // More than one element in the list
            Node<E> currentNode = first;
            while (currentNode.next != last) {
                currentNode = currentNode.next;
            }

            currentNode.next = first;
            last = currentNode;
        } else {
            first = null;
            last = null;
        }

        size--;

        return item;
    }

    public boolean contains(E item) {
        if (first == null) {
            return false;
        }

        Node<E> currentNode = first;

        do {
            if (Objects.equals(item, currentNode.data)) {
                return true;
            }

            currentNode = currentNode.next;
        } while (currentNode != first);
        
        return false;
    }
}
