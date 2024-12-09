package edu.practice.datastructures.hashtable;

public class LinearProbingHashTable<K, V> {

    static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.7;

    private Entry<?, ?>[] table;

    private int size;

    public LinearProbingHashTable() {
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

        for (; table[index] != null; index = (index + 1) % table.length) {
            Entry<K, V> entry = (Entry<K, V>) table[index];
            if (entry.key.equals(key)) {
                entry.value = value; // Update existing key's value
                return;
            }
        }

        table[index] = new Entry<>(key, value);
        size++;
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {
        int index = hash(key);

        for (; table[index] != null; index = (index + 1) % table.length) {
            Entry<K, V> entry = (Entry<K, V>) table[index];
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public boolean remove(K key) {
        int index = hash(key);

        for (; table[index] != null; index = (index + 1) % table.length) {
            Entry<K, V> entry = (Entry<K, V>) table[index];
            if (entry.key.equals(key)) {
                entry.value = null; // Removal

                // Rehash subsequent keys
                index = (index + 1) % table.length;
                for (; table[index] != null; index = (index + 1) % table.length) {
                    Entry<K, V> rehashedEntry = (Entry<K, V>) table[index];

                    table[index] = null;
                    size--;

                    put(rehashedEntry.key, rehashedEntry.value); // Reinsert
                }

                return true;
            }
        }

        return false;
    }
}
