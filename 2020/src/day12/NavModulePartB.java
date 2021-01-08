package day12;

import java.awt.Point;

/**
 * Navigation module for performaing navigation
 * based on a set of instructions
 * 
 * Uses a waypoint relative to the current location, at times moving
 * and rotating the waypoint relative to the current location,
 * at other times moving the current location and waypoint together
 * by the distance and heading between current location and waypoint 
 */
public class NavModulePartB implements NavModule
{
    private Point currentLocation;
    private Point waypoint;

    public NavModulePartB(int waypointX, int waypointY)
    {
        currentLocation = new Point(0, 0);
        waypoint = new Point(waypointX, waypointY);
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
                    moveWaypoint(instruction.getDirection(), instruction.getAmount());
                    break;
                case 'L':
                case 'R':
                    rotateWaypoint(instruction.getDirection(), instruction.getAmount());
                    break;
                case 'F':
                    moveCurrentLocation(instruction.getAmount());
                    break;
            }
        }
    }

    /**
     * Moves the waypoint to a new location
     */
    private void moveWaypoint(char direction, int distance)
    {
        switch(direction)
        {
            case 'N':
                waypoint.y += distance;
                break;
            case 'S':
                waypoint.y -= distance;
                break;
            case 'E':
                waypoint.x += distance;
                break;
            case 'W':
                waypoint.x -= distance;
                break;
        }
    }

    /**
     * Rotates the waypoint about the current location
     */
    private void rotateWaypoint(char direction, int amount)
    {
        // For ease of rotation, establish a "relative waypoint" which is
        // calculated as if the current location were at the origin (0,0)
        int shiftX = 0 - currentLocation.x;
        int shiftY = 0 - currentLocation.y;
        Point relativeWaypoint = new Point(waypoint.x + shiftX, waypoint.y + shiftY);

        // Establish how many times we need to rotate 90 degrees
        int turnCount = amount / 90;

        // Rotate the appropriate number of times
        for (int i = 0; i < turnCount; i++)
        {
            Point newRelativeWaypoint;
            switch(direction)
            {
                case 'L':
                    // Rotate the relative waypoint counter-clockwise 90 degrees about (0,0)
                    newRelativeWaypoint = new Point(relativeWaypoint.y * -1, relativeWaypoint.x);
                    relativeWaypoint = newRelativeWaypoint;
                    break;
                case 'R':
                    // Rotate the relative waypoint clockwise 90 degrees about (0,0)
                    newRelativeWaypoint = new Point(relativeWaypoint.y, relativeWaypoint.x * -1);
                    relativeWaypoint = newRelativeWaypoint;
                    break;
            }
        }

        // Update the waypoint to be the relative waypoint's new location shifted back by the correct amount
        waypoint = new Point(relativeWaypoint.x - shiftX, relativeWaypoint.y - shiftY);
    }

    /**
     * Moves the current location to the waypoint the specified number of times
     * <p>
     * Moves the waypoint along with the current location so their
     * relationship remains the same
     */
    private void moveCurrentLocation(int amount)
    {
        // Determine the x and y distance between current location and waypoint
        int xChange = (waypoint.x - currentLocation.x) * amount;
        int yChange = (waypoint.y - currentLocation.y) * amount;

        // Move the current location and waypoint
        currentLocation.x += xChange;
        currentLocation.y += yChange;
        waypoint.x += xChange;
        waypoint.y += yChange;
    }

    @Override
    public Point getCurrentLocation()
    {
        return currentLocation;
    }
}
