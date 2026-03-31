package com.caldwell.arclight.manager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

// ********************************************************************************** //
// Title: Arclight                                                                    //
// Author: Gabriel Caldwell                                                           //
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022                                    //
// File: BinarySearchTree.java                                                        //
// Description: Search functionality using a binary search tree structure             //
// ********************************************************************************** //

public class BinarySearchTree<E> implements Serializable {

    // fields
    //==================================================================================================================
    protected TreeNode<E> root;
    protected int size = 0;
    protected java.util.Comparator<E> comparator;
    //==================================================================================================================



    // constructors
    //******************************************************************************************************************
    public BinarySearchTree() {
        this.comparator = (element1, element2) -> ((Comparable<E>)element1).compareTo(element2);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree(E[] arr) {
        this.comparator = (element1, element2) -> ((Comparable<E>)element1).compareTo(element2);
        for (E e : arr) {
            add(e);
        }
    }

    public BinarySearchTree(ArrayList<E> arr) {
        this.comparator = (element1, element2) -> ((Comparable<E>)element1).compareTo(element2);
        for (E e : arr) {
            add(e);
        }
    }
    //******************************************************************************************************************



    // getters
    //==================================================================================================================
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
    //==================================================================================================================



    // search
    //******************************************************************************************************************
    public boolean search(E e) {
        TreeNode<E> curr = root;

        while (curr != null) {
            if (comparator.compare(e, curr.element) < 0) {
                curr = curr.left;
            }
            else if (comparator.compare(e, curr.element) > 0) {
                curr = curr.right;
            }
            else {
                return true;
            }
        }
        return false;
    }

    public E returnSearch(E e) {
        TreeNode<E> curr = root;

        while (curr != null) {
            if (comparator.compare(e, curr.element) < 0) {
                curr = curr.left;
            }
            else if (comparator.compare(e, curr.element) > 0) {
                curr = curr.right;
            }
            else {
                break;
            }
        }
        return curr.element;
    }

//    public boolean search(String s) {
//        TreeNode<E> curr = root;
//
//        while (curr != null) {
//            if (s.compareToIgnoreCase(curr.element.getClass().getName()) < 0) {
//                curr = curr.left;
//            }
//            else if (s.compareToIgnoreCase(curr.element.getClass().getName()) > 0) {
//                curr = curr.right;
//            }
//            else {
//                return true;
//            }
//        }
//        return false;
//    }
    //******************************************************************************************************************



    // add
    //==================================================================================================================
    public boolean add(E e) {
        if (root == null) {
            root = newNode(e);
        }
        else {
            TreeNode<E> parent = null;
            TreeNode<E> curr = root;
            while (curr != null) {
                if (comparator.compare(e, curr.element) < 0) {
                    parent = curr;
                    curr = curr.left;
                }
                else if (comparator.compare(e, curr.element) > 0) {
                    parent = curr;
                    curr = curr.right;
                }
                else {
                    return false;
                }
            }
            if (comparator.compare(e, parent.element) < 0) {
                parent.left = newNode(e);
            }
            else {
                parent.right = newNode(e);
            }
        }
        size++;
        return true;
    }

    protected TreeNode<E> newNode(E e) {
        return new TreeNode<E>(e);
    }
    //==================================================================================================================



    // delete
    //******************************************************************************************************************
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> curr = root;
        while (curr != null) {
            if (comparator.compare(e, curr.element) < 0) {
                parent = curr;
                curr = curr.left;
            }
            else if (comparator.compare(e, curr.element) > 0) {
                parent = curr;
                curr = curr.right;
            }
            else {
                break;
            }
        }

        if (curr == null) {
            return false;
        }

        if (curr.left == null) {
            if (parent == null) {
                root = curr.right;
            }
            else {
                if (comparator.compare(e, parent.element) < 0) {
                    parent.left = curr.right;
                }
                else {
                    parent.right = curr.right;
                }
            }
        }
        else {
            TreeNode<E> rightParent = curr;
            TreeNode<E> right = curr.left;

            while (right.right != null) {
                rightParent = right;
                right = right.right;
            }

            curr.element = right.element;

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

    public void empty() {
        size = 0;
        root = null;
    }
    //******************************************************************************************************************



    // TreeNode inner class
    //******************************************************************************************************************
    class TreeNode<E> implements Serializable {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }
    //==================================================================================================================

}
