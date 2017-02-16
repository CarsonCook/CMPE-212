package com.carson.exercise7;

/**
 * Created by Carson on 15/02/2017.
 * An exception thrown by the getRoot() method of the BisectionRootSolver object.
 * The exception is thrown if the number of roots in the supplied root range is not one, or
 * if the root solver cannot converge to a root.
 */
public class RootNotFoundException extends Exception {

    /**
     * String message must be supplied this exception is thrown.
     *
     * @param message The message describing the error.
     */
    public RootNotFoundException(String message) {
        super(message);
    }
}
