package day08;

import java.util.HashSet;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class OpCodeAnalyzer
{
    private static final Pattern PATTERN_OP_CODE = Pattern.compile("(\\w{3})\\s([+\\-0-9]+)");
    
    /**
     * Runs the supplie op codes until an infinite loop is detected,
     * and then returns the value of the accumulator at that point
     */
    public static int runUntilInfiniteLoopDetected(String[] commands)
    {
        int accumulator = 0;
        Integer pointer = 0;
        HashSet<Integer> visitedCommands = new HashSet<>();

        // Re-usable variables for within the execution loop
        Matcher matcher;
        String opCode;
        int opAmt;

        // Execute op codes until an infinite loop is detected
        while (visitedCommands.contains(pointer) == false)
        {
            // Add the current pointer to the history so we can detect
            // an infinite loop if we visit the same pointer again
            visitedCommands.add(pointer);

            // Parse the op code
            matcher = PATTERN_OP_CODE.matcher(commands[pointer]);
            matcher.find();
            opCode = matcher.group(1);
            opAmt = Integer.parseInt(matcher.group(2));

            // Execute the op code
            switch(opCode)
            {
                case "acc": // Adjust the accumulator
                    accumulator += opAmt;
                    pointer++;
                    break;
                case "jmp": // Jump forward or back
                    pointer += opAmt;
                    break;
                case "nop": // No operation, continue on
                    pointer++;
                    break;
                default:
                    break;
            }
        }

        return accumulator;
    }
}
