package day02;

public class PasswordValidator
{
	/**
	 * Validates the password record via method 1
	 * 
	 * Returns true if the validation character appears the correct number of times
	 */
	public boolean validateMethod1(PasswordRecord record)
	{
		if (record == null) return false;
		int occurrences = countOccurrencesOfValidationChar(record.getPassword(), record.getValidationChar());
		return occurrences >= record.getIndex1() && occurrences <= record.getIndex2();
	}

	/**
	 * Validates the password record via method 2
	 * 
	 * Returns true if the character at exactly one of the indexes matches the validation char
	 */
	public boolean validateMethod2(PasswordRecord record)
	{
		if (record == null) return false;
		String password = record.getPassword();
		char validationChar = record.getValidationChar();

		// Indexes are 1-based
		int index1 = record.getIndex1();
		int index2 = record.getIndex2();
		
		// charAt() is 0-based
		char charAtIndex1 = password.charAt(index1 - 1);
		char charAtIndex2 = password.charAt(index2 - 1);

		return charAtIndex1 == validationChar && charAtIndex2 != validationChar ||
			charAtIndex2 == validationChar && charAtIndex1 != validationChar;
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