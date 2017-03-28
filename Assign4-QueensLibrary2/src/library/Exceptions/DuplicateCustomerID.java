package library.Exceptions;

/**
 * Created by Carson on 27/03/2017.
 * 14cdwc
 * Exception for when a Customer has an ID already in use.
 */
public class DuplicateCustomerID extends Exception {
    private int badID;

    public DuplicateCustomerID(int badID) {
        super("You tried to insert a Customer with an ID that is already in use!");
        this.badID = badID;
    }

    public int getBadID() {
        return badID;
    }
}
