package utilities;

public class Test
{
    /**
     * Prints to console a pass/fail assertion of equality
     */
    public static void assertEqual(String testName, long result, long expected)
    {
        String status = result == expected ? "Pass  " : "FAILED";
        System.out.printf("%s | %s | Result: %d | Expected: %d\n", status, testName, result, expected);
    }

    /**
     * Prints to console a pass/fail assertion of equality
     */
    public static void assertEqual(String testName, boolean result, boolean expected)
    {
        String status = result == expected ? "Pass  " : "FAILED";
        System.out.printf("%s | %s | Result: %b | Expected: %b\n", status, testName, result, expected);
    }
}
