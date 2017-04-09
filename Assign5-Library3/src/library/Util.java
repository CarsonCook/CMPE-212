package library;

import library.Enums.CustomerType;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Carson on 26/03/2017.
 * 14cdwc
 * Class for random useful methods that take up space in other classes
 * User input, cloning and sanitation methods.
 */
public class Util {
    /**
     * Gets a user-inputted String representing the type of item being rented. Sanitized to make sure it is a type that exists.
     *
     * @return String
     */
    public static String getItemType() {
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
    public static boolean goodItemName(String item) {
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
    public static Device cloneDevice(Device device) {
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
    public static Book cloneBook(Book book) {
        try {
            return book.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone not supported!");
        }
        return null; //flag for no clone possible
    }


    /**
     * Convenience method to clone a Rental, adds the try-catch block around the clone call.
     *
     * @param rental Rental to be cloned
     * @return Cloned Rental.
     */
    public static Rental cloneRental(Rental rental) {
        try {
            return rental.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone not supported!");
        }
        return null; //flag for no clone possible
    }

    public static Customer getCustomer() {
        CustomerType customerType = getCustomerType();
        String firstName = getString("Enter the customer's first name:");
        String lastName = getString("Enter the customer's last name:");
        String department = getString("Enter the customer's department:");
        return (new Customer(customerType, firstName, lastName, department));
    }

    public static CustomerType getCustomerType() {
        while (true) { //go until get good input and function returns
            String customer = getString("Enter whether the customer is a student or employee:");
            customer = customer.toLowerCase();
            switch (customer) {
                case "student":
                    return CustomerType.STUDENT;
                case "employee":
                    return CustomerType.EMPLOYEE;
                default:
                    System.out.println("You entered a bad customer type! Try again.");
            }
        }
    }

    public static Date getDate(String whatDate) {
        while (true) { //go until input ok, at which point function returns
            String sDate = getString("Enter the " + whatDate + " in format dd-MMM-yyyy (e.g. March is MAR):");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            try {
                return dateFormat.parse(sDate);
            } catch (ParseException e) {
                System.out.println("You didn't enter a proper date. Follow the format dd-MMM-yyyy and try again.");
            }
        }
    }

    /**
     * Gets a user-inputted integer. Used to get year a book was published, days a rental item is late and days
     * a rental has been made for.
     *
     * @param prompt Message to show user
     * @return int
     */
    public static int getInt(String prompt) {
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
    public static String getString(String prompt) {
        return JOptionPane.showInputDialog(null, prompt);
    }
}
