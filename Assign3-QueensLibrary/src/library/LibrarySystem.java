package library;

/**
 * Created by Carson on 03/03/2017.
 * 14cdwc
 * Class to utilize library schema created.
 */
//TODO copy constructor doesn't work
    //Ask user to correct null values

public class LibrarySystem {

    public static void main(String[] args) {
        Laptop laptop = new Laptop(10, "laptop");
        Laptop laptop2 = new Laptop(10, "laptop");
        Adaptor adaptor = new Adaptor(15, "adaptor");
        Adaptor adaptor2 = new Adaptor(15, "adaptor");
        Magazine magazine = new Magazine(2016, "pengu","carson","magazine");
        Magazine magazine2 = new Magazine(2016, "pengu","carson","magazine");
        Textbook textbook = new Textbook(2016, "manji","carson","math");
        Textbook textbook2 = new Textbook(2016, "manji","carson","math");
        System.out.println(laptop.equals(laptop2));
        System.out.println(adaptor.equals(adaptor2));
        System.out.println(magazine.equals(magazine2));
        System.out.println(textbook.equals(textbook2));
    }
}
