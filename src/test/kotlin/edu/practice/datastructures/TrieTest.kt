package edu.practice.datastructures

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TrieTest {

    // (As mentioned previously in the Java implementation)
    // [NOTE] Implemented tests cover almost all cases. Some edge cases might not have been implemented
    // [NOTE] Contributions are appreciated.

    private lateinit var trie: Trie

    @BeforeEach
    fun setup() {
        trie = Trie()
    }

    @Test
    fun trieIsInitializedAsEmpty() {
        assertThat(trie.isEmpty()).isTrue()
        assertThat(trie.size()).isZero()
    }

    @Test
    fun insertWordsAndSearch() {
        trie.insert("apple")
        trie.insert("app")

        assertThat(trie.search("apple")).isTrue()
        assertThat(trie.search("app")).isTrue()
        assertThat(trie.search("ap")).isFalse()

        assertThat(trie.startsWith("app")).isTrue()
        assertThat(trie.startsWith("appl")).isTrue()
        assertThat(trie.startsWith("b")).isFalse()
    }

    @Test
    fun deleteElementsFromTrie() {
        trie.insert("apple")
        trie.insert("app")

        assertThat(trie.delete("apple")).isTrue()
        assertThat(trie.search("apple")).isFalse()
        assertThat(trie.search("app")).isTrue() // "app" shouldn't be affected

        assertThat(trie.delete("app")).isTrue()
        assertThat(trie.search("app")).isFalse()

        assertThat(trie.isEmpty()).isTrue()
    }

    @Test
    fun deleteNonExistentElementFromTrie() {
        trie.insert("cat")

        assertThat(trie.delete("dog")).isFalse()
    }

    @Test
    fun deletePrefixOfAnotherWord() {
        trie.insert("bat")
        trie.insert("batman")

        trie.delete("bat")

        assertThat(trie.search("batman")).isTrue()
    }

    @Test
    fun deleteSharingPrefixOfAnotherWord() {
        trie.insert("cat")
        trie.insert("car")

        trie.delete("car")

        assertThat(trie.search("cat")).isTrue()
    }
}