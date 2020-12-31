package day05;

/**
 * Binary space partitioning (BSP) search handler
 */
public class BSPSearch
{
    private int lowerBound;
    private int upperBound;

    /**
     * Constructs a new binary space partitioning (BSP) search handler with specific upper and lower bounds
     *
     * @param lowerBound The lower bound of the search range
     * @param upperBound The upper bound of the search range
     */
    public BSPSearch(int lowerBound, int upperBound)
    {
        // Make sure lower and upper bounds are in correct relation
        this.lowerBound = lowerBound < upperBound ? lowerBound : upperBound;
        this.upperBound = lowerBound < upperBound ? upperBound : lowerBound;
    }

    /**
     * Performs a binary search and returns the matching number
     * 
     * @param searchPattern A string composed of one or more letters indicating left (<code>B or L</code>) or right (<code>F or R</code>) in the tree
     * @return The matching number, or <code>Integer.MIN_VALUE</code> if unsuccessful
     */
    public int find(String searchPattern)
    {
        int lBound = lowerBound;
        int uBound = upperBound;

        searchPattern = normalizeSearchPattern(searchPattern);

        for (int i = 0; i < searchPattern.length(); i++)
        {
            int diff = uBound - lBound;
            int shiftIfOdd = (diff % 2 == 0) ? 0 : 1; // If odd difference, shift by 1 to get to the correct side of the mid point
            char searchChar = searchPattern.charAt(i);
            if (searchChar == 'L') {
                uBound = uBound - (diff / 2) - shiftIfOdd;
            } else {
                lBound = lBound + (diff / 2) + shiftIfOdd;
            }
        }

        return lBound == uBound ? lBound : Integer.MIN_VALUE;
    }

    /**
     * Normalize search pattern to accomodate alternate characters (<code>F -> L, B -> R</code>)
     */
    private String normalizeSearchPattern(String searchPattern)
    {
        searchPattern = searchPattern.replace('F', 'L');
        searchPattern = searchPattern.replace('B', 'R');
        return searchPattern;
    }
}
