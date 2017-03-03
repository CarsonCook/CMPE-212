package library;

/**
 * Created by Carson on 03/03/2017.
 * 14cdwc
 * Child of Book and Item.
 * Class representing a Magazine in the library.
 */
public class Magazine extends Book {

    public Magazine(int year, String authors, String publisher, String name) {
        super(year, authors, publisher, name);
    }

    public Magazine(Magazine magazine) {
        super(magazine);
    }

    @Override
    public String toString() {
        return "ID: " + getID() + " magazine name: " + getName() + " year: " + getYear() + " authors: " + getAuthors() +
                " publisher: " + getPublisher();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Magazine)) {
            return false;
        }
        Magazine otherMag = (Magazine) obj;
        return otherMag.getName().equals(this.getName()) && otherMag.getID() == this.getID() && otherMag.getYear() == this.getYear()
                && otherMag.getAuthors().equals(this.getAuthors()) && otherMag.getPublisher().equals(this.getPublisher());
    }

    @Override
    public Magazine clone() throws CloneNotSupportedException {
        return new Magazine(this);
    }

    @Override
    public double getLateFees(int daysLate) {
        return 0.75 * daysLate;
    }
}
