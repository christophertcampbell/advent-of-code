package day09;

import utilities.FileIO;
import utilities.Test;

public class Day09
{
    /**
     * Returns the first number in the input which is not a
     * sum of two numbers within its preamble
     */
    public static long partA(long[] input, int preambleLength)
    {
        return XMASCodeAnalyzer.findFirstInvalidNumber(input, preambleLength);
    }

    /**
     * Returns the encryption weakness (smallest + largest number) of the
     * subarray whose sum equals the invalid number
     */
    public static long partB(long[] input, int preambleLength)
    {
        long invalidNum = XMASCodeAnalyzer.findFirstInvalidNumber(input, preambleLength);
        return XMASCodeAnalyzer.getEncryptionWeakness(input, invalidNum);
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        long[] testInput = FileIO.readAsLongs("2020/src/day09/Day09TestInput.txt");
        long[] realInput = FileIO.readAsLongs("2020/src/day09/Day09Input.txt");

        Test.assertEqual("Day 9 - Part A - Test input", partA(testInput, 5), 127);
        Test.assertEqual("Day 9 - Part A - Challenge input", partA(realInput, 25), 133015568);
        Test.assertEqual("Day 9 - Part B - Test input", partB(testInput, 5), 62);
        Test.assertEqual("Day 9 - Part B - Challenge input", partB(realInput, 25), 16107959);
    }

    public static void main(String[] args)
    {
        run();
    }
}