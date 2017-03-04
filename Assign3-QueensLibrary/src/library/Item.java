package library;

/**
 * Created by Carson on 02/03/2017.
 * 14cdwc
 * Parent class for all items within the library.
 * Abstract class - a constructor and several parameters with getters and setters.
 * <p>
 * Null value in constructor fails - how to check before calling super constructor
 */
public abstract class Item {
    private static int instanceCounter = 0; //counts instances of Item - all items in the library
    private int id;
    private String name;

    /**
     * Constructor used to set the name of the item in the library, and to increment the ID of the item.
     * ID is the number of items thus far in the library.
     *
     * @param name String representing the name of the item.
     */
    public Item(String name) {
        //use setter's ability to check for invalid name
        setName(name);
        instanceCounter++; //another instance made
        id = instanceCounter; //ID is just the number of instances at this point in time - always increments so no ID the same
    }

    /**
     * Abstract method ensuring every item has a getLateFees method.
     *
     * @param daysLate int representing the number of days the item is late
     * @return double representing the fee to be paid, based on item rental cost/number of days late
     */
    abstract public double getLateFees(int daysLate);

    @Override
    public String toString() {
        return "ID: " + id + ", name: " + name;
    }

    /**
     * Getter for ID
     *
     * @return int that is the ID of the calling item.
     */
    public int getID() {
        return id;
    }

    /**
     * Getter for name.
     *
     * @return String that is the name of the calling item.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name. Also sanitizes value to be set and if it is bad, set's it to a default value "library".
     *
     * @param name String that is the name of the calling item.
     */
    public void setName(String name) {
        //check if bad name, if it is, set to flag/default value library
        if (name.isEmpty()) {
            name = "library";
        }
        this.name = name;
    }
}
