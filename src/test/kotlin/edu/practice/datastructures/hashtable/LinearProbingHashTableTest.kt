package edu.practice.datastructures.hashtable

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LinearProbingHashTableTest {

    private lateinit var hashTable: HashTable<String, Int>

    @BeforeEach
    fun setup() {
        hashTable = LinearProbingHashTable()
    }

    @Test
    fun hashTableIsInitializedAsEmpty() {
        assertThat(hashTable.isEmpty()).isTrue()
        assertThat(hashTable.size()).isZero()
    }


    // Put tests indirectly tests Get method
    @Test
    fun putAnElementInTable() {
        val key = "key1"
        val `val` = 50
        hashTable.put(key, `val`)

        assertThat(hashTable.isEmpty()).isFalse()
        assertThat(hashTable.size()).isOne()
        assertThat(hashTable.get(key)).isEqualTo(`val`)
    }

    @Test
    fun updateExistingElementInTable() {
        val key = "key1"
        val `val` = 50
        hashTable.put(key, `val`)
        val newVal = 60
        hashTable.put(key, newVal)

        assertThat(hashTable.get(key)).isNotEqualTo(`val`)
        assertThat(hashTable.get(key)).isEqualTo(newVal)
    }

    @Test
    fun putMultipleElementsInTable() {
        val vals = intArrayOf(50, 60, 70)
        val keys = arrayOf("key1", "key2", "key3")
        for (i in keys.indices) {
            hashTable.put(keys[i], vals[i])
        }

        for (i in keys.indices) {
            assertThat(hashTable.get(keys[i])).isEqualTo(vals[i])
        }
    }

    @Test
    fun checkIfTableIntactAfterResizing() {
        val lengthForInsert = 14
        for (i in 0..<lengthForInsert) {
            hashTable.put("key$i", i)
        }

        assertThat(hashTable.size()).isEqualTo(lengthForInsert)
        assertThat(hashTable.get("key12")).isEqualTo(12)
    }

    @Test
    fun getNonExistentElementInTable() {
        assertThat(hashTable.get("Non Existent Key")).isNull()
    }

    @Test
    fun removeAnElementInTable() {
        val vals = intArrayOf(50, 60, 70)
        val keys = arrayOf("key1", "key2", "key3")
        for (i in keys.indices) {
            hashTable.put(keys[i], vals[i])
        }

        assertThat(hashTable.remove(keys[1])).isTrue()
        assertThat(hashTable.get(keys[1])).isNull()
    }
}