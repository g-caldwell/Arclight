package com.caldwell.arclight.manager;
import com.caldwell.arclight.bodies.Star;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

// ********************************************************************************** //
// Title: Arclight                                                                    //
// Author: Gabriel Caldwell                                                           //
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022                                    //
// File: Manager.java                                                                 //
// Description: Main class for manager feature                                        //
// ********************************************************************************** //

public class Manager implements Serializable {

    // fields
    //==================================================================================================================
    ArrayList<Star> stars;
    Stack<Star> starStack;
    BinarySearchTree<Star> starTree;
    HashTable<Integer, Star> starHashTable;
    //==================================================================================================================



    // constructors
    //******************************************************************************************************************
    public Manager() {
        this.stars = new ArrayList<>();
        this.starStack = new Stack<>();
        this.starTree = new BinarySearchTree<>();
        this.starHashTable = new HashTable<>();
    }

    public Manager(ArrayList<Star> stars) {
        this.stars = stars;
    }
    //******************************************************************************************************************



    // getters & setters
    //=================================================================================================================
    public ArrayList<Star> getStars() {
        return this.stars;
    }

    public Stack<Star> getStarStack() {
        return starStack;
    }

    public BinarySearchTree<Star> getStarTree() {
        return this.starTree;
    }

    public HashTable<Integer, Star> getStarHashTable() {
        return this.starHashTable;
    }

    public void setStars(ArrayList<Star> stars) {
        this.stars = stars;
    }

    public void setStarStack(Stack<Star> starStack) {
        this.starStack = starStack;
    }

    public void setStarTree(BinarySearchTree<Star> starTree) {
        this.starTree = starTree;
    }

    public void setStarHashTable(HashTable<Integer, Star> starHashTable) {
        this.starHashTable = starHashTable;
    }
    //=================================================================================================================



