package library.Exceptions;

/**
 * Created by Carson on 27/03/2017.
 * 14cdwc
 * Exception for when a Customer has an ID already in use.
 */
public class DuplicateCustomerID extends Exception {
    public DuplicateCustomerID() {
        super("You tried to insert a Customer with an ID that is already in use!");
    }
}
