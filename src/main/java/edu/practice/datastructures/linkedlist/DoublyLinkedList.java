package edu.practice.datastructures.linkedlist;

import java.util.NoSuchElementException;
import java.util.Objects;

public class DoublyLinkedList<E> {

    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E data, Node<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    private int size;
    private Node<E> first;
    private Node<E> last;

    public DoublyLinkedList() {// Initialize empty Linked List
    }

    public int size() { return size; }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void addFirst(E item) {
        first = new Node<>(null, item, first);

        if (last == null) {
            last = first;
        }

        size++;
    }

    public void addLast(E item) {
        // NOTE: I had difficulty in modifying SLL's addLast method to convert to DLL.
        // This was done after 2 failed tests
        // Remember to go through this once again.
        
        final Node<E> newNode = new Node<>(last, item, null);

        if (last == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }

        last = newNode;

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
        first = first.next;

        // First is now pointing to previously 2nd element of list, so we check if there was no other element
        if (first == null) {
            last = null;
        }

        size--;

        return item;
    }

    public E removeLast() {
        final E item = last.data;

        last = last.prev;
        if (last == null) { // That is, if there was only one node in the list
            first = null;
        } else {
            last.next = null;
        }

        size--;

        return item;
    }

    private boolean existsInList(E item, Node<E> firstNode) {
        if (firstNode == null) {
            return false;
        }

        if (Objects.equals(item, firstNode.data)) {
            return true;
        }

        return existsInList(item, firstNode.next);
    }

    public boolean contains(E item) {
        return existsInList(item, first);
    }
}
