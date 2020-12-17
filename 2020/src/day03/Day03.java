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
        int collisionCount = 0;
        int width = input[0].length();

        // Starting position
        int x = 0;
        int y = 0;

        // Distance to move each iteration
        int stepX = 3;
        int stepY = 1;

        while (y < input.length)
        {
            // Check for collision
            if (input[y].charAt(x) == '#') {
                collisionCount++;
            }

            // Move to next position
            x += stepX;
            y += stepY;

            // Check if new position is out-of-bounds and wrap if necessary
            if (x >= width) {
                x -= width;
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
