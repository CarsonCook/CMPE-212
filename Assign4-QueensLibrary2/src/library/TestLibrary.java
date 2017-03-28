package library;

import library.Exceptions.DuplicateCustomerID;
import library.Exceptions.DuplicateTransactionID;

import java.util.Date;

import static library.Util.getCustomer;
import static library.Util.getDate;

/**
 * Created by Carson on 26/03/2017.
 * 14cdwc
 * Tester class for the Library System
 */
//TODO test exceptions work, add more items
public class TestLibrary {

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.addItem();
        try {
            system.addCustomer(getCustomer());
            //  system.addCustomer(getCustomer());
            system.addTransaction(LibrarySystem.getItems().get(0), LibrarySystem.getCustomers().get(0), null);
            //    system.addTransaction(LibrarySystem.getItems().get(1), LibrarySystem.getCustomers().get(1), getDate());
        } catch (DuplicateCustomerID e) {
            System.out.println(e + " Customer " + e.getBadID() + " was not inserted.");
        } catch (DuplicateTransactionID e) {
            System.out.println(e + " Transaction " + e.getBadID() + "was not made.");
        }
        system.printAllRentals();
        LibrarySystem.getRentals().get(0).itemReturned(getDate());
        double lateFees = 0;
        double totalCost = 0;
        Date date = getDate();
        for (Rental rental : LibrarySystem.getRentals().values()) {
            if (rental.isLate(date)) {
                lateFees += rental.getLateFees();
            }
            totalCost += rental.getRentalCost(); //get rental cost, then add all late fees for total
        }
        System.out.println("Total late fees: " + lateFees + " total costs: " + totalCost);
        LibrarySystem.printLateRentals(getDate());
    }
}
