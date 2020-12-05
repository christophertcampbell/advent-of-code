package utilities;

import java.io.File;
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
		}
		catch (Exception e)
		{
			System.out.println("Exception: " + e.getMessage());
		}

		return input.toArray(new String[0]);
	}
}