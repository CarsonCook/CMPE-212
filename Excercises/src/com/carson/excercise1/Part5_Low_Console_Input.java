package com.carson.excercise1;

import java.io.IOException;

/**
 * Created by Carson on 16/01/2017.
 */
public class Part5_Low_Console_Input {

    public static void main(String[] args){
        byte[] buffer = new byte[5]; //takes first 5 chars. No error if type in more than 5 chars
        int numRead = -1;
        System.out.print("Please enter something: ");
        try {
            numRead = System.in.read(buffer); //read() takes every character, including enter key
        } catch (IOException e) {
            System.out.println("Should not have got here!");
        }
        System.out.println("You typed in " + numRead + " characters.");
        System.out.println("As bytes:");
        for (int i = 0; i < numRead; i++)
            System.out.println(buffer[i]);
        System.out.println("As characters:");
        for (int i = 0; i < numRead; i++)
            System.out.println((char)buffer[i]);
    }
}
