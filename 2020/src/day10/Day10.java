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
     * Returns the total number of distinct ways to combine the input numbers
     * in their same relative order, without exceeding a difference of 3 between
     * consequtive numbers, and still arrive at the same final value
     */
    public static long partB(int[] input)
    {
        /**
         * Analysis:
         * 
         * All differences between consecutive numbers in the input are either 1 or 3.
         * Therefore, by recognizing the finite set of patterns we are working with,
         * we can figure out how many possible permutations each pattern represents.
         * 
         * By multiplying all sections' possible permutations together, we arrive
         * at the total number of permutations possible.
         * 
         * Example:
         * 0   3  4*  5*  6   9   <- input (* = can remove without exceeding a difference of 3)
         *  \ / \ / \ / \ / \ /
         *   3   1   1   1   3    <- differences
         * 
         * Following are the patterns of differences and their equivalent multipliers
         * (representing the number of permutations possible without exceeding
         * a difference of 3 between consecutive numbers):
         * 
         * 33     - 1 base permutation + 0 variations = 1
         * 313    - 1 base permutation + 0 variations = 1
         * 3113   - 1 base permutation + 1 variation  = 2
         * 31113  - 1 base permutation + 3 variations = 4
         * 311113 - 1 base permutation + 6 variations = 7
         * 
         * Example:
         * 311331113 -> [3113][31113] -> 2 x 4 = 8 distinct permutations
         */

        input = sortAndAddBounds(input);

        /**
         * Create an array with values which represent the number
         * of permutations possible for x number of differences of 1
         * occurring between differences of 3 (x being the array index)
         */
        int[] onesCountMultipliers = {1, 1, 2, 4, 7};

        int onesCount = 0;
        long permutations = 1;
        
        for (int i = 1; i < input.length; i++)
        {
            int diff  = input[i] - input[i-1];
            if (diff == 1) {
                onesCount++;
            } else if (diff == 3) {
                permutations *= onesCountMultipliers[onesCount];
                onesCount = 0;
            }
        }

        return permutations;
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
        int[] testInput1 = FileIO.readAsInts("2020/src/day10/Day10TestInput1.txt");
        int[] testInput2 = FileIO.readAsInts("2020/src/day10/Day10TestInput2.txt");
        int[] realInput = FileIO.readAsInts("2020/src/day10/Day10Input.txt");

        Test.assertEqual("Day 10 - Part A - Test input #1", partA(testInput1), 35);
        Test.assertEqual("Day 10 - Part A - Test input #2", partA(testInput2), 220);
        Test.assertEqual("Day 10 - Part A - Challenge input", partA(realInput), 2170);
        Test.assertEqual("Day 10 - Part B - Test input #1", partB(testInput1), 8);
        Test.assertEqual("Day 10 - Part B - Test input #2", partB(testInput2), 19208);
        Test.assertEqual("Day 10 - Part B - Challenge input", partB(realInput), 24803586664192L);
    }

    public static void main(String[] args)
    {
        run();
    }
}