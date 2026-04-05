package com.caldwell.arclight.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

// Used to store and search for objects with unique identifiers
public class BinarySearchTree<E> implements Serializable {

    protected Node<E> root;
    protected int size = 0;
    protected java.util.Comparator<E> comparator;

    // Default constructor
    public BinarySearchTree() {
        this.comparator = (element1, element2) -> ((Comparable<E>)element1).compareTo(element2);
    }

    // Defined comparator constructor
    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    // Prefill constructor
    public BinarySearchTree(E[] prefillArray) {
        this.comparator = (element1, element2) -> ((Comparable<E>)element1).compareTo(element2);
        addAllFromArray(prefillArray);
    }

    // Passed an arraylist of elements with an arbitrary type
    public BinarySearchTree(ArrayList<E> arr) {
        this.comparator = (element1, element2) -> ((Comparable<E>)element1).compareTo(element2);
        addAllFromArrayList(arr);
    }

    public Node<E> getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    public Comparator<E> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    // Create new node
    private Node<E> newNode(E e) {
        return new Node<E>(e);
    }

    // Add an element to the tree
    public void add(E element) {
        root = addHelper(root, newNode(element));
        size++;
    }

    // Does not handle == case only > or <
    private Node<E> addHelper(Node<E> root, Node<E> node) {

        // Beginning or end of the tree found, add a node in that position and return it
        if (root == null) {
            root = node;
            return root;
        }
        // Return the current node, then recursively call helper method on its left child
        else if (comparator.compare(node.element, root.element) < 0) {
            root.left = addHelper(root.left, node);
        }
        // Return the current node, the recursively call helper method on its right child
        else {
            root.right = addHelper(root.right, node);
        }
        return root;
    }

    public void addAllFromArray(E[] array) {
        for(E e : array) {
            add(e);
        }
    }

    public void addAllFromArrayList(ArrayList<E> arrayList) {
        for(E e : arrayList) {
            add(e);
        }
    }

    // delete
    public void delete(E element) {
        if (contains(element)) {
            removeHelper(root, element);
        }
    }

    // Returns null if node does not exist
    private Node<E> removeHelper(Node<E> root, E element) {
        // Returns root when a match is found
        if (root == null) {
            return root;
        }
        // Recursively call helper method on left child of the root
        else if (comparator.compare(element, root.element) < 0) {
            root.left = removeHelper(root.left, element);
        }
        // Recursively call helper method on right child of the root
        else if (comparator.compare(element, root.element) > 0) {
            root.right = removeHelper(root.right, element);
        }
        else {
            if (root.left == null && root.right == null) {
                root = null;
                return root;
            }
            // If only the right child exists
            else if (root.right != null) {
                root.element = successor(root);
                root.right = removeHelper(root.right, root.element);
            }
            // If only the left child exists
            else {
                root.element = predecessor(root);
                root.left = removeHelper(root.left, root.element);
            }
        }
        return root;
    }

    private E predecessor(Node<E> root) {
        // Find the largest element on the left side of the tree
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }
        return root.element;
    }

    private E successor(Node<E> root) {
        // Find the smallest element on the right side of the tree
        root = root.right;
        while(root.left != null) {
            root = root.left;
        }
        return root.element;
    }

    // Empty the tree by removing its root, dereferencing all its elements
    public void empty() {
        size = 0;
        root = null;
    }

    public boolean contains(E element) {
        return containsHelper(root, element);
    }

    private boolean containsHelper(Node<E> root, E element) {
        // Return false if no match is found
        if (root == null) {
            return false;
        }
        // Return true if a match is found
        else if (comparator.compare(element, root.element) == 0) {
            return true;
        }
        // Recursively call helper method on left child of the root
        else if (comparator.compare(element, root.element) < 0) {
            return containsHelper(root.left, element);
        }
        // Recursively call helper method on the right child of the root
        else {
            return containsHelper(root.right, element);
        }
    }

    // Returns the node that contains a given value
    public Node<E> retrieveNode(E element) {
        return retrieveNodeHelper(root, element);
    }

    // Does not handle case where node does not exist
    private Node<E> retrieveNodeHelper(Node<E> root, E element) {
        // Return root if match is found
        if (comparator.compare(element, root.element) == 0) {
            return root;
        }
        // Recursively call helper method on left child of the root
        else if (comparator.compare(element, root.element) < 0) {
            root = retrieveNodeHelper(root.left, element);
        }
        // Recursively call helper method on right child of the root
        else {
            root = retrieveNodeHelper(root.right, element);
        }
        return root;
    }

    class Node<E> implements Serializable {
        protected E element;
        protected Node<E> left;
        protected Node<E> right;

        public Node(E e) {
            element = e;
        }
    }
}