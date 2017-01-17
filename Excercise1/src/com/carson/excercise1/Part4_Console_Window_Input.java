package com.carson.excercise1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Carson on 16/01/2017.
 */
public class Part4_Console_Window_Input {

    public static void main(String[] args) {
        /*Scanner input = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int userNum = input.nextInt()+20;
        System.out.println("Your number +20 = "+userNum);
        input.close();*/
        goodInputPractice();
    }
    //.nextLine() is only one to read the enter key -> use to get rid of enters from the buffer

    private static void goodInputPractice() {
        String dump;
        int input = 0;
        Scanner scanner = new Scanner(System.in);
        boolean inputOK = false;
        while (!inputOK) {
            try {
                System.out.println("Enter an integer: ");
                input = scanner.nextInt();
                dump = scanner.nextLine(); //gets rid of enter key
                inputOK = true;
            } catch (InputMismatchException e) { //input not of same type as .nextTYPE() call
                dump = scanner.nextLine();
                System.out.println("\"" + dump + "\" is not an int.");
            }
        }
        System.out.println("Your int: " + input);
        scanner.close();
    }
}
