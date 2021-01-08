package day12;

import java.awt.Point;

/**
 * Navigation module for performaing navigation
 * based on a set of instructions
 * 
 * Uses a simple navigation method where the current location
 * is moved orthagonally by a specified distance, or
 * the heading is rotated and then the location moved forward
 * by a specified distance
 */
public class NavModulePartA implements NavModule
{
    private Point currentLocation;
    private int currentHeadingIndex;
    private static final char[] headings = {'E', 'S', 'W', 'N'};

    public NavModulePartA()
    {
        currentLocation = new Point(0, 0);
        currentHeadingIndex = 0;
    }

    /**
     * Navigates to a new location based upon an array of instructions
     */
    @Override
    public void navigate(NavInstruction[] input)
    {
        for (int i = 0; i < input.length; i++)
        {
            NavInstruction instruction = input[i];
            switch(instruction.getDirection())
            {
                case 'N':
                case 'S':
                case 'E':
                case 'W':
                    move(instruction.getDirection(), instruction.getAmount());
                    break;
                case 'L':
                case 'R':
                    turn(instruction.getDirection(), instruction.getAmount());
                    break;
                case 'F':
                    move(headings[currentHeadingIndex], instruction.getAmount());
                    break;
            }
        }
    }

    /**
     * Moves to a new location
     */
    private void move(char direction, int distance)
    {
        switch(direction)
        {
            case 'N':
                currentLocation.y += distance;
                break;
            case 'S':
                currentLocation.y -= distance;
                break;
            case 'E':
                currentLocation.x += distance;
                break;
            case 'W':
                currentLocation.x -= distance;
                break;
        }
    }

    /**
     * Turns to a new heading
     */
    private void turn(char direction, int amount)
    {
        switch(direction)
        {
            case 'L':
                currentHeadingIndex -= amount / 90;
                if (currentHeadingIndex < 0) {
                    // The heading index is out of bounds, wrap it around
                    currentHeadingIndex += headings.length;
                }
                break;
            case 'R':
                currentHeadingIndex += amount / 90;
                if (currentHeadingIndex >= headings.length) {
                    // The heading index is out of bounds, wrap it around
                    currentHeadingIndex -= headings.length;
                }
                break;
        }
    }

    @Override
    public Point getCurrentLocation()
    {
        return currentLocation;
    }
}
