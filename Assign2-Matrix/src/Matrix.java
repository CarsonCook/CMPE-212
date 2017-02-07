import javax.swing.*;

/**
 * Created by Carson on 06/02/2017.
 * Carson Cook - 14cdwc - CMPE 212 Assignment 2.
 */
public class Matrix {
    //todo matrix on matrix math check
    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        System.out.println("Original Matrix:");
        for (int i = 0; i < matrix.getM(); i++) {
            for (int j = 0; j < matrix.getN(); j++) {
                System.out.print(matrix.get(i, j) + " ");
            }
            System.out.print("\n");
        }
        System.out.println("mult by 5:");
        matrix.multiply(5);
        for (int i = 0; i < matrix.getM(); i++) {
            for (int j = 0; j < matrix.getN(); j++) {
                System.out.print(matrix.get(i, j) + " ");
            }
            System.out.print("\n");
        }
    }

    private static int dimenMin = 0; //minimum dimensions for the values

    private int m; //number of rows in values
    private int n; //number of columns in values
    private double values[][]; //values in the matrix

    public Matrix(int m, int n) {
        if (goodMatrixSize(m, n)) {
            this.m = m;
            this.n = n;
            values = new double[m][n];
        } else {
            //bad matrix dimensions, kill program
            System.out.println("Bad values for dimensions of matrix");
            System.exit(1);
        }
    }

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

    public Matrix add(Matrix m) {
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

    public Matrix subtract(Matrix m) {
        Matrix newM = new Matrix(m.getM(), m.getN());
        //loop through entire matrix
        for (int i = 0; i < m.getM(); i++) {
            for (int j = 0; j < m.getN(); j++) {
                //change the current matrix to have correct values
                //saves allocating space for a new Matrix()
                newM.set(i, j, m.get(i, j) - values[i][j]);
            }
        }
        return newM;
    }

    Matrix multiply(Matrix m) {
        Matrix newM = new Matrix(m.getM(), m.getN());
        //loop through all matrix values
        for (int i = 0; i < m.getM(); i++) {
            for (int j = 0; j < m.getN(); j++) {
                newM.set(i, j, m.get(i, j) * values[this.m][n]);
            }
        }
        return newM; //this matrix is the one that has been changed
    }

    /**
     * Multiplies the calling Matrix class' values by a scalar.
     * @param x The scalar to multiply the values by.
     * @return The new Matrix, with its values multiplied by x.
     * "This" because the calling instance of the class is the one
     * that has been affected.
     */
    Matrix multiply(double x) {
        //loop through all matrix values
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //manipulating this matrix
                values[i][j] = x * values[i][j];
            }
        }
        return this; //this matrix is the one that has been changed
    }

    Matrix divide(Matrix m) {
        Matrix newM = new Matrix(m.getM(), m.getN());
        //loop through all matrix values
        for (int i = 0; i < m.getM(); i++) {
            for (int j = 0; j < m.getN(); j++) {
                newM.set(i, j, m.get(i, j) / values[this.m][n]);
            }
        }
        return newM; //this matrix is the one that has been changed
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
        return Double.MAX_VALUE; //flag for bad calling with no square matrix, user should
        //check its a square matrix before this method is called
    }

    Matrix inverse() {
        if (isSquare() && m < 4) {

        }
        return null; //flag for bad calling with no square matrix, user should check first
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
