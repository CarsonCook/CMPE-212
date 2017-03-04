package library;

/**
 * Created by Carson on 02/03/2017.
 * 14cdwc
 * Child of Device and Item.
 * Class representing a Laptop in the library.
 */
public class Laptop extends Device {

    /**
     * Constructor that sets attribute for this Laptop.
     *
     * @param rentalCost double representing the rental cost of this Laptop.
     * @param name       String representing the name of this Laptop.
     */
    public Laptop(double rentalCost, String name) {
        super(rentalCost, name);
    }

    /**
     * Copy constructor for a Laptop.
     *
     * @param laptop Laptop to be copied
     */
    public Laptop(Laptop laptop) {
        super(laptop);
    }

    @Override
    public boolean equals(Object obj) {
        //need to check if obj is an instance of Adaptor, if not, caller and obj are not equal
        //also deals with null parameter
        if (!(obj instanceof Laptop)) {
            return false;
        }
        Laptop otherLaptop = (Laptop) obj;
        return otherLaptop.getName().equals(this.getName()) && otherLaptop.getID() == this.getID()
                && otherLaptop.getRentalCost() == this.getRentalCost();
    }

    @Override
    public String toString() {
        return "Device " + super.toString();
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
