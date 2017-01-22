package com.carson;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Carson on 19/01/2017.
 */
public class Assign1_14cdwc {

    //class constants - unchanging messages/conditionals
    private static final int WIN_CONDITION = 100;
    private static final int COMP_HOLD_SCORE = 40;
    private static final String COMP_WON_MESSAGE = "*****COMPUTER WON!*****";
    public static final String HUMAN_WON_MESSAGE = "*****YOU WON!*****";
    private static final String DOUBLE_ONES_MESSAGE = "TURN OVER! Score sum is zero!";
    private static final String SINGLE_ONE_MESSAGE = "TURN OVER! Turn sum is zero!";
    private static final String HOLD = "n";
    private static final String ROLL = "y";

    //constant classes
    private static final Scanner mScanner = new Scanner(System.in);
    private static final Random generator = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        int humanScore = 0; //total human score
        int compScore = 0; //total computer score
        // Init these variables out of while loop to remove extra inits during loop
        //these can be used for computer AND human because they are only used during 1 turn at a time
        boolean humanTurn = true;//used to switch turns. Starts as true so human goes first.
        int roll1 = 0;
        int roll2 = 0;
        while (!isWin(humanScore) && !isWin(compScore)) {
            roll1 = getRoll();
            roll2 = getRoll();
            if (humanTurn) {
                print("Player's turn:\nPlayer rolled " + String.valueOf(roll1) + " + " + String.valueOf(roll2));
                if (roll1 == 1 && roll2 == 1) { //total score is 0, turn over
                    print(DOUBLE_ONES_MESSAGE);
                    humanScore = 0;
                    humanTurn = false;
                } else {
                    if (roll1 == 1 || roll2 == 1) { //turnScore is 0, turn over
                        print(SINGLE_ONE_MESSAGE);
                        humanTurn = false;
                    } else if (roll1 == roll2) { //have to go again
                        humanScore += getTurnScore(roll1, roll2);
                    } else {
                        humanScore += getTurnScore(roll1, roll2);
                        humanTurn = humanHoldTurn(mScanner);
                    }
                }
            } else { //always either human's turn or computer's turn, so else can be used
                print("Computer's turn:\nComputer rolled " + String.valueOf(roll1) + " + " + String.valueOf(roll2));
                if (roll1 == 1 && roll2 == 1) { //total score is 0, turn over
                    print(DOUBLE_ONES_MESSAGE);
                    compScore = 0;
                    humanTurn = true;
                } else {
                    if (roll1 == 1 || roll2 == 1) { //turnScore is 0, turn over
                        print(SINGLE_ONE_MESSAGE);
                        humanTurn = true;
                    } else if (roll1 == roll2) { //have to go again
                        compScore += getTurnScore(roll1, roll2);
                    } else {
                        compScore += getTurnScore(roll1, roll2);
                        humanTurn = computerEndTurn(compScore);
                    }
                }
            }
            print("Player's sum is: " + String.valueOf(humanScore) + " Computer's sum is: " + String.valueOf(compScore));
        }
        endGame(humanScore);//game is over, decide who won, notify and close streams
    }

    private static int getTurnScore(int roll1, int roll2) {
        if (roll1 == roll2) { //shouldn't get here due to calling method in main(), but still dummy proof
            return 0;
        }
        if (roll1 == 1 || roll2 == 1) { //also shouldn't get here
            return 0; //one roll was 1, so turnScore is 0
        }
        return roll1 + roll2;
    }

    private static int getRoll() {
        return generator.nextInt(5) + 1; //generates number 0-5, then add one for dice roll of 1-6
    }

    private static boolean humanHoldTurn(Scanner scanner) {
        String dump;
        String input = "";
        boolean inputOK = false;
        while (!inputOK) {
            try {
                Assign1_14cdwc.print("Roll again? (y/s)?: ");
                input = scanner.next();
                dump = scanner.nextLine(); //gets rid of enter key
                if (goodInput(input)) {
                    inputOK = true;
                } else {
                    Assign1_14cdwc.print("That was not a valid answer!");
                }
            } catch (InputMismatchException e) { //input not of same type as .nextTYPE() call
                dump = scanner.nextLine();
                Assign1_14cdwc.print("\"" + dump + "\" is not an int.");
            }
        }
        return input.equals(HOLD);//true if hold, false if not
    }

    private static boolean goodInput(String input) {
        return input.equals(ROLL) || input.equals(HOLD); //only options are to enter y or n
    }

    private static void endGame(int humanScore) {
        //get here, then either humanWin() or compWin() returns true
        //thus, if humanWin() false, compWin() is true
        String message = isWin(humanScore) ? HUMAN_WON_MESSAGE : COMP_WON_MESSAGE;
        print(message);
        mScanner.close(); //close scanner at end of program, NOT in middle, this creates an error if try to get input again
    }

    public static boolean computerEndTurn(int score) {
        return score >= COMP_HOLD_SCORE; //problem defined computer logic on when to hold
    }

    private static boolean isWin(int score) {
        return score >= WIN_CONDITION;
    }

    private static void print(String message) {
        System.out.println(message);
    }
}
