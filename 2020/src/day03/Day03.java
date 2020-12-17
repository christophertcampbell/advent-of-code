/**
 * Solution to Advent of Code 2020, Day 03
 * by Chris Campbell
 */

package day03;

import utilities.FileIO;

public class Day03
{
    public int partA(String[] input)
    {
        return countOccurrences(input, '#', 0, 0, 3, 1);
    }

    /**
     * Counts the occurrences of a specific char within the input array,
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
    
    public static void main(String[] args)
    {
        String[] testInput = FileIO.readAsStrings("2020/src/day03/Day03TestInput.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day03/Day03Input.txt");

        Day03 day03 = new Day03();

        System.out.printf("Day 3 - Part A - Test output: %d : Expected: %d\n", day03.partA(testInput), 7);
        System.out.printf("Day 3 - Part A - Challenge output: %d : Expected: %d\n", day03.partA(realInput), 272);
    }
}
