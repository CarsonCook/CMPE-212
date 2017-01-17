package com.carson.excercise1.IO_Helper;

import javax.swing.*;

/**
 * Created by Carson on 17/01/2017.
 */

public class IOHelper {

    public static int getInt(int low, String prompt) {
        return getInt(low, prompt, Integer.MAX_VALUE);
    }

    public static int getInt(String prompt, int high) {
        return getInt(Integer.MIN_VALUE, prompt, high);
    }

    public static int getInt(String prompt) {
        return getInt(Integer.MIN_VALUE, prompt, Integer.MAX_VALUE);
    }

    public static int getInt() {
        return getInt(Integer.MIN_VALUE, "", Integer.MAX_VALUE);
    }

    public static int getInt(int low, String prompt, int high) {
        int num = 0;
        boolean inputOK = false;
        while (!inputOK) {
            String input = JOptionPane.showInputDialog(null, prompt);
            try {
                num = Integer.parseInt(input);
                if (num >= low && num <= high) {
                    inputOK = true;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "That integer wasn't in the proper range!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "That was not an integer!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        return num;
    }

    public static double getDouble(double low, String prompt) {
        return getDouble(low, prompt, Double.MAX_VALUE);
    }

    public static double getDouble(String prompt, double high) {
        return getDouble(Double.MIN_VALUE, prompt, high);
    }

    public static double getDouble(String prompt) {
        return getDouble(Double.MIN_VALUE, prompt, Double.MAX_VALUE);
    }

    public static double getDouble() {
        return getDouble(Double.MIN_VALUE, "", Double.MAX_VALUE);
    }

    public static double getDouble(double low, String prompt, double high) {
        double num = 0;
        boolean inputOK = false;
        while (!inputOK) {
            String input = JOptionPane.showInputDialog(null, prompt);
            try {
                num = Double.parseDouble(input);
                if (num >= low && num <= high) {
                    inputOK = true;
                } else {
                    JOptionPane.showMessageDialog(null, "That double was not in the proper range!",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "That wasn't a double!",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        return num;
    }
}
