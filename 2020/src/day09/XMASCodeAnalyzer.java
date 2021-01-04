package day09;

import java.util.Arrays;

public class XMASCodeAnalyzer
{
    /**
     * Returns the first number in the input which is not a
     * sum of two numbers within its preamble
     */
    public static long findFirstInvalidNumber(long[] input, int preambleLength)
    {
        int pointer = preambleLength;
        long[] preamble;
        boolean foundMatch;

        /**
         * Loop through each input (excluding the initial preamble)
         * and look for a number which is not the sum of two numbers
         * within its preamble
         */
        while (pointer < input.length)
        {
            preamble = Arrays.copyOfRange(input, pointer - preambleLength, pointer);
            foundMatch = false;
            
            // Search preamble for two numbers whose sum equals the target number
            for (long num1 : preamble)
            {
                long num2 = input[pointer] - num1;
                if (num1 != num2 && arrayContains(preamble, num2)) {
                    foundMatch = true;
                    break;
                }
            }

            // If we found a match we can continue on to the next input
            if (foundMatch) {
                pointer++;
                continue;
            }

            // Did not find a match, this is the number we are looking for
            return input[pointer];
        }

        return -1;
    }

    /**
     * returns true if the array contains the specified number, false otherwise
     */
    private static boolean arrayContains(long[] nums, long searchNum)
    {
        for (long num : nums)
        {
            if (num == searchNum) {
                return true;
            }
        }
        return false;
    }
}
