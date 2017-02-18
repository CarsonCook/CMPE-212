package com.carson.exercise9;

/**
 * Created by Carson on 17/02/2017.
 * CMPE 212 Section 01 - Exercise 9 - Property DB.
 * Child of Developed.
 * Parent of Condo, Cottage, Home.
 */
public abstract class Residential extends Developed {

    private int numBedrooms;

    public Residential(int listPrice, int buildingCoverage, int numBedrooms) {
        super(listPrice, buildingCoverage);
        this.numBedrooms = numBedrooms;
    }

    public String toString() {
        return numBedrooms + " bedrooms, " + super.toString();
    }
}
