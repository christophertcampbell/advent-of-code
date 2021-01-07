package day11;

/**
 * This is a modified Game of Life problem. In this case,
 * there are three states (empty seat (L), occupied seat (#),
 * and unoccupiable floor space (.)), and the rules are
 * different than a standard Game of Life program
 * 
 * In "Visible Seat Mode" takes into account all occupied
 * seats visible from each point. Otherwise only takes into
 * account occupied seats immediately adjacent to each point.
 */
public class SeatLayoutGameOfLife
{
    private char[][] data;
    private int width;
    private int height;
    private boolean isVisibleSeatMode;

    public SeatLayoutGameOfLife(String[] input, boolean isVisibleSeatMode)
    {
        /**
         * Convert input to an array of chars
         * and record the width and height
         * for ease of processing
         */
        width = input[0].length();
        height = input.length;
        data = new char[height][width];
        this.isVisibleSeatMode = isVisibleSeatMode;
        for (int i = 0; i < input.length; i++)
        {
            data[i] = input[i].toCharArray();
        }
    }
    
    /**
     * Runs the program and returns a count of occupied seats after
     * the seat layout has reached a state of equilibrium
     */
    public int run()
    {
        boolean madeChange = true;
        char[][] newData;

        // The max number of surrounding occipied seats before
        // an occupied seat becomes unoccupied
        int maxAdjacentOccupiedSeatCount = isVisibleSeatMode ? 4 : 3;

        // Run until we reach a state where no more changes occur
        while (madeChange)
        {
            madeChange = false;
            newData = new char[height][width];

            // Note: We are leaving the original data array unchanged
            // until the very end, so all of our analysis takes place
            // on the original state of the data, not the modified
            // state as we apply the rules

            // Fill in empty seats (if there are no adjacent occupied seats)
            // This process also fully populates the newData array
            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    if (isUnoccupiedSeat(x, y) && getSurroundingOccupiedSeatCount(x, y) == 0) {
                        newData[y][x] = '#'; // Make the seat occupied
                        madeChange = true;
                    } else {
                        newData[y][x] = data[y][x]; // No change
                    }
                }
            }

            // Remove occupied seats (if there are 4 or more adjacent occupied seats)
            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    if (isOccupiedSeat(x, y) && getSurroundingOccupiedSeatCount(x, y) > maxAdjacentOccupiedSeatCount) {
                        newData[y][x] = 'L'; // Make the seat unoccupied
                        madeChange = true;
                    }
                }
            }

            // Update the main set of data
            data = newData;
        }
        
        return getTotalOccupiedSeatCount();
    }

    private boolean isUnoccupiedSeat(int x, int y)
    {
        return isInBounds(x, y) && data[y][x] == 'L';
    }

    private boolean isOccupiedSeat(int x, int y)
    {
        return isInBounds(x, y) && data[y][x] == '#';
    }

    private boolean isInBounds(int x, int y)
    {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    /**
     * Returns the count of surrounding occupied seats
     */
    private int getSurroundingOccupiedSeatCount(int x, int y)
    {
        return isVisibleSeatMode
            ? getSurroundingOccupiedSeatCountVisible(x, y)
            : getSurroundingOccupiedSeatCountAdjacent(x, y);
    }

    /**
     * Returns the count of occupied seats that are
     * immediately adjacent to the specified point
     */
    private int getSurroundingOccupiedSeatCountAdjacent(int x, int y)
    {
        int count = 0;
        for (int y2 = y - 1; y2 <= y + 1; y2++)
        {
            for (int x2 = x - 1; x2 <= x + 1; x2++)
            {
                if (!(x2 == x && y2 == y) && isOccupiedSeat(x2, y2)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Returns the count of occupied seats that are
     * visible from the specified point
     */
    private int getSurroundingOccupiedSeatCountVisible(int x, int y)
    {
        int count = 0;
        // Search for visible occupied seats in all directions
        for (int yDiff = -1; yDiff <= 1; yDiff++)
        {
            for (int xDiff = -1; xDiff <= 1; xDiff++)
            {
                if (xDiff == 0 && yDiff == 0) {
                    continue; // Skip over the original point
                }

                // First point in search direction
                int workingX = x + xDiff;
                int workingY = y + yDiff;

                while (isInBounds(workingX, workingY))
                {
                    if (isOccupiedSeat(workingX, workingY)) {
                        // Occupied seat, count it and move on to the next search direction
                        count++;
                        break;
                    } else if (isUnoccupiedSeat(workingX, workingY)) {
                        // Unoccupied seat, don't count it but move on to the next search direction
                        break;
                    }
                    
                    // Move to next point in current search direction
                    workingX += xDiff;
                    workingY += yDiff;
                }
            }
        }
        return count;
    }

    /**
     * Returns the total count of all occupied seats
     */
    private int getTotalOccupiedSeatCount()
    {
        int count = 0;
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if (data[y][x] == '#') {
                    count++;
                }
            }
        }
        return count;
    }
}
