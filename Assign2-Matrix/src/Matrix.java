import javax.swing.*;

/**
 * Created by Carson on 06/02/2017.
 * Carson Cook - 14cdwc - CMPE 212 Assignment 2.
 */
public class Matrix {

    //TODO cut off .0 in something like 1.0

    public static void main(String[] args) {
        Matrix matrix1 = new Matrix();
        System.out.println("Original Matrix 1:");
        for (int i = 0; i < matrix1.getM(); i++) {
            for (int j = 0; j < matrix1.getN(); j++) {
                System.out.print(matrix1.get(i, j) + " ");
            }
            System.out.print("\n");
        }


        Matrix matrix2 = new Matrix();
        System.out.println("Original Matrix 2:");
        for (int i = 0; i < matrix2.getM(); i++) {
            for (int j = 0; j < matrix2.getN(); j++) {
                System.out.print(matrix2.get(i, j) + " ");
            }
            System.out.print("\n");
        }

        System.out.println("matrix2/matrix1:");
        Matrix matrix3 = matrix1.divide(matrix2);
        for (int i = 0; i < matrix3.getM(); i++) {
            for (int j = 0; j < matrix3.getN(); j++) {
                System.out.print(matrix3.get(i, j) + " ");
            }
            System.out.print("\n");
        }
    }

    private static int dimenMin = 0; //minimum dimensions for the values

    private int m; //number of rows in values
    private int n; //number of columns in values
    private double values[][]; //values in the matrix

    /**
     * Constructor for a matrix. Input the dimensions of the matrix and it is
     * initialized to 0s.
     *
     * @param m Number of rows.
     * @param n Number of columns.
     */
    public Matrix(int m, int n) {
        if (goodMatrixSize(m, n)) {
            this.m = m;
            this.n = n;
            values = new double[m][n]; //initializes matrix to all 0s
        } else {
            //bad matrix dimensions, kill program
            System.out.println("Bad values for dimensions of matrix");
            System.exit(1);
        }
    }

