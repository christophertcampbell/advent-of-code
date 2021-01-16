package day16;

public class Range
{
    private int low;
    private int high;

    public Range(int low, int high)
    {
        this.low = low < high ? low : high;
        this.high = high > low ? high : low;
    }

    public boolean isWithinRange(int num)
    {
        return num >= low && num <= high;
    }
}
