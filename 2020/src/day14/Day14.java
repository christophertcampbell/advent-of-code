package day14;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utilities.FileIO;
import utilities.Test;

public class Day14
{
    private static final Pattern PATTERN_PARSE_INSTRUCTION = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");

    /**
     * Applies the appropriate bitmask to each value before it is written to memory,
     * and returns the sum of all values in memory upon completion
     */
    public static long partA(String[] input)
    {
        long setMask = 0;
        long unsetMask = 0;
        HashMap<Integer, Long> values = new HashMap<>();

        for (String line : input)
        {
            if (line.substring(0,4).equals("mask")) {
                // Found a new bitmask
                setMask = 0;
                unsetMask = 0;
                for (int i = 0; i < line.length(); i++)
                {
                    // Read bits from the right (least significant bit at right)
                    // and add to the appropriate "set" or "unset" mask
                    int bitIndex = line.length() - 1 - i;
                    if (line.charAt(i) == '1') {
                        setMask += Math.pow(2, bitIndex);
                    } else if (line.charAt(i) == '0') {
                        unsetMask += Math.pow(2, bitIndex);
                    }
                }
            } else {
                // Found a new entry
                Matcher matcher = PATTERN_PARSE_INSTRUCTION.matcher(line);
                if (matcher.find()) {
                    int memLocation = Integer.parseInt(matcher.group(1));
                    long newValue = Integer.parseInt(matcher.group(2));
                    // Apply the "set" and "unset" masks to the new value
                    newValue = (newValue | setMask) & ~unsetMask;
                    values.put(memLocation, newValue);
                };
            }
        }

        // Sum the values in all memory locations
        long sum = 0;
        for (Map.Entry<Integer, Long> entry : values.entrySet())
        {
            sum += entry.getValue();
        }
        
        return sum;
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[] testInput = FileIO.readAsStrings("2020/src/day14/Day14TestInput.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day14/Day14Input.txt");

        Test.assertEqual("Day 14 - Part A - Test input", partA(testInput), 165);
        Test.assertEqual("Day 14 - Part A - Challenge input", partA(realInput), 3059488894985L);
    }

    public static void main(String[] args)
    {
        run();
    }
}