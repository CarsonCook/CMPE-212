package library;

import library.Exceptions.DuplicateCustomerID;
import library.Exceptions.DuplicateTransactionID;

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
        system.addItem();
        system.addItem(0); //test if id already used exception will work
        system.addItem();
        try {
            system.addCustomer(getCustomer());
            system.addCustomer(getCustomer());
            system.addTransaction(LibrarySystem.getItems().get(0), LibrarySystem.getCustomers().get(0), getDate());
            system.addTransaction(LibrarySystem.getItems().get(1), LibrarySystem.getCustomers().get(1), getDate());
        } catch (DuplicateCustomerID e) {
            System.out.println(e + " Customer " + e.getBadID() + " was not inserted.");
        } catch (DuplicateTransactionID e) {
            System.out.println(e + " Transaction " + e.getBadID() + "was not made.");
        }
    }
}
