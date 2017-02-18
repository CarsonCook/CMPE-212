package com.carson.exercise9;

/**
 * Created by Carson on 17/02/2017.
 * CMPE 212 Section 01 - Exercise 9 - Property DB.
 * Class for developed land.
 * Child of Property.
 * Parent of Office, Industrial, Cottage, Condo, Home.
 */
public abstract class Developed extends Property {

    private int buildingCoverage;

    public Developed(int listPrice, int buildingCoverage) {
        super(listPrice);
        this.buildingCoverage = buildingCoverage;
    }

    public String getPricePerBuildingArea(){
        int areaPrice = getListPrice() * 1000 / buildingCoverage;
        return "Price is $" + areaPrice + " per square metre of building.";
    }

    public String toString(){
        return buildingCoverage+" square metre building, "+super.toString();
    }
}
