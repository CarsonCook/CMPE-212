package com.carson.exercise3;

import com.carson.exercise1.IO_Helper.IOHelper;

import java.util.Random;

/**
 * Created by Carson on 11/02/2017.
 * CMPE 212 Section 001 Exercise 3 Game of Nim
 */
public class NimGame {

    public static void main(String[] args) {
        int pileSize = getRandInt(100, 10);
        boolean humanTurn = humanStarts();
        boolean computerSmart = doesComputerPlaySmart();
        displayStartingConditions(humanTurn, pileSize, computerSmart);
        while (pileSize > 1) {
            int choice;
            //get choice for whoever's turn it is
            if (humanTurn) {
                choice = getHumanChoice(pileSize / 2);
            } else {
                choice = getComputerChoice(pileSize, computerSmart);
            }
            //make choice affect pile
            pileSize -= choice;
            //display info
            displayNumbersChosen(humanTurn, choice);
            displayMarblesLeft(pileSize);
            //check if that turn won the game
            if (pileSize <= 0) {
                break;
            }
            //change turns
            humanTurn = !humanTurn;
            //slow down execution so user can read game, and random seed is more different
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("ERROR: " + e);
            }
        }
        displayWinner(humanTurn);
    }

    /**
     * Gets the choice of the computer. Contains logic to check if the computer is to play smartly or randomly.
     *
     * @param pileSize  The number of marbles in play.
     * @param playSmart Whether or not the computer is playing smart.
     * @return int that is the number of marbles the computer has chosen.
     */
    private static int getComputerChoice(int pileSize, boolean playSmart) {
        if (playSmart) {
            //check if can make smart choice
            if (pileSize - 3 <= pileSize / 2 || pileSize - 7 <= pileSize / 2 || pileSize - 15 <= pileSize / 2
                    || pileSize - 31 <= pileSize / 2 || pileSize - 63 <= pileSize / 2) {
                int choice = getRandInt(pileSize / 2, 1);
                if (pileSize - 63 <= pileSize / 2 && pileSize - 63 > 0) {
                    choice = pileSize - 63;
                } else if (pileSize - 31 <= pileSize / 2 && pileSize - 63 > 0) {
                    choice = pileSize - 31;
                } else if (pileSize - 15 <= pileSize / 2 && pileSize - 63 > 0) {
                    choice = pileSize - 15;
                } else if (pileSize - 7 <= pileSize / 2 && pileSize - 63 > 0) {
                    choice = pileSize - 7;
                } else if (pileSize - 3 <= pileSize / 2 && pileSize - 3 > 0) {
                    choice = pileSize - 3;
                }
                return choice;
            } else { //if no smart choice available, choose randomly
                return getRandInt(pileSize / 2, 1);
            }
        } else {
            return getRandInt(pileSize / 2, 1);
        }
    }

    /**
     * Displays who won.
     *
     * @param humanTurn Who's turn it is - oppostie won.
     */
    private static void displayWinner(boolean humanTurn) {
        String output = (humanTurn ? "Computer won!" : "Human won!");
        System.out.println(output);
    }

    /**
     * Displays the starting conditions - who starts the game, the starting pile size and if the computer is playing smart.
     *
     * @param humanStarts   Who starts the game.
     * @param startingPile  The starting pile size.
     * @param computerSmart Describes if the computer is playing smart or randomly.
     */
    private static void displayStartingConditions(boolean humanStarts, int startingPile, boolean computerSmart) {
        String output = (humanStarts ? "Human" : "Computer") + " starts, with a starting marble pile of " + startingPile;
        System.out.println(output);
        System.out.println("Computer is playing " + (computerSmart ? "smartly" : "randomly"));
    }

    /**
     * Dsiplays how many marbles are left in the pile. Has logic to give proper singular/plural grammar
     *
     * @param marblesLeft Number of marbles left.
     */
    private static void displayMarblesLeft(int marblesLeft) {
        if (marblesLeft == 1) {
            System.out.println("There is " + marblesLeft + " marble left");
        } else {
            System.out.println("There are " + marblesLeft + " marbles left");
        }
    }

    /**
     * Displays who chose what number of values.
     *
     * @param humanTurn Describes who's turn it is.
     * @param numChosen Number of marbles chosen.
     */
    private static void displayNumbersChosen(boolean humanTurn, int numChosen) {
        String output = (humanTurn ? "Human" : "Computer") + " chose " + numChosen + " marbles";
        System.out.println(output);
    }

    /**
     * Gets input from the human for choice of how many marbles to take.
     *
     * @param max Maximum choice human can make.
     * @return int that is the choice of the human.
     */
    private static int getHumanChoice(int max) {
        return IOHelper.getInt("Enter your choice of marbles, with a max of " + max, max);
    }

    /**
     * Method to get random integer. Used for initial size of marble bag, things that are supposed to be random.
     *
     * @param max Max value to find for random value for.
     * @param min Min value to find random value for.
     * @return int that is random and between max and min.
     */
    private static int getRandInt(int max, int min) {
        if (max == 0) {
            return 0;
        } else if (max < 0) {
            //make max positive, then return the negative of that value
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
    private static boolean humanStarts() {
        return getRandInt(2, 0) == 2; //50/50 random number is 2
    }

    /**
     * Method to find if the computer is playing smartly. 50/50 chance.
     *
     * @return True if the computer will play smart, false if not.
     */
    private static boolean doesComputerPlaySmart() {
        return humanStarts(); //same logic
    }
}
