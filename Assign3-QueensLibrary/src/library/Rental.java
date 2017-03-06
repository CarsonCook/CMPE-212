package library;

/**
 * Created by Carson on 04/03/2017.
 * 14cdwc
 * Class to hold a rental transaction for the library
 */
public class Rental {

    private Item item;
    private int customerID, rentalDays, daysLate;
    private static int numInstances = 0;

    public Rental(Item item, int rentalDays, int daysLate) {
        //use setters to filter out bad values
        setItem(item);
        setDaysLate(daysLate);
        setRentalDays(rentalDays);
        numInstances++;
        this.customerID = numInstances;
    }

    /**
     * Copy constructor for a Rental object.
     *
     * @param rental Rental object to be copied.
     */
    public Rental(Rental rental) {
        this(rental.item, rental.rentalDays, rental.daysLate);
    }

    @Override
    public String toString() {
        return "Rental: customer ID: " + customerID + ", item: " + item.getName() + ", rental days: " + rentalDays
                + ", days late: " + daysLate;
    }

    @Override
    public Rental clone() throws CloneNotSupportedException {
        return new Rental(this);
    }

    @Override
    public boolean equals(Object obj) {
        //check obj isn't null and is a Rental object
        if (!(obj instanceof Rental)) {
            return false;
        }
        Rental otherRental = (Rental) obj;
        //check values are equal
        return otherRental.customerID == this.customerID && otherRental.daysLate == this.daysLate &&
                otherRental.rentalDays == this.rentalDays && otherRental.item.equals(this.item);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        //check for bad item, if it is, set to a default/flag value
        if (item == null) {
            item = new Device(0, "not null");
        }
        this.item = item;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        //check for bad rentalDays, if it is, set to a default/flag value
        if (rentalDays < 0) {
            rentalDays = 0;
        }
        this.rentalDays = rentalDays;
    }

    public int getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(int daysLate) {
        //check for bad daysLate, if it is, set to a default/flag value
        if (daysLate < 0) {
            daysLate = 0;
        }
        this.daysLate = daysLate;
    }
}
