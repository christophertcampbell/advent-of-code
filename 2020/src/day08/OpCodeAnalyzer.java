package day08;

import java.util.HashSet;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class OpCodeAnalyzer
{
    private static final Pattern PATTERN_OP_CODE = Pattern.compile("(\\w{3})\\s([+\\-0-9]+)");
    
    /**
     * Runs the supplied op codes until an infinite loop is detected,
     * then returns the value of the accumulator at that point
     */
    public static int runUntilInfiniteLoopDetected(String[] commands)
    {
        return run(commands, false).getValue();
    }

    /**
     * Runs the supplied op codes, attempting to fix the infinite loop,
     * and returns the value of the accumulator at the end
     */
    public static int runWithInifiniteLoopFix(String[] commands)
    {
        return run(commands, true).getValue();
    }

    /**
     * Runs the supplied op codes and returns the value of the accumulator upon termination
     * <p>
     * Terminates upon infinite loop. Optionally can try to fix the infinite loop.
     */
    private static OpCodeResult run(String[] commands, boolean fixInfiniteLoop)
    {
        int accumulator = 0;
        Integer pointer = 0;
        HashSet<Integer> visitedCommands = new HashSet<>();
        int pointerLastCommand = commands.length - 1;
        boolean foundInfiniteLoop = false;

        // Re-usable variables for within the execution loop
        Matcher matcher;
        String opCode;
        int opAmt;

        // Execute op codes until an infinite loop is detected
        while (pointer <= pointerLastCommand)
        {
            // Terminate if we have found an infinite loop
            if (visitedCommands.contains(pointer)) {
                foundInfiniteLoop = true;
                break;
            }

            // Add the current pointer to the history so we can detect
            // an infinite loop if we visit the same pointer again
            visitedCommands.add(pointer);

            // Parse the op code
            matcher = PATTERN_OP_CODE.matcher(commands[pointer]);
            matcher.find();
            opCode = matcher.group(1);
            opAmt = Integer.parseInt(matcher.group(2));

            // Fix the infinite loop if requested
            // Checks to see if flipping a jmp/nop command would
            // allow the program to complete successfully
            if (fixInfiniteLoop == true && (opCode.equals("jmp") || opCode.equals("nop"))) {

                // Flip the jmp/nop command and execute the modified set of commands,
                // resetting the jmp/nop command back to its original value afterwards
                String newOpCode = opCode.equals("jmp") ? "nop" : "jmp";
                commands[pointer] = commands[pointer].replace(opCode, newOpCode);
                OpCodeResult alternateResult = run(commands, false);
                commands[pointer] = commands[pointer].replace(newOpCode, opCode);
                
                // If we found a fix (did not encounter an infinite loop) return the alternate result
                if (alternateResult.encounteredInfiniteLoop() == false) {
                    return alternateResult;
                }
            }

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

        return new OpCodeResult(accumulator, foundInfiniteLoop);
    }
}
