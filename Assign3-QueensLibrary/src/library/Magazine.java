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

    /**
     * Copy constructor.
     *
     * @param magazine Magazine object to be copied.
     */
    public Magazine(Magazine magazine) {
        super(magazine);
    }

    @Override
    public String toString() {
        return "Magazine " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        //need to check if obj is an instance of Adaptor, if not, caller and obj are not equal
        //also deals with null parameter
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
