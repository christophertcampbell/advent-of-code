package day02;

public class PasswordValidatorMethod2 implements PasswordValidator
{
	/**
	 * Validates the password
	 * 
	 * Returns true if the character at exactly one of the indexes matches the validation char
	 */
	public boolean validate(PasswordRecord record)
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
}