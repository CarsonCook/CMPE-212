package com.carson.exercise9;

/**
 * Created by Carson on 17/02/2017.
 * CMPE 212 Section 01 - Exercise 9 - Property DB.
 * Class for Farmed Land - child of OpenLand and Property.
 */
public class FarmedLand extends OpenLand {

    private String crop;

    public FarmedLand(int hectares, int listPrice, String crop) {
        super(hectares, listPrice);
        this.crop = crop;
    }

    public String toString() {
        return "Farmed land, " + crop + " crop, " + super.toString();
    }
}
