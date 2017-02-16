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
     * Compares the area of the parameter Triangle to the current Triangle's area.
     *
     * @param tri The parameter Triangle to compare area with.
     * @return 0 if areas equal, -1 if the parameter area is higher, 1 if the current area is higher.
     */
    public int compareTo(Triangle tri) {
        if (this.getArea() == tri.getArea()) {
            return 0;
        } else if (this.getArea() > tri.getArea()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Checks if the parameter Triangle is the same as the calling Triangle. Equal if the 3 dimensions
     * are equal, in any order (e.g. a==c, b==b, c==a).
     *
     * @param tri The parameter Triangle to see if it is equal to the caller.
     * @return True if the Triangles are equal, false if not.
     */
    public boolean isEqual(Triangle tri) {
        if (tri.a == this.a && tri.b == this.b && tri.c == this.c) {
            return true;
        } else if (tri.a == this.a && tri.c == this.b && tri.b == this.c) {
            return true;
        } else if (tri.b == this.a && tri.a == this.b && tri.c == this.c) {
            return true;
        } else if (tri.b == this.a && tri.c == this.b && tri.a == this.c) {
            return true;
        } else if (tri.c == this.a && tri.a == this.b && tri.b == this.c) {
            return true;
        } else if (tri.c == this.a && tri.b == this.b && tri.a == this.c) {
            return true;
        }
        return false;
    }

    /**
     * Creates a String representation of the Triangle. In the format a,b,c.
     *
     * @return The String representing the Triangle.
     */
    public String toString() {
        return (a + "," + b + "," + c);
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
