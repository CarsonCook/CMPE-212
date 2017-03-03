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
        super(name);
        //utilize setters checking for bad values
        setYear(year);
        setAuthors(authors);
        setPublisher(publisher);
    }

    public Book(Book book) {
        super(book.getName());
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
    public Book clone() throws CloneNotSupportedException {
        return new Book(this);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        //check for bad year, if it is, set it to 0 as a flag/default value
        if (year < 0) {
            year = 0;
        }
        this.year = year;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        //check if authors is empty, if it is, set it to Carson Cook as a flag/default value
        if (authors.isEmpty()) {
            authors = "Carson Cook";
        }
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        //don't check for empty publisher because a book can be made with no publisher
        this.publisher = publisher;
    }
}
