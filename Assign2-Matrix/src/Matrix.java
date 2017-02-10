import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;

/**
 * Created by Carson on 06/02/2017.
 * Carson Cook - 14cdwc - CMPE 212 Assignment 2.
 */
public class Matrix {

    public static void main(String[] args) {
        Matrix matrix1 = new Matrix();
        System.out.println(matrix1.toString() + "\n");

        System.out.println(matrix1.inverse());
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
     * Constructor for a Matrix. User inputs the dimensions and all
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

    /**
     * Constructor for a Matrix. Takes the file name/path of a file and creates a Matrix from it.
     * The file must follow the following format:
     * The first number is the number of rows, then a comma, then the number of columns.
     * Then, on the next line, the first row is specified with a comma only between each number.
     * Subsequent rows come on next lines.
     *
     * @param fileName The path/name of the file to create the Matrix from.
     */
    public Matrix(String fileName) {
        try {
            BufferedReader csvFile = new BufferedReader(new FileReader(fileName));
            String input = "";
            String dataRow = readCSVLine(csvFile);
            while (dataRow != null) {
                input += (dataRow + "\n"); //readLine doesn't read the end line character
                dataRow = readCSVLine(csvFile);
            }
            parseMatrix(input);
        } catch (FileNotFoundException e) {
            System.out.println("error: " + e);
        }
    }

    /**
     * Parses through the String version of the input file and creates a Matrix from the information in the String.
     *
     * @param input String containing the information from the csv file.
     */
    private void parseMatrix(String input) {
        //get dimensions
        m = Character.getNumericValue(input.charAt(0));
        n = Character.getNumericValue(input.charAt(2));//comma separates m and n
        //now make matrix
        values = new double[m][n];
        //counters for where we are in matrix
        int row = 0;
        int col = 0;
        for (int i = 4; i < input.length(); i++) { //i starts at 4 because already dealt with top line ('m'',''n''\n')
            //get each number - split by commas and end lines
            String oneNum = "";
            int j = i;
            while (input.charAt(j) != ',' && input.charAt(j) != '\n') {
                oneNum += input.charAt(j);
                j++;
            }
            //set number in matrix
            this.set(row, col, Double.parseDouble(oneNum));
            //get ready to set number in next position in Matrix
            if (col == n - 1) {
                col = 0;
                row++;
            } else {
                col++;
            }
            //continue through string
            i = j;
        }
    }

    /**
     * Method to read a line from the csv file used to create a Matrix.
     * Puts a try-catch block around the readLine() call from the BufferedReader.
     * Method is private and static.
     *
     * @param csvFile The BufferedReader the Matrix is being created from.
     * @return One line of the csv file, or, if and IOException
     * is caught, returns null.
     */
    private static String readCSVLine(BufferedReader csvFile) {
        try {
            return csvFile.readLine();
        } catch (IOException e) {
            System.out.println("error: " + e);
            return null;
        }
    }

    /**
     * Method to add two matrices. Checks for matrices that can't be added (aren't same size) and will
     * kill program if they can't be added. Dimensions will be okay because the matrices have
     * already been initialized, so the dimensions have been checked.
     *
     * @param m Matrix that is added to the calling instance of Matrix.
     * @return new Matrix that is the result of the calling instance and the parameter Matrix added.
     */
    public Matrix add(Matrix m) {
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
    public Matrix subtract(Matrix m) {
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
    public Matrix multiply(Matrix m) {
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

        return newM;
    }

    /**
     * Multiplies the calling Matrix class' values by a scalar.
     *
     * @param x The scalar to multiply the values by.
     * @return The new Matrix, with its values multiplied by x.
     */
    public Matrix multiply(double x) {
        Matrix newM = new Matrix(m, n);
        //loop through all matrix values
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //manipulating this matrix
                newM.set(i, j, x * values[i][j]);
            }
        }
        return newM;
    }

    /**
     * Divides the parameter Matrix by the calling Matrix (m/this).
     * Matrix division is actually just the numerator * inverse of denominator
     * (m*this^-1).
     * Don't need to check for proper Matrix sizes here because that will be done inside the
     * multiply function.
     *
     * @param m Matrix being divided by calling instance.
     * @return resultant Matrix gotten from dividing the parameter Matrix by the calling instance.
     */
    public Matrix divide(Matrix m) {
        //matrix division is just multiplying the numerator matrix by the inverse of the denominator
        Matrix dividerMatrix = this.inverse();
        return m.multiply(dividerMatrix);
    }

    /**
     * Method to find the determinant of a matrix. Matrix must be square,
     * and the question stipulates that the matrix must be less than size 4.
     * If the matrix does not pass these stipulations, the method will end the program.
     *
     * @return The maximum value of a double if a poor matrix is sent in, otherwise
     * it will be the determinant of the matrix.
     */
    public double determinant() {
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
                    return (values[0][0] * (values[1][1] * values[2][2] - values[1][2] * values[2][1]) -
                            values[1][0] * (values[0][1] * values[2][2] - values[0][2] * values[2][1]) +
                            values[2][0] * (values[0][1] * values[1][2] - values[0][2] * values[1][1]));
            }
        }
        //kill program so nothing bad happens
        System.out.println("You tried to find determinant of matrix that isn't square or is greater than size 4");
        System.exit(4);
        return Double.MAX_VALUE; //won't actually get here but removes IDE error. Would be flag for bad call of method.
    }


    /**
     * Method to calculate inverse of calling Matrix. Matrix must be square,
     * and the question stipulates that the matrix must be less than size 4.
     * If the matrix does not pass these stipulations, the method will end the program.
     *
     * @return Matrix that is the inverse of the calling Matrix.
     */
    public Matrix inverse() {
        //question stipulation to have square matrix and size less than 4.
        //m can be used to check size, because m == n
        //because calling Matrix has been instantiated, no need to check that
        //dimensions are above 0
        if (isSquare() && m < 4) {
            //we know matrix size is 1, 2 or 3, so we can just make special case for each
            switch (m) {
                case 1:
                    //inverse of matrix size 1 is just that same matrix
                    return this;

                case 2:
                    //inverse of {{a,b},{c,d}} size 2 is 1/det * {{d,-b},{-c,a}}
                    Matrix copy = new Matrix(this.getM(), this.getN());
                    //set new values for the special matrix to get inverse
                    copy.set(0, 0, this.get(1, 1));
                    copy.set(1, 1, this.get(0, 0));
                    copy.set(0, 1, -1 * this.get(0, 1));
                    copy.set(1, 0, -1 * this.get(1, 0));
                    return copy.multiply(1.0 / this.determinant()); //1.0 to force division to be a float value

                case 3:
                    //first find matrix of minors: each position in Matrix is a determinant
                    //of the 2x2 matrix when you cross out the numbers in same row/column as j
                    Matrix inv = new Matrix(3, 3);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            inv.set(i, j, getDetMatrix(this, i, j).determinant());
                        }
                    }

                    //now swap values in middle of each edge and swap sign(matrix of co-factors and adjoint)
                    //also swap top right and bottom left (but not sign)
                    double top = inv.get(0, 1);
                    double left = inv.get(1, 0);
                    double topRight = inv.get(0, 2);
                    double bottomLeft = inv.get(2, 0);
                    double right = inv.get(1, 2);
                    double bottom = inv.get(2, 1);

                    inv.set(0, 1, left * -1); //top set
                    inv.set(1, 0, top * -1); //left set
                    inv.set(0, 2, bottomLeft); //top right set
                    inv.set(2, 0, topRight); //bottom left set
                    inv.set(1, 2, bottom * -1); //right set
                    inv.set(2, 1, right * -1); //bottom set

                    //now multiply this adjoint matrix by determinant of original matrix
                    return inv.multiply(1.0/this.determinant()); //1.0 so that division is forced to be a float value
            }
        }
        //kill program so nothing bad happens
        System.out.println("You tried to find inverse of matrix that isn't square or is bigger than 4x4");
        System.exit(5);
        return null; //won't actually get here but removes IDE error. Would be flag for bad call of method.
    }

    /**
     * Method to get the Matrix to find the determinant of when getting the Matrix of Minors for the inverse method.
     * Does not need to sanitize parameters because it is only called from a method that already does that.
     *
     * @param orgMatrix Matrix form which the minor Matrix is found.
     * @param row       The row cancelled out of the orgMatrix.
     * @param col       The column cancelled out of the orgMatrix.
     * @return the minor Matrix for the given row/column cancelling out.
     */
    private Matrix getDetMatrix(Matrix orgMatrix, int row, int col) {
        Matrix minorMatrix = new Matrix(2, 2);
        //know that orgMatrix is 3x3, so just hardcode resultant minorMatrix
        if (row == 0 && col == 0) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    minorMatrix.set(i, j, orgMatrix.get(i + 1, j + 1));
                }
            }
        } else if (row == 0 && col == 1) {
            minorMatrix.set(0, 0, orgMatrix.get(1, 0));
            minorMatrix.set(0, 1, orgMatrix.get(1, 2));
            minorMatrix.set(1, 0, orgMatrix.get(2, 0));
            minorMatrix.set(1, 1, orgMatrix.get(2, 2));
        } else if (row == 0 && col == 2) {
            minorMatrix.set(0, 0, orgMatrix.get(1, 0));
            minorMatrix.set(0, 1, orgMatrix.get(1, 1));
            minorMatrix.set(1, 0, orgMatrix.get(2, 0));
            minorMatrix.set(1, 1, orgMatrix.get(2, 1));
        } else if (row == 1 && col == 0) {
            minorMatrix.set(0, 0, orgMatrix.get(0, 1));
            minorMatrix.set(0, 1, orgMatrix.get(0, 2));
            minorMatrix.set(1, 0, orgMatrix.get(2, 1));
            minorMatrix.set(1, 1, orgMatrix.get(2, 2));
        } else if (row == 1 && col == 1) {
            minorMatrix.set(0, 0, orgMatrix.get(0, 0));
            minorMatrix.set(0, 1, orgMatrix.get(0, 2));
            minorMatrix.set(1, 0, orgMatrix.get(2, 0));
            minorMatrix.set(1, 1, orgMatrix.get(2, 2));
        } else if (row == 1 && col == 2) {
            minorMatrix.set(0, 0, orgMatrix.get(0, 0));
            minorMatrix.set(0, 1, orgMatrix.get(0, 1));
            minorMatrix.set(1, 0, orgMatrix.get(2, 0));
            minorMatrix.set(1, 1, orgMatrix.get(2, 1));
        } else if (row == 2 && col == 0) {
            minorMatrix.set(0, 0, orgMatrix.get(0, 1));
            minorMatrix.set(0, 1, orgMatrix.get(0, 2));
            minorMatrix.set(1, 0, orgMatrix.get(1, 1));
            minorMatrix.set(1, 1, orgMatrix.get(1, 2));
        } else if (row == 2 && col == 1) {
            minorMatrix.set(0, 0, orgMatrix.get(0, 0));
            minorMatrix.set(0, 1, orgMatrix.get(0, 2));
            minorMatrix.set(1, 0, orgMatrix.get(1, 0));
            minorMatrix.set(1, 1, orgMatrix.get(1, 2));
        } else if (row == 2 && col == 2) {
            minorMatrix.set(0, 0, orgMatrix.get(0, 0));
            minorMatrix.set(0, 1, orgMatrix.get(0, 1));
            minorMatrix.set(1, 0, orgMatrix.get(1, 0));
            minorMatrix.set(1, 1, orgMatrix.get(1, 1));
        }
        return minorMatrix;
    }

    /**
     * Method that checks if a matrix is square and returns a boolean indicating if it is square.
     * A square matrix is one with an equal number of rows and columns.
     *
     * @return Boolean: true if matrix is square, false if not.
     */
    public boolean isSquare() {
        return m == n; //same number of rows as columns
    }

    /**
     * Method that finds the transpose of the calling instance Matrix.
     * No need to check for proper dimensions because an already instantiated
     * Matrix is calling this function, so the dimensions work.
     *
     * @return the transpose of the calling Matrix.
     */
    public Matrix transpose() {
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
     * colliding with the java.lang.object toString() method.
     *
     * @return String showing the Matrix, in the format defined by the question.
     */
    public String toString() {
        String output = m + "," + n + "\n";
        DecimalFormat formatter = new DecimalFormat("0.#"); //cuts off trailing 0s
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                output += formatter.format(values[i][j]);
                //only add comma if number not the last one on the line
                if (j < n - 1) {
                    output += ",";
                }
            }
            output += "\n";
        }
        return output;
    }

    /**
     * Method to print a Matrix to a csv file. The format is
     * defined by the question: number of rows, number of columns
     * number,number
     * number,number etc.
     *
     * @param fileName The file name/path of where the Matrix is being printed.
     */
    public void print(String fileName) {
        String output = this.toString(); //format to output in
        try {
            PrintWriter pw = new PrintWriter(new File(fileName));
            System.out.println("matrix to print:\n" + this);
            pw.write(output);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("error: " + e);
        }
    }

    /**
     * Method to create identity Matrix of size defined by a parameter.
     * Identity Matrix is square with all 0s except the diagonals are 1s.
     * Static method.
     *
     * @param size integer for number of columns and rows in Matrix.
     * @return the identity Matrix of size of the parameter.
     */
    public static Matrix identity(int size) {
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
    public int getM() {
        return m;
    }

    /**
     * Getter for n attribute - the number of columns in the matrix.
     *
     * @return n attribute.
     */
    public int getN() {
        return n;
    }

    /**
     * Getter for a value at a specific location in the matrix.
     *
     * @param i The row number for the value.
     * @param j The column number for the value.
     * @return Double value within the matrix.
     */
    public double get(int i, int j) {
        return values[i][j];
    }

    /**
     * Setter for a value at a specific location in the matrix.
     *
     * @param i   The row number for the value.
     * @param j   The column number for the value.
     * @param val The value to be set.
     */
    public void set(int i, int j, double val) {
        //if value is super small round to 0
        //gets rid of floating point error, which also is an issue that can show a "-0"
        if (val<Math.abs(0.000001)){
            val=0;
        }
        values[i][j] = val;
    }
}
