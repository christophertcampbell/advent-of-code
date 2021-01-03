package day08;

import utilities.FileIO;
import utilities.Test;

public class Day08
{
    /**
     * Returns the value of the accumulator once an
     * infinite loop is detected in the op code
     */
    public static int partA(String[] input)
    {
        return OpCodeAnalyzer.runUntilInfiniteLoopDetected(input);
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[] testInput = FileIO.readAsStrings("2020/src/day08/Day08TestInput.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day08/Day08Input.txt");

        Test.assertEqual("Day 8 - Part A - Test input", partA(testInput), 5);
        Test.assertEqual("Day 8 - Part A - Challenge input", partA(realInput), 1614);
    }

    public static void main(String[] args)
    {
        run();
    }
}