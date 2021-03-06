package library;

/**
 * Created by Carson on 02/03/2017.
 * 14cdwc
 * Child of Item. Parent for Adaptor and Laptop items in library.
 */
public class Device extends Item {
    private double rentalCost;

    public Device(double rentalCost, String name) {
        super(name);
        //use setter's check for invalid rental cost
        setRentalCost(rentalCost);
    }

    public Device(Device device) {
        //don't call super or this in order to check for a null device being copied
        if (device == null) {
            //no values available, so use default ones
            this.setName("");
            this.setRentalCost(0);
        } else {
            this.setName(device.getName());
            this.setRentalCost(device.rentalCost);
        }
    }

    @Override
    public boolean equals(Object obj) {
        //need to check if obj is of type Device, if not, caller and obj are not equal
        if (obj == null || !(obj.getClass() == Device.class)) {
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

    public double getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(double rentalCost) {
        //check if rental cost invalid, if it is, set to 0 as a flag/default value
        if (rentalCost < 0) {
            rentalCost = 0;
        }
        this.rentalCost = rentalCost;
    }
}
