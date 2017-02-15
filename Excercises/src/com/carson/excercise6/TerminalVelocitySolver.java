package com.carson.excercise6;

import com.carson.excercise1.IO_Helper.IOHelper;

/**
 * Created by Carson on 14/02/2017.
 * CMPE 212 Section 01 - Exercise 6 Terminal Velocity of a Sphere
 */
public class TerminalVelocitySolver {

    public static final double GRAVITY = 9.80665; //m/sec^2
    public static final double SPHERE_DENSITY = 7860; //kg/m^3
    public static final double AIR_DENSITY = 1.23; //kg/m^3
    public static final double AIR_VISCOSITY = 1.79e-5; //Pa-sec
    public static final double SPHERE_DIAMETER = 0.0005; //mm

    public static double getReynoldsNum(double velocity) {
        return velocity * SPHERE_DIAMETER * AIR_DENSITY / AIR_VISCOSITY;
    }

    public static double getDragCoeff(double velocity) {
        double reynoldsNum = getReynoldsNum(velocity);
        double dragCoeff = -1;
        if (reynoldsNum < 0.1) {
            dragCoeff = 24 / reynoldsNum;
        } else if (reynoldsNum <= 1000) {
            dragCoeff = (24 / reynoldsNum) * (1 + 0.14 * Math.pow(reynoldsNum, 0.7));
        } else {
            System.out.println("Bad reynoldsNum");
            System.exit(2);
            return 0;
        }
        return dragCoeff;
    }

    public static double findRoot(double velocity) {
        double dragCoefficient = getDragCoeff(velocity);
        return velocity - Math.sqrt((4 * GRAVITY * (SPHERE_DENSITY - AIR_DENSITY) * SPHERE_DIAMETER) /
                (3 * dragCoefficient * AIR_DENSITY));
    }

    public static double findRoot(double a, double b, double epsilon,
                                  int maxIterations) {
        // Reverse a and b if a is > b
        double temp;
        if (a > b) {
            temp = a;
            a = b;
            b = temp;
        }
        double fa = findRoot(a);
        double fb = findRoot(b);
        double mid;
        double fmid;
        int loopCount = 0;
        // Check to make sure a root exists in the supplied initial interval.
        // Note that we are assuming that only one root will exist in this range!
        if (fa * fb > 0) {
            System.out.println("No root in supplied interval!");
            System.exit(0);
        }
        // Carry out the bisection technique (see the lab statement for a description
        // of the algorithm).
        do {
            mid = (a + b) / 2.0;
            fmid = findRoot(mid);
            if (fa * fmid < 0) {
                b = mid;
            } else {
                a = mid;
            }
            loopCount = loopCount + 1;
        } while (loopCount < maxIterations && b - a > epsilon);
        // Check to make sure that the algorithm did converge to a root.
        if (loopCount == maxIterations) {
            System.out.println("Too many iterations, root not found!");
            System.exit(0);
        }
        // Display the number of iterations used.
        System.out.println("\n" + loopCount + " iterations used.");
        return mid;
    }

    public static void main(String[] args) {
        double low;
        double high;
        double epsilon;
        double root;
        int maxI;
        int numDigits;
        // Obtain needed parameters from the user
        // (The IOHelper class could have been used here instead...)
        low = IOHelper.getInt("Enter low range for root: ");
        high = IOHelper.getInt("Enter high range for root: ");
        epsilon = IOHelper.getDouble("Enter value for epsilon: ");

        // Use epsilon to approximate the number of digits that should
        // be displayed for the root
        numDigits = -(int) (Math.log(epsilon) / Math.log(10.));
        String numberFormat = "%8." + numDigits + "f";
        maxI = IOHelper.getInt("Enter value for maximum number of allowed iterations: ");

        // Obtain the root and display the result.
        root = findRoot(low, high, epsilon, maxI);
        System.out.printf("\nThe root is: " + numberFormat + " metres/second", root);
        System.out.println("\n(The function at the root is: " + findRoot(root) + ")");
    }
}
