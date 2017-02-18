package com.carson.exercise9;

/*
 * A testing class for Exercise 9.  When run with your hierarchy classes, the output should match that
 * appended to this program.
 */

import java.util.ArrayList;

public class TestProperties {

    public static void main(String[] args) {

        // Create data structure to hold real estate properties
        ArrayList<Property> propertyDB = new ArrayList<>();

        // Add properties shown in table to data structure
        propertyDB.add(new VacantLand(120, 120));            // price, hectares
        propertyDB.add(new FarmedLand(270, 100, "corn"));    // price, hectares, crop
        propertyDB.add(new Industrial(350, 800, 100, 200));    // price, coverage, width, depth
        propertyDB.add(new Office(80, 180, 30, 60));        // price, coverage, width, depth
        propertyDB.add(new Home(235, 240, 4, 20, 40));        // price, coverage, numBedrooms, width, depth
        propertyDB.add(new Cottage(230, 200, 3, 50, 100, 50));    // price, coverage, numBedrooms, width, depth, lakeFront
        propertyDB.add(new Condo(289, 200, 3));                // price, coverage, numBedrooms

        // Test toString() and getPricePerArea() for all objects
        // Test getPricePerBuildingArea() only for those properties that are developed - that have a building
        // on them.
        for (Property prop : propertyDB) {
            System.out.println(prop);
            System.out.println(prop.getPricePerArea());
            if (prop instanceof Developed)
                System.out.println(((Developed) prop).getPricePerBuildingArea());
            System.out.println();
        }

    } // end main

} // end TestProperties
/* OUTPUT:
Vacant land, 120 hectares, asking price $120,000.
Price is $1000 per hectare.

Farmed land, corn crop, 100 hectares, asking price $270,000.
Price is $2700 per hectare.

Industrial space, 100 m width by 200 m deep lot, 800 square metre building, asking price $350,000.
Price is $17 per square metre of industrial lot.
Price is $437 per square metre of building.

Office space, 30 m width by 60 m deep lot, 180 square metre building, asking price $80,000.
Price is $44 per square metre of industrial lot.
Price is $444 per square metre of building.

Home home, 20 m width by 40 m deep lot, 4 bedrooms, 240 square metre building, asking price $235,000.
Price is $293 per square metre of residential lot.
Price is $979 per square metre of building.

Cottage with 50 m lakefront, 50 m width by 100 m deep lot, 3 bedrooms, 200 square metre building, asking price $230,000.
Price is $46 per square metre of residential lot.
Price is $1150 per square metre of building.

Condominium, 3 bedrooms, 200 square metre building, asking price $289,000.
Price is $1445 per square metre of building.
Price is $1445 per square metre of building.

*/
