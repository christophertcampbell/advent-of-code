package day04;

public class PassportValidator
{
    private static final String[] REQUIRED_KEYS = new String[] {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    
    /**
     * Validates the passport info
     */
    public boolean validate(PassportInfo passportInfo)
    {
        return hasRequiredKeys(passportInfo);
    }

    /**
     * Validates whether the passport info contains the required keys
     */
    private boolean hasRequiredKeys(PassportInfo passportInfo)
    {
        for (String key : REQUIRED_KEYS)
        {
            if (passportInfo.containsKey(key) == false) {
                return false; // Missing a required key
            }
        }

        return true;
    }
}
