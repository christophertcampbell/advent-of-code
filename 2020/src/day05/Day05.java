package day05;

import java.util.Arrays;
import utilities.FileIO;
import utilities.Test;

public class Day05
{
    private static final BSPSearch rows = new BSPSearch(0, 127);
    private static final BSPSearch columns = new BSPSearch(0, 7);

    /**
     * Returns the highest seat number
     */
    public static int partA(String[] input)
    {
        int maxSeatNumber = Integer.MIN_VALUE;

        for (String line : input)
        {
            if (line.length() != 10) {
                continue;
            }

            int rowNumber = rows.find(line.substring(0, 7));
            int colNumber = columns.find(line.substring(7, 10));
            int seatNumber = (rowNumber * 8) + colNumber;
            if (seatNumber > maxSeatNumber) {
                maxSeatNumber = seatNumber;
            }
        }

        return maxSeatNumber;
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[] testInput = FileIO.readAsStrings("2020/src/day05/Day05TestInput.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day05/Day05Input.txt");

        Test.assertEqual("Day 5 - Part A - Column Parsing", columns.find("RLR"), 5);
        Test.assertEqual("Day 5 - Part A - Column Parsing", columns.find("RRR"), 7);
        Test.assertEqual("Day 5 - Part A - Column Parsing", columns.find("RLL"), 4);

        Test.assertEqual("Day 5 - Part A - Row Parsing", rows.find("FBFBBFF"), 44);
        Test.assertEqual("Day 5 - Part A - Row Parsing", rows.find("BFFFBBF"), 70);
        Test.assertEqual("Day 5 - Part A - Row Parsing", rows.find("FFFBBBF"), 14);
        Test.assertEqual("Day 5 - Part A - Row Parsing", rows.find("BBFFBBF"), 102);

        Test.assertEqual("Day 5 - Part A - Seat ID Parsing", partA(Arrays.copyOfRange(testInput, 0, 1)), 357);
        Test.assertEqual("Day 5 - Part A - Seat ID Parsing", partA(Arrays.copyOfRange(testInput, 1, 2)), 567);
        Test.assertEqual("Day 5 - Part A - Seat ID Parsing", partA(Arrays.copyOfRange(testInput, 2, 3)), 119);
        Test.assertEqual("Day 5 - Part A - Seat ID Parsing", partA(Arrays.copyOfRange(testInput, 3, 4)), 820);

        Test.assertEqual("Day 5 - Part A - Test input", partA(testInput), 820);
        Test.assertEqual("Day 5 - Part A - Challenge input", partA(realInput), 842);
    }

    public static void main(String[] args)
    {
        run();
    }
}