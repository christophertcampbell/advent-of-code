package day12;

import java.awt.Point;

/**
 * Navigation module for performaing navigation
 * based on a set of instructions
 */
public interface NavModule
{
    public void navigate(NavInstruction[] input);
    public Point getCurrentLocation();
}
