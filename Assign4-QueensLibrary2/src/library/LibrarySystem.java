package library;

import library.Exceptions.DuplicateItemID;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Carson on 03/03/2017.
 * 14cdwc
 * Class to utilize library schema created.
 */
public class LibrarySystem {

    private static ArrayList<Rental> mRentals = new ArrayList<>();
    private static int idCounter = 0;

    public static void main(String[] args) {
        //test normal constructor and clone method/copy constructor
        Adaptor adaptor = new Adaptor(5, "HDMI Adaptor", ++idCounter);
        Adaptor cloneAdaptor = (Adaptor) cloneDevice(adaptor);
        System.out.println(adaptor);
        System.out.println(cloneAdaptor);
        Laptop laptop = new Laptop(15, "Asus", ++idCounter);
        Laptop cloneLaptop = (Laptop) cloneDevice(laptop);
        System.out.println(laptop);
        System.out.println(cloneLaptop);
        Magazine magazine = new Magazine(2016, "Carson Cook", "Time", "Cool stuff", ++idCounter);
        Magazine cloneMagazine = (Magazine) cloneBook(magazine);
        System.out.println(magazine);
        System.out.println(cloneMagazine);
        Textbook textbook = new Textbook(2016, "Carson Cook", "Queens", "Programming 101", ++idCounter);
        Textbook cloneTextbook = (Textbook) cloneBook(textbook);
        System.out.println(textbook);
        System.out.println(cloneTextbook);
        //check for null values/copying null item
        System.out.println(new Adaptor(null));
        System.out.println(new Laptop(null));
        System.out.println(new Magazine(null));
        System.out.println(new Textbook(null));
        //check equals method
        System.out.println(adaptor.equals(magazine));
        System.out.println(adaptor.equals(adaptor));
        System.out.println(laptop.equals(magazine));
        System.out.println(laptop.equals(laptop));
        System.out.println(magazine.equals(adaptor));
        System.out.println(magazine.equals(magazine));
        System.out.println(textbook.equals(magazine));
        System.out.println(textbook.equals(textbook));
        //test rental system - also tests getLateFees for devices
        try {
            addTransaction(adaptor);
        } catch (DuplicateItemID e) {
            System.out.println(e + " " + adaptor.getName() + " was not inserted.");
        }
        try {
            addTransaction(laptop);
        } catch (DuplicateItemID e) {
            System.out.println(e + " " + laptop.getName() + " was not inserted.");
        }
        try {
            addTransaction(magazine);
        } catch (DuplicateItemID e) {
            System.out.println(e + " " + magazine.getName() + " was not inserted.");
        }
        try {
            addTransaction(textbook);
        } catch (DuplicateItemID e) {
            System.out.println(e + " " + textbook.getName() + " was not inserted.");
        }
        printAllRentals();
        System.out.println("Total rental costs: " + getTotalRentalCosts());
        System.out.println("Total late fees: " + getTotalLateFees());
    }

    private static void addTransaction(Item newItem) throws DuplicateItemID {
        for (Rental rental : mRentals) {
            if (newItem.getID() == rental.getItem().getID()) {
                throw new DuplicateItemID();
            }
        }
        Rental newRental = new Rental(newItem, getInt("Enter the number of days " + newItem.getName() + " will be rented for"),
                getInt("Enter the number of days " + newItem.getName() + " is late"));
        mRentals.add(newRental);
    }

    private static void addTransaction(int id) {
        String itemType = getItemType();
        Item newItem; //never will be null after switch due to getItemType sanitizing input
        switch (itemType) {
            case "device":
                newItem = new Device(getRentalCost(), getString("Enter the device's name"), id);
                break;
            case "book":
                newItem = new Book(getInt("Enter the year the book was published"), getString("Enter the author(s)"),
                        getString("Enter the publisher"), getString("Enter the book's name"), id);
                break;
            case "adaptor":
                newItem = new Adaptor(getRentalCost(), getString("Enter the adaptor's name"), id);
                break;
            case "laptop":
                newItem = new Laptop(getRentalCost(), getString("Enter the laptop's name"), id);
                break;
            case "magazine":
                newItem = new Magazine(getInt("Enter the year the magazine was published"), getString("Enter the author(s)"),
                        getString("Enter the publisher"), getString("Enter the magazine's name"), id);
                break;
            case "textbook":
                newItem = new Textbook(getInt("Enter the year the textbook was published"), getString("Enter the author(s)"),
                        getString("Enter the publisher"), getString("Enter the textbook's name"), id);
                break;
            default: //dummy proof bad item type - should already be done but do here too
                System.out.println("You did not enter a proper Item type, so nothing was inserted");
                return;
        }
        try {
            addTransaction(newItem);
        } catch (DuplicateItemID e) {
            System.out.println(e + " " + newItem.getName() + " was not inserted.");
        }
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

    private static void printAllRentals() {
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
