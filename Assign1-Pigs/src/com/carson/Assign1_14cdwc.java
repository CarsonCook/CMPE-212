package com.carson;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Carson on 19/01/2017.
 * CMPE 212 Assignment 1, Friday, January 27, 2017.
 * Carson Cook - 10186142 - 14cdwc
 */
public class Assign1_14cdwc {
    //constants - unchanging messages/conditionals
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

    private enum Turn {HUMAN, COMPUTER} //used for clear definition of whose turn it is

    //constant classes used in multiple methods
    private static final Scanner mScanner = new Scanner(System.in);
    private static final Random mGenerator = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        int humanScore = 0; //total human score
        int compScore = 0; //total computer score
        // Init these variables out of while loop to remove extra initialization steps in loop
        //these can be used for computer AND human because they are only used during 1 turn at a time
        Turn turn;
        int roll1;
        int roll2;
        int turnScore;
        int turnNum = 1;
        //loop for entire game, goes until the game ends with a win condition
        while (!isWin(humanScore) && !isWin(compScore)) {
            //change turn information to be for human
            turn = Turn.HUMAN;
            turnScore = 0;
            printWhoseTurn(turn);

            //loop for human's turn, goes until they choose to hold or a one is rolled
            while (true) { //execute player turn until condition or input that says to break
                roll1 = getRoll();
                roll2 = getRoll();
                int[] turnInfo = handleRoll(turn, turnScore, roll1, roll2, humanScore);
                turnScore = turnInfo[0];
                if (turnInfo[1] == FLAG_BREAK) {
                    break;
                } else if (turnInfo[1] == FLAG_CONTINUE) {
                    continue;
                }
                if (isWin(humanScore + turnScore)) { //auto stop if human has enough points to win
                    endGame(humanScore + turnScore, compScore);
                }
                if (!doesHumanContinueTurn()) { //get here then person has choice to hold or continue with turn
                    break;
                }
            }

            //change turn information for computer's turn
            humanScore += turnScore; //turnScore and humanScore set to 0 if the roll
            // calls for it, so can still just add together
            turn = Turn.COMPUTER;
            turnScore = 0;

            printScoreSummary(humanScore, compScore);
            printWhoseTurn(turn);

            //loop for computer's turn, goes until it chooses to hold or a one is rolled
            while (true) { //execute computer turn until condition says to break
                roll1 = getRoll();
                roll2 = getRoll();
                int[] turnInfo = handleRoll(turn, turnScore, roll1, roll2, compScore);
                turnScore = turnInfo[0];
                if (turnInfo[1] == FLAG_BREAK) {
                    break;
                } else if (turnInfo[1] == FLAG_CONTINUE) {
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
            turnNum++;
            printScoreSummary(humanScore, compScore);
            pauseGame(turnNum);
        }
        endGame(humanScore, compScore);//shouldn't reach due to end once player/computer won, but keep just in case
    }

    /**
     * Computes logic for each roll for player/computer's turn.
     * Uses rollLogic() to compute turnScore and while loop flag.
     * @param turn Enum describing whether it is the player's or computer's turn.
     * @param turnScore Score of the turn thus far.
     * @param roll1 One of the dice roll.
     * @param roll2 Other dice roll.
     * @param totalScore Game score thus far.
     * @return Array with the new turn score and a flag for the turn while loop.
     *         Flag says whether to force end, force roll or give a choice.
     */
    private static int[] handleRoll(Turn turn, int turnScore, int roll1, int roll2, int totalScore) {
        int[] retArr = {0, FLAG_NORMAL}; //will hold turnScore and information to continue or end turn
        retArr[0] = rollLogic(turnScore, totalScore, roll1, roll2, turn);
        if (specialRoll(roll1, roll2)) {
            if (retArr[0] > 0) { //special roll and turnScore not 0/offsetting total score means
                //doubles were rolled, so force another roll
                print(DOUBLES_MESSAGE);
                retArr[1] = FLAG_CONTINUE;
            } else {
                retArr[1] = FLAG_BREAK;
            }
        } else {
            printSummary(turn, retArr[0], totalScore);
        }
        return retArr;
    }

    /**
     * Logic deciding if a roll that requires different handling than just adding them and
     * giving the choice to hold or not.
     * @param roll1 One dice roll.
     * @param roll2 The other dice roll.
     * @return True if special handling is need, false if normal.
     */
    private static boolean specialRoll(int roll1, int roll2) {
        return (roll1 == 1 && roll2 == 1) || (roll1 == 1 || roll2 == 1) || (roll1 == roll2);
    }

    /**
     * Computes a turn score for the given roll. Returns -1*gameScore if
     * need to reset game score, as after this method returns, the value is
     * added to gameScore.
     * @param turnScore Turn score thus far.
     * @param totalScore Total game score thus far.
     * @param roll1 One of the dice roll.
     * @param roll2 The other dice roll.
     * @param turn Describes whether it is the player's or user's turn.
     * @return int that holds the new turnScore.
     */
    private static int rollLogic(int turnScore, int totalScore, int roll1, int roll2, Turn turn) {
        printRoll(turn, roll1, roll2);
        if (roll1 == 1 && roll2 == 1) {
            print(DOUBLE_ONES_MESSAGE);
            return -totalScore; //use turnScore to set totalScore to 0 after this returns
        } else if (roll1 == 1 || roll2 == 1) {
            print(SINGLE_ONE_MESSAGE);
            return 0; //turnScore is 0
        } else {
            return turnScore + roll1 + roll2;
        }
    }

    /**
     * Prints roll information to the console.
     * @param turn Describes whether it is the player's or user's turn.
     * @param roll1 One of the dice roll.
     * @param roll2 The other dice roll.
     */
    private static void printRoll(Turn turn, int roll1, int roll2) {
        if (turn == Turn.HUMAN) {
            print("Player rolled " + numToWord(roll1) + " + " + numToWord(roll2) + "\n");
        } else {
            print("Computer rolled " + numToWord(roll1) + " + " + numToWord(roll2) + "\n");
        }
    }

    /**
     * Prints the turn and game sum for current turn to console, after a roll.
     * @param turn Describes whether it is the player's or user's turn.
     * @param turnScore Turn score thus far.
     * @param totalScore Game score thus far.
     */
    private static void printSummary(Turn turn, int turnScore, int totalScore) {
        if (turn == Turn.HUMAN) {
            print("Player's turn sum is: " + turnScore + " and game sum is: " + totalScore + "\n");
        } else {
            print("Computer's turn sum is: " + turnScore + " and game sum is: " + totalScore + "\n");
        }
    }

    /**
     * Prints whose turn it is to console, before the turn.
     * @param turn Describes whether it is the player's or user's turn.
     */
    private static void printWhoseTurn(Turn turn) {
        if (turn == Turn.HUMAN) {
            print("\nPlayer's turn:\n");
        } else {
            print("\nComputer's turn:\n");
        }
    }

    /**
     * Converts a dice roll number to a string.
     * @param num The roll as an int.
     * @return String that holds the word corresponding to the int parameter.
     */
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

    /**
     * Prints the game score for player and user to console.
     * @param humanScore Human's game score thus far.
     * @param compScore Computer's game score thus far.
     */
    private static void printScoreSummary(int humanScore, int compScore) {
        print("\nPlayer's sum is: " + humanScore + " Computer's sum is: " + compScore + "\n");
    }

    /**
     * Stops the game until the user hits enter. Also prints the turn number
     * that is about to be played to console.
     * @param turnNum The turn number about to be played.
     */
    private static void pauseGame(int turnNum) {
        print("Press <enter> to start turn " + turnNum);
        mScanner.nextLine(); //waits until user inputs an enter
    }

    /**
     * Computes a dice roll.
     * @return int that is random from 1 to 6, simulating a dice roll.
     */
    private static int getRoll() {
        return mGenerator.nextInt(6) + 1; //generates number 0-5, then add one for dice roll of 1-6
    }

    /**
     * Gets input from the human player to see if they want to end their turn or continue.
     * User enters a 'y' to continue, 'n' if not.
     * @return boolean - true if the human wants to continue, else false.
     */
    private static boolean doesHumanContinueTurn() {
        String input = "";
        boolean inputOK = false;
        while (!inputOK) {
            print("Roll again? (" + ROLL + "/" + HOLD + ")?: ");
            input = mScanner.next();
            mScanner.nextLine(); //gets rid of enter key
            if (goodInput(input)) {
                inputOK = true;
            } else {
                print("That was not a valid answer!\n");
            }
        }
        return input.equals(ROLL);//true if roll, false if hold
    }

    /**
     * Checks if the user entered a proper input for whether they want to roll again or not.
     * 'y' and 'n' are the only valid inputs.
     * @param input The input of the user.
     * @return True if good input ('y' or 'n'), false if not.
     */
    private static boolean goodInput(String input) {
        return input.equals(ROLL) || input.equals(HOLD); //only options are to enter y or n
    }

    /**
     * Stops the game and program. Called when there has been a winner.
     * @param humanScore The score of the human player.
     * @param compScore The score of the computer.
     */
    private static void endGame(int humanScore, int compScore) {
        //get here, then either human or computer won
        //thus, if isWin(humanScore) false, isWin(compScore) is true
        print("Player score: " + humanScore + " Computer score: " + compScore);
        String message = isWin(humanScore) ? HUMAN_WON_MESSAGE : COMP_WON_MESSAGE;
        print(message);
        mScanner.close(); //close scanner at end of program, NOT in middle, this creates an error if try to get input again
        System.exit(0); //if called for auto-win, need to end the program
    }

    /**
     * Computes logic for if the computer wants to roll again.
     * @param turnScore The turn score thus far.
     * @param compScore The total score thus far.
     * @return True if the computer has won or has a turn score of at least 20. Otherwise false.
     */
    private static boolean computerEndsTurn(int turnScore, int compScore) {
        return turnScore >= COMP_HOLD_SCORE || (compScore + turnScore) >= WIN_CONDITION;
    }

    /**
     * Check if a score is high enough to win.
     * @param score The score being checked.
     * @return True if the score is high enough to win, else false.
     */
    private static boolean isWin(int score) {
        return score >= WIN_CONDITION;
    }

    /**
     * Prints information out to the console.
     * Only used to avoid typing 'System.out.' each time.
     * @param message The string to be printed to the console
     */
    private static void print(String message) {
        System.out.print(message);
    }
}
