package edu.practice.datastructures.hashtable

interface HashTable<K, V> {

    fun size(): Int
    fun isEmpty(): Boolean

    fun put(key: K, value: V)

    fun get(key: K): V?

    fun remove(key: K): Boolean
}