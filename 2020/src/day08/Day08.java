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
     * Returns the value of the accumulator once the op code
     * has run, including fixing the infinite loop
     */
    public static int partB(String[] input)
    {
        return OpCodeAnalyzer.runWithInifiniteLoopFix(input);
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
        Test.assertEqual("Day 8 - Part B - Test input", partB(testInput), 8);
        Test.assertEqual("Day 8 - Part B - Challenge input", partB(realInput), 1260);
    }

    public static void main(String[] args)
    {
        run();
    }
}