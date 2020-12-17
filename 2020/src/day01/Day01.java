/*
 * Solution to Advent of Code 2020, Day 01
 * by Chris Campbell
 */

package day01;

import java.util.Arrays;
import utilities.FileIO;
import utilities.Test;

public class Day01
{
    /*
     * Search an array of integers for two numbers
     * whose sum is 2020, and return the product of
     * the two numbers
     */
    public int partA(int[] nums)
    {      
        return getProductOfTwoNumbersWhoseSumEquals(nums, 2020);
    }

    /*
     * Search an array of integers for three numbers
     * whose sum is 2020, and return the product of
     * the three numbers
     */
    public int partB(int[] nums)
    {
        for (int num : nums)
        {
            /*
             * For each number, determine the matching addend
             * which would equal 2020 and then check if there
             * are two numbers in the array which sum up to
             * equal the matching addend
             */
            int matchingNum = 2020 - num;
            int matchingProduct = getProductOfTwoNumbersWhoseSumEquals(nums, matchingNum);
            if (matchingProduct > 0) {
                return num * matchingProduct;
            }
        }

        return -1;
    }

    /*
     * Searches an array for two numbers whose sum equals the target sum
     * Returns the product of the two numbers, or -1 if a match is not found
     */
    private int getProductOfTwoNumbersWhoseSumEquals(int[] nums, int targetSum)
    {
        // Sort the array so we can use a binary search
        Arrays.sort(nums);

        for (int num : nums)
        {
            /*
             * If the complementary number is present in the array,
             * return the product
             */
            int matchingNum = targetSum - num;
            if (Arrays.binarySearch(nums, matchingNum) >= 0) {
                return num * matchingNum;
            }
        }
        
        return -1;
    }
  
    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        int[] testInput = FileIO.readAsInts("2020/src/Day01/Day01TestInput.txt");
        int[] realInput = FileIO.readAsInts("2020/src/Day01/Day01Input.txt");

        Day01 day01 = new Day01();
        
        Test.assertEqual("Day 1 - Part A - Test input", day01.partA(testInput), 514579);
        Test.assertEqual("Day 1 - Part A - Challenge input", day01.partA(realInput), 692916);
        Test.assertEqual("Day 1 - Part B - Test input", day01.partB(testInput), 241861950);
        Test.assertEqual("Day 1 - Part B - Challenge input", day01.partB(realInput), 289270976);
    }

    public static void main(String[] args)
    {
        run();
    }
}