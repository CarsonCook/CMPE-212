package com.carson.excercise1;

import javax.swing.*;

/**
 * Created by Carson on 17/01/2017.
 */
public class Part6_GUI_Input {

    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog(null, "Enter a name:", "Name", JOptionPane.QUESTION_MESSAGE);
        JOptionPane.showMessageDialog(null, name, "Your name is:", JOptionPane.INFORMATION_MESSAGE);

        String input = JOptionPane.showInputDialog(null, "Enter a number", "Title");
        try {
            int num = Integer.parseInt(input);
            JOptionPane.showMessageDialog(null, num);
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"That's not an int!");
        }
    }
}
