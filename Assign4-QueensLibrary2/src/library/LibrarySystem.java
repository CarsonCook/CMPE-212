package library;

import library.Exceptions.DuplicateItemID;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static library.Util.*;

/**
 * Created by Carson on 03/03/2017.
 * 14cdwc
 * Class to utilize library schema created.
 * Imports all of Util so that methods do not need Class.method, saving space.
 */
//TODO make object of library system in main
//TODO test in another class
public class LibrarySystem {

    private static HashMap<Integer, Item> items = new HashMap<>();
    private static HashMap<Integer, Customer> customers = new HashMap<>();
    private static HashMap<Integer, Rental> rentals = new HashMap<>();

    /**
     * Adds a Rental to the system using an Item.
     *
     * @param newItem Item to be rented.
     * @throws DuplicateItemID The Item has an ID that is already in use.
     */
    public void addTransaction(Item newItem) throws DuplicateItemID {
        for (Rental rental : rentals.values()) {
            if (rental.getItem().getID() == newItem.getID()) {
                throw new DuplicateItemID();
            }
        }
        Date returnDate = getReturnDate();
        Customer customer = getCustomer();
        Rental newRental = new Rental(newItem, customer, new Date(), returnDate);
        rentals.put(newItem.getID(), newRental);
    }

    /**
     * Adds a Rental to the system using an ID. Doesn't throw DuplicateIDException because
     * it calls the other addTransaction which will throw that.
     *
     * @param id ID of the Item to be rented.
     */
    public void addTransaction(int id) {
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

    public double getTotalLateFees() {
        double totalLateFees = 0;
        for (Rental rental : rentals.values()) {
            totalLateFees += rental.getLateFees();
        }
        return totalLateFees;
    }

    public double getTotalRentalCosts() {
        double totalRentalCosts = 0;
        for (Rental rental : rentals.values()) {
            if (rental.getItem() instanceof Device) {
                totalRentalCosts += ((Device) rental.getItem()).getRentalCost();
            }
        }
        return totalRentalCosts;
    }

    public void printAllRentals() {
        for (Rental rental : rentals.values()) {
            System.out.println(rental);
        }
    }

    public double getRentalCost() {
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
}
