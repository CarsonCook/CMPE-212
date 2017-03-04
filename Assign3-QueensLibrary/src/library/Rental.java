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

    /**
     * Constructor setting attribute values.
     *
     * @param item       Item that was rented.
     * @param rentalDays Days item was rented for.
     * @param daysLate   Days the item is late.
     */
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
        new Rental(rental.item, rental.rentalDays, rental.daysLate);
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

    /**
     * Getter for the item rented.
     *
     * @return Item object that was rented.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Setter for the Item rented. Filters out bad values and sets it to a default/flag.
     *
     * @param item Item representing what was rented out.
     */
    public void setItem(Item item) {
        //check for bad item, if it is, set to a default/flag value
        if (item == null) {
            item = new Device(0, "not null");
        }
        this.item = item;
    }

    /**
     * Getter for the item rented.
     *
     * @return Item object that was rented.
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Getter for the number of days the item was rented for.
     *
     * @return int that is the number of days item was rented for.
     */
    public int getRentalDays() {
        return rentalDays;
    }

    /**
     * Setter for the number of days the item is rented for. Filters out bad values and sets it to a default/flag.
     *
     * @param rentalDays int representing the number of days the item is reted for.
     */
    public void setRentalDays(int rentalDays) {
        //check for bad rentalDays, if it is, set to a default/flag value
        if (rentalDays < 0) {
            rentalDays = 0;
        }
        this.rentalDays = rentalDays;
    }

    /**
     * Getter for the number of days the item is late
     *
     * @return int that is the number of days the item is late.
     */
    public int getDaysLate() {
        return daysLate;
    }

    /**
     * Setter for the number of days the item is late. Filters out bad values and sets it to a default/flag.
     *
     * @param daysLate int representing the number of days the item is late.
     */
    public void setDaysLate(int daysLate) {
        //check for bad daysLate, if it is, set to a default/flag value
        if (daysLate < 0) {
            daysLate = 0;
        }
        this.daysLate = daysLate;
    }
}
