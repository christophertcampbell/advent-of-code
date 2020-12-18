package day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import utilities.FileIO;
import utilities.Test;

public class Day04
{
    /**
     * Returns a count of how many passports have the required keys
     */
    public int partA(String[] input)
    {
        int validPassports = 0;
        ArrayList<PassportInfo> passports = parsePassportInfos(input);
        Iterator<PassportInfo> passportIterator = passports.iterator();
        PassportValidator passportValidator = new PassportValidator();

        while (passportIterator.hasNext())
        {
            if (passportValidator.validate(passportIterator.next())) {
                validPassports++;
            }
        }

        return validPassports;
    }

    /**
     * Parses the input into an ArrayList of passport info
     */
    private ArrayList<PassportInfo> parsePassportInfos(String[] input)
    {
        ArrayList<PassportInfo> passports = new ArrayList<PassportInfo>();
        int tailIndex = 0;

        // Loop through the input, allowing the head index to go one place past the end of the array
        // so we can correctly slice off the last group of passport info
        for (int headIndex = 0; headIndex <= input.length; headIndex++)
        {
            // Blank entries are separators between groups of passport info
            // When we encounter one (or the end of the input), use the head and tail
            // indexes to get the current group of passport info before moving on
            if (headIndex == input.length || input[headIndex] == null || input[headIndex].isEmpty()) {
                if (headIndex != tailIndex) {
                    String[] subset = Arrays.copyOfRange(input, tailIndex, headIndex);
                    PassportInfo passportInfo = new PassportInfo(subset);
                    passports.add(passportInfo);
                }

                // Move tail index ahead to where the head index
                // will be on the next iteration, so we can start
                // looking for the next group of passport info
                tailIndex = headIndex + 1;
            }
        }

        return passports;
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[] testInput = FileIO.readAsStrings("2020/src/day04/Day04TestInput.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day04/Day04Input.txt");

        Day04 day04 = new Day04();

        Test.assertEqual("Day 4 - Part A - Test input", day04.partA(testInput), 2);
        Test.assertEqual("Day 4 - Part A - Challenge input", day04.partA(realInput), 204);
    }

    public static void main(String[] args)
    {
        run();
    }
}
