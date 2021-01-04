package day10;

import java.util.Arrays;
import utilities.FileIO;
import utilities.Test;

public class Day10
{
    /**
     * Returns the product of the counts of 1-joltage and 3-joltage differences
     * found in the sorted input, including the initial port joltage (0) and
     * final device joltage
     */
    public static int partA(int[] input)
    {
        Arrays.sort(input);
        int countDiffOf1 = 0;
        int countDiffOf3 = 0;
        int diff;

        // Check difference between port joltage (0) and first joltage in input
        diff = input[0] - 0;
        if (diff == 1) countDiffOf1++;
        if (diff == 3) countDiffOf3++;

        // Check difference between each contiguous pair of numbers in the input
        for (int i = 1; i < input.length; i++)
        {
            diff  = input[i] - input[i-1];
            if (diff == 1) countDiffOf1++;
            if (diff == 3) countDiffOf3++;
        }

        countDiffOf3++; // Device joltage is 3 higher than highest number in input

        return countDiffOf1 * countDiffOf3;
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        int[] testInput = FileIO.readAsInts("2020/src/day10/Day10TestInput.txt");
        int[] realInput = FileIO.readAsInts("2020/src/day10/Day10Input.txt");

        Test.assertEqual("Day 10 - Part A - Test input", partA(testInput), 220);
        Test.assertEqual("Day 10 - Part A - Challenge input", partA(realInput), 2170);
    }

    public static void main(String[] args)
    {
        run();
    }
}