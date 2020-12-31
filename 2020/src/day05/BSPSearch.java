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
        searchPattern = normalizeSearchPattern(searchPattern);
        return doSearch(searchPattern, lowerBound, upperBound);
    }

    /**
     * Recursive binary search based upon a search pattern of <code>R</code> and <code>L</code> characters
     * 
     * @param searchPattern A string composed of one or more letters indicating left (<code>L</code>) or right (<code>R</code>) in the tree
     * @return The matching number, or <code>Integer.MIN_VALUE</code> if unsuccessful
     */
    private int doSearch(String searchPattern, int minNum, int maxNum)
    {
        int diff = maxNum - minNum;

        // Base case, only one search letter left
        if (searchPattern.length() == 1) {
            if (diff == 0) {
                // Bounds are the same, return it
                return minNum;
            } else if (diff == 1) {
                // Bounds are sequential, return the higher or lower as appropriate
                return searchPattern.charAt(0) == 'L' ? minNum : maxNum;
            }

            // Bounds are too far apart to find an answer
            System.out.println("Pattern: " + searchPattern + " | minNum: " + minNum + " | maxNum: " + maxNum);
            return Integer.MIN_VALUE;
        }

        // Multiple search letters left, narrow down our search by moving the bounds and continue looking
        int shiftIfOdd = (diff % 2 == 0) ? 0 : 1; // If odd difference, shift by 1 to get to the correct side of the mid point
        if (searchPattern.charAt(0) == 'L') {
            // Want the left half, move the right bound in
            maxNum = maxNum - (diff / 2) - shiftIfOdd;
        } else {
            // Want the right half, move the left bound in
            minNum = minNum + (diff / 2) + shiftIfOdd;
        }

        String newSearchPattern = searchPattern.substring(1, searchPattern.length()); // Trim off the first character
        return doSearch(newSearchPattern, minNum, maxNum); 
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
