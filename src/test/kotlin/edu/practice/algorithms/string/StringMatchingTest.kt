package edu.practice.algorithms.string

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StringMatchingTest {

    private lateinit var text: String
    private lateinit var pattern: String

    @BeforeEach
    fun setup() {
        text = "abracadabraabracadabra"
        pattern = "abra"
    }

    @Test
    fun naiveStringMatchesCorrectlyReturnsWhenPatternIsPresent() {
        assertThat(naiveStringMatch(text, pattern)).containsExactlyInAnyOrder(0, 7, 11, 18)
    }

    @Test
    fun naiveStringMatchesCorrectlyReturnsWhenPatternIsAbsent() {
        assertThat(naiveStringMatch(text, "non_existent_pattern")).isEmpty()
    }

    @Test
    fun rabinKarpMatchesCorrectlyReturnsWhenPatternIsPresent() {
        assertThat(rabinKarpMatch(text, pattern)).containsExactlyInAnyOrder(0, 7, 11, 18)
    }

    @Test
    fun rabinKarpMatchesCorrectlyReturnsWhenPatternIsAbsent() {
        assertThat(rabinKarpMatch(text, "non_existent_pattern")).isEmpty()
    }

    @Test
    fun finiteAutomatonMatchesCorrectlyReturnsWhenPatternIsPresent() {
        assertThat(finiteAutomatonStringMatch(text, pattern)).containsExactlyInAnyOrder(0, 7, 11, 18)
    }

    @Test
    fun finiteAutomatonMatchesCorrectlyReturnsWhenPatternIsAbsent() {
        assertThat(finiteAutomatonStringMatch(text, "non_existent_pattern")).isEmpty()
    }

    @Test
    fun knuthMorrisPrattMatchesCorrectlyReturnsWhenPatternIsPresent() {
        assertThat(knuthMorrisPrattStringMatch(text, pattern)).containsExactlyInAnyOrder(0, 7, 11, 18)
    }

    @Test
    fun knuthMorrisPrattMatchesCorrectlyReturnsWhenPatternIsAbsent() {
        assertThat(knuthMorrisPrattStringMatch(text, "non_existent_pattern")).isEmpty()
    }

    @Test
    fun suffixArrayForEmptyString() {
        assertThat(buildSuffixArray("")).isEmpty()
    }

    @Test
    fun suffixArrayForSingleCharacter() {
        assertThat(buildSuffixArray("a")).containsExactly(0)
    }

    @Test
    fun suffixArrayForRepeatedCharacters() {
        assertThat(buildSuffixArray("aaaa")).containsExactly(3, 2, 1, 0)
    }

    @Test
    fun suffixArrayForBanana() {
        assertThat(buildSuffixArray("banana")).containsExactly(5, 3, 1, 0, 4, 2)
    }

    @Test
    fun computeLCPForSingleCharacter() {
        val text = "a"
        val suffixArray = buildSuffixArray(text) // Expected: [0]
        val lcp = computeLCP(text, suffixArray)
        assertThat(lcp).containsExactly(0)
    }

    @Test
    fun computeLCPForDistinctCharacters() {
        val text = "abcd"
        val suffixArray = buildSuffixArray(text)
        // None of the adjacent suffixes share a common prefix.
        val lcp = computeLCP(text, suffixArray)
        assertThat(lcp).containsExactly(0, 0, 0, 0)
    }

    @Test
    fun computeLCPForRepeatedCharacters() {
        val text = "aaaa"
        val suffixArray = buildSuffixArray(text)
        // Expected suffix array for "aaaa" is [3, 2, 1, 0]
        val lcp = computeLCP(text, suffixArray)
        assertThat(lcp).containsExactly(0, 1, 2, 3)
    }

    @Test
    fun computeLCPForBanana() {
        val text = "banana"
        val suffixArray = buildSuffixArray(text)
        // Expected suffix array for "banana" is [5, 3, 1, 0, 4, 2]
        val lcp = computeLCP(text, suffixArray)
        assertThat(lcp).containsExactly(0, 1, 3, 0, 0, 2)
    }

    @Test
    fun computeLCPThrowsExceptionForMismatchedSuffixArray() {
        val text = "abc"
        // Invalid suffix array whose size does not match the text length.
        val wrongSuffixArray = intArrayOf(0, 1)
        assertThatThrownBy { computeLCP(text, wrongSuffixArray) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Suffix array size must match text length.")
    }
}