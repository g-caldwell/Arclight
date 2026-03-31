package com.caldwell.arclight.journal;
import java.io.Serializable;
import java.util.ArrayList;

// ********************************************************************************** //
// Title: Arclight                                                                    //
// Author: Gabriel Caldwell                                                           //
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022                                    //
// File: JournalLinkedList.java                                                       //
// Description: Linked list implementation for journal function                       //
// ********************************************************************************** //

public class JournalLinkedList<E> implements Serializable {

    // fields
    //=================================================================================================================
    private Node<E> head;
    private Node<E> tail;
    private int size;
    //=================================================================================================================



    // constructors
    //*****************************************************************************************************************
    public JournalLinkedList() {

    }

    public JournalLinkedList(E[] elements) {
        addAll(elements);
    }

    public JournalLinkedList(ArrayList<E> elements) {
        addAll(elements);
    }
    //*****************************************************************************************************************



    // getters & setters
    //=================================================================================================================
    public E getHead() {
        if (size == 0) {
            return null;
        }
        else {
            return head.element;
        }
    }

    public E getTail() {
        if (size == 0) {
            return null;
        }
        else {
            return tail.element;
        }
    }

    public int getSize() {
        return this.size;
    }
    //=================================================================================================================



    // addition methods
    //*****************************************************************************************************************
    // add element to the list
    public void add(E element, int index) {
        if (size == 0) {
            addFirst(element);
        }
        else if (index >= size) {
            addLast(element);
        }
        else {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(element);
            (current.next).next = temp;
            size++;
        }
    }

    // add element to the beginning of the list
    public void addFirst(E element) {
        Node<E> temp = new Node<>(element);
        temp.next = head;
        head = temp;
        size++;
        if (tail == null) {
            tail = head;
        }
    }

    // add element to the end of the list
    public void addLast(E element) {
        Node<E> temp = new Node<>(element);
        if (tail == null) {
            head = tail = temp;
        }
        else {
            tail.next = temp;
            tail = temp;
        }
        size++;
    }

    // add an entire arraylist to the list
    public void addAll(ArrayList<E> list) {
        for (E temp : list) {
            addLast(temp);
        }
    }

    // add an entire array to the list
    public void addAll(E[] list) {
        for (E temp : list) {
            addLast(temp);
        }
    }
    //*****************************************************************************************************************



    // removal methods
    //=================================================================================================================
    // remove element from list
    public void remove(E element) {
        if (head == element) {
            removeFirst();
        }
        else if (tail == element) {
            removeLast();
        }
        else {
            Node<E> current = head;
            for (int i = 0; i < size; i++) {
                if (current.element == element) {
                    remove(i);
                }
                else {
                    current = current.next;
                }
            }
        }
    }

    // remove element at specified index
    public E remove(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        else if (index == 0) {
            return removeFirst();
        }
        else if (index == size-1) {
            return removeLast();
        }
        else {
            Node<E> pre = head;
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            Node<E> current = pre.next;
            pre.next = current.next;
            size--;
            return current.element;
        }
    }

    // remove the first element in the list
    public E removeFirst() {
        if (size == 0) {
            return null;
        }
        else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null) {
                tail = null;
            }
            return temp.element;
        }
    }

    // remove the last element in the list
    public E removeLast() {
        if (size == 0) {
            return null;
        }
        else {
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }
            E temp = tail.element;
            tail = current;
            tail.next = null;
            size--;
            return temp;
        }
    }

    // remove all current elements in the list
    public void removeAll() {
        for (int i = 0; i < size; i++) {
            remove(i);
        }
    }
    //=================================================================================================================



    // retrieval and logic methods
    //*****************************************************************************************************************
    // checks if the list is empty
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    // finds specified element and returns its index
    public int indexOf(E element) {
        Node<E> current = head;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (current == element) {
                index = i;
            }
        }
        return index;
    }

    // checks if the list contains a specified element
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    // retrieves an element at a specified index
    public E get(int index) {

        if (index < 0 || index > size - 1){
            return null;
        }

        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.element;
    }
    //*****************************************************************************************************************



    // toString
    //=================================================================================================================
    // returns the list as a string
    @Override
    public String toString() {
        String out = "Elements: [";
        for (int i = 0; i < size; i++) {
            out += get(i).toString();
            out += ", ";
        }
        out += "]";
        return out;
    }
    //=================================================================================================================



    // inner node class
    // *****************************************************************************************************************
    private static class Node<E> implements Serializable {
        E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }
    // *****************************************************************************************************************

}
