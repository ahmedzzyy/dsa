package edu.practice.datastructures.hashtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChainingHashTableTest {

    ChainingHashTable<String, Integer> hashTable;

    @BeforeEach
    void setup() {
        hashTable = new ChainingHashTable<>();
    }

    @Test
    void hashTableIsInitializedAsEmpty() {
        assertThat(hashTable.isEmpty()).isTrue();
        assertThat(hashTable.size()).isZero();
    }

    // Put tests indirectly tests Get method

    @Test
    void putAnElementInTable() {
        String key = "key1"; int val = 50;
        hashTable.put(key, val);

        assertThat(hashTable.isEmpty()).isFalse();
        assertThat(hashTable.size()).isOne();
        assertThat(hashTable.get(key)).isEqualTo(val);
    }

    @Test
    void updateExistingElementInTable() {
        String key = "key1"; int val = 50;
        hashTable.put(key, val);
        int newVal = 60;
        hashTable.put(key, newVal);

        assertThat(hashTable.get(key)).isNotEqualTo(val);
        assertThat(hashTable.get(key)).isEqualTo(newVal);
    }

    @Test
    void putMultipleElementsInTable() {
        int[] vals = {50, 60, 70};
        String[] keys = {"key1", "key2", "key3"};
        for (int i = 0; i < keys.length; i++) {
            hashTable.put(keys[i], vals[i]);
        }

        for (int i = 0; i < keys.length; i++) {
            assertThat(hashTable.get(keys[i])).isEqualTo(vals[i]);
        }
    }

    @Test
    void checkIfTableIntactAfterResizing() {
        int lengthForInsert = 14;
        for (int i = 0; i < lengthForInsert; i++) {
            hashTable.put("key" + i, i);
        }

        assertThat(hashTable.size()).isEqualTo(lengthForInsert);
        assertThat(hashTable.get("key12")).isEqualTo(12);
    }

    @Test
    void getNonExistentElementInTable() {
        assertThat(hashTable.get("Non Existent Key")).isNull();
    }

    @Test
    void removeAnElementInTable() {
        int[] vals = {50, 60, 70};
        String[] keys = {"key1", "key2", "key3"};
        for (int i = 0; i < keys.length; i++) {
            hashTable.put(keys[i], vals[i]);
        }

        assertThat(hashTable.remove(keys[1])).isTrue();
        assertThat(hashTable.get(keys[1])).isNull();
    }
}
