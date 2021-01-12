package day14;

import java.util.ArrayList;
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
        BitMasks masks = new BitMasks();
        HashMap<Long, Long> memory = new HashMap<>();

        for (String line : input)
        {
            if (line.substring(0,4).equals("mask")) {
                // Found a new bitmask
                masks = parseBitMasks(line);
            } else {
                Matcher matcher = PATTERN_PARSE_INSTRUCTION.matcher(line);
                if (matcher.find()) {
                    // Found a new memory allocation instruction
                    long address = Long.parseLong(matcher.group(1));
                    long value = Long.parseLong(matcher.group(2));

                    // Apply the bit masks to the value
                    value = value | masks.setMask; // Apply the "set" bitmask
                    value = value & ~masks.unsetMask; // Apply the "unset" bitmask
                    memory.put(address, value);
                };
            }
        }

        return sumValues(memory);
    }

    /**
     * Applies the appropriate bitmask to each memory address before it is written to,
     * and returns the sum of all values in memory upon completion
     */
    public static long partB(String[] input)
    {
        BitMasks masks = new BitMasks();
        HashMap<Long, Long> memory = new HashMap<>();

        for (String line : input)
        {
            if (line.substring(0,4).equals("mask")) {
                // Found a new bitmask
                masks = parseBitMasks(line);
            } else {
                Matcher matcher = PATTERN_PARSE_INSTRUCTION.matcher(line);
                if (matcher.find()) {
                    // Found a new memory allocation instruction
                    long address = Long.parseLong(matcher.group(1));
                    long value = Integer.parseInt(matcher.group(2));

                    // Apply the "set" bitmask to the memory address
                    address = address | masks.setMask;
                    
                    // Apply all floating bitmasks to the memory address
                    long baseAddress = address & ~masks.floatingBitMasks.get(masks.floatingBitMasks.size() - 1); // Unset all floating bits
                    for (long floatingBitMask : masks.floatingBitMasks)
                    {
                        address = baseAddress | floatingBitMask;
                        memory.put(address, value);
                    }
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
        ArrayList<Long> floatingBits = new ArrayList<>();
        ArrayList<Long> floatingBitMasks = new ArrayList<>();

        /**
         * Read bits (least significant bit at right) and add
         * to the appropriate "set", "unset" or "floating" bitmask
         */
        for (int i = 0; i < str.length(); i++)
        {
            int bitIndexFromRight = str.length() - 1 - i;
            long bitValue = (long) Math.pow(2, bitIndexFromRight);
            if (str.charAt(i) == '1') {
                setMask += bitValue;
            } else if (str.charAt(i) == '0') {
                unsetMask += bitValue;
            } else if (str.charAt(i) == 'X') {
                floatingBits.add(bitValue);
            }
        }

        /**
         * Create all permutations of the floating bits.
         * Floating bits can take on either state, 0 or 1.
         * 
         * Do not create if the number of floating bits would make the
         * processing too time consuming (eg: Part A test data, which
         * doesn't need floating bit masks generated)
         */
        if (floatingBits.size() > 0 && floatingBits.size() < 20)
        {
            for (long floatingBit : floatingBits)
            {
                if (floatingBitMasks.size() == 0) {
                    // Initial floating bit
                    floatingBitMasks.add(0L);
                    floatingBitMasks.add(floatingBit);
                } else {
                    int masksCount = floatingBitMasks.size();
                    for (int x = 0; x < masksCount; x++)
                    {
                        // Leave the existing floating bitmasks alone (equivalent of adding the zero
                        // state of the new bit) and duplicate the existing masks with the new bit added.
                        // This process doubles the number of bitmasks each time.
                        floatingBitMasks.add(floatingBitMasks.get(x) + floatingBit);
                    }
                }
            }
        }

        return new BitMasks(setMask, unsetMask, floatingBitMasks);
    }

    /**
     * Returns the sum of all values in a HashMap
     */
    private static long sumValues(HashMap<Long, Long> map)
    {
        long sum = 0;
        for (Map.Entry<Long, Long> entry : map.entrySet())
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
        String[] testInputPartA = FileIO.readAsStrings("2020/src/day14/Day14TestInputPartA.txt");
        String[] testInputPartB = FileIO.readAsStrings("2020/src/day14/Day14TestInputPartB.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day14/Day14Input.txt");

        Test.assertEqual("Day 14 - Part A - Test input", partA(testInputPartA), 165);
        Test.assertEqual("Day 14 - Part A - Challenge input", partA(realInput), 3059488894985L);
        Test.assertEqual("Day 14 - Part B - Test input", partB(testInputPartB), 208);
        Test.assertEqual("Day 14 - Part B - Challenge input", partB(realInput), 2900994392308L);
    }

    public static void main(String[] args)
    {
        run();
    }
}