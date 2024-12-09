package edu.practice.datastructures.hashtable;

public class ChainingHashTable<K, V> {

    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        Entry(K key, V value, Entry<K, V> next) {
            this(key, value);
            this.next = next;
        }
    }

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.7;

    private Entry<?, ?>[] table;

    private int size;

    public ChainingHashTable() {
        table = new Entry<?, ?>[INITIAL_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(K key) {
        return (key != null) ? Math.abs(key.hashCode() % table.length) : -1;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = table.length * 2;
        Entry<?, ?>[] oldTable = table;

        table = new Entry<?, ?>[newCapacity];
        size = 0;

        for (Entry<?, ?> entry : oldTable) {
            if (entry != null) {
                put((K) entry.key, (V) entry.value);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void put(K key, V value) {
        if ((double) size / table.length >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int index = hash(key);
        Entry<K, V> entry = (Entry<K, V>) table[index];

        for (; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                entry.value = value; // Update existing key's value
                return;
            }
        }

        Entry<K, V> prevEntry = (Entry<K, V>) table[index];
        table[index] = new Entry<>(key, value, prevEntry);
        size++;
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {
        int index = hash(key);

        for (Entry<?, ?> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                return (V) entry.value;
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public boolean remove(K key) {
        int index = hash(key);

        Entry<K, V> entry = (Entry<K, V>) table[index];
        for (Entry<K, V> prev = null; entry != null; prev = entry, entry = entry.next) {
            if (entry.key.equals(key)) {
                // Removal
                if (prev != null) {
                    prev.next = entry.next;
                } else {
                    table[index] = entry.next;
                }

                size--;

                return true;
            }
        }

        return false;
    }
}
