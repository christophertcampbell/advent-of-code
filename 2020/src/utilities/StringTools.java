package utilities;

public class StringTools
{
    /**
     * Parses the string to an integer
     * <p>
     * Returns <code>Integer.MIN_VALUE</code> if unable to parse to an integer
     */
    public static int parseToInt(String value)
    {
        try
        {
            return Integer.parseInt(value);
        }
        catch (Exception e)
        {
            return Integer.MIN_VALUE;
        }
    }
}
