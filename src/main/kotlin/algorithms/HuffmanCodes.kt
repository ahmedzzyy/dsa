package edu.practice.algorithms

/**
 * Represents a node in a Huffman tree.
 *
 * **Internal nodes** are used for structure and have no character, only a combined frequency.
 *
 * @property char The character stored in the node. For internal nodes, this is `null`.
 * @property freq The frequency of the character, or for internal nodes, the combined frequency of the child nodes.
 * @property left The left child node. `null` for leaf nodes.
 * @property right The right child node. `null` for leaf nodes.
 */
data class HuffmanNode(
    val char: Char? = null, // Optionally null for internal nodes
    val freq: Int,
    val left: HuffmanNode? = null,
    val right: HuffmanNode? = null
)

/**
 * Builds a Huffman tree from a map of character frequencies.
 *
 * The algorithm constructs an optimal prefix-free binary tree using a greedy approach.
 *
 * @param frequencies A map each key is a character and its corresponding value is its frequency.
 * @return The root node of the constructed Huffman tree, or `null` if the frequency map is empty.
 */
fun buildHuffmanTree(frequencies: Map<Char, Int>): HuffmanNode? {
    val queue = java.util.PriorityQueue<HuffmanNode>(compareBy { it.freq })

    for ((char, freq) in frequencies) {
        queue.offer(HuffmanNode(char, freq))
    }

    while (queue.size > 1) {
        val left = queue.poll()
        val right = queue.poll()
        val merged = HuffmanNode(
            // char = null for internal nodes
            freq = left.freq + right.freq,
            left = left,
            right = right
        )
        queue.offer(merged)
    }

    return queue.poll()
}