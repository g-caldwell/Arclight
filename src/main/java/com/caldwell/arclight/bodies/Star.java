package com.caldwell.arclight.bodies;
import java.io.Serializable;

// ********************************************************************************** //
// Title: Arclight                                                                    //
// Author: Gabriel Caldwell                                                           //
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022                                    //
// File: Star.java                                                                    //
// Description: Class to create star objects                                          //
// ********************************************************************************** //

public class Star implements Serializable {

    // basic attributes
    private String name;
    private String color;
    private double distance;

    // attributes to be added later
    /*private String classification;
    private double age;
    private double brightness;
    private double temperature;
    private double size;*/

    // default constructor
    public Star() {

    }

    // overloaded constructor
    public Star(String name, String color, double distance) {
        this.name = name;
        this.color = color;
        this.distance = distance;
    }

    // getters and setters
    // ================================================================================================================

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    // ================================================================================================================

}
