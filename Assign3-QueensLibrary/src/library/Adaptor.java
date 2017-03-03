package library;

/**
 * Created by Carson on 02/03/2017.
 * 14cdwc
 * Child of Item and Device.
 * Class representing an Adaptor in the library.
 */
public class Adaptor extends Device {

    /**
     * Constructor that sets attribute values.
     * @param rentalCost double representing rental cost of the Adaptor.
     * @param name String representing the name of the Adaptor.
     */
    public Adaptor(double rentalCost, String name) {
        super(rentalCost, name);
    }

    /**
     * Copy constructor for an Adaptor.
     * @param adaptor Adaptor to be copied.
     */
    public Adaptor(Adaptor adaptor) {
        super(adaptor);
    }

    @Override
    public boolean equals(Object obj) {
        //need to check if obj is an instance of Adaptor, if not, caller and obj are not equal
        //also deals with null parameter
        if (!(obj instanceof Adaptor)) {
            return false;
        }
        Adaptor otherAdaptor = (Adaptor) obj;
        //check if values are equal
        return otherAdaptor.getName().equals(this.getName()) && otherAdaptor.getID() == this.getID()
                && otherAdaptor.getRentalCost() == this.getRentalCost();
    }

    @Override
    public String toString() {
        return "ID: " + getID() + " adaptor name: " + getName() + " rental cost: " + getRentalCost();
    }

    @Override
    public Adaptor clone() throws CloneNotSupportedException {
        return new Adaptor(this);
    }

    @Override
    public double getLateFees(int daysLate) {
        return 2.5 * daysLate + 0.15 * getRentalCost();
    }
}
