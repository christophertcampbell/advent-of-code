package day02;

/**
 * A password record
 */
public class PasswordRecord
{
	private int minCount;
	private int maxCount;
	private char validationChar;
	private String password;

	/**
	 * Constructs a password record, with information including
	 * the password, the validation character and the parameters
	 */
	public PasswordRecord(int minCount, int maxCount, char validationChar, String password)
	{
		this.minCount = minCount;
		this.maxCount = maxCount;
		this.validationChar = validationChar;
		this.password = password;
	}

	/**
	 * Validates the password record
	 * 
	 * Returns true if the password is valid, false otherwise
	 */
	public boolean validate()
	{
		int occurrences = countOccurrencesOfValidationChar();
		return occurrences >= minCount && occurrences <= maxCount;
	}

	/**
	 * Returns a count of the number of times the search character
	 * occurs in the password
	 */
	private int countOccurrencesOfValidationChar()
	{
		int count = 0;
		for (int i = 0; i < password.length(); i++)
		{
			if (password.charAt(i) == validationChar) {
				count++;
			}
		}

		return count;
	}
}