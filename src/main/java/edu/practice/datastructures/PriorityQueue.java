package edu.practice.datastructures;

public class PriorityQueue<E extends Comparable<E>> {

    private final Heap<E> heap;

    public PriorityQueue() {
        this(true);
    }

    public PriorityQueue(boolean isMinHeap) {
        this.heap = new Heap<>(isMinHeap);
    }

    public boolean offer(E data) {
        if (data == null) {
            return false;
        }

        heap.insert(data);
        return true;
    }

    public E poll() {
        if (isEmpty()) {
            return null;
        }

        return heap.remove();
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }

        return heap.peek();
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
