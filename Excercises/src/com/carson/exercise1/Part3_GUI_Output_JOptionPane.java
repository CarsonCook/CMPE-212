package com.carson.exercise1;

import javax.swing.*;

/**
 * Created by Carson on 16/01/2017.
 */
public class Part3_GUI_Output_JOptionPane {

    public static void main(String[] args) {
        int test = 20;
        JOptionPane.showMessageDialog(null, "Value is " + test, "VALUE",
                JOptionPane.WARNING_MESSAGE);
        System.exit(0); //forces program to end
    }

}