    /**
     * Constructor for a matrix. User inputs the dimensions and all
     * of the values in it through JOptionPane popups.
     */
    public Matrix() {
        //get dimensions
        m = getInt("Enter the number of rows (m): ");
        n = getInt("Enter the number of columns (n):");
        values = new double[m][n];
        //get values
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                values[i][j] = getDouble("Enter the value in the matrix for position " +
                        "(" + i + "," + j + "):");
            }
        }
    }

    //TODO constructor for csv file

    /**
     * Constructor for a matrix in order to copy it.
     *
     * @param oldMatrix the matrix to be copied.
     */
    public Matrix(Matrix oldMatrix) {
        this.m = oldMatrix.m;
        n = oldMatrix.n;
        values = oldMatrix.values;
    }

    /**
     * Method to add two matrices. Checks for matrices that can't be added (aren't same size) and will
     * kill program if they can't be added. Dimensions will be okay because the matrices have
     * already been initialized, so the dimensions have been checked.
     *
     * @param m Matrix that is added to the calling instance of Matrix.
     * @return new Matrix that is the result of the calling instance and the parameter Matrix added.
     */
    Matrix add(Matrix m) {
        //to add matrices, need them to be the same size
        if (m.getM() != this.m || m.getN() != n) {
            //kill program so nothing bad happens
            System.out.println("You tried to add matrices that aren't the same size!");
            System.exit(1);
        }

        Matrix newM = new Matrix(m.getM(), m.getN());
        //loop through entire matrix
        for (int i = 0; i < m.getM(); i++) {
            for (int j = 0; j < m.getN(); j++) {
                //change the current matrix to have correct values
                //saves allocating space for a new Matrix()
                newM.set(i, j, values[i][j] + m.get(i, j));
            }
        }
        return newM;
    }

    /**
     * Method that subtracts the parameter Matrix from the calling instance of Matrix.
     * Checks that the two Matrices can actually be subtracted (must be same size), if not,
     * kills the program.
     *
     * @param m Matrix to be subtracted from the calling instance of Matrix.
     * @return the resultant Matrix from subtracting the parameter Matrix from the calling
     * instance.
     */
    Matrix subtract(Matrix m) {
        //to subtract matrices, need them to be the same size
        if (m.getM() != this.m || m.getN() != n) {
            //kill program so nothing bad happens
            System.out.println("You tried to subtract matrices that aren't the same size!");
            System.exit(2);
        }

        Matrix newM = new Matrix(m.getM(), m.getN());
        //loop through entire matrix
        for (int i = 0; i < m.getM(); i++) {
            for (int j = 0; j < m.getN(); j++) {
                //change the current matrix to have correct values
                //saves allocating space for a new Matrix()
                newM.set(i, j, values[i][j] - m.get(i, j));
            }
        }
        return newM;
    }

    /**
     * Method that multiplies the calling instance of Matrix and the parameter Matrix,
     * in that order. If the matrices can't be multiplied (calling instance number of
     * columns doesn't equal parameter's number of rows) kills the program.
     *
     * @param m Matrix to multiply the calling instance of Matrix with.
     * @return the resultant Matrix from calling instance * parameter Matrix.
     */
    Matrix multiply(Matrix m) {
        //to multiply matrices, need the first matrix's number of columns
        //to equal the second matrix's number of rows
        if (m.getM() != n) {
            //kill program so nothing bad happens
            System.out.println("You tried to multiply matrices that don't have the proper dimensions!");
            System.exit(3);
        }

        //resultant has same number of rows as first matrix, number of columns of second matrix
        Matrix newM = new Matrix(this.m, m.getN());

        //set values of resultant matrix
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < m.getN(); j++) {
                int onePositionValue = 0;
                for (int k = 0; k < n; k++) {
                    //add up values at each position
                    onePositionValue += values[i][k] * m.get(k, j);
                }
                newM.set(i, j, onePositionValue);
            }
        }

        return newM; //this matrix is the one that has been changed
    }

    /**
     * Multiplies the calling Matrix class' values by a scalar.
     *
     * @param x The scalar to multiply the values by.
     * @return The new Matrix, with its values multiplied by x.
     * "This" because the calling instance of the class is the one
     * that has been affected.
     */
    Matrix multiply(double x) {
        Matrix newM = new Matrix(m, n);
        //loop through all matrix values
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //manipulating this matrix
                newM.set(i, j, x * values[i][j]);
            }
        }
        return newM; //this matrix is the one that has been changed
    }

    //TODO fix divide method
    Matrix divide(Matrix m) {
        Matrix dividerMatrix = this.inverse();
        return dividerMatrix.multiply(m);
    }

    /**
     * Method to find the determinant of a matrix. Matrix must be square,
     * and the question stipulates that the matrix must be less than size 4.
     * If the matrix does not pass these stipulations, the method will return
     * the maximum value of a double as a flag that an issue occurred.
     *
     * @return The maximum value of a double if a poor matrix is sent in, otherwise
     * it will be the determinant of the matrix.
     */
    double determinant() {
        //question stipulation to have square matrix and size less than 4.
        //m can be used to check size, because m == n
        if (isSquare() && m < 4) {
            //we know matrix size is 1, 2 or 3, so we can just make special case for each
            switch (m) {
                case 1:
                    return values[0][0];
                case 2:
                    return values[0][0] * values[1][1] - values[0][1] * values[1][0];
                case 3:
                    //TODO get algorithm(?)
                    return (values[0][0] * (values[1][1] * values[2][2] - values[1][2] * values[2][1]) +
                            values[1][0] * (values[0][1] * values[2][2] - values[0][2] * values[2][1]) +
                            values[2][0] * (values[0][1] * values[1][2] - values[0][2] * values[1][1]));
            }
        }
        //kill program so nothing bad happens
        System.out.println("You tried to find determinant of matrix that isn't square (or greater than size 4");
        System.exit(4);
        return Double.MAX_VALUE; //won't actually get here but removes IDE error. Would be flag for bad call of method.
    }

    Matrix inverse() {
        //question stipulation to have square matrix and size less than 4.
        //m can be used to check size, because m == n
        if (isSquare() && m < 4) {
            //we know matrix size is 1, 2 or 3, so we can just make special case for each
            switch (m) {
                case 1:
                    //inverse of matrix size 1 is just that same matrix
                    return this;
                case 2:
                    //inverse of {{a,b},{c,d}} size 2 is 1/det * {{d,-b},{-c.a}}
                    double det = this.determinant();
                    Matrix copy = new Matrix(this);
                    //set new values for the special matrix to get inverse
                    copy.set(0, 0, this.get(1, 1));
                    copy.set(1, 1, this.get(0, 0));
                    copy.set(0, 1, -1 * this.get(0, 1));
                    copy.set(1, 0, -1 * this.get(1, 0));
                    return copy.multiply(1.0 / det);
                case 3:
                    //TODO get algorithm
            }
        }
        //kill program so nothing bad happens
        System.out.println("You tried to find inverse of matrix that isn't square");
        System.exit(5);
        return null; //won't actually get here but removes IDE error. Would be flag for bad call of method.
    }

    /**
     * Method that checks if a matrix is square and returns a boolean indicating if it is square.
     * A square matrix is one with an equal number of rows and columns.
     *
     * @return Boolean: true if matrix is square, false if not.
     */
    boolean isSquare() {
        return m == n; //same number of rows as columns
    }

    /**
     * Method that finds the transpose of the calling instance Matrix.
     * No need to check for proper dimensions because an already instantiated
     * Matrix is calling this function, so the dimensions work.
     *
     * @return the transpose of the calling Matrix.
     */
    Matrix transpose() {
        Matrix transpose = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                transpose.set(i, j, values[j][i]);
            }
        }
        return transpose;
    }

    /**
     * Method to turn Matrix into String in format defined by question.
     * Defined as public unlike other methods because there was an issue
     * with the java.lang.object toString() method.
     *
     * @return String showing the Matrix, in the format defined by the question.
     */
    public String toString() {
        String output = m + "," + n + "\n";
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                output += values[i][j];
                //only add comma if number not the last one on the line
                if (j < n - 1) {
                    output += ",";
                }
            }
            output += "\n";
        }
        return output;
    }

    //TODO print method

    /**
     * Method to create identity Matrix of size defined by a parameter.
     * Identity Matrix is square with all 0s except the diagonals are 1s.
     * Static method.
     * @param size integer for number of columns and rows in Matrix.
     * @return the identity Matrix of size of the parameter.
     */
    static Matrix identity(int size) {
        //initialize Matrix with all 0s
        Matrix I = new Matrix(size, size);
        //fill diagonals with 1s
        for (int k = 0; k < size; k++) {
            I.values[k][k] = 1;
        }
        return I;
    }

    /**
     * Method that checks that proper matrix dimensions have been given. Used by
     * the constructor that takes number of rows and columns values and initializes
     * a matrix from that.
     * Proper dimensions for a matrix are that both number of rows and columns are greater than 0.
     * This method is static and private.
     *
     * @param m The number of columns (integer).
     * @param n The number of rows (integer).
     * @return True if both dimensions are greater than 0, else false.
     */
    private static boolean goodMatrixSize(int m, int n) {
        //both dimensions have to be greater than 0
        return (m > dimenMin && n > dimenMin);
    }

    /**
     * Method that gets an integer as input from the user. Does this
     * through JOption pane popup windows. This is used to get the
     * dimensions for the user inputted matrix.
     * Method is private and static.
     *
     * @param title String to display in input window.
     * @return Integer that user inputted.
     */
    private static int getInt(String title) {
        int num = -1;//flag for didn't get proper input
        boolean inputOK = false;
        while (!inputOK) { //keep going until get proper input
            String input = JOptionPane.showInputDialog(null, title);
            //try catch in case a non integer entered
            try {
                num = Integer.parseInt(input);
                inputOK = true;
                //need an number above 0
                if (num < dimenMin) {
                    inputOK = false;
                    JOptionPane.showMessageDialog(null, "Larger dimension please");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "That's not an int!");
                inputOK = false;
            }
        }
        return num;
    }

    /**
     * Method that gets a number as input from the user. Does this
     * through JOption pane popup windows. This is used to get the
     * values for the user inputted matrix.
     * Method is private and static.
     *
     * @param title String to display in input window.
     * @return Number that user inputted.
     */
    private static double getDouble(String title) {
        double num = Double.MAX_VALUE;//flag for didn't get proper input
        //shouldn't ever need to use as a flag, however
        boolean inputOK = false;
        while (!inputOK) { //try catch in case a non integer entered
            String input = JOptionPane.showInputDialog(null, title);
            //try catch in case a non number entered
            try {
                num = Double.parseDouble(input);
                inputOK = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "That's not a number!");
                inputOK = false;
            }
        }
        return num;
    }

    /**
     * Getter for m attribute - the number of rows in the matrix.
     *
     * @return m attribute.
     */
    int getM() {
        return m;
    }

    /**
     * Getter for n attribute - the number of columns in the matrix.
     *
     * @return n attribute.
     */
    int getN() {
        return n;
    }

    /**
     * Getter for a value at a specific location in the matrix.
     *
     * @param i The row number for the value.
     * @param j The column number for the value.
     * @return Double value within the matrix.
     */
    double get(int i, int j) {
        return values[i][j];
    }

    /**
     * Setter for a value at a specific location in the matrix.
     *
     * @param i   The row number for the value.
     * @param j   The column number for the value.
     * @param val The value to be set.
     */
    void set(int i, int j, double val) {
        values[i][j] = val;
    }
}
