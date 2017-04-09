package library.Exceptions;

/**
 * Created by Carson on 27/03/2017.
 * 14cdwc
 * Exception for when a return is made before it was Rented.
 */
public class DateReturnedBeforeDateRented extends Exception {
    public DateReturnedBeforeDateRented() {
        super("You tried to return an Item before it was logged as Rented!");
    }
}
