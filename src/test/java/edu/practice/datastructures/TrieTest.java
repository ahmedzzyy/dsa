package edu.practice.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrieTest {

    // [NOTE] Implemented tests cover almost all cases. Some edge cases might not have been implemented
    // [NOTE] Contributions are appreciated.

    Trie trie;

    @BeforeEach
    void setup() {
        trie = new Trie();
    }

    @Test
    void trieIsInitializedAsEmpty() {
        assertThat(trie.isEmpty()).isTrue();
        assertThat(trie.size()).isZero();
    }

    @Test
    void insertWordsAndSearch() {
        trie.insert("apple");
        trie.insert("app");

        assertThat(trie.search("apple")).isTrue();
        assertThat(trie.search("app")).isTrue();
        assertThat(trie.search("ap")).isFalse();

        assertThat(trie.startsWith("app")).isTrue();
        assertThat(trie.startsWith("appl")).isTrue();
        assertThat(trie.startsWith("b")).isFalse();
    }

    @Test
    void deleteElementsFromTrie() {
        trie.insert("apple");
        trie.insert("app");

        assertThat(trie.delete("apple")).isTrue();
        assertThat(trie.search("apple")).isFalse();
        assertThat(trie.search("app")).isTrue(); // "app" shouldn't be affected

        assertThat(trie.delete("app")).isTrue();
        assertThat(trie.search("app")).isFalse();

        assertThat(trie.isEmpty()).isTrue();
    }

    @Test
    void deleteNonExistentElementFromTrie() {
        trie.insert("cat");

        assertThat(trie.delete("dog")).isFalse();
    }

    @Test
    void deletePrefixOfAnotherWord() {
        trie.insert("bat");
        trie.insert("batman");

        trie.delete("bat");

        assertThat(trie.search("batman")).isTrue();
    }

    @Test
    void deleteSharingPrefixOfAnotherWord() {
        trie.insert("cat");
        trie.insert("car");

        trie.delete("car");

        assertThat(trie.search("cat")).isTrue();
    }
}
