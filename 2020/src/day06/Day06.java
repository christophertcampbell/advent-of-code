package day06;

import java.util.HashSet;
import utilities.FileIO;
import utilities.Test;

public class Day06
{
    /**
     * Returns a sum of all groups' unique characters
     * (counts unique characters in each group)
     */
    public static int partA(String[][] input)
    {
        int count = 0;
        for (String[] group : input)
        {
            count += countUniqueChars(group);
        }
        return count;
    }

    /**
     * Returns a count of unique chars in a group of strings
     */
    private static int countUniqueChars(String[] input)
    {
        HashSet<Character> uniqueChars = new HashSet<Character>();
        for (String line : input)
        {
            for (char c : line.toCharArray())
            {
                uniqueChars.add(c);
            }
        }
        return uniqueChars.size();
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[][] testInput = FileIO.readAsGroupsOfStrings("2020/src/day06/Day06TestInput.txt");
        String[][] realInput = FileIO.readAsGroupsOfStrings("2020/src/day06/Day06Input.txt");

        Test.assertEqual("Day 6 - Part A - Test input", partA(testInput), 11);
        Test.assertEqual("Day 6 - Part A - Challenge input", partA(realInput), 6662);
    }

    public static void main(String[] args)
    {
        run();
    }
}