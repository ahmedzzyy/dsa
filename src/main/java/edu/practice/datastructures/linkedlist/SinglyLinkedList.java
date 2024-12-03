package edu.practice.datastructures.linkedlist;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {

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

    public SinglyLinkedList() {// Initialize empty Linked List
    }

    public int size() { return size; }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void addFirst(E item) {
        first = new Node<>(item, first);

        if (last == null) {
            last = first;
        }

        size++;
    }

    public void addLast(E item) {
        final Node<E> newNode = new Node<>(item, null);

        if (last == null) {
            last = newNode;
            first = newNode;
        } else {
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

        if (first != last) { // More than one element in the list
            Node<E> currentNode = first;
            while (currentNode.next != last)
                currentNode = currentNode.next;

            currentNode.next = null;
            last = currentNode;
        } else {
            first = null;
            last = null;
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
