/*****************************************************************************************************************
 * Class: Fan
 *
 * Description: This class represents a ceiling fan object that can travel in 2 directions with 4 speeds (including off).
 *              It contains functions to set and retrieve information about its speed and direction.
 *
 * Author: Logan Sutherland
 *
 *
 *****************************************************************************************************************/

public class Fan {
    private int direction; //direction the fan blades are travelling
    private int speed; //speed the fan blades are moving at

    /**
     * Constructor for Fan class
     * Sets the default values for the fan's direction and speed
     */
    public Fan() {
        this.direction = 1; //set default direction to right
        this.speed = 0; //set default speed to 0
    }

    /**
     * Simulates the user pulling the direction cord of the fan.
     * When called it toggles the direction of the fan's blades from 1 (right) to -1 (left) or vice versa
     */
    public void pullDirectionCord() {
        this.direction *= -1; //multiply direction value by -1 to toggle between left and right directions
    }

    /**
     * Simulates the user pulling the speed cord of the fan.
     * When called it increments the fan's speed by 1. If the speed is increased above 3, the speed is reset to 0
     */
    public void pullSpeedCord() {
        this.speed++; //increment speed by 1

        if (this.speed > 3) //if speed over 3, reset to 0
            this.speed = 0;
    }

    /**
     * Returns the current direction of the fan.
     * @return      the fan's current direction
     */
    public int getDirection() {
        return this.direction;
    }

    /**
     * Returns the current speed of the fan
     * @return      the fan's current speed
     */
    public int getSpeed() {
        return this.speed;
    }
}
