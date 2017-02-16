package com.carson.exercise7;

/**
 * Created by Carson on 15/02/2017.
 * This class describes the behaviour of an iron sphere 0.5 mm in diameter free falling
 * in air. The equation accepts the velocity of the sphere.
 * The iron sphere is at its terminal velocity when the equation is zero.
 * The equation is defined between 0 and 30 metres/second.
 */
public class FallingSphereEquation implements Equation {

    public static final double GRAVITY = 9.80665; //m/sec^2
    public static final double SPHERE_DENSITY = 7860; //kg/m^3
    public static final double AIR_DENSITY = 1.23; //kg/m^3
    public static final double AIR_VISCOSITY = 1.79e-5; //Pa-sec
    public static final double SPHERE_DIAMETER = 0.0005; //mm


    // These range limits were determined by trial and error.  The drag coefficient formula
    // is not defined for velocities over 30 metres/second.
    @Override
    public double getRangeLow() {
        return 0;
    }

    @Override
    public double getRangeHigh() {
        return 30;
    }

    /**
     * Implements the equation to find the Reynolds number of a given velocity.
     *
     * @param velocity The velocity for which to find the Reynolds number.
     * @return Double that is the Reynolds number.
     */
    private double reynoldsNum(double velocity) {
        return velocity * SPHERE_DIAMETER * AIR_DENSITY / AIR_VISCOSITY;
    }

    /**
     * Finds the drag coefficient for a given velocity.
     *
     * @param velocity The velocity for which the drag coefficient is needed.
     * @return A double that is the drag coefficient, unless the drag coefficient is undefined,
     * in which case it returns 0.
     */
    private double dragCoeff(double velocity) {
        double reynolds = reynoldsNum(velocity);
        if (reynolds < 0.1)
            return 24 / reynolds;
        else if (reynolds >= 0.1 && reynolds <= 1000)
            return (24 / reynolds) * (1 + 0.14 * Math.pow(reynolds, 0.7));
        else
            return 0;    // The drag coefficient is not defined.
    }

    /**
     * An equation to be solved for the terminal velocity of an iron sphere 0.5 mm in diameter free
     * falling in air.
     *
     * @param velocity The velocity in metres per second.
     * @return A value that should be zero when the equation is supplied with the terminal velocity.
     */
    @Override
    public double evaluate(double velocity) {
        double dragCoefficient = dragCoeff(velocity);
        return velocity - Math.sqrt((4 * GRAVITY * (SPHERE_DENSITY - AIR_DENSITY) * SPHERE_DIAMETER) /
                (3 * dragCoefficient * AIR_DENSITY));
    }
}
