package com.carson;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Carson on 19/01/2017.
 */
public class Assign1_14cdwc {
    //class constants - unchanging messages/conditionals
    private static final int WIN_CONDITION = 100;
    private static final int COMP_HOLD_SCORE = 20;
    private static final int FLAG_NORMAL = 0;
    private static final int FLAG_CONTINUE = 1;
    private static final int FLAG_BREAK = -1;
    private static final String COMP_WON_MESSAGE = "*****COMPUTER WON!*****";
    private static final String HUMAN_WON_MESSAGE = "*****YOU WON!*****";
    private static final String DOUBLE_ONES_MESSAGE = "TURN OVER! Score sum is zero!\n";
    private static final String SINGLE_ONE_MESSAGE = "TURN OVER! Turn sum is zero!\n";
    private static final String DOUBLES_MESSAGE = "Doubles! MUST roll again\n";
    private static final String HOLD = "n";
    private static final String ROLL = "y";

    //constant classes
    private static final Scanner mScanner = new Scanner(System.in);
    private static final Random mGenerator = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        int humanScore = 0; //total human score
        int compScore = 0; //total computer score
        // Init these variables out of while loop to remove extra inits during loop
        //these can be used for computer AND human because they are only used during 1 turn at a time
        boolean humanTurn = true;//used to switch turns. Starts as true so human goes first.
        int roll1 = 0;
        int roll2 = 0;
        int turnScore = 0;
        int turn = 1;
        while (!isWin(humanScore) && !isWin(compScore)) {
            //change turn information to be for human
            humanTurn = true;
            turnScore = 0;
            printWhoseTurn(humanTurn);
            while (true) { //execute player turn until condition or input that says to break
                roll1 = getRoll();
                roll2 = getRoll();
                int[] turnInfo = turn(humanTurn, turnScore, roll1, roll2, humanScore);
                turnScore = turnInfo[0];
                if (turnInfo[1] == -1) {
                    break;
                } else if (turnInfo[1] == 1) {
                    continue;
                }
                //auto stop if human has enough points to win
                if (isWin(humanScore + turnScore)) {
                    endGame(humanScore + turnScore, compScore);
                }
                //get here then person has choice to hold or continue with turn
                if (!doesHumanContinueTurn()) {
                    break;
                }
            }

            //change turn information for computer's turn
            humanScore += turnScore; //turnScore and humanScore set to 0 if the roll
            // calls for it, so can still just add together
            humanTurn = false;
            turnScore = 0;
            printScoreSummary(humanScore, compScore);
            printWhoseTurn(humanTurn);

            while (true) { //execute computer turn until condition says to break
                roll1 = getRoll();
                roll2 = getRoll();
                int[] turnInfo = turn(humanTurn, turnScore, roll1, roll2, compScore);
                turnScore = turnInfo[0];
                if (turnInfo[1] == -1) {
                    break;
                } else if (turnInfo[1] == 1) {
                    continue;
                }
                //auto stop if computer has enough points to win
                if (isWin(compScore + turnScore)) {
                    endGame(humanScore, compScore);
                }
                //get here then computer has choice to hold or continue with turn
                if (computerEndsTurn(turnScore, compScore)) {
                    break;
                }
            }
            compScore += turnScore;
            turn++;
            printScoreSummary(humanScore, compScore);
            pauseGame(turn);
        }
        endGame(humanScore, compScore);//shouldn't reach due to end once player/computer won, but keep just in case
    }

    private static int[] turn(boolean humanTurn, int turnScore, int roll1, int roll2, int totalScore) {
        int[] retArr = {0, FLAG_NORMAL}; //will hold turnScore and information to continue or end turn
        if (specialRoll(roll1, roll2)) {
            retArr[0] = handleRoll(turnScore, totalScore, roll1, roll2, humanTurn);
            if (retArr[0] > 0) { //special roll and turnScore not 0/offsetting total score means
                //doubles were rolled, so force another roll
                print(DOUBLES_MESSAGE);
                retArr[1] = FLAG_CONTINUE;
            } else {
                retArr[1] = FLAG_BREAK;
            }
        } else {
            retArr[0] = handleRoll(turnScore, totalScore, roll1, roll2, humanTurn);
            printSummary(humanTurn, turnScore, totalScore);
        }
        return retArr;
    }

    private static boolean specialRoll(int roll1, int roll2) {
        return (roll1 == 1 && roll2 == 1) || (roll1 == 1 || roll2 == 1) || (roll1 == roll2);
    }

    private static int handleRoll(int turnScore, int totalScore, int roll1, int roll2, boolean humanTurn) {
        printRoll(humanTurn, roll1, roll2);
        if (roll1 == 1 && roll2 == 1) {
            print(DOUBLE_ONES_MESSAGE);
            return -totalScore; //use turnScore to set totalScore to 0
        } else if (roll1 == 1 || roll2 == 1) {
            print(SINGLE_ONE_MESSAGE);
            return 0; //turnScore is 0
        } else {
            return turnScore + roll1 + roll2;
        }
    }

    private static void printRoll(boolean humanTurn, int roll1, int roll2) {
        if (humanTurn) {
            print("Player rolled " + numToWord(roll1) + " + " + numToWord(roll2) + "\n");
        } else {
            print("Computer rolled " + numToWord(roll1) + " + " + numToWord(roll2) + "\n");
        }
    }

    private static void printSummary(boolean humanTurn, int turnScore, int totalScore) {
        if (humanTurn) {
            print("Player's turn sum is: " + turnScore + " and game sum is: " + totalScore + "\n");
        } else {
            print("Computer's turn sum is: " + turnScore + " and game sum is: " + totalScore + "\n");
        }
    }

    private static void printWhoseTurn(boolean humanTurn) {
        if (humanTurn) {
            print("\nPlayer's turn:\n");
        } else {
            print("\nComputer's turn:\n");
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

    private static void printScoreSummary(int humanScore, int compScore) {
        print("\nPlayer's sum is: " + humanScore + " Computer's sum is: " + compScore + "\n");
    }

    private static void pauseGame(int turn) {
        print("Press <enter> to start turn " + turn);
        String dump = mScanner.nextLine(); //waits until user inputs an enter
    }

    private static int getRoll() {
        return mGenerator.nextInt(6) + 1; //generates number 0-5, then add one for dice roll of 1-6
    }

    private static boolean doesHumanContinueTurn() {
        String dump;
        String input = "";
        boolean inputOK = false;
        while (!inputOK) {
            print("Roll again? (y/n)?: ");
            input = mScanner.next();
            dump = mScanner.nextLine(); //gets rid of enter key
            if (goodInput(input)) {
                inputOK = true;
            } else {
                print("That was not a valid answer!\n");
            }
        }
        return input.equals(ROLL);//true if roll, false if hold
    }

    private static boolean goodInput(String input) {
        return input.equals(ROLL) || input.equals(HOLD); //only options are to enter y or n
    }

    private static void endGame(int humanScore, int compScore) {
        //get here, then either human or computer won
        //thus, if isWin(humanScore) false, isWin(compScore) is true
        print("Player score: " + humanScore + " Computer score: " + compScore);
        String message = isWin(humanScore) ? HUMAN_WON_MESSAGE : COMP_WON_MESSAGE;
        print(message);
        mScanner.close(); //close scanner at end of program, NOT in middle, this creates an error if try to get input again
        System.exit(0); //if called for auto-win, need to end the program
    }

    private static boolean computerEndsTurn(int turnScore, int compScore) {
        return turnScore >= COMP_HOLD_SCORE || (compScore + turnScore) >= WIN_CONDITION; //problem defined computer logic on when to hold --> hold when
    }

    private static boolean isWin(int score) {
        return score >= WIN_CONDITION;
    }

    private static void print(String message) {
        System.out.print(message);
    }
}
