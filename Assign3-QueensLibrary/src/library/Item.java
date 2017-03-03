package library;

/**
 * Created by Carson on 02/03/2017.
 * 14cdwc
 * Parent class for all items within the library.
 *
 * Null value in constructor fails - how to check before calling super constructor
 */
public abstract class Item {
    private static int instanceCounter = 0;
    private int id;
    private String name;

    public Item(String name) {
        //use getter's ability to check for invalid name
        setName(name);
        instanceCounter++;
        id = instanceCounter;
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
