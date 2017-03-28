package library;

import library.Enums.RentalStatus;
import library.Exceptions.DuplicateCustomerID;
import library.Exceptions.DuplicateItemID;
import library.Exceptions.DuplicateTransactionID;
import library.Exceptions.WrongRentalCost;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static library.Util.*;

/**
 * Created by Carson on 03/03/2017.
 * 14cdwc
 * Class to utilize library schema created.
 * Imports all of Util so that methods do not need Class.method, saving space.
 */
public class LibrarySystem {

    private static HashMap<Integer, Item> items = new HashMap<>();
    private static HashMap<Integer, Customer> customers = new HashMap<>();
    private static HashMap<Integer, Rental> rentals = new HashMap<>();

    /**
     * Adds a Customer to the collection of Customers.
     *
     * @param newCust New Customer to be added.
     * @throws DuplicateCustomerID The Customer has an ID that is already in use.
     */
    public void addCustomer(Customer newCust) throws DuplicateCustomerID {
        //check for duplicate ID
        if (customers.containsKey(newCust.getID())) {
            throw new DuplicateCustomerID(newCust.getID());
        }
        customers.put(newCust.getID(), newCust);
    }

    public void addCustomer() {

    }

    /**
     * Adds a Rental to the collection on Rentals using an Item.
     *
     * @param newItem Item to be rented.
     * @throws DuplicateTransactionID The Item has an ID that is already in use.
     */
    public void addTransaction(Item newItem, Customer customer) throws DuplicateTransactionID {
        addTransaction(newItem, customer, new Date());
    }

    public void addTransaction(Item newItem, Customer customer, Date date) throws DuplicateTransactionID {
        Date returnDate = getDate();
        Rental newRental = new Rental(newItem, customer, date, returnDate);
        //check for duplicate ID
        if (rentals.containsKey(newRental.getID())) {
            throw new DuplicateTransactionID(newRental.getID());
        }
        rentals.put(newRental.getID(), newRental);
    }

    /**
     * Method that adds a new Item to the collection of Items, items. Lets user input Item values,
     * forces user to continue to input values until they are acceptable.
     *
     * @param newItem New Item to be added.
     * @throws DuplicateItemID Item has an ID already in use.
     */
    public void addItem(Item newItem) throws DuplicateItemID {
        if (newItem != null) {
            if (items.containsKey(newItem.getID())) {
                throw new DuplicateItemID(newItem.getID());
            }
            items.put(newItem.getID(), newItem);
        } else {
            System.out.println("You tried to add a null item!");
        }
    }

    /**
     * Method that adds a new Item to the collection of Items, items. Lets user input Item values,
     * forces user to continue to input values until they are acceptable. Doesn't throw DuplicateItemID
     * because it calls a method that does.
     */
    public void addItem() {
        String itemType = getItemType();
        Item newItem = null; //never will be null after switch due to getItemType sanitizing input
        boolean itemOK = false;
        while (!itemOK) {
            switch (itemType) {
                case "device":
                    newItem = new Device(getRentalCost(), getString("Enter the device's name"));
                    itemOK = true;
                    break;
                case "book":
                    newItem = new Book(getInt("Enter the year the book was published"), getString("Enter the author(s)"),
                            getString("Enter the publisher"), getString("Enter the book's name"));
                    itemOK = true;
                    break;
                case "adaptor":
                    newItem = new Adaptor(getRentalCost(), getString("Enter the adaptor's name"));
                    itemOK = true;
                    break;
                case "laptop":
                    newItem = new Laptop(getRentalCost(), getString("Enter the laptop's name"));
                    itemOK = true;
                    break;
                case "magazine":
                    newItem = new Magazine(getInt("Enter the year the magazine was published"), getString("Enter the author(s)"),
                            getString("Enter the publisher"), getString("Enter the magazine's name"));
                    itemOK = true;
                    break;
                case "textbook":
                    newItem = new Textbook(getInt("Enter the year the textbook was published"), getString("Enter the author(s)"),
                            getString("Enter the publisher"), getString("Enter the textbook's name"));
                    itemOK = true;
                    break;
                default: //dummy proof bad item type - should already be done but do here too
                    System.out.println("You did not enter a proper Item type, so nothing was inserted");
                    itemOK = false;
            }
        }
        try {
            addItem(newItem);
        } catch (DuplicateItemID e) {
            System.out.println(e + " " + newItem.getName() + " was not inserted.");
        }
    }

    /**
     * Method that adds an Item to the collections of Items, items. Lets user input Item values,
     * forces user to continue to input values until they are acceptable. Doesn't throw DuplicateItemID
     * because it calls a method that does.
     *
     * @param id ID for the new Item to have.
     */
    public void addItem(int id) {
        String itemType = getItemType();
        Item newItem = null; //never will be null after switch due to getItemType sanitizing input
        boolean itemOK = false;
        while (!itemOK) {
            switch (itemType) {
                case "device":
                    newItem = new Device(getRentalCost(), getString("Enter the device's name"), id);
                    itemOK = true;
                    break;
                case "book":
                    newItem = new Book(getInt("Enter the year the book was published"), getString("Enter the author(s)"),
                            getString("Enter the publisher"), getString("Enter the book's name"), id);
                    itemOK = true;
                    break;
                case "adaptor":
                    newItem = new Adaptor(getRentalCost(), getString("Enter the adaptor's name"), id);
                    itemOK = true;
                    break;
                case "laptop":
                    newItem = new Laptop(getRentalCost(), getString("Enter the laptop's name"), id);
                    itemOK = true;
                    break;
                case "magazine":
                    newItem = new Magazine(getInt("Enter the year the magazine was published"), getString("Enter the author(s)"),
                            getString("Enter the publisher"), getString("Enter the magazine's name"), id);
                    itemOK = true;
                    break;
                case "textbook":
                    newItem = new Textbook(getInt("Enter the year the textbook was published"), getString("Enter the author(s)"),
                            getString("Enter the publisher"), getString("Enter the textbook's name"), id);
                    itemOK = true;
                    break;
                default: //dummy proof bad item type - should already be done but do here too
                    System.out.println("You did not enter a proper Item type, so nothing was inserted");
                    itemOK = false;
            }
        }
        try {
            addItem(newItem);
        } catch (DuplicateItemID e) {
            System.out.println(e + " " + newItem.getName() + " was not inserted.");
        }
    }

