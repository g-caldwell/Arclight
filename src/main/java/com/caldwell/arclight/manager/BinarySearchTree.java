package com.caldwell.arclight.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree<E> implements Serializable {

    protected TreeNode<E> root;
    protected int size = 0;
    protected java.util.Comparator<E> comparator;

    // Default constructor
    public BinarySearchTree() {
        this.comparator = (element1, element2) -> ((Comparable<E>)element1).compareTo(element2);
    }

    // Passed a comparator with an arbitrary type
    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    // Passed an array of elements with an arbitrary type
    public BinarySearchTree(E[] arr) {
        this.comparator = (element1, element2) -> ((Comparable<E>)element1).compareTo(element2);
        for (E e : arr) {
            add(e);
        }
    }

    // Passed an arraylist of elements with an arbitrary type
    public BinarySearchTree(ArrayList<E> arr) {
        this.comparator = (element1, element2) -> ((Comparable<E>)element1).compareTo(element2);
        for (E e : arr) {
            add(e);
        }
    }

    public TreeNode<E> getRoot() {
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
    protected TreeNode<E> newNode(E e) {
        return new TreeNode<E>(e);
    }

    // search
    public boolean contains(E e) {
        // Start at the root of the tree
        TreeNode<E> current_node = root;

        // Search through the tree until either a dead end is hit or the element is found
        while (current_node != null) {
            // Proceed to the left of the current node
            if (comparator.compare(e, current_node.element) < 0) {
                current_node = current_node.left;
            }
            // Proceed to the right of the current node
            else if (comparator.compare(e, current_node.element) > 0) {
                current_node = current_node.right;
            }
            // The element was found
            else {
                return true;
            }
        }
        // The element was not found
        return false;
    }

    public E getElement(E e) {
        // Start at the root of the tree
        TreeNode<E> current_node = root;
        // Search the tree until you reach the element
        while (current_node != null) {
            // If the element is less than the current node's, proceed to the left of the current node
            if (comparator.compare(e, current_node.element) < 0) {
                current_node = current_node.left;
            }
            // If the element is greater than the current node's, proceed to the right of the current node
            else if (comparator.compare(e, current_node.element) > 0) {
                current_node = current_node.right;
            }
            // The element was found
            else {
                break;
            }
        }
        return current_node.element;
    }

    // Add an element to the tree
    public void add(E element) {

        // Create a new root if the tree is empty
        if (root == null) {
            root = newNode(element);
        }
        else {
            // Start at the top of the tree (root node has no parent)
            TreeNode<E> parent = null;
            TreeNode<E> current_node = root;

            // Search through the tree until you hit an end-point (leaf)
            while (current_node != null) {
                // If the element is less than the current node's, proceed to the left of the current node
                if (comparator.compare(element, current_node.element) < 0) {
                    parent = current_node;
                    current_node = current_node.left;
                }
                // If the element is greater than the current node's, proceed to the right of the current node
                else if (comparator.compare(element, current_node.element) > 0) {
                    parent = current_node;
                    current_node = current_node.right;
                }
                // The element must be equal to the current node
                else {
                    //return false;
                }
            }
            // If the element is less than the leaf's, add to its left child
            if (comparator.compare(element, parent.element) < 0) {
                parent.left = newNode(element);
            }
            // Otherwise add to its right child
            else {
                parent.right = newNode(element);
            }
        }
        // Update size
        size++;
    }

    // delete
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current_node = root;
        while (current_node != null) {
            if (comparator.compare(e, current_node.element) < 0) {
                parent = current_node;
                current_node = current_node.left;
            }
            else if (comparator.compare(e, current_node.element) > 0) {
                parent = current_node;
                current_node = current_node.right;
            }
            else {
                break;
            }
        }

        if (current_node == null) {
            return false;
        }

        if (current_node.left == null) {
            if (parent == null) {
                root = current_node.right;
            }
            else {
                if (comparator.compare(e, parent.element) < 0) {
                    parent.left = current_node.right;
                }
                else {
                    parent.right = current_node.right;
                }
            }
        }
        else {
            TreeNode<E> rightParent = current_node;
            TreeNode<E> right = current_node.left;

            while (right.right != null) {
                rightParent = right;
                right = right.right;
            }

            current_node.element = right.element;

            if (rightParent.right == right) {
                rightParent.right = right.left;
            }
            else {
                rightParent.left = right.left;
            }
        }
        size--;
        return true;
    }

    // Empty the tree by removing its root
    public void empty() {
        size = 0;
        root = null;
    }

    class TreeNode<E> implements Serializable {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }
}