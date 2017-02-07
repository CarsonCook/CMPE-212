import javax.swing.*;

/**
 * Created by Carson on 06/02/2017.
 * Carson Cook - 14cdwc - CMPE 212 Assignment 2.
 */
public class Matrix {

    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        System.out.println("Original Matrix:");
        for (int i = 0; i < matrix.getM(); i++) {
            for (int j = 0; j < matrix.getN(); j++) {
                System.out.print(matrix.get(i, j) + " ");
            }
            System.out.print("\n");
        }
        System.out.println("det: "+matrix.determinant());
    }

    //minimum dimensions for the values
    private static int dimenMin = 0;

    private int m; //number of rows in values
    private int n; //number of columns in values
    private double values[][];

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

    //done
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
     * through JOption pane popup windows.
     * @param title
     * @return
     */
    private int getInt(String title) {
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

    private double getDouble(String title) {
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

    int getM() {
        return m;
    }

    int getN() {
        return n;
    }

    double get(int i, int j) {
        return values[i][j];
    }

    void set(int i, int j, double val) {
        values[i][j] = val;
    }
}
