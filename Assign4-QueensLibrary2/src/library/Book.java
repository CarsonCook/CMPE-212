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
        setYear(year);
        setAuthors(authors);
        setPublisher(publisher);
    }

    public Book(int year, String authors, String publisher, String name, int id) {
        super(name, id);
        //utilize setters checking for bad values
        setYear(year);
        setAuthors(authors);
        setPublisher(publisher);
    }

    /**
     * Copy constructor for a Book.
     *
     * @param book Book to be copied.
     */
    public Book(Book book) {
        super(book.getName(), book.getID()); //can throw a null exception but example code did this essentially
        //don't call super or this in order to check for a null device being copied
        if (book == null) {
            //no values available, so use default ones
            this.setAuthors("");
            this.setPublisher("");
            this.setYear(0);
        } else {
            this.setAuthors(book.authors);
            this.setPublisher(book.publisher);
            this.setYear(book.year);
        }
    }

    @Override
    public double getLateFees(int daysLate) {
        return 0.5 * daysLate;
    }

    @Override
    public String toString() {
        return "Book " + super.toString() + ", year: " + year + ", authors: " + authors + ", publisher: " + publisher;
    }

    @Override
    public boolean equals(Object obj) {
        //need to check if obj is of type Book, if not, caller and obj are not equal
        if (obj == null || !(obj.getClass() == Book.class)) {
            return false;
        }
        Book otherBook = (Book) obj;
        return otherBook.getName().equals(this.getName()) &&
                otherBook.getID() == this.getID() && otherBook.year == this.year &&
                otherBook.authors.equals(this.authors) && otherBook.publisher.equals(this.publisher);
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
        if (authors == null || authors.isEmpty()) {
            authors = "Carson Cook";
        }
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        //check for bad publisher value
        if (publisher == null || publisher.isEmpty()) {
            publisher = "none";
        }
        this.publisher = publisher;
    }
}
