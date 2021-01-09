package day13;

import utilities.FileIO;
import utilities.Test;

public class Day13
{
    public static int partA(String[] input)
    {
        int targetTime = Integer.parseInt(input[0]);
        String busIDsCommaSeparated = input[1];
        BusSchedule busSchedule = new BusSchedule(busIDsCommaSeparated);
        return busSchedule.getEarliestBusMultiple(targetTime);
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[] testInput = FileIO.readAsStrings("2020/src/day13/Day13TestInput.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day13/Day13Input.txt");

        Test.assertEqual("Day 13 - Part A - Test input", partA(testInput), 295);
        Test.assertEqual("Day 13 - Part A - Challenge input", partA(realInput), 3246);
    }

    public static void main(String[] args)
    {
        run();
    }
}