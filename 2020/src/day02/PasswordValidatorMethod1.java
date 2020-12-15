package day02;

public class PasswordValidatorMethod1 implements PasswordValidator
{
    /**
     * Validates the password
     * 
     * Returns true if the validation character appears the correct number of times
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