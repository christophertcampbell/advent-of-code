package day12;

import java.awt.Point;

/**
 * A navigation module handles the logic
 * for navigating based on a set of instructions
 */
public interface NavModule
{
    public void navigate(NavInstruction[] input);
    public Point getCurrentLocation();
}
