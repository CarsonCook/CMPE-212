package library.Exceptions;

/**
 * Created by Carson on 27/03/2017.
 * 14cdwc
 * Exception for a Rental having an ID already in use.
 */
public class DuplicateTransactionID extends Exception {
    private int badID;

    public DuplicateTransactionID(int badID) {
        super("You tried to enter a transaction ID that is already in use!");
        this.badID = badID;
    }

    public int getBadID() {
        return badID;
    }
}
