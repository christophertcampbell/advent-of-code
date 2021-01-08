package day12;

import java.awt.Point;

public class NavComputer
{
    private Point currentLocation;
    private int currentHeadingIndex;
    private static final char[] headings = {'E', 'S', 'W', 'N'};

    public NavComputer()
    {
        currentLocation = new Point(0, 0);
        currentHeadingIndex = 0;
    }

    /**
     * Navigates to a new location based upon an array of instructions
     */
    public void navigate(String[] input)
    {
        for (int i = 0; i < input.length; i++)
        {
            NavInstruction instruction = parseInstruction(input[i]);
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
                    currentHeadingIndex += headings.length;
                }
                break;
            case 'R':
                currentHeadingIndex += amount / 90;
                if (currentHeadingIndex >= headings.length) {
                    currentHeadingIndex -= headings.length;
                }
                break;
        }
    }

    /**
     * Parses the string input into a usable NavInstruction
     */
    private NavInstruction parseInstruction(String input)
    {
        return new NavInstruction(input.charAt(0), Integer.parseInt(input.substring(1)));
    }

    /**
     * Returns the manhattan distance between the
     * start point and the current point
     */
    public int getManhattanDistanceFromStart()
    {
        return Math.abs(currentLocation.x) + Math.abs(currentLocation.y);
    }
}
