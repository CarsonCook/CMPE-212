package com.carson.exercise7;

/**
 * Created by Carson on 15/02/2017.
 * Interface to describe an equation.
 */
public interface Equation {
    double getRangeLow();
    double getRangeHigh();
    double evaluate(double x);
}
