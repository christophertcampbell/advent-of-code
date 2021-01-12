package day15;

import java.util.HashMap;
import utilities.FileIO;
import utilities.Test;

public class Day15
{
    public static int partA(int[] nums)
    {
        return runPattern(nums, 2020);
    }

    public static int partB(int[] nums)
    {
        return runPattern(nums, 30000000);
    }

    /**
     * Navigates a pattern of numbers, deciding the next number
     * based upon whether the previous number has occurred
     * previously or not. Returns the final number.
     */
    private static int runPattern(int[] nums, int targetIndex)
    {
        HashMap<Integer, Integer> history = new HashMap<>();
        int nextNum = 0;
        int nextIndex = nums.length + 1;

        // Build the beginning history of what index
        // each starting number was found at
        for (int i = 0; i < nums.length; i++)
        {
            history.put(nums[i], i + 1);
        }

        // Loop until we have evaluated the pattern up to the target index
        while (nextIndex < targetIndex)
        {
            if (history.containsKey(nextNum)) {
                // Number has occurred previously
                int newNextNum = nextIndex - history.get(nextNum); // Difference from when the number occurred last
                history.put(nextNum, nextIndex); // Update the number's history to the current index
                nextNum = newNextNum;
            } else {
                // Number has not occurred yet
                history.put(nextNum, nextIndex);
                nextNum = 0;
            }
            nextIndex++;
        }

        return nextNum;
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        int[][] testInput = FileIO.readAsCommaSeparatedInts("2020/src/day15/Day15TestInput.txt");
        int[][] realInput = FileIO.readAsCommaSeparatedInts("2020/src/day15/Day15Input.txt");

        Test.assertEqual("Day 15 - Part A - Test input #1", partA(testInput[0]), 436);
        Test.assertEqual("Day 15 - Part A - Test input #2", partA(testInput[1]), 1);
        Test.assertEqual("Day 15 - Part A - Test input #3", partA(testInput[2]), 10);
        Test.assertEqual("Day 15 - Part A - Test input #4", partA(testInput[3]), 27);
        Test.assertEqual("Day 15 - Part A - Test input #5", partA(testInput[4]), 78);
        Test.assertEqual("Day 15 - Part A - Test input #6", partA(testInput[5]), 438);
        Test.assertEqual("Day 15 - Part A - Test input #7", partA(testInput[6]), 1836);
        Test.assertEqual("Day 15 - Part A - Challenge input", partA(realInput[0]), 662);

        Test.assertEqual("Day 15 - Part B - Test input #1", partB(testInput[0]), 175594);
        Test.assertEqual("Day 15 - Part B - Test input #2", partB(testInput[1]), 2578);
        Test.assertEqual("Day 15 - Part B - Test input #3", partB(testInput[2]), 3544142);
        Test.assertEqual("Day 15 - Part B - Test input #4", partB(testInput[3]), 261214);
        Test.assertEqual("Day 15 - Part B - Test input #5", partB(testInput[4]), 6895259);
        Test.assertEqual("Day 15 - Part B - Test input #6", partB(testInput[5]), 18);
        Test.assertEqual("Day 15 - Part B - Test input #7", partB(testInput[6]), 362);
        Test.assertEqual("Day 15 - Part B - Challenge input", partB(realInput[0]), 37312);
    }

    public static void main(String[] args)
    {
        run();
    }
}