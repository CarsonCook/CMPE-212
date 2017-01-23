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
    private static final String DOUBLES_MESSAGE = "Doubles! MUST roll again";
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
        int turnScore = 0;
        while (!isWin(humanScore) && !isWin(compScore)) {
            while (true) { //execute player turn until condition or input that says to break
                roll1 = getRoll();
                roll2 = getRoll();
                if (roll1 == 1 && roll2 == 1) {
                    humanScore = 0;
                    turnScore = 0;
                    printRoll(humanTurn, roll1, roll2, 0, humanScore, compScore);
                    print(DOUBLE_ONES_MESSAGE);
                    break;
                } else if (roll1 == 1 || roll2 == 1) {
                    printRoll(humanTurn, roll1, roll2, 0, humanScore, compScore);
                    print(SINGLE_ONE_MESSAGE);
                    turnScore = 0;
                    break;
                } else if (roll1 == roll2) {
                    turnScore = turnScore + roll1 + roll2;
                    printRoll(humanTurn, roll1, roll2, turnScore, humanScore, compScore);
                    printSummary(humanTurn, turnScore, humanScore);
                    print(DOUBLES_MESSAGE);
                    continue;
                } else {
                    turnScore = turnScore + roll1 + roll2;
                    printRoll(humanTurn, roll1, roll2, turnScore, humanScore, compScore);
                    printSummary(humanTurn, turnScore, humanScore);
                }
                //get here then person has choice to hold or continue with turn
                if (!doesHumanContinueTurn()) {
                    break;
                }
            }
            //change turn information for computer's turn
            humanScore += turnScore;
            humanTurn = !humanTurn;
            turnScore = 0;

            pauseGame(humanScore, compScore);
            while (true) { //execute computer turn until condition says to break
                roll1 = getRoll();
                roll2 = getRoll();
                if (roll1 == 1 && roll2 == 1) {
                    compScore = 0;
                    turnScore = 0;
                    printRoll(humanTurn, roll1, roll2, 0, humanScore, compScore);
                    print(DOUBLE_ONES_MESSAGE);
                    break;
                } else if (roll1 == 1 || roll2 == 1) {
                    turnScore = 0;
                    printRoll(humanTurn, roll1, roll2, 0, humanScore, compScore);
                    print(SINGLE_ONE_MESSAGE);
                    break;
                } else if (roll1 == roll2) {
                    turnScore = turnScore + roll1 + roll2;
                    printRoll(humanTurn, roll1, roll2, turnScore, humanScore, compScore);
                    printSummary(humanTurn, turnScore, humanScore);
                    print(DOUBLES_MESSAGE);
                    continue;
                } else {
                    turnScore = turnScore + roll1 + roll2;
                    printRoll(humanTurn, roll1, roll2, turnScore, humanScore, compScore);
                    printSummary(humanTurn, turnScore, humanScore);
                }
                //get here then computer has choice to hold or continue with turn
                if (computerEndsTurn(turnScore, compScore)) {
                    break;
                }
            }
            compScore += turnScore;
            humanTurn = !humanTurn;
            turnScore = 0;
            pauseGame(humanScore, compScore);
            /*roll1 = getRoll();
            roll2 = getRoll();
            forceTurnOver = false;//assume there is a choice to start each turn
            forceRoll = false; //assume choice to start turn
            if (roll1 == 1 && roll2 == 1) { //turn score and total score 0 for whoever's turn it is
                humanScore = (humanTurn ? 0 : humanScore);
                compScore = (humanTurn ? compScore : 0);
                turnScore = 0;
                forceTurnOver = true;
                printTurnInfo(humanTurn, roll1, roll2, turnScore, humanScore,
                        compScore, forceTurnOver, forceRoll, DOUBLE_ONES_MESSAGE);
            } else if (roll1 == 1 || roll2 == 1) { //turn score set to 0
                turnScore = 0;
                forceTurnOver = true;
                printTurnInfo(humanTurn, roll1, roll2, turnScore, humanScore,
                        compScore, forceTurnOver, forceRoll, SINGLE_ONE_MESSAGE);
            } else if (roll1 == roll2) { //force another roll
                humanScore = (humanTurn ? humanScore + turnScore : humanScore);
                compScore = (humanTurn ? compScore : compScore + turnScore);
                forceRoll = true;
                printTurnInfo(humanTurn, roll1, roll2, turnScore, humanScore,
                        compScore, forceTurnOver, forceRoll, DOUBLES_MESSAGE);
            } else { //up to player/computer if they continue
                turnScore = roll1 + roll2;
                humanScore = (humanTurn ? humanScore + turnScore : humanScore);
                compScore = (humanTurn ? compScore : compScore + turnScore);
                printTurnInfo(humanTurn, roll1, roll2, turnScore, humanScore,
                        compScore, forceTurnOver, forceRoll, DOUBLES_MESSAGE);
            }
            if (switchTurn(forceRoll, forceTurnOver, humanTurn, compScore)) {
                humanTurn = !humanTurn;
            }*/
        }
        endGame(humanScore);//game is over, decide who won, notify and close streams
    }

    private static boolean choiceToContinue(boolean forceRoll, boolean forceTurnOver) {
        return !forceRoll && !forceTurnOver;
    }

    private static void printRoll(boolean humanTurn, int roll1, int roll2, int turnScore, int humanScore, int compScore) {
        if (humanTurn) {
            print("Player rolled " + numToWord(roll1) + " + " + numToWord(roll2));
        } else {
            print("Computer rolled " + numToWord(roll1) + " + " + numToWord(roll2));
        }
    }

    private static void printSummary(boolean humanTurn, int turnScore, int totalScore) {
        if (humanTurn) {
            print("Player's turn sum is: " + turnScore + " and game sum is: " + totalScore);
        } else {
            print("Computer's turn sum is: " + turnScore + " and game sum is: " + totalScore);
        }
    }

    private static void printWhoseTurn(boolean humanTurn) {
        if (humanTurn) {
            print("Player's turn");
        } else {
            print("Computer's turn");
        }
    }

    private static String numToWord(int num) {
        //num parameter only from 1 to 6, so we can use a switch statement
        switch (num) {
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            default:
                return "ERROR";
        }
    }

    private static void pauseGame(int humanScore, int compScore) {
        print("\nPlayer's sum is: " + String.valueOf(humanScore) + " Computer's sum is: " + String.valueOf(compScore));
        //pause program so user can absorb info
        print("Press <enter> to continue");
        String dump = mScanner.nextLine();
    }

    private static boolean switchTurn(boolean forceRoll, boolean forceTurnOver, boolean humanTurn, int compScore) {
        if (humanTurn) {
            return forceTurnOver || (choiceToContinue(forceRoll, forceTurnOver) && doesHumanContinueTurn());
        } else {
            return forceTurnOver || computerEndsTurn(compScore);
        }
    }

    private static int getRoll() {
        return generator.nextInt(5) + 1; //generates number 0-5, then add one for dice roll of 1-6
    }

    private static boolean doesHumanContinueTurn() {
        String dump;
        String input = "";
        boolean inputOK = false;
        while (!inputOK) {
            try {
                Assign1_14cdwc.print("Roll again? (y/n)?: ");
                input = mScanner.next();
                dump = mScanner.nextLine(); //gets rid of enter key
                if (goodInput(input)) {
                    inputOK = true;
                } else {
                    Assign1_14cdwc.print("That was not a valid answer!");
                }
            } catch (InputMismatchException e) { //input not of same type as .nextTYPE() call
                dump = mScanner.nextLine();
                Assign1_14cdwc.print("\"" + dump + "\" is not an int.");
            }
        }
        return input.equals(ROLL);//true if roll, false if hold
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

    public static boolean computerEndsTurn(int turnScore, int compScore) {
        return turnScore >= COMP_HOLD_SCORE || compScore >= WIN_CONDITION; //problem defined computer logic on when to hold --> hold when
    }

    private static boolean isWin(int score) {
        return score >= WIN_CONDITION;
    }

    private static void print(String message) {
        System.out.println(message);
    }
}
