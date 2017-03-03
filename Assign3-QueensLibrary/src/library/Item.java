package library;

/**
 * Created by Carson on 02/03/2017.
 * 14cdwc
 * Parent class for all items within the library.
 */
public abstract class Item {
    private static int id;
    private String name;

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
        this.name = name;
    }
}
