package edu.practice.datastructures

class Trie {

    private data class Node(
        val children: MutableMap<Char, Node> = hashMapOf(),
        var isEndOfWord: Boolean = false
    )

    private val root: Node = Node()
    private var size: Int = 0

    fun insert(word: String) {
        var currentNode = root

        for (char in word) {
            currentNode = currentNode.children.computeIfAbsent(char) { Node() }
        }

        if (!currentNode.isEndOfWord) {
            currentNode.isEndOfWord = true
            size++
        }
    }

    fun search(word: String): Boolean {
        var currentNode = root

        for (char in word) {
            currentNode = currentNode.children[char] ?: return false
        }

        return currentNode.isEndOfWord
    }

    fun startsWith(prefix: String): Boolean {
        var currentNode = root

        for (char in prefix) {
            currentNode = currentNode.children[char] ?: return false
        }

        return true
    }

    fun delete(word: String): Boolean {
        return deleteFromWord(root, word)
    }

    private fun deleteFromWord(currentNode: Node, word: String, index: Int = 0): Boolean {
        if (index == word.length) { // Word matched
            if (!currentNode.isEndOfWord) { // But not end of word
                return false
            }

            currentNode.isEndOfWord = false
            size--
            return currentNode.children.isEmpty()
        }

        val char = word[index]
        val nextNode = currentNode.children[char] ?: return false

        val shouldDeleteCurrentNode = deleteFromWord(nextNode, word, index + 1)
        if (shouldDeleteCurrentNode) {
            currentNode.children.remove(char)
            return currentNode.children.isEmpty() && !currentNode.isEndOfWord
        }

        return index == 0
    }

    fun size(): Int = size

    fun isEmpty(): Boolean = (size == 0)
}