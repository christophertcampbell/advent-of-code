package day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketAnalyzer
{
    private static final Pattern RULES_PATTERN = Pattern.compile("(\\S+):\\s(\\d+)-(\\d+) or (\\d+)-(\\d+)");
    private  static final Pattern TICKET_PATTERN = Pattern.compile("(\\d+)");

    private ArrayList<Range> validRanges;
    private ArrayList<Integer[]> nearbyTickets;
    private Integer[] yourTicket;

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

    /**
     * Returns the sum of all invalid numbers on nearby tickets
     * according to rules that are parsed from the beginning of the input
     */
    public int getScanningErrorRate()
    {
        HashMap<Integer, Boolean> history = new HashMap<>();
        int errorRate = 0;
        for (Integer[] ticket : nearbyTickets)
        {
            for (Integer num : ticket)
            {
                if (history.containsKey(num)) {
                    // We have seen this number before, use our previous results
                    if (history.get(num) == false) {
                        errorRate += num;
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
                    errorRate += num;
                }
            }
        }

        return errorRate;
    }
}
