package library;

/**
 * Created by Carson on 03/03/2017.
 * 14cdwc
 * Child of Item.
 * Parent of Textbook and Magazine.
 * Class representing a Book in the library.
 */
public class Book extends Item {

    private int year;
    private String authors, publisher;

    public Book(int year, String authors, String publisher, String name) {
        this.year = year;
        this.authors = authors;
        this.publisher = publisher;
        setName(name);
    }

    public Book(Book book) {
        if (book == null) {
            System.out.println("Tried to copy a null device");
            System.exit(1);
        }
        new Book(book.year, book.authors, book.publisher, book.getName());
    }

    @Override
    public double getLateFees(int daysLate) {
        return 0.5 * daysLate;
    }

    @Override
    public String toString() {
        return "ID: " + getID() + " book name: " + getName() + " year: " + year + " authors: " + authors + " publisher: ";
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Book(this);
    }

    public int getYear() {
        return year;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }
}
