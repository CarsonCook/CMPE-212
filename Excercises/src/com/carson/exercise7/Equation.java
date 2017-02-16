package com.carson.exercise7;

/**
 * Created by Carson on 15/02/2017.
 * Interface to describe an equation.
 */
public interface Equation {

    /**
     * This method must return the low end of the range over which the equation is defined.
     * @return The low end of the range over which the equation is defined.
     */
    double getRangeLow();

    /**
     * This method must return the high end of the range over which the equation is defined.
     * @return The high end of the range over which the equation is defined.
     */
    double getRangeHigh();

    /**
     * This method returns y, where y = f(x) for the equation f.
     * @param x The input value for the equation.  This value must lie in-between the range limit
     * 	values.
     * @return The value of the function at the supplied value of x.
     */
    double evaluate(double x);
}
