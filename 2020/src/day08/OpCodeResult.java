package day08;

/**
 * Contains the results of the execution of a series of op codes
 */
public class OpCodeResult
{
    private int value;
    private boolean encounteredInfiniteLoop;

    public OpCodeResult(int value, boolean encounteredInfiniteLoop)
    {
        this.value = value;
        this.encounteredInfiniteLoop = encounteredInfiniteLoop;
    }

    public int getValue()
    {
        return value;
    }

    public boolean encounteredInfiniteLoop()
    {
        return encounteredInfiniteLoop;
    }
}
