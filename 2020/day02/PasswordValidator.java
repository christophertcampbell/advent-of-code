package day02;

public class PasswordValidator
{
	/**
	 * Validates the password record
	 * 
	 * Returns true if the password is valid, false otherwise
	 */
	public boolean validate(PasswordRecord record)
	{
		if (record == null) return false;
		int occurrences = countOccurrencesOfValidationChar(record.getPassword(), record.getValidationChar());
		return occurrences >= record.getIndex1() && occurrences <= record.getIndex2();
	}

	/**
	 * Returns a count of the number of times the search character
	 * occurs in the password
	 */
	private int countOccurrencesOfValidationChar(String password, char validationChar)
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