package com.carson.exercise8;

/**
 * Created by Carson on 15/02/2017.
 * Thrown by Triangle object when the input dimensions do not satisfy a proper triangle.
 */
public class IllegalTriangleException extends Exception {

    /**
     * Message must be supplied when this is thrown.
     * @param message The message describing the error.
     */
    IllegalTriangleException(String message) {
        super(message);
    }
}
