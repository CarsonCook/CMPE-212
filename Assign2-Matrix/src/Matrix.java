/**
 * Created by Carson on 06/02/2017.
 * Carson Cook - 14cdwc - CMPE 212 Assignment 2.
 */
public class Matrix {

    //minimum dimensions for the values
    private static int oneDimenMin = 0;
    private static int bothDimenMin = -1;

    private int m; //number of rows in values
    private int n; //number of columns in values
    private double values[][];

    public Matrix(int m, int n) {
        if (goodMatrixSize(m, n)) {
            this.m = m;
            this.n = n;
            this.values = new double[m][n];
        } else {
            System.out.println("Bad values sizes");
        }
    }

    public Matrix add(Matrix m) {
        //loop through entire matrix
        for (int i = 0; i < m.getM(); i++) {
            for (int j = 0; j < m.getN(); j++) {
                //change the current matrix to have correct values
                //saves allocating space for a new Matrix()
                m.set(i, j, values[i][j] + m.get(i, j));
            }
        }
        return m;
    }

    public Matrix subtract(Matrix m) {
        //loop through entire matrix
        for (int i = 0; i < m.getM(); i++) {
            for (int j = 0; j < m.getN(); j++) {
                //change the current matrix to have correct values
                //saves allocating space for a new Matrix()
                m.set(i, j, m.get(i, j) - values[i][j]);
            }
        }
        return m;
    }

    public Matrix multiply(Matrix m) {
        //loop through all matrix values
        for (int i = 0; i < m.getM(); i++) {
            for (int j = 0; j < m.getN(); j++) {
                m.set(i, j, m.get(i, j) * values[this.m][n]);
            }
        }
        return m; //this matrix is the one that has been changed
    }

    public Matrix multiply(double x) {
        //loop through all matrix values
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //manipulating this matrix
                values[i][j] = x * values[i][j];
            }
        }
        return this; //this matrix is the one that has been changed
    }

    public Matrix divide(Matrix m) {
        //loop through all matrix values
        for (int i = 0; i < m.getM(); i++) {
            for (int j = 0; j < m.getN(); j++) {
                m.set(i, j, m.get(i, j) / this.values[this.m][this.n]);
            }
        }
        return m; //this matrix is the one that has been changed
    }

    public double determinant() {
        //question stipulation to have square matrix and size less than 4.
        //m can be used to check size, because m == n
        if (isSquare() && m < 4) {
            
        }
        return Double.MAX_VALUE; //flag for bad calling with no square matrix, user should
                                //check its a square matrix before this method is called
    }

    public boolean isSquare() {
        return m == n; //same number of rows as columns
    }

    private static boolean goodMatrixSize(int m, int n) {
        //one can be equal to oneDimenMin, but both have to be greater than bothDimenMin
        return ((m > oneDimenMin || n > oneDimenMin) && (m > bothDimenMin && n > bothDimenMin));
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public double get(int i, int j) {
        return values[i][j];
    }

    public void set(int i, int j, double val) {
        values[i][j] = val;
    }
}
