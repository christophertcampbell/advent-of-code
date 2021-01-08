package day12;

import java.awt.Point;

public class NavComputer
{
    /**
     * NavModule handles the actual navigation logic
     */
    private NavModule navModule;

    public NavComputer(NavModule navModule)
    {
        this.navModule = navModule;
    }

    /**
     * Navigates to a new location based upon an array of instructions
     */
    public void navigate(String[] input)
    {
        NavInstruction[] navInstructions = parseInstructions(input);
        navModule.navigate(navInstructions);
    }

    /**
     * Parses the string input into a usable NavInstruction
     */
    private NavInstruction[] parseInstructions(String[] input)
    {
        NavInstruction[] navInstructions = new NavInstruction[input.length];
        for (int i = 0; i < input.length; i++)
        {
            navInstructions[i] = new NavInstruction(input[i].charAt(0), Integer.parseInt(input[i].substring(1)));
        }
        return navInstructions;
    }

    /**
     * Returns the manhattan distance between the
     * start point and the current point
     */
    public int getManhattanDistanceFromStart()
    {
        Point currentLocation = navModule.getCurrentLocation();
        return Math.abs(currentLocation.x) + Math.abs(currentLocation.y);
    }
}
