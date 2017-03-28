package library;

import library.Enums.CustomerType;
import library.Enums.RentalStatus;
import library.Exceptions.DateReturnedBeforeDateRented;
import library.Exceptions.WrongRentalCost;

import java.util.Date;

/**
 * Created by Carson on 04/03/2017.
 * 14cdwc
 * Class to hold a rental transaction for the library
 */
//TODO test null date
public class Rental {

    private Item item;
    private Date rentalDate, estReturnDate, actReturnDate; //date holds date and time
    private int id;
    private static int instanceCounter = 0;
    private Customer customer;
    private RentalStatus status;

    public Rental(Item item, Customer customer, Date rentalDate, Date estReturnDate) {
        //use setters to filter out bad values
        setItem(item);
        setCustomer(customer);
        setRentalDate(rentalDate);
        setEstReturnDate(estReturnDate);
        setStatus(RentalStatus.ACTIVE); //upon rental addition, it is active
        instanceCounter++;
        id = instanceCounter;
    }

    public Rental(Item item, int id, Customer customer, Date rentalDate, Date estReturnDate) {
        //use setters to filter out bad values
        setItem(item);
        setCustomer(customer);
        setRentalDate(rentalDate);
        setEstReturnDate(estReturnDate);
        setStatus(RentalStatus.ACTIVE); //upon rental addition, it is active
        this.id = id;
        instanceCounter++;
    }

    /**
     * Copy constructor for a Rental object.
     *
     * @param rental Rental object to be copied.
     */
    public Rental(Rental rental) {
        this(rental.item, rental.customer, rental.rentalDate, rental.estReturnDate);
    }

    @Override
    public String toString() {
        return "Rental{" +
                "item=" + item +
                ", rentalDate=" + rentalDate +
                ", estReturnDate=" + estReturnDate +
                ", actReturnDate=" + actReturnDate +
                ", id=" + id +
                ", customer=" + customer +
                ", status=" + status +
                '}';
    }

    @Override
    public Rental clone() throws CloneNotSupportedException {
        return new Rental(this);
    }

    @Override
    public boolean equals(Object obj) {
        //need to check if obj is of type Rental, if not, caller and obj are not equal
        if (obj == null || !(obj.getClass() == Rental.class)) {
            return false;
        }
        Rental otherRental = (Rental) obj;
        //check values are equal
        return otherRental.id == this.id && otherRental.customer.equals(this.customer) && otherRental.rentalDate.equals(this.rentalDate)
                && otherRental.estReturnDate.equals(this.estReturnDate) && otherRental.actReturnDate.equals(this.actReturnDate)
                && otherRental.item.equals(this.item);
    }

    /**
     * Handles logic for getting the late fees of a Rental.
     *
     * @return 0 if Rental not late, else the late fee for the rented Item.
     */
    public double getLateFees() {
        //getTime() gets ms, so convert to days
        if (status == RentalStatus.LATE) {
            int daysLate = (int) estReturnDate.getTime() / 1000 / 60 / 60 / 24 -
                    (int) actReturnDate.getTime() / 1000 / 60 / 60 / 24;
            return item.getLateFees(daysLate);
        } else {
            return 0; //not late, no cost
        }
    }

    /**
     * Method that gets the rental cost of a Rental.
     *
     * @return 0 if Rental is not a Device and thus has no rental cost, else the rental cost of the Device, with a 25%
     * discount applied if the Customer is a STUDENT.
     */
    public double getRentalCost() {
        if (item instanceof Device) {
            double baseCost = -1; //if the getRentalCost() fails, stay at -1 so exception thrown and message displayed
            try {
                baseCost = ((Device) item).getRentalCost();
                if (baseCost < 0) {
                    throw new WrongRentalCost();
                }
            } catch (WrongRentalCost e) {
                System.out.println(e + " 0 will be used");
            }
            if (getCustomer().getType() == CustomerType.STUDENT) { //student, so 25% discount
                return baseCost - baseCost * 0.25;
            } else {
                return baseCost;
            }
        } else { //no rental cost for the Item (it is a Book)
            return 0;
        }
    }

    public double getTotalToBePaid() {
        return getRentalCost() + getLateFees();
    }

    /**
     * Method that returns true if the Rental is late, and sets status to late.
     *
     * @param curDate Current date, to be compared to when the Rental should be returned.
     * @return True if late (curDate<returnDate) else false
     */
    public boolean isLate(Date curDate) {
        if (estReturnDate.before(curDate)) {
            setStatus(RentalStatus.LATE);
            return true;
        }
        return false;
    }

    /**
     * Method that handles logic for when the Rental is returned
     *
     * @param retDate Date Rental is returned.
     */
    public void itemReturned(Date retDate) {
        try {
            if (actReturnDate.before(rentalDate)) {
                throw new DateReturnedBeforeDateRented();
            }
        } catch (DateReturnedBeforeDateRented e) {
            System.out.println(e + " Entered as returned the day it was rented.");
            retDate = rentalDate;
        }
        setStatus(RentalStatus.CLOSED);
        setActReturnDate(retDate);
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        if (status == null) { //avoid null exceptions, use default value
            status = RentalStatus.ACTIVE;
        }
        this.status = status;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        if (rentalDate == null) { //to avoid null exceptions, make a default/flag date value
            rentalDate = new Date(1);
        }
        this.rentalDate = rentalDate;
    }

    public Date getEstReturnDate() {
        return estReturnDate;
    }

    public void setEstReturnDate(Date estReturnDate) {
        if (estReturnDate == null) { //to avoid null exceptions, make a default/flag date value
            estReturnDate = new Date(1);
        }
        this.estReturnDate = estReturnDate;
    }

    public Date getActReturnDate() {
        return actReturnDate;
    }

    public void setActReturnDate(Date actReturnDate) {
        this.actReturnDate = actReturnDate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        //check for bad item, if it is, set to a default/flag value
        if (item != null) {
            this.item = item;
        } else {
            System.out.println("You tried to set a null Item! Nothing was done.");
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer == null) { //if bad customer value, create a default customer
            customer = new Customer(CustomerType.EMPLOYEE, "first", "last", "dep");
        }
        this.customer = customer;
    }

    public int getID() {
        return id;
    }
}
