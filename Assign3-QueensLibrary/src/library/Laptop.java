package library;

/**
 * Created by Carson on 02/03/2017.
 * 14cdwc
 * Child of Device and Item.
 * Class representing a Laptop in the library.
 */
public class Laptop extends Device {

    public Laptop(double rentalCost, String name) {
        super(rentalCost, name);
    }

    public Laptop(Device device) {
        super(device);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Laptop)) {
            return false;
        }
        Laptop otherLaptop = (Laptop) obj;
        return otherLaptop.getName().equals(this.getName()) && otherLaptop.getID() == this.getID()
                && otherLaptop.getRentalCost() == this.getRentalCost();
    }

    @Override
    public String toString() {
        return "ID: " + getID() + " laptop name: " + getName() + " rental cost: " + getRentalCost();
    }

    @Override
    public Laptop clone() throws CloneNotSupportedException {
        return new Laptop(this);
    }

    @Override
    public double getLateFees(int daysLate) {
        return 5 * daysLate + 0.2 * getRentalCost();
    }
}
