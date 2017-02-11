package com.carson.excercise3;

import com.carson.excercise1.IO_Helper.IOHelper;

import java.util.Random;

/**
 * Created by Carson on 11/02/2017.
 * CMPE 212 Section 001 Exercise 3 Game of Nim
 */
public class NimGame {

    public static void main(String[] args) {

    }

    public static int getHumanChoice(int max) {
        return IOHelper.getInt("Enter your choice of marbles", max);
    }

    /**
     * Method to get random integer. Used for initial size of marble bag, things that are supposed to be random.
     *
     * @param max Max value to find for random value for.
     * @param min Min value to find random value for.
     * @return int that is random and between max and min.
     */
    public static int getRandInt(int max, int min) {
        if (max == 0) {
            return 0;
        } else if (max < 0) {
            max *= -1;
            Random random = new Random(System.currentTimeMillis());
            return -1 * random.nextInt(max) + min;
        } else {
            Random random = new Random(System.currentTimeMillis());
            return random.nextInt(max) + min;
        }
    }

    /**
     * Method to find who starts the game. 50/50 chance.
     *
     * @return True if human will start the game, false if computer starts.
     */
    public static boolean humanStarts() {
        return getRandInt(2, 0) == 2; //50/50 random number is 2
    }

    /**
     * Method to find if the computer is playing smartly. 50/50 chance.
     *
     * @return True if the computer will play smart, false if not.
     */
    public static boolean computerPlaysSmart() {
        return humanStarts(); //same logic
    }
}
