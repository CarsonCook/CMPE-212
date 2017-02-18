package com.carson.exercise9;

/**
 * Created by Carson on 17/02/2017.
 * CMPE 212 Section 01 - Exercise 9 - Property DB.
 * Class for Condo property.
 * Child of Residential.
 */
public class Condo extends Residential {

    public Condo(int listPrice, int buildingCoverage, int numBedrooms) {
        super(listPrice, buildingCoverage, numBedrooms);
    }

    public String getPricePerArea() {
        return getPricePerBuildingArea();
    }

    public String toString() {
        return "Condominium " + super.toString();
    }
}
