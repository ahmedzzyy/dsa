package edu.practice.datastructures.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BinarySearchTree<E extends Comparable<E>> {

    private static class Node<E> {
        Node<E> left;
        E data;
        Node<E> right;

        Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node<E> root;
    private int size;

    public BinarySearchTree() {
    }

    public int size() { return size; }

    public boolean isEmpty() {
        return (size == 0);
    }

    public E getRoot() {
        if (isEmpty()) {
            return null;
        }

        return root.data;
    }

    private Node<E> addItemToNode(E item, Node<E> currentNode) {

        if (currentNode == null) {
            return new Node<>(item);
        }

        if (item.compareTo(currentNode.data) < 0) {
            currentNode.left =  addItemToNode(item, currentNode.left);
        } else if (item.compareTo(currentNode.data) >= 0) {
            currentNode.right = addItemToNode(item, currentNode.right);
        }

        return currentNode;
    }

    public void add(E item) {
        root = addItemToNode(item, root);

        size++;
    }

    private Node<E> findSmallestNode(Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            return node;
        }

        return findSmallestNode(node.left);
    }

    private Node<E> removeNodeWithItem(E item, Node<E> currentNode) {
        if (currentNode == null) { return null; }

        int itemComparedToNodeData = item.compareTo(currentNode.data);

        if (itemComparedToNodeData < 0) {
            currentNode.left = removeNodeWithItem(item, currentNode.left);
        } else if (itemComparedToNodeData > 0) {
            currentNode.right = removeNodeWithItem(item, currentNode.right);
        } else { // Node's data == item to remove

            if (currentNode.left == null && currentNode.right == null) {
                // No children
                return null;
            } else if (currentNode.left == null) {
                // Only right child
                return currentNode.right;
            } else if (currentNode.right == null) {
                // Only right child
                return currentNode.left;
            } else {
                // Both children
                Node<E> newSuccessorNode = findSmallestNode(currentNode.right);
                // Current Node copies data from the new successor's data
                currentNode.data = newSuccessorNode.data;
                // Remove the new successor's node which contains the value to delete
                currentNode.right = removeNodeWithItem(newSuccessorNode.data, currentNode.right);
            }
        }

        return currentNode;
    }

    public void remove(E item) {
        root = removeNodeWithItem(item, root);

        size--;
    }

    private <T> T checkIfItemPresentInNode(E item, Node<E> currentNode, Function<Node<E>, T> action) {
        if (currentNode == null) {
            return action.apply(null);
        }

        int itemComparedToNodeData = item.compareTo(currentNode.data);

        if (itemComparedToNodeData < 0) {
            return checkIfItemPresentInNode(item, currentNode.left, action);
        } else if (itemComparedToNodeData > 0) {
            return checkIfItemPresentInNode(item, currentNode.right, action);
        } else { // Node's data == item to check for
            return action.apply(currentNode);
        }
    }

    public boolean contains(E item) {
        return checkIfItemPresentInNode(item, root, node -> node != null && node.data.equals(item));
    }


    public boolean isLeaf(E item) {
        return checkIfItemPresentInNode(item, root, node ->
                node.left == null && node.right == null
        );
    }

    private int calculateHeightOfNode(Node<E> node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = calculateHeightOfNode(node.left);
        int rightHeight = calculateHeightOfNode(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int height() {
        return calculateHeightOfNode(root);
    }

    private void inorderTraversalToGivenList(List<E> resultList, Node<E> currentNode) {
        if (currentNode == null) { return; }

        inorderTraversalToGivenList(resultList, currentNode.left);

        resultList.add(currentNode.data);

        inorderTraversalToGivenList(resultList, currentNode.right);
    }

    public List<E> inorderTraversal() {
        List<E> result = new ArrayList<>();
        inorderTraversalToGivenList(result, root);
        return result;
    }

    private void preorderTraversalToGivenList(List<E> resultList, Node<E> currentNode) {
        if (currentNode == null) { return; }

        resultList.add(currentNode.data);

        preorderTraversalToGivenList(resultList, currentNode.left);

        preorderTraversalToGivenList(resultList, currentNode.right);
    }

    public List<E> preorderTraversal() {
        List<E> result = new ArrayList<>();
        preorderTraversalToGivenList(result, root);
        return result;
    }

    private void postorderTraversalToGivenList(List<E> resultList, Node<E> currentNode) {
        if (currentNode == null) { return; }

        postorderTraversalToGivenList(resultList, currentNode.left);

        postorderTraversalToGivenList(resultList, currentNode.right);

        resultList.add(currentNode.data);
    }

    public List<E> postorderTraversal() {
        List<E> result = new ArrayList<>();
        postorderTraversalToGivenList(result, root);
        return result;
    }
}
