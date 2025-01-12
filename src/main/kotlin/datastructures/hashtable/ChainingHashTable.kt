package edu.practice.datastructures.hashtable

import kotlin.math.abs

class ChainingHashTable<K, V>: HashTable<K, V> {

    companion object {
        private data class Entry<K, V>(val key: K, var value: V, var next: Entry<K, V>? = null)

        private const val INITIAL_CAPACITY = 16
        private const val LOAD_FACTOR_THRESHOLD = 0.7
    }

    private var table: Array<Entry<K, V>?> = arrayOfNulls(INITIAL_CAPACITY)
    private var size = 0

    private fun hash(key: K): Int {
        return abs(key.hashCode() % table.size)
    }

    override fun put(key: K, value: V) {
        if (size.toDouble() / table.size >= LOAD_FACTOR_THRESHOLD) {
            resize()
        }

        val index = hash(key)
        var entry = table[index]

        while (entry != null) {
            if (entry.key == key) {
                entry.value = value // Update existing key's value
                return
            }

            entry = entry.next
        }

        val prevEntry = table[index]
        table[index] = Entry(key, value, prevEntry)
        size++
    }

    override fun get(key: K): V? {
        val index = hash(key)
        var entry = table[index]
        while (entry != null) {
            if (entry.key == key) {
                return entry.value
            }

            entry = entry.next
        }

        return null
    }

    override fun remove(key: K): Boolean {
        val index = hash(key)
        var entry = table[index]
        var prev: Entry<K, V>? = null
        while (entry != null) {
            if (entry.key == key) { // Removal
                if (prev != null) {
                    prev.next = entry.next
                } else {
                    table[index] = entry.next
                }

                size--
                return true
            }

            prev = entry
            entry = entry.next
        }

        return false
    }

    private fun resize() {
        val newCapacity = table.size * 2
        val oldTable: Array<Entry<K, V>?> = table

        table = arrayOfNulls(newCapacity)
        size = 0

        for (entry in oldTable) {
            entry?.let { put(it.key, it.value) }
        }
    }

    override fun size(): Int = size

    override fun isEmpty(): Boolean = (size == 0)
}