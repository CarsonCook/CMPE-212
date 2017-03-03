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

    public Textbook(Textbook textbook) {
        super(textbook);
    }

    @Override
    public String toString() {
        return "ID: " + getID() + " textbook name: " + getName() + " year: " + getYear() + " authors: " + getAuthors() +
                " publisher: " + getPublisher();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Textbook)) {
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
