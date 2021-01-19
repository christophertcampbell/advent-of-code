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
     * Returns the product of all values for fields in "your ticket" which
     * contain the search term within the field name
     */
    public static long partB(String[] input, String fieldNameSearchTerm)
    {
        TicketAnalyzer ticketAnalyzer = new TicketAnalyzer(input);
        return ticketAnalyzer.getProductOfFields(fieldNameSearchTerm);
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[] testInputPartA = FileIO.readAsStrings("2020/src/day16/Day16TestInputPartA.txt");
        String[] testInputPartB = FileIO.readAsStrings("2020/src/day16/Day16TestInputPartB.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day16/Day16Input.txt");

        Test.assertEqual("Day 16 - Part A - Test input", partA(testInputPartA), 71);
        Test.assertEqual("Day 16 - Part A - Challenge input", partA(realInput), 19087);
        Test.assertEqual("Day 16 - Part B - Test input", partB(testInputPartB, "row"), 11);
        Test.assertEqual("Day 16 - Part B - Test input", partB(testInputPartB, "class"), 12);
        Test.assertEqual("Day 16 - Part B - Test input", partB(testInputPartB, "seat"), 13);
        Test.assertEqual("Day 16 - Part B - Challenge input", partB(realInput, "departure"), 1382443095281L);
    }

    public static void main(String[] args)
    {
        run();
    }
}