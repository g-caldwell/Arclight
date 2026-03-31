package com.caldwell.arclight.manager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// ********************************************************************************** //
// Title: Arclight                                                                    //
// Author: Gabriel Caldwell                                                           //
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022                                    //
// File: HashTable.java                                                               //
// Description: Hash table for verifying bodies                                       //
// ********************************************************************************** //

public class HashTable<K, V> implements Serializable {

    // fields
    //==================================================================================================================
    private final static int standardCapacity = 5000;
    private final static double loadFact = 0.8;
    private double maxLoadFact;
    private int capacity = 0;
    private int threshold = 0;
    private int size = 0;
    private LinkedList<Entry<K,V>>[] table;
    //==================================================================================================================



    // constructors
    //******************************************************************************************************************
    public HashTable() {
        this(standardCapacity, loadFact);
    }

    public HashTable(int c) {
        this(c, loadFact);
    }

    public HashTable(int capacity, double maxLoadFact) {
        if (capacity >= 0 && maxLoadFact >= 0) {
            this.capacity = Math.max(standardCapacity, capacity);
            this.maxLoadFact = maxLoadFact;
            this.threshold = (int)(this.capacity*maxLoadFact);
            this.table = new LinkedList[this.capacity];
        }
    }
    //******************************************************************************************************************



    // getters
    //==================================================================================================================
    public int getCapacity() {
        return this.capacity;
    }

    public int getThreshold() {
        return this.threshold;
    }

    public int getSize() {
        return this.size;
    }

    public LinkedList<Entry<K, V>>[] getTable() {
        return this.table;
    }
    //==================================================================================================================



    // methods
    //******************************************************************************************************************
    public boolean isEmpty() {
        return size == 0;
    }

    public int index(int key) {
        return (key & 0x7FFFFFFF) % this.capacity;
    }

    public boolean containsKey(K key) {
        return hasKey(key);
    }

    public boolean hasKey(K key) {
        int index = index(key.hashCode());
        return seekEntry(index, key) != null;
    }

    public V get(K key) {
        V value = null;
        if (key != null) {
            int index = index(key.hashCode());
            Entry<K, V> ent = seekEntry(index, key);
            if (ent != null) {
                value = ent.val;
            }
        }
        return value;
    }

    private Entry<K, V> seekEntry(int index, K key) {
        if (key == null) {
            return null;
        }
        LinkedList<Entry<K, V>> ll = table[index];
        if (ll == null) {
            return null;
        }
        for (Entry<K, V> e : ll) {
            if (e.key.equals(key)) {
                return e;
            }
        }
        return null;
    }

    private void resize() {
        capacity *= 2;
        threshold = (int)(capacity*maxLoadFact);
        LinkedList<Entry<K, V>>[] nt = new LinkedList[capacity];

        for (int i = 0; i<table.length; i++) {
            if (table[i] != null) {
                for (Entry<K, V> e : table[i]) {
                    int index = index(e.hash);
                    LinkedList<Entry<K, V>> ll = nt[index];
                    if (ll == null) {
                        nt[index] = ll = new LinkedList<>();
                    }
                    ll.add(e);
                }
                table[i].clear();
                table[i] = null;
            }
        }
        table = nt;
    }

    public int hash(int i) {
        // hash within a range of 100,000 elements
        return i%100000;
    }

    public int stringHash(String s) {
        int key = 0;
        // ascii code for string
        try {
            for (int i = 0; i<s.length(); i++) {
                char c = s.charAt(i);
                key += c;
            }
        }
        // catch null values
        catch (NullPointerException nullPointerException) {
            System.out.println("string is null, enter a valid string");
            key = -1;
        }
        return key;
    }

    public List<K> listKeys() {
        List<K> keys = new ArrayList<>(getSize());
        for (LinkedList<Entry<K, V>> ll : table) {
            if (ll != null) {
                for (Entry<K, V> e : ll) {
                    keys.add(e.key);
                }
            }
        }
        return keys;
    }

    public List<V> listVals() {
        List<V> vals = new ArrayList<>(getSize());
        for (LinkedList<Entry<K, V>> ll : table) {
            if (ll != null) {
                for (Entry<K, V> e : ll) {
                    vals.add(e.val);
                }
            }
        }
        return vals;
    }
    //******************************************************************************************************************



    // insert, remove, search
    //==================================================================================================================
    public void add(K key, V val) {
        insert(key, val);
    }

    public void addAll(ArrayList<K> keys, ArrayList<V> vals) {
        // create the keys array from the vals array in some other method then pass them both here

        ArrayList<Entry<K, V>> entries = new ArrayList<>();

        for (int i = 0; i<keys.size(); i++) {
            entries.add(new Entry<K, V>(keys.get(i), vals.get(i)));
        }
    }

    public void insert(K key, V val) {
        if (key != null) {
            Entry<K, V> ent = new Entry<>(key, val);
            int index = index(ent.hash);
            insertEntry(index, ent);
        }
    }

    public void insertEntry(int index, Entry<K, V> ent) {
        LinkedList<Entry<K, V>> ll = table[index];
        if (ll == null) {
            table[index] = ll = new LinkedList<>();
        }
        Entry<K, V> entry = seekEntry(index, ent.key);
        if (entry == null) {
            ll.add(ent);
            if (size++ > threshold) {
                resize();
            }
        }
        else {
            entry.val = ent.val;
        }
    }

    public void remove(K key) {
        if (key != null) {
            int index = index(key.hashCode());
            removeEntry(index, key);
        }
    }

    private void removeEntry(int index, K key) {
        Entry<K, V> ent = seekEntry(index, key);
        if (ent != null) {
            LinkedList<Entry<K, V>> ll = table[index];
            ll.remove(ent);
            size--;
        }
    }

    public void emptyTable() {
        Arrays.fill(table, null);
        this.size = 0;
    }

    public void search(int key) {
        int index = hash(key);
    }
    //==================================================================================================================

}
