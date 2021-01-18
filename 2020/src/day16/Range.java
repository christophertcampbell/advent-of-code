package day16;

public class Range
{
    private String name;
    private int low1;
    private int high1;
    private int low2;
    private int high2;

    public Range(String name, int low1, int high1, int low2, int high2)
    {
        this.name = name;
        this.low1 = low1;
        this.high1 = high1;
        this.low2 = low2;
        this.high2 = high2;
    }

    public boolean isWithinRange(int num)
    {
        return (num >= low1 && num <= high1) ||
               (num >= low2 && num <= high2);
    }

    public String getName()
    {
        return name;
    }
}
