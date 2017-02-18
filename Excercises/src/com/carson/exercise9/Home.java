package com.carson.exercise9;

/**
 * Created by Carson on 17/02/2017.
 * CMPE 212 Section 01 - Exercise 9 - Property DB.
 * Class for Home properties
 * Child of Residential.
 * Parent of Home, Cottage.
 */
public class Home extends Residential {

    private int lotDepth, lotWidth;

    public Home(int listPrice, int buildingCoverage, int numBedrooms, int lotWidth, int lotDepth) {
        super(listPrice, buildingCoverage, numBedrooms);
        this.lotDepth = lotDepth;
        this.lotWidth = lotWidth;
    }

    @Override
    public String getPricePerArea() {
        double areaPrice = (double) getListPrice() * 1000 / (double) (lotWidth * lotDepth);
        return "Price is $" + areaPrice + " per square metre of industrial lot.";
    }

    public String toString() {
        return "Home, " + lotWidth + " m width, " + lotDepth + " m depth, " + super.toString();
    }
}
