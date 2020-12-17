/**
 * Solution to Advent of Code 2020, Day 03
 * by Chris Campbell
 */

package day03;

import utilities.FileIO;
import utilities.Test;

public class Day03
{
    /**
     * Counts occurrences of a character on a path
     * through an array of strings
     */
    public int partA(String[] input)
    {
        return countOccurrences(input, '#', 0, 0, 3, 1);
    }

    /**
     * Counts occurrences of a character on a
     * number of paths through an array of strings
     * and multiplies the counts together
     */
    public long partB(String[] input)
    {
        int[] nums = new int[5];

        // Get counts
        nums[0] = countOccurrences(input, '#', 0, 0, 1, 1);
        nums[1] = countOccurrences(input, '#', 0, 0, 3, 1);
        nums[2] = countOccurrences(input, '#', 0, 0, 5, 1);
        nums[3] = countOccurrences(input, '#', 0, 0, 7, 1);
        nums[4] = countOccurrences(input, '#', 0, 0, 1, 2);

        // Multiply all counts together
        long result = 1;
        for (int num : nums)
        {
            result *= num;
        }

        return result;
    }

    /**
     * Counts the occurrences of a specific character within the input array,
     * starting at a specified xy position and moving after each
     * iteration until the end of the array is reached
     */
    public int countOccurrences(String[] input, char searchChar, int startX, int startY, int stepX, int stepY)
    {
        int collisionCount = 0;

        // Initialize working position
        int x = startX;
        int y = startY;

        while (y < input.length)
        {
            // Check for collision
            if (input[y].charAt(x) == searchChar) {
                collisionCount++;
            }

            // Move to next position
            x += stepX;
            y += stepY;

            // Check if new position is out-of-bounds and wrap if necessary
            if (y < input.length && x >= input[y].length()) {
                x -= input[y].length();
            }
        }

        return collisionCount;
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[] testInput = FileIO.readAsStrings("2020/src/day03/Day03TestInput.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day03/Day03Input.txt");

        Day03 day03 = new Day03();

        Test.assertEqual("Day 3 - Part A - Test input", day03.partA(testInput), 7);
        Test.assertEqual("Day 3 - Part A - Challenge input", day03.partA(realInput), 272);
        Test.assertEqual("Day 3 - Part B - Test input", day03.partB(testInput), 336);
        Test.assertEqual("Day 3 - Part B - Challenge input", day03.partB(realInput), 3898725600L);
    }

    public static void main(String[] args)
    {
        run();
    }
}
