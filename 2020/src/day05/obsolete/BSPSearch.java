package day05.obsolete;

/**
 * Binary space partitioning (BSP) search handler
 * 
 * This was my initial solution. I re-did my solution as a shorter
 * method within Day05.java after recognizing that the search pattern
 * is a simple binary pattern whose value can be easily calculated
 * (thank you Steve for the suggestion to re-examine the challenge
 * from the perspective of binary numbers!)
 */
public class BSPSearch
{
    /**
     * Performs a binary search and returns the matching number
     * 
     * @param searchPattern A string composed of one or more letters indicating left (<code>F or L</code>) or right (<code>B or R</code>) in the search range
     * @return The matching number, or <code>Integer.MIN_VALUE</code> if unsuccessful
     */
    public int find(String searchPattern, int lowerBound, int upperBound)
    {
        for (int i = 0; i < searchPattern.length(); i++)
        {
            int diff = upperBound - lowerBound;

            // If difference is odd, shift by 1 to get to the correct side of the mid point
            int shiftIfOdd = (diff % 2 == 0) ? 0 : 1;

            char searchChar = searchPattern.charAt(i);
            if (searchChar == 'L' || searchChar == 'F') {
                upperBound = upperBound - (diff / 2) - shiftIfOdd;
            } else {
                lowerBound = lowerBound + (diff / 2) + shiftIfOdd;
            }
        }

        return lowerBound == upperBound ? lowerBound : Integer.MIN_VALUE;
    }
}
