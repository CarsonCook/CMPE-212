package com.carson.exercise9;

/**
 * Created by Carson on 17/02/2017.
 * CMPE 212 Section 01 - Exercise 9 - Property DB.
 * Class for Industrial properties.
 * Child of Business.
 */
public class Industrial extends Business {

    public Industrial(int listPrice, int buildingCoverage, int lotWidth, int lotDepth) {
        super(listPrice, buildingCoverage, lotWidth, lotDepth);
    }

    public String toString() {
        return "Industrial space, " + super.toString();
    }
}
