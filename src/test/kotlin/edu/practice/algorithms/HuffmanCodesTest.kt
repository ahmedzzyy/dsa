package edu.practice.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HuffmanCodesTest {

    @Test
    fun `buildHuffmanTree returns null for empty frequency map`() {
        val frequencies = emptyMap<Char, Int>()
        val tree = buildHuffmanTree(frequencies)
        assertThat(tree).isNull()
    }

    @Test
    fun `buildHuffmanTree returns single leaf node for one frequency`() {
        val frequencies = mapOf('a' to 5)
        val tree = buildHuffmanTree(frequencies)

        assertThat(tree).isNotNull
        assertThat(tree?.char).isEqualTo('a')
        assertThat(tree?.freq).isEqualTo(5)
        assertThat(tree?.left).isNull()
        assertThat(tree?.right).isNull()
    }

    @Test
    fun `buildHuffmanTree has correct total frequency`() {
        val frequencies = mapOf(
            'a' to 5,
            'b' to 9,
            'c' to 12,
            'd' to 13,
            'e' to 16,
            'f' to 45
        )
        val tree = buildHuffmanTree(frequencies)

        assertThat(tree).isNotNull
        assertThat(tree?.freq).isEqualTo(100)
    }
}