package com.carson.exercise1;

/**
 * Created by Carson on 16/01/2017.
 */
public class Part2_Printf_Output {

    public static void main(String[] args) {
        System.out.println("Pi: " + Math.PI + " E: " + Math.E);
        System.out.printf("Pi to 3 dec: %5.3f\n", Math.PI);
        System.out.printf("Pi: %5.3f E: %5.3f\n", Math.PI, Math.E);
    }
}
