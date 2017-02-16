package com.carson.exercise8;

/**
 * Created by Carson on 15/02/2017.
 * CMPE 212 Section 1 - Exercise 8.
 */
public class Triangle {

    private double a;
    private double b;
    private double c;

    Triangle(double a, double b, double c) throws IllegalTriangleException {
        if (a + b > c && b + c > a && a + c > b) {
            throw new IllegalTriangleException("Improper triangle dimensions were given");
        } else {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    /**
     * Accessor to get the dimensions of the Triangle.
     *
     * @return Array holding a, b and c respectively.
     */
    public double[] getDimensions() {
        double[] dimens = new double[3];
        dimens[0] = a;
        dimens[1] = b;
        dimens[2] = c;
        return dimens;
    }

    /**
     * Accessor to get the perimeter of the Triangle.
     *
     * @return The perimeter of the Triangle.
     */
    public double getPerimeter() {
        return a + b + c;
    }

    /**
     * Accessor to get the area of the Triangle.
     *
     * @return The area of the Triangle.
     */
    public double getArea() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}
