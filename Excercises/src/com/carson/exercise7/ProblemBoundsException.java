package com.carson.exercise7;

/**
 * Created by Carson on 15/02/2017.
 * An exception thrown by the BisectionRootSolver object when supplied parameters
 * do not describe a problem that can be solved.
 */
public class ProblemBoundsException extends Exception {

    /**
     * String message must be supplied this exception is thrown.
     *
     * @param message The message describing the error.
     */
    public ProblemBoundsException(String message) {
        super(message);
    }
}
