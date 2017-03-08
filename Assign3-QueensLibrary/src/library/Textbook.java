package library;

/**
 * Created by Carson on 03/03/2017.
 * 14cdwc
 * Child of Book and Item.
 * Class representing a Textbook in the library.
 */
public class Textbook extends Book {

    public Textbook(int year, String authors, String publisher, String name) {
        super(year, authors, publisher, name);
    }

    /**
     * Copy constructor for a Textbook.
     *
     * @param textbook Textbook to be copied.
     */
    public Textbook(Textbook textbook) {
        super(textbook);
    }

    @Override
    public String toString() {
        return "Textbook " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        //need to check if obj is of type Textbook, if not, caller and obj are not equal
        if (obj == null || !(obj.getClass() == Textbook.class)) {
            return false;
        }
        Textbook otherText = (Textbook) obj;
        return otherText.getName().equals(this.getName()) && otherText.getID() == this.getID() &&
                otherText.getYear() == this.getYear() && otherText.getAuthors().equals(this.getAuthors()) &&
                otherText.getPublisher().equals(this.getPublisher());
    }

    @Override
    public Textbook clone() throws CloneNotSupportedException {
        return new Textbook(this);
    }

    @Override
    public double getLateFees(int daysLate) {
        return daysLate;
    }
}
