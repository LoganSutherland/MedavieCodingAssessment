/*****************************************************************************************************************
 * Class: Main
 *
 * Description: This class is the main class of the application. It initializes a Fan, JFrame, and CustomJPanel to represent our application
 *
 * Author: Logan Sutherland
 *
 *
 *****************************************************************************************************************/

import javax.swing.*;

public class Main {

    /**
     * Function initializes fan, JFrame, and JPanel to create our application
     * @param args
     */
    public static void main(String[] args) {
        Fan fan = new Fan();
        JFrame frame = new JFrame();

        //set jfame properties
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create and add custom jpanel to frame
        CustomJPanel customPanel = new CustomJPanel(fan);
        frame.add(customPanel);
        frame.pack();
    }
}
