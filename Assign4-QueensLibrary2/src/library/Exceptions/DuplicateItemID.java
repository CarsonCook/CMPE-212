package library.Exceptions;

/**
 * Created by Carson on 25/03/2017.
 * 14cdwc
 * Exception for an Item having an ID that is already used.
 */
public class DuplicateItemID extends Exception {
    private int badID;

    public DuplicateItemID(int badID) {
        super("You tried to insert an Item with an ID that is already in use!");
        this.badID = badID;
    }

    public int getBadID() {
        return badID;
    }
}
