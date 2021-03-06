package library;

import library.Enums.CustomerType;
import library.Enums.RentalStatus;
import library.Exceptions.DuplicateCustomerID;
import library.Exceptions.DuplicateItemID;
import library.Exceptions.DuplicateTransactionID;
import library.Exceptions.WrongRentalCost;

import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private HashMap<Integer, Item> items = new HashMap<>();
    private HashMap<Integer, Customer> customers = new HashMap<>();
    private HashMap<Integer, Rental> rentals = new HashMap<>();

    /**
     * Adds a Customer to the collection of Customers.
     *
     * @param newCust New Customer to be added.
     * @throws DuplicateCustomerID The Customer has an ID that is already in use.
     */
    public void addCustomer(Customer newCust) throws DuplicateCustomerID {
        //check for duplicate ID
        if (customers.containsKey(newCust.getID())) {
            throw new DuplicateCustomerID(newCust);
        }
        customers.put(newCust.getID(), newCust);
    }

    public void addCustomer() {
        try {
            addCustomer(getCustomer());
        } catch (DuplicateCustomerID e) {
            System.out.println(e + " Customer " + e.getBadCustomer() + " was not inserted");
        }
    }

    /**
     * Adds a Rental to the collection on Rentals using an Item.
     *
     * @param newItem Item to be rented.
     * @throws DuplicateTransactionID The Item has an ID that is already in use.
     */
    public void addTransaction(Item newItem, Customer customer) throws DuplicateTransactionID {
        addTransaction(newItem, customer, getDate("date rented"));
    }

    public void addTransaction(Item newItem, Customer customer, Date date) throws DuplicateTransactionID {
        Date returnDate = getDate("estimated return date");
        Rental newRental = new Rental(newItem, customer, date, returnDate);
        //check for duplicate ID
        if (rentals.containsKey(newRental.getID())) {
            throw new DuplicateTransactionID(newRental);
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
                throw new DuplicateItemID(newItem);
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
                    newItem = new Device(getUserRentalCost(), getString("Enter the device's name"));
                    itemOK = true;
                    break;
                case "book":
                    newItem = new Book(getInt("Enter the year the book was published"), getString("Enter the author(s)"),
                            getString("Enter the publisher"), getString("Enter the book's name"));
                    itemOK = true;
                    break;
                case "adaptor":
                    newItem = new Adaptor(getUserRentalCost(), getString("Enter the adaptor's name"));
                    itemOK = true;
                    break;
                case "laptop":
                    newItem = new Laptop(getUserRentalCost(), getString("Enter the laptop's name"));
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
                    newItem = new Device(getUserRentalCost(), getString("Enter the device's name"), id);
                    itemOK = true;
                    break;
                case "book":
                    newItem = new Book(getInt("Enter the year the book was published"), getString("Enter the author(s)"),
                            getString("Enter the publisher"), getString("Enter the book's name"), id);
                    itemOK = true;
                    break;
                case "adaptor":
                    newItem = new Adaptor(getUserRentalCost(), getString("Enter the adaptor's name"), id);
                    itemOK = true;
                    break;
                case "laptop":
                    newItem = new Laptop(getUserRentalCost(), getString("Enter the laptop's name"), id);
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

    public void printLateRentals() {
        String output = "Late rentals:\n";
        for (Rental rental : rentals.values()) {
            if (rental.isLate(new Date())) {
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
            try {
                double oneCost = getItemRentalCost(rental);
                if (oneCost < 0) {
                    throw new WrongRentalCost();
                }
                totalRentalCosts += oneCost;
            } catch (WrongRentalCost e) {
                System.out.println(e + " 0 was used as the rental cost.");
            }
        }
        return totalRentalCosts;
    }

    public double getItemRentalCost(Rental rental) {
        if (rental == null || !(rental.getItem() instanceof Device)) {
            return 0;
        }
        Device device = (Device) rental.getItem();
        if (rental.getCustomer().getType() == CustomerType.STUDENT) {
            return device.getRentalCost() - 0.25 * device.getRentalCost();
        }
        return device.getRentalCost(); //not student, no discount
    }

    public void printAllCustomers() {
        for (Customer customer : customers.values()) {
            System.out.println(customer);
        }
    }

    public void printAllItems() {
        for (Item item : items.values()) {
            System.out.println(item);
        }
    }

    public void printAllRentals() {
        for (Rental rental : rentals.values()) {
            System.out.println(rental);
        }
    }

    public double getUserRentalCost() {
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

    public HashMap<Integer, Item> getItems() {
        return items;
    }

    public HashMap<Integer, Customer> getCustomers() {
        return customers;
    }

    public HashMap<Integer, Rental> getRentals() {
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

    public void readFile(String filePath) {
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                switch (filePath) {
                    case "items.in":
                        parseItemFileLine(line);
                        break;
                    case "customers.in":
                        parseCustomerFileLine(line);
                        break;
                    case "trans.in":
                        parseTransFileLine(line);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void parseItemFileLine(String line) {
        String[] values = line.split(", ");
        Item newItem = null;
        switch (values[0]) { //type of item
            case "Book":
                newItem = new Book(Integer.parseInt(values[5]), values[3], values[4], values[2], Integer.parseInt(values[1]));
                break;
            case "Textbook":
                newItem = new Textbook(Integer.parseInt(values[5]), values[3], values[4], values[2], Integer.parseInt(values[1]));
                break;
            case "Magazine":
                newItem = new Magazine(Integer.parseInt(values[5]), values[3], values[4], values[2], Integer.parseInt(values[1]));
                break;
            case "Device":
                newItem = new Device(Double.parseDouble(values[3]), values[2], Integer.parseInt(values[1]));
                break;
            case "Adaptor":
                newItem = new Adaptor(Double.parseDouble(values[3]), values[2], Integer.parseInt(values[1]));
                break;
            case "Laptop":
                newItem = new Laptop(Double.parseDouble(values[3]), values[2], Integer.parseInt(values[1]));
                break;
        }
        if (newItem == null) {
            System.out.println("Bad item in file, nothing added to system");
        } else {
            items.put(newItem.getID(), newItem);
        }
    }

    private void parseCustomerFileLine(String line) {
        String[] values = line.split(", ");
        String[] names = values[1].split(" ");
        String lastName = names.length < 2 ? null : names[1];
        CustomerType type = values[3].toUpperCase().equals(CustomerType.EMPLOYEE.toString()) ?
                CustomerType.EMPLOYEE : CustomerType.STUDENT;
        Customer newCust = new Customer(type, names[0], lastName, values[2], Integer.parseInt(values[0]));
        customers.put(newCust.getID(), newCust);
    }

    private void parseTransFileLine(String line) {
        String[] values = line.split(", ");
        int transID = Integer.parseInt(values[0]);
        int itemID = Integer.parseInt(values[1]);
        int custID = Integer.parseInt(values[2]);
        Date rentalDate = new Date();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        try {
            rentalDate = df.parse(values[3]);
        } catch (ParseException e) {
            System.out.println("Bad rental date, set to today");
        }
        Date expectedReturnDate = new Date();
        try {
            expectedReturnDate = df.parse(values[4]);
        } catch (ParseException e) {
            System.out.println("Bad rental date, set to today");
        }
        Date returnedDate = new Date();
        try {
            returnedDate = df.parse(values[5]);
        } catch (ParseException e) {
            System.out.println("Bad rental date, set to today");
        }
        Rental newRental = new Rental(items.get(itemID), transID, customers.get(custID), rentalDate, expectedReturnDate);
        if (returnedDate.getTime() > 0) {
            newRental.itemReturned(returnedDate);
        }
        System.out.println(newRental);
    }

    public void writeItemsToFile(String filePath) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(filePath);
            bw = new BufferedWriter(fw);
            String output = "";
            for (Item item : items.values()) {
                if (item instanceof Device) {
                    if (item.getClass() == Laptop.class) {
                        output = output.concat("Laptop, ");
                    } else if (item.getClass() == Adaptor.class) {
                        output = output.concat("Adaptor, ");
                    } else { //Device
                        output = output.concat("Device, ");
                    }
                    output = output.concat(item.getID() + ", " + item.getName() + ", " + ((Device) item).getRentalCost() + "\n");
                } else { //Book
                    if (item.getClass() == Textbook.class) {
                        output = output.concat("Textbook, ");
                    } else if (item.getClass() == Magazine.class) {
                        output = output.concat("Magazine, ");
                    } else {//book
                        output = output.concat("Book, ");
                    }
                    output = output.concat(item.getID() + ", " + item.getName() + ", " + ((Book) item).getAuthors() + ", "
                            + ((Book) item).getPublisher() + ", " + ((Book) item).getYear() + "\n");
                }
            }
            output = output.trim(); //remove last endline
            bw.write(output);
        } catch (IOException e) {
            System.out.println("bad file path, nothing written");
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void writeTransactionsToFile(String filePath) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(filePath);
            bw = new BufferedWriter(fw);
            String output = "";
            for (Rental rental : rentals.values()) {
                output = output.concat(rental.getID() + ", " + rental.getItem().getID() + ", " + rental.getCustomer().getID()
                        + ", " + rental.getRentalDate() + ", " + rental.getEstReturnDate() + ", " + rental.getActReturnDate() + "\n");
            }
            output = output.trim(); //remove last endline
            bw.write(output);
        } catch (IOException e) {
            System.out.println("bad file path, nothing written");
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
