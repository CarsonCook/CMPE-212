package library;

import java.util.ArrayList;

/**
 * Created by Carson on 03/03/2017.
 * 14cdwc
 * Class to utilize library schema created.
 */
//TODO copy constructor breaks if null inputted
//TODO Ask user to correct null values
//TODO ask user for values

public class LibrarySystem {

    private static ArrayList<Rental> mRentals = new ArrayList<>();

    public static void main(String[] args) {
     //   Laptop laptop = new Laptop(10, "laptop");
      //  Laptop laptop2 = new Laptop(10, "laptop");
        Adaptor adaptor = new Adaptor(15, "adaptor");
      //  Adaptor adaptor2 = new Adaptor(15, "adaptor");
      //  Magazine magazine = new Magazine(2016, "pengu", "carson", "magazine");
      //  Magazine magazine2 = new Magazine(2016, "pengu", "carson", "magazine");
      //  Textbook textbook = new Textbook(2016, "manji", "carson", "math");
      //  Textbook textbook2 = new Textbook(2016, "manji", "carson", "math");
        System.out.println(adaptor);
        System.out.println(cloneDevice(adaptor));
    }

    /**
     * Convience method to clone a device, adds the try-catch block around the clone call.
     *
     * @param device Device to be cloned
     * @return Cloned device.
     */
    private static Device cloneDevice(Device device) {
        try {
            return device.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone not supported!");
        }
        return null; //flag for no clone possible
    }

    /**
     * Convience method to clone a Book, adds the try-catch block around the clone call.
     *
     * @param book Book to be cloned
     * @return Cloned Book.
     */
    private static Book cloneBook(Book book) {
        try {
            return book.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone not supported!");
        }
        return null; //flag for no clone possible
    }

    /**
     * Adds a rental transaction to the ArrayList mRentals that holds all library rentals.
     *
     * @param item Item that was rented.
     */
    private static void addTransaction(Item item) {
        Rental rental = new Rental(item, 0, 0);
        mRentals.add(rental);
    }

    /**
     * Gets the total late fees from all rentals in the system.
     *
     * @return double representing total late fees.
     */
    private static double getTotalLateFees() {
        double totalLateFees = 0;
        for (Rental rental : mRentals) {
            totalLateFees += rental.getItem().getLateFees(rental.getDaysLate());
        }
        return totalLateFees;
    }

    /**
     * Gets the total rental costs from all devices in the system.
     *
     * @return double representing total rental costs.
     */
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
