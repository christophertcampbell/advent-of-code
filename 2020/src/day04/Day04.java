package day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utilities.FileIO;
import utilities.Test;

public class Day04
{
    private static final Pattern INPUT_PARSE_PATTERN = Pattern.compile("(\\S+):(\\S+)", Pattern.CASE_INSENSITIVE);
    private static final String[] REQUIRED_KEYS = new String[] {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

    /**
     * Returns a count of how many passports have the required keys
     */
    public int partA(String[] input)
    {
        int validPassports = 0;
        ArrayList<HashMap<String, String>> passports = parsePassportInfos(input);
        Iterator<HashMap<String, String>> passportIterator = passports.iterator();

        while (passportIterator.hasNext())
        {
            if (hasRequiredKeys(passportIterator.next())) {
                validPassports++;
            }
        }

        return validPassports;
    }

    /**
     * Parses the input into an ArrayList of passport info
     */
    private ArrayList<HashMap<String, String>> parsePassportInfos(String[] input)
    {
        ArrayList<HashMap<String, String>> passports = new ArrayList<HashMap<String, String>>();
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
                    HashMap<String, String> passportInfo = constructPassportInfo(subset);
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
     * Constructs a HashMap of passport info from a subset of the original input
     */
    private HashMap<String, String> constructPassportInfo(String[] subset)
    {
        HashMap<String, String> passportInfo = new HashMap<String, String>();
        Matcher matcher;
        for (String str : subset)
        {
            matcher = INPUT_PARSE_PATTERN.matcher(str);
            while (matcher.find()) {
                // Group 1 is the key, Group 2 is the value
                passportInfo.put(matcher.group(1), matcher.group(2));
            }
        }

        return passportInfo;
    }

    /**
     * Validates whether the passport info contains the required keys
     */
    private boolean hasRequiredKeys(HashMap<String, String> passportInfo)
    {
        for (String key : REQUIRED_KEYS)
        {
            if (passportInfo.containsKey(key) == false) {
                return false; // Missing a required key
            }
        }

        return true;
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
