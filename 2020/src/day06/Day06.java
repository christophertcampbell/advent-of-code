package day06;

import java.util.HashSet;
import java.util.HashMap;
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
     * Returns a sum of all groups' common characters
     * (counts characters which are common in all lines within each group)
     */
    public static int partB(String[][] input)
    {
        int count = 0;
        for (String[] group : input)
        {
            count += countCommonChars(group);
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
     * Returns a count of unique chars which all strings share in common
     */
    private static int countCommonChars(String[] input)
    {
        HashMap<Character, Integer> charCounts = new HashMap<Character, Integer>();

        // Build a count of how many lines each character appears in within the group
        for (String line : input)
        {
            String encounteredChars = "";
            for (char c : line.toCharArray())
            {
                // Don't count the character again if we have already
                // encountered it within this line
                if (encounteredChars.contains(String.valueOf(c))) {
                    continue;
                }

                encounteredChars += c;
                if (charCounts.containsKey(c)) {
                    charCounts.put(c, charCounts.get(c) + 1);
                } else {
                    charCounts.put(c, 1);
                }
            }
        }

        // Count how many characters are common among all lines in the group
        int count = 0;
        for (HashMap.Entry<Character, Integer> charCount : charCounts.entrySet())
        {
            // If a character's count is the same as the length of the group,
            // then all lines within the group share the character in common
            if (charCount.getValue() == input.length) {
                count++;
            }
        }

        return count;
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
        Test.assertEqual("Day 6 - Part B - Test input", partB(testInput), 6);
        Test.assertEqual("Day 6 - Part B - Challenge input", partB(realInput), 3382);
    }

    public static void main(String[] args)
    {
        run();
    }
}