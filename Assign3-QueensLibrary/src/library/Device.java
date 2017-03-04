package library;

/**
 * Created by Carson on 02/03/2017.
 * 14cdwc
 * Child of Item. Parent for Adaptor and Laptop items in library.
 */
public class Device extends Item {
    private double rentalCost;

    /**
     * Constructor that sets attribute values.
     *
     * @param rentalCost double representing the cost of renting this Device.
     * @param name       String representing the name of this Device.
     */
    public Device(double rentalCost, String name) {
        super(name);
        //use setter's check for invalid rental cost
        setRentalCost(rentalCost);
    }

    /**
     * Copy constructor for a Device.
     *
     * @param device Device object to be copied.
     */
    public Device(Device device) {
        super(device.getName());
        new Device(device.rentalCost, device.getName());
    }

    @Override
    public boolean equals(Object obj) {
        //need to check if obj is an instance of Adaptor, if not, caller and obj are not equal
        //also deals with null parameter
        if (!(obj instanceof Device)) {
            return false;
        }
        Device otherDevice = (Device) obj;
        return otherDevice.getName().equals(this.getName()) &&
                otherDevice.getID() == this.getID() && otherDevice.rentalCost == this.rentalCost;
    }

    @Override
    public String toString() {
        return "Device " + super.toString() + ", rental cost: " + rentalCost;
    }

    @Override
    public Device clone() throws CloneNotSupportedException {
        return new Device(this); //newer Java version so don't need to cast
    }

    @Override
    public double getLateFees(int daysLate) {
        return 2 * daysLate + 0.1 * rentalCost;
    }

    /**
     * Getter for the rental cost of this Device.
     *
     * @return double representing the rental cost.
     */
    public double getRentalCost() {
        return rentalCost;
    }

    /**
     * Setter for the rental cost of this Device. Also sanitizes the parameter and if it is bad sets it to a default value.
     *
     * @param rentalCost double representing the rental cost of this device.
     */
    public void setRentalCost(double rentalCost) {
        //check if rental cost invalid, if it is, set to 0 as a flag/default value
        if (rentalCost < 0) {
            rentalCost = 0;
        }
        this.rentalCost = rentalCost;
    }
}
