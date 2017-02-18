package com.carson.exercise9;

/**
 * Created by Carson on 17/02/2017.
 * CMPE 212 Section 01 - Exercise 9 - Property DB.
 * Base for Farmed Land, Vacant Land.
 * Child of Property.
 */
public class OpenLand extends Property {

    private int hectares;

    public OpenLand(int listPrice, int hectares) {
        super(listPrice);
        this.hectares = hectares;
    }

    public String getPricePerArea() {
        double areaPrice = (double) getListPrice() * 1000 / (double) hectares;
        return "Price is $" + areaPrice + " per hectare.";
    }

    public String toString() {
        return hectares + " hectares, " + super.toString();
    }
}
