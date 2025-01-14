package edu.practice.datastructures.hashtable

import kotlin.math.abs

class LinearProbingHashTable<K, V>: HashTable<K, V> {

    companion object {
        private data class Entry<K, V>(val key: K, var value: V)

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

        var index = hash(key)
        while (table[index] != null) {
            val entry = table[index]
            if (entry?.key == key) {
                entry?.value = value // Update existing key's value
                return
            }

            index = (index + 1) % table.size
        }

        table[index] = Entry(key, value)
        size++
    }

    override fun get(key: K): V? {
        var index = hash(key)
        while (table[index] != null) {
            val entry = table[index]
            if (entry?.key == key) {
                return entry?.value
            }

            index = (index + 1) % table.size
        }

        return null
    }

    override fun remove(key: K): Boolean {
        var index = hash(key)
        while (table[index] != null) {
            val entry = table[index]
            if (entry?.key == key) {
                table[index] = null // Removal

                // Rehashing subsequent keys
                index = (index + 1) % table.size
                while (table[index] != null) {
                    val rehashedEntry = table[index]

                    table[index] = null
                    size--

                    rehashedEntry?.let { put(it.key, it.value) }
                    index = (index + 1) % table.size
                }

                return true
            }

            index = (index + 1) % table.size
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