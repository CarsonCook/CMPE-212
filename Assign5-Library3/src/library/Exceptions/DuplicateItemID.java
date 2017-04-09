package library.Exceptions;

import library.Item;

/**
 * Created by Carson on 25/03/2017.
 * 14cdwc
 * Exception for an Item having an ID that is already used.
 */
public class DuplicateItemID extends Exception {
    private Item badItem;

    public DuplicateItemID(Item badItem) {
        super("You tried to insert an Item with an ID that is already in use!");
        this.badItem = badItem;
    }

    public Item getBadItem() {
        return badItem;
    }
}
