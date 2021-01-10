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

    public static long partB(String busIDsCommaSeparated)
    {
        BusSchedule busSchedule = new BusSchedule(busIDsCommaSeparated);
        return busSchedule.getEarliestSynchronizedTime();
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[] testInput = FileIO.readAsStrings("2020/src/day13/Day13TestInput.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day13/Day13Input.txt");

        Test.assertEqual("Day 13 - Part A - Test input #1", partA(testInput), 295);
        Test.assertEqual("Day 13 - Part A - Challenge input", partA(realInput), 3246);
        Test.assertEqual("Day 13 - Part B - Test input #1", partB(testInput[1]), 1068781);
        Test.assertEqual("Day 13 - Part B - Test input #2", partB(testInput[2]), 3417);
        Test.assertEqual("Day 13 - Part B - Test input #3", partB(testInput[3]), 754018);
        Test.assertEqual("Day 13 - Part B - Test input #4", partB(testInput[4]), 779210);
        Test.assertEqual("Day 13 - Part B - Test input #5", partB(testInput[5]), 1261476);
        Test.assertEqual("Day 13 - Part B - Test input #6", partB(testInput[6]), 1202161486);
        Test.assertEqual("Day 13 - Part B - Challenge input", partB(realInput[1]), 1010182346291467L);
    }

    public static void main(String[] args)
    {
        run();
    }
}