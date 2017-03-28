package library;

import library.Enums.CustomerType;
import library.Exceptions.DuplicateCustomerID;
import library.Exceptions.DuplicateItemID;
import library.Exceptions.DuplicateTransactionID;

import java.util.Date;

import static library.Util.getCustomer;
import static library.Util.getDate;

/**
 * Created by Carson on 26/03/2017.
 * 14cdwc
 * Tester class for the Library System
 */
public class TestLibrary {

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();

        Laptop laptop = new Laptop(10, "Asus", 1);
        Adaptor adaptor = new Adaptor(15, "Thunder", 1); //test duplicate item ID
        Customer customer = new Customer(CustomerType.STUDENT, "Carson", "Cook", "Eng", 1);
        Customer customer2 = new Customer(CustomerType.STUDENT, "Bob", "Job", "Eng", 1); //test duplicate customer id
        insertItem(system, laptop);
        insertItem(system, adaptor);
        LibrarySystem.printAllItems();

        insertCustomer(system, customer);
        insertCustomer(system, customer2);
        LibrarySystem.printAllCustomers();

        insertTransaction(system, laptop, customer);
        LibrarySystem.printAllRentals();
        LibrarySystem.printLateRentals();
        System.out.println("Total rent costs: " + system.getTotalRentalCosts() + " total late fees: " +
                system.getTotalLateFees());
    }

    /**
     * The following are convenience methods to avoid having a bunch of try catch blocks in main
     */

    private static void insertItem(LibrarySystem system) {
        system.addItem();
    }

    private static void insertItem(LibrarySystem system, Item item) {
        try {
            system.addItem(item);
        } catch (DuplicateItemID e) {
            System.out.println(e + " Item " + e.getBadItem() + " was not inserted");
        }
    }

    private static void insertItem(LibrarySystem system, int id) {
        system.addItem(id);
    }

    private static void insertCustomer(LibrarySystem system) {
        system.addCustomer();
    }

    private static void insertCustomer(LibrarySystem system, Customer customer) {
        try {
            system.addCustomer(customer);
        } catch (DuplicateCustomerID e) {
            System.out.println(e + " Customer " + e.getBadCustomer() + " was not inserted");
        }
    }

    private static void insertTransaction(LibrarySystem system, Item item, Customer customer) {
        try {
            system.addTransaction(item, customer);
        } catch (DuplicateTransactionID e) {
            System.out.println(e + " Rental " + e.getBadRental() + " was not inserted");
        }
    }

    private static void insertTransaction(LibrarySystem system, Item item, Customer customer, Date date) {
        try {
            system.addTransaction(item, customer, date);
        } catch (DuplicateTransactionID e) {
            System.out.println(e + " Rental " + e.getBadRental() + " was not inserted");
        }
    }
}
