package library;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Carson on 03/03/2017.
 * 14cdwc
 * Class to utilize library schema created.
 */
//TODO null in normal constructor
//todo rental tostring call item to string
public class LibrarySystem {

    private static ArrayList<Rental> mRentals = new ArrayList<>();

    public static void main(String[] args) {
        addTransaction();
        addTransaction();
        printRentals();
        System.out.println(getTotalLateFees());
        System.out.println(getTotalRentalCosts());
    }

    private static void addTransaction() {
        String itemType = getItemType();
        Item newItem = null; //never will be null after switch due to getItemType sanitizing input
        switch (itemType) {
            case "device":
                newItem = new Device(getRentalCost(), getString("Enter the device's name"));
                break;
            case "book":
                newItem = new Book(getInt("Enter the year the book was published"), getString("Enter the author(s)"),
                        getString("Enter the publisher"), getString("Enter the book's name"));
                break;
            case "adaptor":
                newItem = new Adaptor(getRentalCost(), getString("Enter the adaptor's name"));
                break;
            case "laptop":
                newItem = new Laptop(getRentalCost(), getString("Enter the laptop's name"));
                break;
            case "magazine":
                newItem = new Book(getInt("Enter the year the magazine was published"), getString("Enter the author(s)"),
                        getString("Enter the publisher"), getString("Enter the magazine's name"));
                break;
            case "textbook":
                newItem = new Book(getInt("Enter the year the textbook was published"), getString("Enter the author(s)"),
                        getString("Enter the publisher"), getString("Enter the textbook's name"));
                break;
        }
        Rental newRental = new Rental(newItem, getInt("Enter the number of days the item will be rented for"),
                getInt("Enter the number of days the item is late"));
        mRentals.add(newRental);
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

    private static void printRentals() {
        for (Rental rental : mRentals) {
            System.out.println(rental);
        }
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
     * Gets a user-inputted integer. Used to get year a book was published, days a rental item is late and days
     * a rental has been made for.
     *
     * @param prompt Message to show user
     * @return int
     */
    private static int getInt(String prompt) {
        boolean inputOK = false;
        int days = -1;
        String input;
        while (!inputOK) {
            input = JOptionPane.showInputDialog(null, prompt);
            try {
                days = Integer.parseInt(input);
                if (days >= 0) {
                    inputOK = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("That wasn't an integer!");
            }
        }
        return days;
    }

    /**
     * Gets a user-inputted String. Used to get the item's name, and book's author(s) and publisher.
     *
     * @param prompt Message to show user
     * @return String
     */
    private static String getString(String prompt) {
        return JOptionPane.showInputDialog(null, prompt);
    }

    /**
     * Gets user-inputted double representing the rental cost of a device.
     *
     * @return double
     */
    private static double getRentalCost() {
        boolean inputOK = false;
        double cost = -1;
        String input;
        while (!inputOK) {
            input = JOptionPane.showInputDialog(null, "Enter the rental cost of the device being rented");
            try {
                cost = Double.parseDouble(input);
                if (cost >= 0) {
                    inputOK = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("That wasn't a number!");
            }
        }
        return cost;
    }

    /**
     * Gets a user-inputted String representing the type of item being rented. Sanitized to make sure it is a type that exists.
     *
     * @return String
     */
    private static String getItemType() {
        boolean inputOK = false;
        String type = "";
        while (!inputOK) {
            type = JOptionPane.showInputDialog(null, "Enter the item type being rented");
            type = type.trim().toLowerCase();
            inputOK = goodItemName(type);
        }
        return type;
    }

    /**
     * Sanitizes inputted item types.
     *
     * @param item String to sanitize.
     * @return true if good type, else false.
     */
    private static boolean goodItemName(String item) {
        item = item.trim().toLowerCase();
        return item.equals("device") || item.equals("book") || item.equals("adaptor") ||
                item.equals("laptop") || item.equals("textbook") || item.equals("magazine");
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
