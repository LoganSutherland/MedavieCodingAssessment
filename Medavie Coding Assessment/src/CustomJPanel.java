/*****************************************************************************************************************
 * Class: CustomJPanel
 *
 * Description: This class extends and adds additional functions to the JPanel class.
 *              This class represents the drawing area of the application.
 *
 * Author: Logan Sutherland
 *
 *
 *****************************************************************************************************************/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class CustomJPanel extends JPanel implements ActionListener {

    private Rectangle2D directionCord, speedCord; //rectangles used to represent the two pull cord buttons
    private Image fanImage; //image of ceiling fan

    private Timer timer; //java swing timer

    //fan rotation values
    private double fanRotationInterp = 0; //interpolation value used to determine current angle of rotation for fan
    private double fanRotationMax = 360; //max rotation value for fan
    private double fanRotationMin = 0; //min rotation value for fan

    private Fan fan; //fan object

    /**
     * Constructor for CustomJPanel class.
     * Sets up initial values, sets up timer and mouse event
     * @param fan fan to display on screen
     */
    public CustomJPanel(Fan fan) {
        this.fan = fan;

        this.setPreferredSize(new Dimension(750, 750)); //set dimension of initial window
        this.setDoubleBuffered(true); //set double buffering to true for smooth animations

        //set up pull cord button rectangles
        directionCord = new Rectangle2D.Double(250, 100, 105, 25);
        speedCord = new Rectangle2D.Double(420, 100, 95, 25);;

        fanImage = new ImageIcon("fan.png").getImage(); //retrieve ceiling fan image

        timer = new Timer(10, this); //create new timer to call Action
        timer.start();

        //create mouse listener to check for mouse input
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

            if (directionCord.contains(e.getX(), e.getY())) { //if mouse click occurs within direction cord button
                System.out.println("Direction Cord Pulled");
                fan.pullDirectionCord();
                System.out.println(fan.getDirection());
            }

            if (speedCord.contains(e.getX(), e.getY())) { //if mouse click occurs within speed cord button
                System.out.println("Speed Cord Pulled");
                fan.pullSpeedCord();
                System.out.println(fan.getSpeed());
            }
            }
        });
    }

    /**
     * Function paints the pull buttons for the direction and speed cords,
     * rotates and draws the fan object.
     * This function is called by repaint 100 times a second.
     * @param g graphics object cast to Graphics2D
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        double fanTheta;

        Graphics2D g2Dcasted = (Graphics2D) g; //cast graphics object to Graphics2D for more options

        g2Dcasted.setColor(Color.GREEN); //set draw color to green

        //draw pull cord button rectangles
        g2Dcasted.fill(directionCord);
        g2Dcasted.fill(speedCord);

        g2Dcasted.setColor(Color.BLACK); //set draw color to black

        //draw pull cord button text
        g2Dcasted.drawString("Pull Direction Cord", 250, 115);
        g2Dcasted.drawString("Pull Speed Cord", 420, 115);

        AffineTransform reset = g2Dcasted.getTransform(); //store current transform matrix

        fanTheta = (1 - fanRotationInterp) * fanRotationMin + fanRotationMax * fanRotationInterp; //calculate fan's angle of rotation via interpolation

        g2Dcasted.rotate(Math.toRadians(fanTheta), 375, 375); //apply rotation
        g2Dcasted.drawImage(fanImage, 250, 250, null); //draw fan image

        g2Dcasted.setTransform(reset); //reset transform matrix
    }

    /**
     * Function called by java swing timer that calculates the new fan rotation intperolation value
     * and repaints the JPanel
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (this.fan.getSpeed()) { //check current fan speed
            case 0:
                this.fanRotationInterp += 0;
                break;
            case 1:
                this.fanRotationInterp += 0.001 * this.fan.getDirection(); //multiply increase by fan direction to move rotate either right (positive) or left (negative)
                break;
            case 2:
                this.fanRotationInterp += 0.005 * this.fan.getDirection(); //multiply increase by fan direction to move rotate either right (positive) or left (negative)
                break;
            case 3:
                this.fanRotationInterp += 0.01 * this.fan.getDirection(); //multiply increase by fan direction to move rotate either right (positive) or left (negative)
                break;
        }

        if (fanRotationInterp >= 1.0 && this.fan.getDirection() == 1) //if fan rotation has reached max value for turning right
            fanRotationInterp = 0;

        if (fanRotationInterp <= 0 && this.fan.getDirection() == -1) //if fan rotation has reached max value for turning left
            fanRotationInterp = 1;

        repaint(); //repaint jpanel
    }
}
