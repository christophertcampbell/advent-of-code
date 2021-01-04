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
     * Returns the encryption weakness (smallest + largest number) of the
     * subarray whose sum equals the target sum
     */
    public static long getEncryptionWeakness(long[] input, long targetSum)
    {
        long[] subArray = findContiguousSubArrayWithSum(input, targetSum);
        long lowestNum = Long.MAX_VALUE;
        long highestNum = Long.MIN_VALUE;

        for (long num : subArray)
        {
            if (num < lowestNum) lowestNum = num;
            if (num > highestNum) highestNum = num;
        }

        return lowestNum + highestNum;
    }

    /**
     * Returns the contiguous subarray whose sum equals the target sum,
     * or an empty array if no match is found
     */
    private static long[] findContiguousSubArrayWithSum(long[] input, long targetSum)
    {
        // Begin with a starting current sum of just the first number
        int tailPointer = 0;
        int headPointer = 0;
        long currentSum = input[0];


        // Step through the input, adding each number to the current sum and checking for a match
        while (headPointer < input.length && currentSum != targetSum)
        {
            // Advance the head pointer and the current sum
            headPointer++;
            currentSum += input[headPointer];

            // If the new current sum is larger than the current sum, remove numbers
            // from the beginning of the subarray and advance the tail pointer
            while (currentSum > targetSum)
            {
                currentSum -= input[tailPointer];
                tailPointer++;
            }

            // If we have found a match, stop looking
            if (currentSum == targetSum) {
                break;
            }
        }

        return currentSum == targetSum
            ? Arrays.copyOfRange(input, tailPointer, headPointer + 1)
            : new long[0];
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
