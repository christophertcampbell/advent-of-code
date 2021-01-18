package day16;

import utilities.FileIO;
import utilities.Test;

public class Day16
{
    /**
     * Returns the sum of all invalid numbers on nearby tickets
     * according to rules that are parsed from the beginning of the input
     */
    public static int partA(String[] input)
    {
        TicketAnalyzer ticketAnalyzer = new TicketAnalyzer(input);
        return ticketAnalyzer.getScanningErrorRate();
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[] testInput = FileIO.readAsStrings("2020/src/day16/Day16TestInput.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day16/Day16Input.txt");

        Test.assertEqual("Day 16 - Part A - Test input", partA(testInput), 71);
        Test.assertEqual("Day 16 - Part A - Challenge input", partA(realInput), 19087);
    }

    public static void main(String[] args)
    {
        run();
    }
}