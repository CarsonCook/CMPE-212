package library;

/**
 * Created by Carson on 02/03/2017.
 * 14cdwc
 * Child of Item and Device.
 * Class representing an Adaptor in the library.
 */
public class Adaptor extends Device {

    public Adaptor(double rentalCost, String name) {
        super(rentalCost, name);
    }

    public Adaptor(Adaptor adaptor) {
        super(adaptor);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Adaptor)) {
            return false;
        }
        Adaptor otherAdaptor = (Adaptor) obj;
        return otherAdaptor.getName().equals(this.getName()) && otherAdaptor.getID() == this.getID()
                && otherAdaptor.getRentalCost() == this.getRentalCost();
    }

    @Override
    public String toString() {
        return "ID: " + getID() + " adaptor name: " + getName() + " rental cost: " + getRentalCost();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Adaptor(this);
    }

    @Override
    public double getLateFees(int daysLate) {
        return 2.5 * daysLate + 0.15 * getRentalCost();
    }
}
