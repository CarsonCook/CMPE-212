package library;

/**
 * Created by Carson on 02/03/2017.
 * 14cdwc
 * Parent class for all items within the library.
 * <p>
 * Test for null value in copy constructor
 * test ID incremented properly
 */
public abstract class Item {
    private static int id = 0;
    private String name;

    public Item(String name) {
        this.name = name;
        id++;
    }

    abstract public double getLateFees(int daysLate);

    //instructions said only one abstract, but that all classes need these methods,
    //so to remind myself I made abstract methods for them.
    abstract public String toString();

    abstract public boolean equals(Object obj);

    abstract public Object clone() throws CloneNotSupportedException;

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        //check if empty name, if it is, set to flag/default value library
        if (name.isEmpty()) {
            name = "library";
        }
        this.name = name;
    }
}
