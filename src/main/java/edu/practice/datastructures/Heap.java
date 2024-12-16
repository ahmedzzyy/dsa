package edu.practice.datastructures;

import java.util.Arrays;

public class Heap<E extends Comparable<E>> {

    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    E[] heap;
    private final boolean isMinHeap;
    private int size;

    public Heap() {
        this(true);
    }

    public Heap(boolean isMinHeap) {
        this(DEFAULT_INITIAL_CAPACITY, isMinHeap);
    }

    @SuppressWarnings("unchecked")
    public Heap(int initialCapacity, boolean isMinHeap) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException();
        }

        this.heap = (E[]) new Comparable[initialCapacity];
        this.isMinHeap = isMinHeap;
        size = 0;
    }

    private boolean compare(E a, E b) {
        return isMinHeap ? (a.compareTo(b) < 0) : (a.compareTo(b) > 0);
    }

    private void swap(int i, int j) {
        E temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            if (compare(heap[index], heap[parentIndex])) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int smallestOrLargest = index;

        if (leftChild < size && compare(heap[leftChild], heap[smallestOrLargest])) {
            smallestOrLargest = leftChild;
        }

        if (rightChild < size && compare(heap[rightChild], heap[smallestOrLargest])) {
            smallestOrLargest = leftChild;
        }

        if (smallestOrLargest != index) {
            swap(index, smallestOrLargest);
            heapifyDown(smallestOrLargest);
        }
    }

    public void insert(E data) {
        if (size == heap.length) {
            // Resize needed
            heap = Arrays.copyOf(heap, heap.length * 2);
        }

        heap[size] = data;
        heapifyUp(size);
        size++;
    }

    public E remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is Empty.");
        }

        E root = heap[0];
        heap[0] = heap[size - 1];
        size--;

        heapifyDown(0);
        return root;
    }

    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is Empty.");
        }

        return heap[0];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }
}