    public static void printLateRentals(Date curDate) {
        String output = "Late rentals:\n";
        for (Rental rental : rentals.values()) {
            if (rental.isLate(curDate)) {
                output += rental.toString();
            }
        }
        System.out.println(output);
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
                try {
                    double oneCost = ((Device) rental.getItem()).getRentalCost();
                    if (oneCost < 0) {
                        throw new WrongRentalCost();
                    }
                    totalRentalCosts += oneCost;
                } catch (WrongRentalCost e) {
                    System.out.println(e + " 0 was used as the rental cost.");
                }
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
                } else {
                    throw new WrongRentalCost();
                }
            } catch (WrongRentalCost e) {
                System.out.println(e + " Try again.");
            } catch (NumberFormatException e) {
                System.out.println("That wasn't a number!");
            }
        }
        return cost;
    }

    public static HashMap<Integer, Item> getItems() {
        return items;
    }

    public static HashMap<Integer, Customer> getCustomers() {
        return customers;
    }

    public static HashMap<Integer, Rental> getRentals() {
        return rentals;
    }

    /**
     * The following all search for Items. There is one for each possible field for an Item, with the
     * parameter being the field. The method will return a list of Items that match the parameter.
     */

    public Item searchItem(int id) {
        return items.get(id); //id is the key in the map, will only ever be one so no list
    }

    public ArrayList<Item> searchItem(String term) { //search for name, publisher, authors
        ArrayList<Item> foundItems = new ArrayList<>();
        for (Item item : items.values()) {
            if (item.getName().equals(term)) {
                foundItems.add(item); //name matches search
            } else if (item instanceof Book && (((Book) item).getAuthors().equals(term) ||
                    ((Book) item).getPublisher().equals(term))) {
                foundItems.add(item); //author or publisher matches search
            }
        }
        return foundItems;
    }

    public ArrayList<Item> searchItem(double rentalCost) {
        ArrayList<Item> foundItems = new ArrayList<>();
        for (Item item : items.values()) {
            if (item instanceof Device && ((Device) item).getRentalCost() == rentalCost) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }

    public ArrayList<Item> searchItem(long year) { //long because ID uses the parameter for int
        ArrayList<Item> foundItems = new ArrayList<>();
        for (Item item : items.values()) {
            if (item instanceof Book && ((Book) item).getYear() == year) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }

    /**
     * The following all search for Rentals. There is one for each possible field for a Rental, with the
     * parameter being the field. The method will return a list of Rentals that have fields matching the parameter.
     */

    public ArrayList<Rental> searchRental(Item item) {
        ArrayList<Rental> foundRentals = new ArrayList<>();
        for (Rental rental : rentals.values()) {
            if (rental.getItem().equals(item)) {
                foundRentals.add(rental);
            }
        }
        return foundRentals;
    }

    public ArrayList<Rental> searchRental(Date date) { //for rental date, actual return date and estimated return date
        ArrayList<Rental> foundRentals = new ArrayList<>();
        for (Rental rental : rentals.values()) {
            if (rental.getRentalDate().equals(date) || rental.getActReturnDate().equals(date) ||
                    rental.getEstReturnDate().equals(date)) {
                foundRentals.add(rental);
            }
        }
        return foundRentals;
    }

    public ArrayList<Rental> searchRental(RentalStatus status) {
        ArrayList<Rental> foundRentals = new ArrayList<>();
        for (Rental rental : rentals.values()) {
            if (rental.getStatus() == status) {
                foundRentals.add(rental);
            }
        }
        return foundRentals;
    }

    public ArrayList<Rental> searchRental(int id) {
        ArrayList<Rental> foundRentals = new ArrayList<>();
        for (Rental rental : rentals.values()) {
            if (rental.getID() == id) {
                foundRentals.add(rental);
            }
        }
        return foundRentals;
    }

    /**
     * Method to filter Rentals based on being between two Dates.
     *
     * @param from Date at which Rentals can start to be taken
     * @param to   Date at which Rentals cannot be taken anymore.
     * @return List of Rentals with rental dates between from and to.
     */
    public ArrayList<Rental> filterRentalsByDate(Date from, Date to) {
        ArrayList<Rental> filteredRentals = new ArrayList<>();
        for (Rental rental : rentals.values()) {
            if (rental.getRentalDate().before(to) && from.before(rental.getRentalDate())) {
                filteredRentals.add(rental);
            }
        }
        return filteredRentals;
    }
}
