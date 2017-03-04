package library;

import java.util.ArrayList;

/**
 * Created by Carson on 03/03/2017.
 * 14cdwc
 * Class to utilize library schema created.
 */
//TODO copy constructor doesn't work
//TODO Ask user to correct null values
//TODO ask user for values

public class LibrarySystem {

    private static ArrayList<Rental> mRentals;

    public static void main(String[] args) {
        Laptop laptop = new Laptop(10, "laptop");
        Laptop laptop2 = new Laptop(10, "laptop");
        Adaptor adaptor = new Adaptor(15, "adaptor");
        Adaptor adaptor2 = new Adaptor(15, "adaptor");
        Magazine magazine = new Magazine(2016, "pengu", "carson", "magazine");
        Magazine magazine2 = new Magazine(2016, "pengu", "carson", "magazine");
        Textbook textbook = new Textbook(2016, "manji", "carson", "math");
        Textbook textbook2 = new Textbook(2016, "manji", "carson", "math");
        System.out.println(laptop.equals(laptop2));
        System.out.println(adaptor.equals(adaptor2));
        System.out.println(magazine.equals(magazine2));
        System.out.println(textbook.equals(textbook2));
    }

    private static void addTransaction(Item item) {
        Rental rental = new Rental(item, 0, 0);
        mRentals.add(rental);
    }

    private static double getTotalLateFees() {
        double totalLateFees = 0;
        for (Rental rental : mRentals) {
            totalLateFees += rental.getItem().getLateFees(rental.getDaysLate());
        }
        return totalLateFees;
    }

    private static double getTotalRentalCosts() {
        double totalRentalCosts = 0;
        for (Rental rental : mRentals) {
            if (rental.getItem() instanceof Device) {
                totalRentalCosts += ((Device) rental.getItem()).getRentalCost();
            }
        }
        return totalRentalCosts;
    }
}
