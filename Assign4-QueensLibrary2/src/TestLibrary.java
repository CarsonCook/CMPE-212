import library.*;
import library.Exceptions.DuplicateItemID;

import static library.Util.cloneBook;
import static library.Util.cloneDevice;

/**
 * Created by Carson on 26/03/2017.
 * 14cdwc
 * Tester class for the Library System
 */
public class TestLibrary {

    private static int idCounter = 0;

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
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
            system.addTransaction(adaptor);
        } catch (DuplicateItemID e) {
            System.out.println(e + " " + adaptor.getName() + " was not inserted.");
        }
        try {
            system.addTransaction(laptop);
        } catch (DuplicateItemID e) {
            System.out.println(e + " " + laptop.getName() + " was not inserted.");
        }
        try {
            system.addTransaction(magazine);
        } catch (DuplicateItemID e) {
            System.out.println(e + " " + magazine.getName() + " was not inserted.");
        }
        try {
            system.addTransaction(textbook);
        } catch (DuplicateItemID e) {
            System.out.println(e + " " + textbook.getName() + " was not inserted.");
        }
        system.printAllRentals();
        System.out.println("Total rental costs: " + system.getTotalRentalCosts());
        System.out.println("Total late fees: " + system.getTotalLateFees());
    }
}
