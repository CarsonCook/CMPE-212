package library.Exceptions;


import library.Customer;

/**
 * Created by Carson on 27/03/2017.
 * 14cdwc
 * Exception for when a Customer has an ID already in use.
 */
public class DuplicateCustomerID extends Exception {
    private Customer badCustomer;

    public DuplicateCustomerID(Customer badCustomer) {
        super("You tried to insert a Customer with an ID that is already in use!");
        this.badCustomer = badCustomer;
    }

    public Customer getBadCustomer() {
        return badCustomer;
    }
}
