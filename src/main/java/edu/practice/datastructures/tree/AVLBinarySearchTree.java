package edu.practice.datastructures.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AVLBinarySearchTree<E extends Comparable<E>> {

    private static class Node<E> {
        Node<E> left;
        E data;
        Node<E> right;
        int height;

        Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 0;
        }
    }

    private Node<E> root;
    private int size;

    public AVLBinarySearchTree() {
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

    private int height(Node<E> node) {
        return (node == null) ? -1 : node.height;
    }

    public int height() {
        return (root == null) ? -1 : root.height;
    }

    private void updateHeight(Node<E> node) {
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    private int balanceFactor(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node<E> rightRotate(Node<E> node) {
        if (node == null) {
            return null;
        }

        Node<E> x = node.left;
        Node<E> T2 = x.right;

        // Perform rotation
        x.right = node;
        node.left = T2;

        // Update heights
        updateHeight(x);
        updateHeight(node);

        return x;
    }

    private Node<E> leftRotate(Node<E> node) {
        if (node == null) {
            return null;
        }

        Node<E> x = node.right;
        Node<E> T2 = x.left;

        // Perform rotation
        x.left = node;
        node.right = T2;

        // Update heights
        updateHeight(x);
        updateHeight(node);

        return x;
    }

    private Node<E> leftRightRotate(Node<E> node) {
        if (node == null) {
            return null;
        }

        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    private Node<E> rightLeftRotate(Node<E> node) {
        if (node == null) {
            return null;
        }

        node.right = rightRotate(node.left);
        return leftRotate(node);
    }

    private Node<E> reBalance(Node<E> node) {
        int balanceFactor = balanceFactor(node);

        if (balanceFactor > 1) {
            // Left heavy
            if (balanceFactor(node.left) < 0) { // Left-Right Case
                node = leftRightRotate(node);
            } else { // Left-Left Case
                node = rightRotate(node);
            }
        } else if (balanceFactor < -1) {
            // Right heavy
            if (balanceFactor(node.right) > 0) { // Right-Left Case
                node = rightLeftRotate(node);
            } else { // Right-Right Case
                node = leftRotate(node);
            }
        }

        updateHeight(node);
        return node;
    }

    private Node<E> insert(E item, Node<E> currentNode) {

        if (currentNode == null) {
            size++;
            return new Node<>(item);
        }

        int itemComparedToNode = item.compareTo(currentNode.data);

        if (itemComparedToNode < 0) {
            currentNode.left =  insert(item, currentNode.left);
        } else if (itemComparedToNode > 0) {
            currentNode.right = insert(item, currentNode.right);
        } else {
            return currentNode; // Duplicate Values disallowed
        }

        updateHeight(currentNode);
        return reBalance(currentNode);
    }

    public void add(E item) {
        root = insert(item, root);
    }

    private Node<E> findSmallestNode(Node<E> node) {
        if (node == null) {
            return null;
        }

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    private Node<E> delete(E item, Node<E> currentNode) {
        if (currentNode == null) { return null; }

        int itemComparedToNodeData = item.compareTo(currentNode.data);

        if (itemComparedToNodeData < 0) {
            currentNode.left = delete(item, currentNode.left);
        } else if (itemComparedToNodeData > 0) {
            currentNode.right = delete(item, currentNode.right);
        } else { // Node's data == item to remove

            if (currentNode.left == null || currentNode.right == null) {
                // No children or one child
                currentNode = (currentNode.left != null) ? currentNode.left : currentNode.right;
                size--;
            } else {
                // Both children
                Node<E> newSuccessorNode = findSmallestNode(currentNode.right);
                // Current Node copies data from the new successor's data
                currentNode.data = newSuccessorNode.data;
                // Remove the new successor's node which contains the value to delete
                currentNode.right = delete(newSuccessorNode.data, currentNode.right);
            }
        }

        if (currentNode == null) { // When the node becomes null after deletion
            return null;
        }

        updateHeight(currentNode);
        return reBalance(currentNode);
    }

    public void remove(E item) {
        root = delete(item, root);
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
