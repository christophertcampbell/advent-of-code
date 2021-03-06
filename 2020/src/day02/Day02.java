/**
 * Solution to Advent of Code 2020, Day 02
 * by Chris Campbell
 */

package day02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utilities.FileIO;
import utilities.Test;

public class Day02
{
    /**
     * Regex pattern for parsing the challenge input
     */
    private static final Pattern PARSE_PATTERN = Pattern.compile("^(\\d+)-(\\d+)\\s(\\w):\\s(\\w+)$", Pattern.CASE_INSENSITIVE);

    /**
     * Returns a count of how many passwords are valid by method 1
     */
    public int partA(String[] input)
    {
        PasswordValidatorMethod1 validator = new PasswordValidatorMethod1();
        return countValidPasswords(input, validator);
    }

    /**
     * Returns a count of how many passwords are valid by method 2
     */
    public int partB(String[] input)
    {
        PasswordValidatorMethod2 validator = new PasswordValidatorMethod2();
        return countValidPasswords(input, validator);
    }

    /**
     * Returns a count of how many passwords are valid
     */
    private int countValidPasswords(String[] input, PasswordValidator validator)
    {
        int count = 0;
        for (String entry : input)
        {
            PasswordRecord passwordRecord = parseRecord(entry);
            if (validator.validate(passwordRecord)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Parses a single line of input into a PasswordRecord instance
     * 
     * Returns null if unable to parse
     */
    private PasswordRecord parseRecord(String entry)
    {
        Matcher matcher = PARSE_PATTERN.matcher(entry);
        if (matcher.find()) {
            int index1 = Integer.parseInt(matcher.group(1));
            int index2 = Integer.parseInt(matcher.group(2));
            char validationChar = matcher.group(3).charAt(0);
            String password = matcher.group(4);
            return new PasswordRecord(password, validationChar, index1, index2);
        }

        return null;
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[] testInput = FileIO.readAsStrings("2020/src/day02/Day02TestInput.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day02/Day02Input.txt");

        Day02 day02 = new Day02();

        Test.assertEqual("Day 2 - Part A - Test input", day02.partA(testInput), 2);
        Test.assertEqual("Day 2 - Part A - Challenge input", day02.partA(realInput), 447);
        Test.assertEqual("Day 2 - Part B - Test input", day02.partB(testInput), 1);
        Test.assertEqual("Day 2 - Part B - Challenge input", day02.partB(realInput), 249);
    }

    public static void main(String[] args)
    {
        run();
    }
}