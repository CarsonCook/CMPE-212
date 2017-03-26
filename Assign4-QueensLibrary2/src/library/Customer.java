package library;

import library.Enums.CustomerType;

/**
 * Created by Carson on 25/03/2017.
 * 14cdwd
 * Class defining information about a customer.
 */
public class Customer {
    private CustomerType type;
    private String firstName, lastName, department;
    private int id;
    private static int instanceCounter = 0;

    public Customer(CustomerType type, String firstName, String lastName, String department) {
        instanceCounter++;
        setType(type);
        setFirstName(firstName);
        setLastName(lastName);
        setDepartment(department);
        this.id = instanceCounter;
    }

    public Customer(CustomerType type, String firstName, String lastName, String department, int id) {
        instanceCounter++;
        setType(type);
        setFirstName(firstName);
        setLastName(lastName);
        setDepartment(department);
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", first name: " + firstName + ", last name: " + lastName + ", department: " + department
                + ", type: " + type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != Customer.class) {
            return false;
        }
        Customer otherCust = (Customer) obj;
        return otherCust.type == this.type && otherCust.id == this.id && otherCust.firstName.equals(this.firstName) &&
                otherCust.lastName.equals(this.lastName) && otherCust.department.equals(this.department);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }
}
