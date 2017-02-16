package com.carson.exercise7;

/**
 * This class helps locates a root in the supplied equation.  A root is where the equation equals zero to the
 * supplied tolerance, epsilon.
 *
 * @author Alan McLeod
 * @version 1.0
 */
public class BisectionRootSolver {

    private Equation eqn;
    private double rootRangeLow;
    private double rootRangeHigh;
    private double epsilon;
    private int maxIterations;

    /**
     * The constructor for the BisectionRootSolver object.
     *
     * @param eqnToSolve    An instance of the Equation object to solve.
     * @param rootLow       The low end of the range to investigate for a root.
     * @param rootHigh      The high end of the range to investigate for a root.
     * @param epsilon       The located root must be accurate to this value.
     * @param maxIterations The maximum number of iterations is supplied to prevent an infinite loop in the case
     *                      when the solver cannot converge to a root.
     * @throws ProblemBoundsException If the supplied parameters do not provide a solvable problem. The root range values could
     *                                lie outside the defined range for the equation, for example.  Epsilon might not be valid and the maximum number of
     *                                iterations must lie between 10 and 1 million inclusive.
     */
    public BisectionRootSolver(Equation eqnToSolve, double rootLow, double rootHigh, double epsilon, int maxIterations)
            throws ProblemBoundsException {
        if (eqnToSolve == null)
            throw new ProblemBoundsException("The Equation object is not defined.");
        eqn = eqnToSolve;
        if (rootLow >= rootHigh)
            throw new ProblemBoundsException("The low root must be less than the high root.");
        setRootRangeLow(rootLow);
        setRootRangeHigh(rootHigh);
        setEpsilon(epsilon);
        setMaxIterations(maxIterations);
    }

    private void setRootRangeLow(double rootLow) throws ProblemBoundsException {
        double rangeLimitLow = eqn.getRangeLow();
        double rangeLimitHigh = eqn.getRangeHigh();
        if (rootLow <= rangeLimitLow || rootLow >= rangeLimitHigh)
            throw new ProblemBoundsException("Low root range: " + rootLow + " is outside the defined range for the equation.");
        rootRangeLow = rootLow;
    } // end setRootRangeLow

    private void setRootRangeHigh(double rootHigh) throws ProblemBoundsException {
        double rangeLimitLow = eqn.getRangeLow();
        double rangeLimitHigh = eqn.getRangeHigh();
        if (rootHigh <= rangeLimitLow || rootHigh >= rangeLimitHigh)
            throw new ProblemBoundsException("High root range: " + rootHigh + " is outside the defined range for the equation.");
        rootRangeHigh = rootHigh;
    }

    /**
     * Sets or changes the value that determines the degree of accuracy of the located root.
     *
     * @param epsilon The root accuracy desired.
     * @throws ProblemBoundsException Thrown if epsilon is higher than about 1 part in 100.
     */
    public void setEpsilon(double epsilon) throws ProblemBoundsException {
        int minimumNumIntervalsInRange = 100;
        if ((rootRangeHigh - rootRangeLow) / epsilon < minimumNumIntervalsInRange)
            throw new ProblemBoundsException("Epsilon is too large for this initial root range.");
        this.epsilon = epsilon;
    }

    /**
     * Sets or changes the maximum number of iterations permitted for the solver.  This is considered
     * a "safety valve" in the case of non-convergence.
     *
     * @param maxIterations The maximum number of iterations permitted.
     * @throws ProblemBoundsException If the number of iterations provided is less than 10 or
     *                                greater than 1 million.
     */
    public void setMaxIterations(int maxIterations) throws ProblemBoundsException {
        if (maxIterations < 10 || maxIterations > 1e6)
            throw new ProblemBoundsException("Max number of iterations: " + maxIterations +
                    " is either less than 10 or greater than 1 million.");
        this.maxIterations = maxIterations;
    }

    /**
     * Returns the value of epsilon in use.
     *
     * @return The desired root accuracy.
     */
    public double getEpsilon() {
        return epsilon;
    } // end getEpsilon

    /**
     * Returns the maximum number of iterations permitted.
     *
     * @return The maximum number of iterations that can be used by the solver.
     */
    public int getMaxIterations() {
        return maxIterations;
    } // end getMaxIterations

    private int countZeroCrossings() {
        int intervals = 100;
        int count = 0;
        double delta = (rootRangeHigh - rootRangeLow) / intervals;
        double testVal = rootRangeLow;
        do {
            if (eqn.evaluate(testVal) * eqn.evaluate(testVal + delta) < 0)
                count++;
            testVal += delta;
        } while (testVal < rootRangeHigh);
        return count;
    }

    /**
     * Determines and returns the root of the equation supplied to the constructor in the range supplied,
     * to the accuracy specified by epsilon.
     *
     * @return The located root.
     * @throws RootNotFoundException Thrown if there is not just one root in the supplied root range.
     */
    public double getRoot() throws RootNotFoundException {
        double low = rootRangeLow;
        double high = rootRangeHigh;
        double eqnLow = eqn.evaluate(rootRangeLow);
        double mid;
        double eqnMid;
        int loopCount = 0;
        int numRoots = countZeroCrossings();
        if (numRoots < 1)
            throw new RootNotFoundException("No root found in supplied root range.");
        if (numRoots > 1)
            throw new RootNotFoundException("More than one root in supplied root range.");
        do {
            mid = (low + high) / 2.0;
            eqnMid = eqn.evaluate(mid);
            if (eqnLow * eqnMid < 0)
                high = mid;
            else
                low = mid;
            loopCount++;
        } while (loopCount <= maxIterations && high - low > epsilon);
        // Check to make sure that the algorithm did converge to a root.
        if (loopCount >= maxIterations) {
            throw new RootNotFoundException("Maximum iterations exceeded. Not converging to root.");
        }
        return mid;
    }

}