package com.carson.exercise9;

/**
 * Created by Carson on 17/02/2017.
 * CMPE 212 Section 01 - Exercise 9 - Property DB.
 * Class for Cottage properties.
 * Child of Home.
 */
public class Cottage extends Home {

    private int lakeFrontage;

    public Cottage(int listPrice, int buildingCoverage, int numBedrooms, int lotDepth, int lotWidth, int lakeFrontage) {
        super(listPrice, buildingCoverage, numBedrooms, lotDepth, lotWidth);
        this.lakeFrontage = lakeFrontage;
    }

    public String toString() {
        return "Cottage with " + lakeFrontage + " m lakefront, " + super.toString();
    }
}
