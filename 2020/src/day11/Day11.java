package day11;

import utilities.FileIO;
import utilities.Test;

public class Day11
{
    /**
     * Returns a count of occupied seats after the
     * seat layout has reached a state of equilibrium
     */
    public static int partA(String[] input)
    {
        SeatLayoutGameOfLife seatLayout = new SeatLayoutGameOfLife(input);
        return seatLayout.run();
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[] testInput = FileIO.readAsStrings("2020/src/day11/Day11TestInput.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day11/Day11Input.txt");

        Test.assertEqual("Day 11 - Part A - Test input", partA(testInput), 37);
        Test.assertEqual("Day 11 - Part A - Challenge input", partA(realInput), 2263);
    }

    public static void main(String[] args)
    {
        run();
    }
}