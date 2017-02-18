package com.carson.exercise9;

/**
 * Created by Carson on 17/02/2017.
 * CMPE 212 Section 01 - Exercise 9 - Property DB.
 * Class for Office Properties.
 * Child of Business.
 */
public class Office extends Business {

    public Office(int listPrice, int buildingCoverage, int lotWidth, int lotDepth) {
        super(listPrice, buildingCoverage, lotWidth, lotDepth);
    }

    public String toString() {
        return "Office space, " + super.toString();
    }
}
