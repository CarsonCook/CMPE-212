package library;

import java.util.ArrayList;

/**
 * Created by Carson on 03/03/2017.
 * 14cdwc
 * Class to utilize library schema created.
 */
//TODO copy constructor breaks if null inputted

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
        Rental rental = new Rental(adaptor, 0, 0);
        System.out.println(rental);
        System.out.println(cloneRental(rental));
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

    /**
     * Convenience method to clone a Rental, adds the try-catch block around the clone call.
     *
     * @param rental Rental to be cloned
     * @return Cloned Rental.
     */
    private static Rental cloneRental(Rental rental) {
        try {
            return rental.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone not supported!");
        }
        return null; //flag for no clone possible
    }

    /**
     * Convenience method to clone a Device, adds the try-catch block around the clone call.
     *
     * @param device Device to be cloned
     * @return Cloned Device.
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
     * Convenience method to clone a Book, adds the try-catch block around the clone call.
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
}
