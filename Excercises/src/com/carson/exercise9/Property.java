package com.carson.exercise9;

/**
 * Created by Carson on 17/02/2017.
 * CMPE 212 Section 01 - Exercise 9 - Property DB.
 * Base class for all items in the property database.
 */
public abstract class Property {

    private int listPrice;

    public Property(int listPrice) {
        this.listPrice = listPrice;
    }

    public abstract String getPricePerArea();

    public int getListPrice() {
        return listPrice;
    }

    public String toString() {
        return "asking price $" + listPrice + ",000";
    }
}
