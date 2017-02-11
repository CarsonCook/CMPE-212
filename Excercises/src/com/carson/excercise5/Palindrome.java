package com.carson.excercise5;

/**
 * Created by Carson on 11/02/2017.
 * CMPE 212 Section 001 Exercise 5
 * String methods culminating in finding if a String is a palindrome.
 */
public class Palindrome {

    public static void main(String[] args) {
        System.out.println("string: " + Palindrome.test("string"));
        String s = "1hih2";
        System.out.println(s + ": " + Palindrome.test("1hih2") + " org: " + s);
        String s2 = "hannah";
        System.out.println(s2 + ": " + Palindrome.test("hannah") + " org: " + s2);
    }

    /**
     * Method that removes non-letter characters from the String argument and makes the String
     * all lower case. Returns the resultant String.
     *
     * @param s String to remove non-letters and make lower case.
     * @return Argument String but with no non-letters and all lower case.
     */
    public static String massage(String s) {
        for (int i = 0; i < s.length(); i++) {
            //check for character that isn't a letter
            if (!((s.charAt(i) >= 65 && s.charAt(i) <= 90) || (s.charAt(i) >= 97 && s.charAt(i) <= 122))) {
                s = s.replace(Character.toString(s.charAt(i)), "");
            }
        }
        return s.toLowerCase();
    }

    /**
     * Method that finds if the first and last letters in the String argument are the same.
     *
     * @param s String to see if first and last letters in the String are the same.
     * @return True if the first and last letters are the same, else false.
     */
    public static boolean match(String s) {
        String copy = s;
        copy = Palindrome.massage(copy);
        return copy.charAt(0) == copy.charAt(copy.length() - 1);
    }

    /**
     * Method that removes the first and last letters of the String argument.
     *
     * @param s String to see remove first and last characters from.
     * @return String that doesn't have first and last letters.
     */
    public static String strip(String s) {
        String copy = s;
        int firstLetterPos = copy.length() + 1;
        int lastLetterPos = -1;
        for (int i = 0; i < copy.length(); i++) {
            //check for character that isn't a letter
            if (((copy.charAt(i) >= 65 && copy.charAt(i) <= 90) || (copy.charAt(i) >= 97 && copy.charAt(i) <= 122))) {
                if (i < firstLetterPos) {
                    firstLetterPos = i;
                }
                if (i > lastLetterPos) {
                    lastLetterPos = i;
                }
            }
        }
        copy = copy.replace(Character.toString(copy.charAt(firstLetterPos)), "");
        //lastLetterPos-1 because length goes down by one due to last replace with ""
        copy = copy.replace(Character.toString(copy.charAt(lastLetterPos - 1)), "");
        return copy;
    }

    /**
     * Method that finds if the String argument is a palindrome.
     *
     * @param s String to see if it is a palindrome.
     * @return True if it is a palindrome, else false.
     */
    public static boolean test(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
