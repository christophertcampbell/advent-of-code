package day11;

/**
 * This is a modified Game of Life problem. In this case,
 * there are three states (empty seat (L), occupied seat (#),
 * and unoccupiable floor space (.)), and the rules are
 * different than a standard Game of Life program
 */
public class SeatLayoutGameOfLife
{
    private char[][] data;
    private int width;
    private int height;

    public SeatLayoutGameOfLife(String[] input)
    {
        /**
         * Convert input to an array of chars
         * and record the width and height
         * for ease of processing
         */
        width = input[0].length();
        height = input.length;
        data = new char[height][width];
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
                    if (isOccupiedSeat(x, y) && getSurroundingOccupiedSeatCount(x, y) >= 4) {
                        newData[y][x] = 'L'; // Make the seat unoccupied
                        madeChange = true;
                    }
                }
            }

            // Update the main set of data
            data = newData;
        }
        
        return getOccupiedSeatCount();
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

    private int getSurroundingOccupiedSeatCount(int x, int y)
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

    private int getOccupiedSeatCount()
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
