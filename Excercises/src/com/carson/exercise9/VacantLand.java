package com.carson.exercise9;

/**
 * Created by Carson on 17/02/2017.
 * CMPE 212 Section 01 - Exercise 9 - Property DB.
 * Class for Vacant Land.
 * Child of Open Land.
 */
public class VacantLand extends OpenLand {

    public VacantLand(int listPrice, int hectares) {
        super(hectares, listPrice);
    }

    public String toString() {
        return "Vacant land, " + super.toString();
    }
}
