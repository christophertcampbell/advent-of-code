/**
 * Solution to Advent of Code 2020, Day 02
 * by Chris Campbell
 */

package day02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utilities.FileIO;

public class Day02
{
	/**
	 * Regex pattern for parsing the challenge input
	 */
	private static final Pattern PARSE_PATTERN = Pattern.compile("^(\\d+)-(\\d+)\\s(\\w):\\s(\\w+)$", Pattern.CASE_INSENSITIVE);

	/**
	 * Returns a count of how many of the provided password records are valid
	 */
	public int partA(String[] input)
	{
		int count = 0;
		for (String entry : input)
		{
			PasswordRecord passwordRecord = parseRecord(entry);
			if (passwordRecord != null && passwordRecord.validate()) {
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
			int minCount = Integer.parseInt(matcher.group(1));
			int maxCount = Integer.parseInt(matcher.group(2));
			char searchChar = matcher.group(3).charAt(0);
			String password = matcher.group(4);
			return new PasswordRecord(minCount, maxCount, searchChar, password);
		}

		return null;
	}

	/**
     * Runs the day's solutions
	 */
	public static void main(String[] args)
	{
		String[] testInput = {"1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc"};
		String[] realInput = FileIO.readAsStrings("Day02Input.txt");

		Day02 day02 = new Day02();

        System.out.printf("Day 2 - Part A - Test output: %d : Expected: %d\n", day02.partA(testInput), 2);
        System.out.printf("Day 2 - Part A - Challenge output: %d : Expected: %d\n", day02.partA(realInput), 447);
	}
}