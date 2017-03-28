package library.Exceptions;


import library.Rental;

/**
 * Created by Carson on 27/03/2017.
 * 14cdwc
 * Exception for a Rental having an ID already in use.
 */
public class DuplicateTransactionID extends Exception {
    private Rental badRental;

    public DuplicateTransactionID(Rental badRental) {
        super("You tried to enter a transaction ID that is already in use!");
        this.badRental = badRental;
    }

    public Rental getBadRental() {
        return badRental;
    }
}
