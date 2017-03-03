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

    /**
     * Constructor that sets attribute values.
     *
     * @param year      int representing the year the Book was published.
     * @param authors   String representing the author that wrote the Book.
     * @param publisher String representing the publisher that published the Book.
     * @param name      String representing the name of the Book.
     */
    public Book(int year, String authors, String publisher, String name) {
        super(name);
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
        //need to check if obj is an instance of Adaptor, if not, caller and obj are not equal
        //also deals with null parameter
        if (!(obj instanceof Book)) {
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

    /**
     * Getter for year attribute.
     *
     * @return int representing the year the Book was published.
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter for the year attribute. Also filters bad values (less than 0) and sets them to a default value.
     *
     * @param year int representing the year the Book was published.
     */
    public void setYear(int year) {
        //check for bad year, if it is, set it to 0 as a flag/default value
        if (year < 0) {
            year = 0;
        }
        this.year = year;
    }

    /**
     * Getter for the authors attribute.
     *
     * @return String representing the authors of the Book.
     */
    public String getAuthors() {
        return authors;
    }

    /**
     * Setter for the authors attribute. Also filters bad values (empty) and sets them to a default value.
     *
     * @param authors String representing who wrote the Book.
     */
    public void setAuthors(String authors) {
        //check if authors is empty, if it is, set it to Carson Cook as a flag/default value
        if (authors.isEmpty()) {
            authors = "Carson Cook";
        }
        this.authors = authors;
    }

    /**
     * Getter for the publisher attribute.
     *
     * @return String representing the authors of the Book.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Setter for the publisher attribute. Also filters bad values (less than 0) and sets them to default value.
     *
     * @param publisher String representing who published the Book.
     */
    public void setPublisher(String publisher) {
        //don't check for empty publisher because a book can be made with no publisher
        this.publisher = publisher;
    }
}
