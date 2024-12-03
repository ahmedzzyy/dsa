package edu.practice.datastructures.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<E> {

    private final ArrayList<E> tree;
    private int size;

    public BinaryTree() {
        tree = new ArrayList<>();
        size = 0;
    }

    public int size() { return size; }

    public boolean isEmpty() {
        return (size == 0);
    }

    private int getLeftChildIndex(int parentIndex) {
        return ((2 * parentIndex) + 1);
    }

    private int getRightChildIndex(int parentIndex) {
        return ((2 * parentIndex) + 2);
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    public void add(E item) {
        tree.add(item);
        size++;
    }

    public boolean remove(E item) {
        int index = tree.indexOf(item);

        if (index == -1) { return false; }

        tree.remove(index);
        size--;

        return true;
    }

    public boolean contains(E item) {
        return tree.contains(item);
    }

    public E getRoot() {
        if (isEmpty()) {
            return null;
        }

        return tree.getFirst();
    }

    public boolean isLeaf(E item) {
        int index = tree.indexOf(item);

        if (index == -1) {
            return false;
        }

        return (!hasLeftChild(index) && !hasRightChild(index));
    }

    private int heightFromIndex(int index) {
        if (index >= size) {
            return -1;
        }

        int leftHeight = heightFromIndex(getLeftChildIndex(index));
        int rightHeight = heightFromIndex(getRightChildIndex(index));

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int height() {
        return heightFromIndex(0);
    }

    private void inorderTraversalToGivenList(List<E> resultList, int indexFrom) {
        if (indexFrom >= size) { return; }

        inorderTraversalToGivenList(resultList, getLeftChildIndex(indexFrom));

        resultList.add(tree.get(indexFrom));

        inorderTraversalToGivenList(resultList, getRightChildIndex(indexFrom));
    }

    public List<E> inorderTraversal() {
        List<E> result = new ArrayList<>();
        inorderTraversalToGivenList(result, 0);
        return result;
    }

    private void preorderTraversalToGivenList(List<E> resultList, int indexFrom) {
        if (indexFrom >= size) { return; }

        resultList.add(tree.get(indexFrom));

        preorderTraversalToGivenList(resultList, getLeftChildIndex(indexFrom));

        preorderTraversalToGivenList(resultList, getRightChildIndex(indexFrom));
    }

    public List<E> preorderTraversal() {
        List<E> result = new ArrayList<>();
        preorderTraversalToGivenList(result, 0);
        return result;
    }

    private void postorderTraversalToGivenList(List<E> resultList, int indexFrom) {
        if (indexFrom >= size) { return; }

        postorderTraversalToGivenList(resultList, getLeftChildIndex(indexFrom));

        postorderTraversalToGivenList(resultList, getRightChildIndex(indexFrom));

        resultList.add(tree.get(indexFrom));
    }

    public List<E> postorderTraversal() {
        List<E> result = new ArrayList<>();
        postorderTraversalToGivenList(result, 0);
        return result;
    }
}
