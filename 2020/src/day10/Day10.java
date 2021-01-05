package day10;

import java.util.Arrays;
import utilities.FileIO;
import utilities.Test;

public class Day10
{
    /**
     * Returns the product of the counts of 1-joltage and 3-joltage differences
     * found in the sorted input, including the initial port joltage (0) and
     * the device's built-in adapter joltage (3 higher than highest input)
     */
    public static int partA(int[] input)
    {
        input = sortAndAddBounds(input);
        int countDiffOf1 = 0;
        int countDiffOf3 = 0;

        // Check difference between each contiguous pair of numbers in the input
        for (int i = 1; i < input.length; i++)
        {
            int diff  = input[i] - input[i-1];
            if (diff == 1) countDiffOf1++;
            if (diff == 3) countDiffOf3++;
        }

        return countDiffOf1 * countDiffOf3;
    }

    /**
     * Sorts the input (adapter) array and adds the charging outlet and device's built-in
     * adapter values to the beginning and end (respectively) of the input array
     */
    private static int[] sortAndAddBounds(int[] input)
    {
        Arrays.sort(input);
        int[] modifiedInput = new int[input.length + 2];
        modifiedInput[0] = 0; // Charging outlet
        modifiedInput[modifiedInput.length - 1] = input[input.length - 1] + 3; // Device built-in adapter
        System.arraycopy(input, 0, modifiedInput, 1, input.length); // Original input
        return modifiedInput;
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