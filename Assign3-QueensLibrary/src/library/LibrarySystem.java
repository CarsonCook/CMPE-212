package library;

/**
 * Created by Carson on 03/03/2017.
 * 14cdwc
 * Class to utilize library schema created.
 */
public class LibrarySystem {

    public static void main(String[] args){
        Adaptor one = new Adaptor(10,"test1");
        Adaptor two=new Adaptor(5,"test2");
        System.out.println(one);
        System.out.println(two);
    }
}
