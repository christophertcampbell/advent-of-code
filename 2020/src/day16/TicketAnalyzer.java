package day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketAnalyzer
{
    private static final Pattern RULES_PATTERN = Pattern.compile("(.+):\\s(\\d+)-(\\d+) or (\\d+)-(\\d+)");
    private  static final Pattern TICKET_PATTERN = Pattern.compile("(\\d+)");

    private ArrayList<Range> validRanges;
    private ArrayList<Integer[]> nearbyTickets;
    private Integer[] yourTicket;
    HashMap<Integer, Boolean> validationHistory = new HashMap<>();

    public TicketAnalyzer(String[] input)
    {
        parseInput(input);
    }

    /**
     * Parses the rules and ticket numbers from the input
     */
    private void parseInput(String[] input)
    {
        validRanges = new ArrayList<>();
        nearbyTickets = new ArrayList<>();
        boolean foundNearbyTicketsHeading = false;
        boolean foundYourTicketsHeading = false;
        Matcher matcher;
        
        // Parse the input
        for (String line : input)
        {
            // Check for the "your/nearby tickets" separators
            if (line.equals("nearby tickets:")) {
                foundNearbyTicketsHeading = true;
                continue;
            } else if (line.equals("your ticket:")) {
                foundYourTicketsHeading = true;
                continue;
            }

            // Parse nearby tickets
            if (foundYourTicketsHeading || foundNearbyTicketsHeading) {
                matcher = TICKET_PATTERN.matcher(line);
                ArrayList<Integer> ticket = new ArrayList<>();
                while (matcher.find()) {
                    ticket.add(Integer.parseInt(matcher.group(0)));
                }
                if (ticket.size() > 0) {
                    if (foundNearbyTicketsHeading) {
                        // We are in the "nearby tickets" section
                        nearbyTickets.add(ticket.toArray(new Integer[0]));
                    } else {
                        // We are in the "your ticket" section
                        yourTicket = ticket.toArray(new Integer[0]);
                    }
                }
                continue;
            }

            // Parse rules
            matcher = RULES_PATTERN.matcher(line);
            if (matcher.find()) {
                // Found a new valid range
                String name = matcher.group(1);
                int low1 = Integer.parseInt(matcher.group(2));
                int high1 = Integer.parseInt(matcher.group(3));
                int low2 = Integer.parseInt(matcher.group(4));
                int high2 = Integer.parseInt(matcher.group(5));
                validRanges.add(new Range(name, low1, high1, low2, high2));
                continue;
            }
        }
    }

    // --------------------------------------------------------------------------------
    // Part A methods
    // --------------------------------------------------------------------------------

    /**
     * Returns the sum of all invalid numbers on nearby tickets
     * according to rules that are parsed from the beginning of the input
     */
    public int getScanningErrorRate()
    {
        int errorRate = 0;
        for (Integer[] ticket : nearbyTickets)
        {
            int errorValue = getTicketErrorValue(ticket);
            if (errorValue >= 0) {
                errorRate += errorValue;
            }
        }

        return errorRate;
    }

    /**
     * Returns the sum of all values within a ticket which
     * are invalid for any ranges in the rules.
     * 
     * Returns >= 0 if any invalid numbers are found.
     * Returns == -1 if all numbers are valid.
     */
    private int getTicketErrorValue(Integer[] ticket)
    {
        int errors = -1;
        for (Integer num : ticket)
        {
            if (validationHistory.containsKey(num)) {
                // We have seen this number before, use our previous results
                if (validationHistory.get(num) == false) {
                    errors = errors == -1 ? num : errors + num;
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
            validationHistory.put(num, isValid);
            if (isValid == false) {
                errors = errors == -1 ? num : errors + num;
            }
        }

        return errors;
    }

    // --------------------------------------------------------------------------------
    // Part B methods
    // --------------------------------------------------------------------------------

    /**
     * Returns the product of all values for fields in "your ticket" which
     * contain the search term within the field name
     */
    public long getProductOfFields(String fieldNameSearchTerm)
    {
        Range[] mapOfRanges = getMapOfRanges();
        long product = 1;

        for (int i = 0; i < mapOfRanges.length; i++)
        {
            if (mapOfRanges[i].getName().contains(fieldNameSearchTerm)) {
                product *= yourTicket[i];
            }
        }
        
        return product;
    }

    /**
     * Builds a map of which ranges (rules) correspond to each field in a ticket.
     * The rules are scrambled, so we need to analyze all valid tickets until
     * we find a permutation where each field can only be satisfied by a single rule.
     */
    private Range[] getMapOfRanges()
    {
        // Build the initial collections of possible ranges
        // based upon which ranges are valid for each field in "your ticket"
        ArrayList<ArrayList<Range>> rulesMap = new ArrayList<>();
        for (Integer num : yourTicket)
        {
            ArrayList<Range> possibleRanges = new ArrayList<>();
            for (Range range : validRanges)
            {
                if (range.isWithinRange(num)) {
                    possibleRanges.add(range);
                }
            }
            rulesMap.add(possibleRanges);
        }

        // Now loop through all valid tickets and reduce the number of
        // possible ranges for each field until we arrive at only
        // one possible range for each field
        ArrayList<Integer[]> validTickets = getValidTickets();
        for (Integer[] ticket : validTickets)
        {
            for (int i = 0; i < ticket.length; i++)
            {
                int num = ticket[i];
                ArrayList<Range> rangesToRemove = new ArrayList<>();
                for (Range range : rulesMap.get(i))
                {
                    if (range.isWithinRange(num) == false) {
                        rangesToRemove.add(range);
                    }
                }
                for (Range range : rangesToRemove)
                {
                    rulesMap.get(i).remove(range);
                }
            }
        }

        rulesMap = consolidateSettledRanges(rulesMap);

        // Consolidate the results into a simple array of ranges
        // which correspond to each field in a ticket
        Range[] mapOfRanges = new Range[rulesMap.size()];
        for (int i = 0; i < rulesMap.size(); i++)
        {
            mapOfRanges[i] = rulesMap.get(i).get(0);
        }

        return mapOfRanges;
    }

    /**
     * Returns a collection of valid tickets (no invalid numbers)
     */
    private ArrayList<Integer[]> getValidTickets()
    {
        ArrayList<Integer[]> validTickets = new ArrayList<>();
        for (Integer[] ticket : nearbyTickets)
        {
            int errorValue = getTicketErrorValue(ticket);
            if (errorValue == -1) {
                validTickets.add(ticket);
            }
        }
        return validTickets;
    }

    /**
     * Looks for settled ranges (ranges where there is only one valid possibility for a
     * particular field) and removes those ranges from all other fields so we don't
     * keep searching them
     */
    private ArrayList<ArrayList<Range>> consolidateSettledRanges(ArrayList<ArrayList<Range>> ranges)
    {
        for (int i = 0; i < ranges.size(); i++)
        {
            // If there is only one valid child range, then we have found its correct position.
            // Remove the child range from all other parent ranges which may have it.
            if (ranges.get(i).size() == 1) {
                Range rangeToRemove = ranges.get(i).get(0);
                boolean removedRange = false;
                for (int x = 0; x < ranges.size(); x++)
                {
                    if (x == i) continue; // Don't remove the valid position of the child range
                    if (ranges.get(x).remove(rangeToRemove)) {
                        removedRange = true;
                    };
                }

                if (removedRange) {
                    i = -1; // Reset and begin checking at beginning again since we have modified
                }
            }
        }
        return ranges;
    }
}
