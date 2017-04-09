package library.Exceptions;

/**
 * Created by Carson on 27/03/2017.
 * 14cdwc
 * Exception for when a poor rental cost is obtained
 */
public class WrongRentalCost extends Exception {
    public WrongRentalCost() {
        super("This is a bad rental cost!");
    }
}