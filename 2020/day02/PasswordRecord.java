package day02;

/**
 * A password record
 */
public class PasswordRecord
{
	private int minCount;
	private int maxCount;
	private char searchChar;
	private String password;

	/**
	 * Constructs a password record, with information including
	 * the password, the validation character and the parameters
	 */
	public PasswordRecord(int minCount, int maxCount, char searchChar, String password)
	{
		this.minCount = minCount;
		this.maxCount = maxCount;
		this.searchChar = searchChar;
		this.password = password;
	}

	/**
	 * Validates the password record
	 * 
	 * Returns true if the password is valid, false otherwise
	 */
	public boolean validate()
	{
		int occurrences = countOccurrences(password, searchChar);
		return occurrences >= minCount && occurrences <= maxCount;
	}

	/**
	 * Returns a count of the number of times the search character
	 * occurs in the password
	 */
	private int countOccurrences(String str, char searchChar)
	{
		int count = 0;
		for (int i = 0; i < str.length(); i++)
		{
			if (str.charAt(i) == searchChar) {
				count++;
			}
		}

		return count;
	}
}