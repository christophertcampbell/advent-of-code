package day04;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utilities.StringTools;

public class PassportInfo
{
    private static final Pattern INPUT_PARSE_PATTERN = Pattern.compile("(\\S+):(\\S+)", Pattern.CASE_INSENSITIVE);
    private HashMap<String, String> info;

    /**
     * Constructs a new PassportInfo instance
     * 
     * Expects input to have one or more strings containing the passport info
     * in the format: 'key1:value1 key2:value2 ...'
     */
    public PassportInfo(String[] input)
    {
        info = parseInfo(input);
    }

    /**
     * Returns true of the key is present, false otherwise
     */
    public boolean containsKey(String key)
    {
        return info.containsKey(key);
    }

    /**
     * Returns the value at the specified key, or an empty string if not present
     */
    public String get(String key)
    {
        return containsKey(key) ? info.get(key) : "";
    }

    // Getters
    public int getBirthYear()      { return StringTools.parseToInt(get("byr")); }
    public int getIssueYear()      { return StringTools.parseToInt(get("iyr")); }
    public int getExpirationYear() { return StringTools.parseToInt(get("eyr")); }
    public String getHeight()      { return get("hgt"); }
    public String getHairColor()   { return get("hcl"); }
    public String getEyeColor()    { return get("ecl"); }
    public String getPassportID()  { return get("pid"); }
    public String getCountryID()   { return get("cid"); }

    /**
     * Constructs a HashMap of passport info
     */
    private HashMap<String, String> parseInfo(String[] input)
    {
        HashMap<String, String> passportInfo = new HashMap<String, String>();
        Matcher matcher;
        for (String str : input)
        {
            matcher = INPUT_PARSE_PATTERN.matcher(str);
            while (matcher.find()) {
                // Group 1 is the key, Group 2 is the value
                passportInfo.put(matcher.group(1), matcher.group(2));
            }
        }

        return passportInfo;
    }
}
