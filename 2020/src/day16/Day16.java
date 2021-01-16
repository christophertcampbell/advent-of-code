package day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utilities.FileIO;
import utilities.Test;

public class Day16
{
    private static final Pattern RULES_PATTERN = Pattern.compile("(\\d+)-(\\d+) or (\\d+)-(\\d+)");
    private  static final Pattern TICKET_PATTERN = Pattern.compile("(\\d+)");

    /**
     * Returns the sum of all invalid numbers on nearby tickets
     * according to rules that are parsed from the beginning of the input
     */
    public static int partA(String[] input)
    {
        ArrayList<Integer[]> nearbyTickets = new ArrayList<>();
        ArrayList<Range> validRanges = new ArrayList<>();
        boolean foundNearbyTicketsHeading = false;
        Matcher matcher;
        
        // Parse the input
        for (String line : input)
        {
            // Check for the "nearby tickets" separator
            if (line.equals("nearby tickets:")) {
                foundNearbyTicketsHeading = true;
                continue;
            }

            // Parse nearby tickets
            if (foundNearbyTicketsHeading) {
                matcher = TICKET_PATTERN.matcher(line);
                ArrayList<Integer> nearbyTicket = new ArrayList<>();
                while (matcher.find()) {
                    nearbyTicket.add(Integer.parseInt(matcher.group(0)));
                }
                if (nearbyTicket.size() > 0) {
                    nearbyTickets.add(nearbyTicket.toArray(new Integer[0]));
                }
                continue;
            }

            // Parse rules
            matcher = RULES_PATTERN.matcher(line);
            if (matcher.find()) {
                for (int i = 1; i <= matcher.groupCount(); i += 2)
                {
                    // Found a new valid range
                    int lowNum = Integer.parseInt(matcher.group(i));
                    int highNum = Integer.parseInt(matcher.group(i+1));
                    validRanges.add(new Range(lowNum, highNum));
                }
                continue;
            }
        }

        // Perform analysis
        HashMap<Integer, Boolean> history = new HashMap<>();
        int sum = 0;
        for (Integer[] ticket : nearbyTickets)
        {
            for (Integer num : ticket)
            {
                if (history.containsKey(num)) {
                    // We have seen this number before, use our previous results
                    if (history.get(num) == false) {
                        sum += num;
                    }
                    continue;
                }

                // We have not seen this number before, determine if it is valid
                boolean isValid = false;
                for (Range validRange : validRanges)
                {
                    // If the number is within any valid range, it is valid
                    // and we don't need to check further
                    if (validRange.isWithinRange(num)) {
                        isValid = true;
                        break;
                    }
                }
                history.put(num, isValid);
                if (isValid == false) {
                    sum += num;
                }
            }
        }

        return sum;
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[] testInput = FileIO.readAsStrings("2020/src/day16/Day16TestInput.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day16/Day16Input.txt");

        Test.assertEqual("Day 16 - Part A - Test input", partA(testInput), 71);
        Test.assertEqual("Day 16 - Part A - Challenge input", partA(realInput), 19087);
    }

    public static void main(String[] args)
    {
        run();
    }
}