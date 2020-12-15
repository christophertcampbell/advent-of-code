package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO
{
    /*
     * Returns the content of a file as an array of strings
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
}