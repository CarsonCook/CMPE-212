package com.carson;

import javax.swing.*;

/**
 * Created by Carson on 17/01/2017.
 */
public class lectures {

    public static void main(String[] args) {
        //show input dialog
        //null,<message>,<title>,<icon type>
        //input always taken as a string -> convert string to integer if want an int
        String intString = JOptionPane.showInputDialog(null,
                "Enter an int", "title here", JOptionPane.INFORMATION_MESSAGE);
        //convert String to int:
        int intVal = Integer.parseInt(intString); //Double.parseDouble(); more functions available
        System.out.println("Value: " + intVal * 3);
    }
}
