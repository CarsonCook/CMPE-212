package library;

import library.Exceptions.DuplicateCustomerID;
import library.Exceptions.DuplicateItemID;
import library.Exceptions.DuplicateTransactionID;

import java.util.Date;

/**
 * Created by Carson on 26/03/2017.
 * 14cdwc
 * Tester class for the Library System
 */
public class TestLibrary {

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.readFile("trans.in");
       /* Laptop laptop = new Laptop(10, "Asus", 1);
        Adaptor adaptor = new Adaptor(12, "Bolt");
        Adaptor badAdaptor = new Adaptor(15, "Thunder", 1); //test duplicate item ID
        Magazine magazine = new Magazine(2015, "Carson Cook", "Golden Words", "Cool stuff");
        Textbook textbook = new Textbook(2012, "Manji", "Queens", "271 Info");
        insertItem(system, laptop);
        insertItem(system, adaptor);
        insertItem(system, badAdaptor);
        insertItem(system, magazine);
        insertItem(system, textbook);
        system.printAllItems();

        Customer customer = new Customer(CustomerType.STUDENT, "Carson", "Cook", "Eng", 1);
        Customer badCustomer = new Customer(CustomerType.STUDENT, "Bob", "Guy", "Eng", 1); //test duplicate customer id
        Customer customer2 = new Customer(CustomerType.EMPLOYEE, "Jill", "Reid", "Comp");
        insertCustomer(system, customer);
        insertCustomer(system, badCustomer);
        insertCustomer(system, customer2);
        system.printAllCustomers();

        insertTransaction(system, laptop, customer);
        system.printAllRentals();
        system.printLateRentals();
        System.out.println("Total rental costs: " + system.getTotalRentalCosts() + " total late fees: " +
                system.getTotalLateFees());

        for (Rental rental : system.getRentals().values()) {
            rental.itemReturned(new Date());
        }*/
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
