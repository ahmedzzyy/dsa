package edu.practice.datastructures;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    private static class Node {
        Map<Character, Node> children;
        boolean isEndOfWord;

        public Node() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    private final Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void insert(String word) {
        if (word == null) { return; }

        Node currentNode = root;

        for (char c : word.toCharArray()) {
            currentNode = currentNode.children.computeIfAbsent(c,
                    character -> new Node());
        }

        if (!currentNode.isEndOfWord) {
            // Word is not inserted yet and last character is not marked as end of word
            currentNode.isEndOfWord = true;
            size++;
        }
    }

    public boolean search(String word) {
        if (word == null) { return false; }

        Node currentNode = root;

        for (char character : word.toCharArray()) {
            currentNode = currentNode.children.get(character);

            if (currentNode == null) {
                return false;
            }
        }

        return currentNode.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        if (prefix == null) { return false; }

        Node currentNode = root;

        for (char character : prefix.toCharArray()) {
            currentNode = currentNode.children.get(character);

            if (currentNode == null) {
                return false;
            }
        }

        return true;
    }

    public boolean delete(String word) {
        if (word == null) { return false; }
        return deleteWordFrom(root, word, 0);
    }

    private boolean deleteWordFrom(Node currentNode, String word, int index) {
        if (index == word.length()) {
            // Word is matched
            if (!currentNode.isEndOfWord) {
                return false;
            }

            currentNode.isEndOfWord = false;
            size--;
            return currentNode.children.isEmpty();
        }

        char character = word.charAt(index);
        Node nextNode = currentNode.children.get(character);

        if (nextNode == null) {
            return false;
        }

        boolean shouldDeleteCurrentNode = deleteWordFrom(nextNode, word, index + 1);

        if (shouldDeleteCurrentNode) {
            currentNode.children.remove(character);
            return currentNode.children.isEmpty() && !currentNode.isEndOfWord;
        }

        return index == 0;
    }
}
