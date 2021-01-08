package day12;

import utilities.FileIO;
import utilities.Test;

public class Day12
{
    /**
     * Returns the manhattan distance between the
     * start point and the end point after navigating
     */
    public static int partA(String[] input)
    {
        NavModule navModule = new NavModulePartA();
        NavComputer navComputer = new NavComputer(navModule);
        navComputer.navigate(input);
        return navComputer.getManhattanDistanceFromStart();
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[] testInput = FileIO.readAsStrings("2020/src/day12/Day12TestInput.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day12/Day12Input.txt");

        Test.assertEqual("Day 12 - Part A - Test input", partA(testInput), 25);
        Test.assertEqual("Day 12 - Part A - Challenge input", partA(realInput), 1601);
    }

    public static void main(String[] args)
    {
        run();
    }
}