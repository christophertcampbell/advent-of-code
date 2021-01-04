package day09;

import utilities.FileIO;
import utilities.Test;

public class Day09
{
    /**
     * Returns the first number in the input which is not a
     * sum of two numbers within its preamble
     */
    public static long partA(long[] input, int preambleLength)
    {
        return XMASCodeAnalyzer.findFirstInvalidNumber(input, preambleLength);
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        long[] testInput = FileIO.readAsLongs("2020/src/day09/Day09TestInput.txt");
        long[] realInput = FileIO.readAsLongs("2020/src/day09/Day09Input.txt");

        Test.assertEqual("Day 9 - Part A - Test input", partA(testInput, 5), 127);
        Test.assertEqual("Day 9 - Part A - Challenge input", partA(realInput, 25), 133015568);
    }

    public static void main(String[] args)
    {
        run();
    }
}