    // serialization methods
    //******************************************************************************************************************
    // write array to file
    public void writeStars() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stars.dat"))) {
            oos.writeObject(this.stars);
            System.out.println("save success");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // retrieve array from file
    public void readStars() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("stars.dat"))) {
            this.stars = (ArrayList<Star>) ois.readObject();
            System.out.println("read success");
        }
        catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeTree() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("starTree.dat"))) {
            oos.writeObject(this.starTree);
            System.out.println("save success");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readTree() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("starTree.dat"))) {
            this.starTree = (BinarySearchTree<Star>) ois.readObject();
            System.out.println("read success");
        }
        catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeHash() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("starHash.dat"))) {
            oos.writeObject(this.starHashTable);
            System.out.println("save success");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readHash() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("starHash.dat"))) {
            this.starHashTable = (HashTable<Integer, Star>) ois.readObject();
            System.out.println("read success");
        }
        catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
    //******************************************************************************************************************



    // sorting by distance
    //=================================================================================================================
    // quicksort function for sorting stars by distance,
    // typical quicksort algorithm meaning it operates at O(Nlog(N)) complexity
    public void quickSort(ArrayList<Star> array) {
        quickSort(array, 0, array.size()-1);
    }

    public ArrayList<Star> returnQuickSort(ArrayList<Star> array) {
        quickSort(array, 0, array.size()-1);
        return(array);
    }

    // helper method for quickSort()
    public void quickSort(ArrayList<Star> array, int lowIndex, int highIndex) {

        // checks if there's only one element in the sub array, so it knows when to stop
        if (lowIndex >= highIndex) {
            return;
        }

        // picks the pivot at random and puts it at the end of the array
        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        double pivot = array.get(pivotIndex).getDistance();
        swap(array, pivotIndex, highIndex);

        // sorts the array and returns the place where the array is split
        int leftPointer = partition(array, lowIndex, highIndex, pivot);

        // recursive calls to sort the sub arrays
        quickSort(array, lowIndex, leftPointer - 1);
        quickSort(array,leftPointer + 1, highIndex);
    }

    // sorts then partitions array into 2 sub arrays
    public int partition(ArrayList<Star> array, int lowIndex, int highIndex, double pivot) {

        // pointers receive starting values
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        // walks through the list and swaps elements that are out of order
        while (leftPointer < rightPointer) {
            while (array.get(leftPointer).getDistance() <= pivot && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (array.get(rightPointer).getDistance() >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(array, leftPointer, rightPointer);
        }

        // somehow makes the code work, will break if removed
        if(array.get(leftPointer).getDistance() > array.get(highIndex).getDistance()) {
            swap(array, leftPointer, highIndex);
        }
        else {
            leftPointer = highIndex;
        }
        return leftPointer;
    }

    // swaps elements at designated indices
    public void swap(ArrayList<Star> array, int leftPointer, int rightPointer) {
        Star temp = array.get(leftPointer);
        array.set(leftPointer, array.get(rightPointer));
        array.set(rightPointer, temp);
    }
    //=================================================================================================================



    // sorting by name
    //******************************************************************************************************************
    // quicksort function for sorting stars by name,
    // typical quicksort algorithm meaning it operates at O(Nlog(N)) complexity
    public void quickSortString(ArrayList<Star> array) {
        quickSortString(array, 0, array.size()-1);
    }

    public ArrayList<Star> returnQuickSortString(ArrayList<Star> array) {
        quickSortString(array, 0, array.size()-1);
        return(array);
    }

    // helper method for quickSort()
    public void quickSortString(ArrayList<Star> array, int lowIndex, int highIndex) {

        // checks if there's only one element in the sub array, so it knows when to stop
        if (lowIndex >= highIndex) {
            return;
        }

        // picks the pivot at random and puts it at the end of the array
        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        String pivot = array.get(pivotIndex).getName();
        swapString(array, pivotIndex, highIndex);

        // sorts the array and returns the place where the array is split
        int leftPointer = partitionString(array, lowIndex, highIndex, pivot);

        // recursive calls to sort the sub arrays
        quickSortString(array, lowIndex, leftPointer - 1);
        quickSortString(array,leftPointer + 1, highIndex);
    }

    // sorts then partitions array into 2 sub arrays
    public int partitionString(ArrayList<Star> array, int lowIndex, int highIndex, String pivot) {

        // pointers receive starting values
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        // walks through the list and swaps elements that are out of order
        while (leftPointer < rightPointer) {
            while (array.get(leftPointer).getName().compareToIgnoreCase(pivot) <= 0  && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (array.get(rightPointer).getName().compareToIgnoreCase(pivot) >= 0 && leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(array, leftPointer, rightPointer);
        }

        // somehow makes the code work, will break if removed
        if(array.get(leftPointer).getName().compareToIgnoreCase(array.get(highIndex).getName()) > 0) {
            swap(array, leftPointer, highIndex);
        }
        else {
            leftPointer = highIndex;
        }
        return leftPointer;
    }

    // swaps elements at designated indices
    public void swapString(ArrayList<Star> array, int leftPointer, int rightPointer) {
        Star temp = array.get(leftPointer);
        array.set(leftPointer, array.get(rightPointer));
        array.set(rightPointer, temp);
    }
    //******************************************************************************************************************



    // sorting by color
    //=================================================================================================================
    // quicksort function for sorting stars by name,
    // typical quicksort algorithm meaning it operates at O(Nlog(N)) complexity
    public void quickSortColor(ArrayList<Star> array) {
        quickSortColor(array, 0, array.size()-1);
    }

    public ArrayList<Star> returnQuickSortColor(ArrayList<Star> array) {
        quickSortColor(array, 0, array.size()-1);
        return(array);
    }

    // helper method for quickSort()
    public void quickSortColor(ArrayList<Star> array, int lowIndex, int highIndex) {

        // checks if there's only one element in the sub array, so it knows when to stop
        if (lowIndex >= highIndex) {
            return;
        }

        // picks the pivot at random and puts it at the end of the array
        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        String pivot = array.get(pivotIndex).getColor();
        swapColor(array, pivotIndex, highIndex);

        // sorts the array and returns the place where the array is split
        int leftPointer = partitionColor(array, lowIndex, highIndex, pivot);

        // recursive calls to sort the sub arrays
        quickSortColor(array, lowIndex, leftPointer - 1);
        quickSortColor(array,leftPointer + 1, highIndex);
    }

    // sorts then partitions array into 2 sub arrays
    public int partitionColor(ArrayList<Star> array, int lowIndex, int highIndex, String pivot) {

        // pointers receive starting values
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        // walks through the list and swaps elements that are out of order
        while (leftPointer < rightPointer) {
            while (array.get(leftPointer).getColor().compareToIgnoreCase(pivot) <= 0  && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (array.get(rightPointer).getColor().compareToIgnoreCase(pivot) >= 0 && leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(array, leftPointer, rightPointer);
        }

        // somehow makes the code work, will break if removed
        if(array.get(leftPointer).getColor().compareToIgnoreCase(array.get(highIndex).getColor()) > 0) {
            swap(array, leftPointer, highIndex);
        }
        else {
            leftPointer = highIndex;
        }
        return leftPointer;
    }

    // swaps elements at designated indices
    public void swapColor(ArrayList<Star> array, int leftPointer, int rightPointer) {
        Star temp = array.get(leftPointer);
        array.set(leftPointer, array.get(rightPointer));
        array.set(rightPointer, temp);
    }
    //=================================================================================================================

}
