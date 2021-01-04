package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO
{
    /**
     * Returns the contents of a file as an array of strings
     */
    public static String[] readAsStrings(String filepath)
    {
        ArrayList<String> input = new ArrayList<String>();

        try
        {
            File file = new File(filepath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                input.add(sc.nextLine());
            }
            sc.close();
        }
        catch (FileNotFoundException e)
        {
            /**
             * File was not found
             * 
             * If the filepath contains subdirectories, remove
             * the first one and try again. Allows the file to
             * be found no matter where the 'java' run command
             * is executed within the project directory structure
             */
            if (filepath.contains("/"))
            {
                String[] parts = filepath.split("/", 2);
                return readAsStrings(parts[1]);
            }

            System.out.println("Exception: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }

        return input.toArray(new String[0]);
    }

    /**
     * Returns the contents of a file as an array of integers
     */
    public static int[] readAsInts(String filepath)
    {
        String[] strings = readAsStrings(filepath);
        int[] input = new int[strings.length];
        for (int i = 0; i < strings.length; i++)
        {
            try
            {
                input[i] = Integer.parseInt(strings[i]);
            }
            catch (Exception e)
            {
                System.out.println("Exception: " + e.getMessage());
            }
        }

        return input;
    }

    /**
     * Returns the contents of a file as an array of longs
     */
    public static long[] readAsLongs(String filepath)
    {
        String[] strings = readAsStrings(filepath);
        long[] input = new long[strings.length];
        for (int i = 0; i < strings.length; i++)
        {
            try
            {
                input[i] = Long.parseLong(strings[i]);
            }
            catch (Exception e)
            {
                System.out.println("Exception: " + e.getMessage());
            }
        }

        return input;
    }

    /**
     * Returns the contents of a file as an array of arrays of strings,
     * grouping together continguous lines in the input with
     * blank lines being the delimiter between groups
     */
    public static String[][] readAsGroupsOfStrings(String filepath)
    {
        String[] input = readAsStrings(filepath);

        ArrayList<String[]> groupedInput = new ArrayList<String[]>();
        ArrayList<String> currentGroup = new ArrayList<String>();

        for (int i = 0; i <= input.length; i++)
        {
            if (i == input.length || input[i] == null || input[i].isEmpty()) {
                // Blank line or end of input, add the current group
                // to the collection and start a new current group
                groupedInput.add(currentGroup.toArray(new String[0]));
                currentGroup.clear();
            } else {
                // Add the current line to the current group and continue on
                currentGroup.add(input[i]);
            }
        }

        return groupedInput.toArray(new String[0][]);
    }
}