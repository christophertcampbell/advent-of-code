package day05;

import utilities.FileIO;
import utilities.Test;

public class Day05
{
    /**
     * Returns the highest seat number
     */
    public static int partA(String[] input)
    {
        int maxSeatNumber = Integer.MIN_VALUE;

        for (String line : input)
        {
            int seatNumber = calculateSeatNumber(line);
            if (seatNumber > maxSeatNumber) {
                maxSeatNumber = seatNumber;
            }
        }

        return maxSeatNumber;
    }

    /**
     * Calculates a seat number from the provided binary pattern
     * 
     * @param binaryPattern A binary pattern where <code>B/R</code> characters are high and any other characters are low
     * @return The calculated seat number
     */
    public static int calculateSeatNumber(String binaryPattern)
    {
        int num = 0;
        for (int i = 0; i < binaryPattern.length(); i++)
        {
            if (binaryPattern.charAt(i) == 'B' || binaryPattern.charAt(i) == 'R') {
                // Bit is set high, calculate its power of 2 value and add to the number
                num += Math.pow(2, binaryPattern.length() - i - 1);
            }
        }
        return num;
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[] testInput = FileIO.readAsStrings("2020/src/day05/Day05TestInput.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day05/Day05Input.txt");

        Test.assertEqual("Day 5 - Part A - Column Parsing", calculateSeatNumber("RLR"), 5);
        Test.assertEqual("Day 5 - Part A - Column Parsing", calculateSeatNumber("RRR"), 7);
        Test.assertEqual("Day 5 - Part A - Column Parsing", calculateSeatNumber("RLL"), 4);

        Test.assertEqual("Day 5 - Part A - Row Parsing", calculateSeatNumber("FBFBBFF"), 44);
        Test.assertEqual("Day 5 - Part A - Row Parsing", calculateSeatNumber("BFFFBBF"), 70);
        Test.assertEqual("Day 5 - Part A - Row Parsing", calculateSeatNumber("FFFBBBF"), 14);
        Test.assertEqual("Day 5 - Part A - Row Parsing", calculateSeatNumber("BBFFBBF"), 102);

        Test.assertEqual("Day 5 - Part A - Seat ID Parsing", calculateSeatNumber("FBFBBFFRLR"), 357);
        Test.assertEqual("Day 5 - Part A - Seat ID Parsing", calculateSeatNumber("BFFFBBFRRR"), 567);
        Test.assertEqual("Day 5 - Part A - Seat ID Parsing", calculateSeatNumber("FFFBBBFRRR"), 119);
        Test.assertEqual("Day 5 - Part A - Seat ID Parsing", calculateSeatNumber("BBFFBBFRLL"), 820);

        Test.assertEqual("Day 5 - Part A - Test input", partA(testInput), 820);
        Test.assertEqual("Day 5 - Part A - Challenge input", partA(realInput), 842);
    }

    public static void main(String[] args)
    {
        run();
    }
}