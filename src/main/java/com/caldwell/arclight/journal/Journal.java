package com.caldwell.arclight.journal;
import java.io.*;

// ********************************************************************************** //
// Title: Arclight                                                                    //
// Author: Gabriel Caldwell                                                           //
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022                                    //
// File: Journal.java                                                                 //
// Description: Main journal class                                                    //
// ********************************************************************************** //

public class Journal implements Serializable {

    // fields
    //=================================================================================================================
    private JournalLinkedList<Page> pages;
    //=================================================================================================================



    // constructors
    //*****************************************************************************************************************
    public Journal() {
        this.pages = new JournalLinkedList<>();
    }
    //*****************************************************************************************************************



    // getters & setters
    //=================================================================================================================
    public JournalLinkedList<Page> getPages() {
        return this.pages;
    }

    public void setPages(JournalLinkedList<Page> pages) {
        this.pages = pages;
    }
    //=================================================================================================================



    // serialization methods
    //*****************************************************************************************************************
    // output array to file
    public void writeJournal() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pages.dat"))) {
            oos.writeObject(getPages());
            System.out.println("save success");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // retrieve array from file
    public void readJournal() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pages.dat"))) {
            this.pages.removeAll();
            this.pages = (JournalLinkedList<Page>) ois.readObject();
            System.out.println("read success");
        }
        catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
    //*****************************************************************************************************************



    // page class
    //=================================================================================================================
    public static class Page implements Serializable {
        String text;
        public Page() {
            this.text = "";
        }
        public Page(String text) {
            this.text = text;
        }
        public String getText() {
            return this.text;
        }
        public void setText(String text) {
            this.text = text;
        }
    }
    //=================================================================================================================

}
