package day04;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import utilities.StringTools;

public class PassportValidatorAdvanced extends PassportValidator
{
    private static final Pattern PARSE_PATTERN_HAIR_COLOR = Pattern.compile("^#[0-9a-f]{6}$");
    private static final Pattern PARSE_PATTERN_PASSPORT_ID = Pattern.compile("^[0-9]{9}$");
    private static final List<String> VALID_EYE_COLORS = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

    /**
     * Validates the passport info
     */
    @Override
    public boolean validate(PassportInfo passportInfo)
    {
        if (!hasRequiredKeys(passportInfo)) return false;
        if (!validateBirthYear(passportInfo.getBirthYear())) return false;
        if (!validateIssueYear(passportInfo.getIssueYear())) return false;
        if (!validateExpirationYear(passportInfo.getExpirationYear())) return false;
        if (!validateHeight(passportInfo.getHeight())) return false;
        if (!validateHairColor(passportInfo.getHairColor())) return false;
        if (!validateEyeColor(passportInfo.getEyeColor())) return false;
        if (!validatePassportID(passportInfo.getPassportID())) return false;

        return true;
    }

    // --------------------------------------------------------------------------------
    // Validation methods
    // --------------------------------------------------------------------------------

    public static boolean validateBirthYear(int birthYear)
    {
        return isWithinRange(birthYear, 1920, 2002);
    }

    public static boolean validateIssueYear(int issueYear)
    {
        return isWithinRange(issueYear, 2010, 2020);
    }

    public static boolean validateExpirationYear(int expirationYear)
    {
        return isWithinRange(expirationYear, 2020, 2030);
    }

    public static boolean validateHeight(String heightString)
    {
        if (heightString.length() < 3) return false; // Must be a number + 2 character unit identifier

        String units = heightString.substring(heightString.length() - 2, heightString.length()); // Get the 2 character unit identifier
        String numStr = heightString.substring(0, heightString.length() - 2); // Get the number characters, minus the right 2 characters
        int height = StringTools.parseToInt(numStr);

        switch(units)
        {
            case "cm":
                return isWithinRange(height, 150, 193);
            case "in":
                return isWithinRange(height, 59, 76);
            default:
                return false;
        }
    }

    public static boolean validateHairColor(String hairColor)
    {
        return PARSE_PATTERN_HAIR_COLOR.matcher(hairColor).matches();
    }

    public static boolean validateEyeColor(String eyeColor)
    {
        return VALID_EYE_COLORS.contains(eyeColor);
    }

    public static boolean validatePassportID(String passportID)
    {
        return PARSE_PATTERN_PASSPORT_ID.matcher(passportID).matches();
    }

    // --------------------------------------------------------------------------------
    // Helper methods
    // --------------------------------------------------------------------------------

    /**
     * Returns true if the value is between the min and max values (inclusive), false otherwise
     */
    private static boolean isWithinRange(int value, int minInclusive, int maxInclusive)
    {
        return value >= minInclusive && value <= maxInclusive;
    }
}
