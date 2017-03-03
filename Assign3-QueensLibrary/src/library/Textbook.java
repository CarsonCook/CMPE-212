package library;

/**
 * Created by Carson on 03/03/2017.
 * 14cdwc
 * Child of Book and Item.
 * Class representing a Textbook in the library.
 */
public class Textbook extends Book {

    /**
     * Constructor that sets attribute values.
     *
     * @param year      int representing the year the Book was published.
     * @param authors   String representing the author that wrote the Book.
     * @param publisher String representing the publisher that published the Book.
     * @param name      String representing the name of the Book.
     */
    public Textbook(int year, String authors, String publisher, String name) {
        super(year, authors, publisher, name);
    }

    /**
     * Copy constructor for a Book.
     *
     * @param textbook Textbook to be copied.
     */
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
        //need to check if obj is an instance of Adaptor, if not, caller and obj are not equal
        //also deals with null parameter
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
