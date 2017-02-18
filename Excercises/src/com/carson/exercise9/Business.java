package com.carson.exercise9;

/**
 * Created by Carson on 17/02/2017.
 * Parent for Office and Industrial.
 * Child of Developed.
 */
public class Business extends Developed {

    private int lotWidth, lotDepth;

    public Business(int listPrice, int buildingCoverage, int lotWidth, int lotDepth) {
        super(listPrice, buildingCoverage);
        this.lotWidth = lotWidth;
        this.lotDepth = lotDepth;
    }

    @Override
    public String getPricePerArea() {
        double areaPrice = (double) getListPrice() * 1000 / (double) (lotWidth * lotDepth);
        return "Price is $" + areaPrice + " per square metre of industrial lot.";
    }

    public String toString() {
        return lotWidth + " m width, " + lotDepth + " m depth, " + super.toString();
    }
}
