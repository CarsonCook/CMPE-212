package library;

/**
 * Created by Carson on 02/03/2017.
 * 14cdwc
 * Parent class for all items within the library.
 * Abstract class - a constructor and several parameters with getters and setters.
 */
public abstract class Item {
    private int id;
    private String name;

    public Item(String name, int id) {
        this.id = id;
        //use setter's ability to check for invalid name
        setName(name);
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

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        //check if bad name, if it is, set to flag/default value library
        if (name == null || name.isEmpty()) {
            name = "library";
        }
        this.name = name;
    }
}
