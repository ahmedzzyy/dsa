package edu.practice.algorithms.string

import org.assertj.core.api.Assertions.assertThat
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
}