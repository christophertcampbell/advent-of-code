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
        BitMasks masks = new BitMasks(0,0);
        HashMap<Integer, Long> memory = new HashMap<>();

        for (String line : input)
        {
            if (line.substring(0,4).equals("mask")) {
                // Found a new bitmask
                masks = parseBitMasks(line);
            } else {
                Matcher matcher = PATTERN_PARSE_INSTRUCTION.matcher(line);
                if (matcher.find()) {
                    // Found a new memory allocation instruction
                    int address = Integer.parseInt(matcher.group(1));
                    long value = Integer.parseInt(matcher.group(2));
                    value = value | masks.setMask; // Apply the "set" bitmask
                    value = value & ~masks.unsetMask; // Apply the "unset" bitmask
                    memory.put(address, value);
                };
            }
        }

        return sumValues(memory);
    }

    /**
     * Parses a co-mingled bitmask into usable bitmasks
     */
    private static BitMasks parseBitMasks(String str)
    {
        long setMask = 0;
        long unsetMask = 0;
        for (int i = 0; i < str.length(); i++)
        {
            // Read bits from the right (least significant bit at right)
            // and add to the appropriate "set" or "unset" mask
            int bitIndex = str.length() - 1 - i;
            if (str.charAt(i) == '1') {
                setMask += Math.pow(2, bitIndex);
            } else if (str.charAt(i) == '0') {
                unsetMask += Math.pow(2, bitIndex);
            }
        }
        return new BitMasks(setMask, unsetMask);
    }

    /**
     * Returns the sum of all values in a HashMap
     */
    private static long sumValues(HashMap<Integer, Long> map)
    {
        long sum = 0;
        for (Map.Entry<Integer, Long> entry : map.entrySet())
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