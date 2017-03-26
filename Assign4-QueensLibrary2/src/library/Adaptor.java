package library;

/**
 * Created by Carson on 02/03/2017.
 * 14cdwc
 * Child of Item and Device.
 * Class representing an Adaptor in the library.
 */
public class Adaptor extends Device {

    public Adaptor(double rentalCost,String name){
        super(rentalCost,name);
    }

    public Adaptor(double rentalCost, String name, int id) {
        super(rentalCost, name, id);
    }

    /**
     * Copy constructor for an Adaptor.
     *
     * @param adaptor Adaptor to be copied.
     */
    public Adaptor(Adaptor adaptor) {
        super(adaptor);
    }

    @Override
    public boolean equals(Object obj) {
        //need to check if obj is of type Adaptor, if not, caller and obj are not equal
        if (obj == null || !(obj.getClass() == Adaptor.class)) {
            return false;
        }
        Adaptor otherAdaptor = (Adaptor) obj;
        //check if values are equal
        return otherAdaptor.getName().equals(this.getName()) && otherAdaptor.getID() == this.getID()
                && otherAdaptor.getRentalCost() == this.getRentalCost();
    }

    @Override
    public String toString() {
        return "Adaptor " + super.toString();
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
