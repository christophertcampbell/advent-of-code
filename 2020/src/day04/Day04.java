package day04;

import utilities.FileIO;
import utilities.Test;

public class Day04
{
    /**
     * Returns a count of how many passports are valid by the Part A method
     */
    public int partA(String[][] input)
    {
        PassportValidator passportValidator = new PassportValidator();
        return parseAndValidate(input, passportValidator);
    }

    /**
     * Returns a count of how many passports are valid by the Part B method
     */
    public int partB(String[][] input)
    {
        PassportValidator passportValidator = new PassportValidatorAdvanced();
        return parseAndValidate(input, passportValidator);
    }

    /**
     * Parses the input and uses the provided validator to validate each passport record
     * Returns a count of how many passports are valid
     */
    private int parseAndValidate(String[][] input, PassportValidator passportValidator)
    {
        int validPassports = 0;

        for (String[] group : input)
        {
            PassportInfo passport = new PassportInfo(group);
            if (passportValidator.validate(passport)) {
                validPassports++;
            }
        }

        return validPassports;
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[][] testInputPartA = FileIO.readAsGroupsOfStrings("2020/src/day04/Day04TestInputPartA.txt");
        String[][] testInputPartB = FileIO.readAsGroupsOfStrings("2020/src/day04/Day04TestInputPartB.txt");
        String[][] realInput = FileIO.readAsGroupsOfStrings("2020/src/day04/Day04Input.txt");

        Day04 day04 = new Day04();

        Test.assertEqual("Day 4 - Part A - Test input", day04.partA(testInputPartA), 2);
        Test.assertEqual("Day 4 - Part A - Challenge input", day04.partA(realInput), 204);
        Test.assertEqual("Day 4 - Part B - Test input", day04.partB(testInputPartB), 4);
        Test.assertEqual("Day 4 - Part B - Challenge input", day04.partB(realInput), 179);

        Test.assertEqual("Day 4 - Part B - Birth Year Validation", PassportValidatorAdvanced.validateBirthYear(2002), true);
        Test.assertEqual("Day 4 - Part B - Birth Year Validation", PassportValidatorAdvanced.validateBirthYear(2003), false);
        Test.assertEqual("Day 4 - Part B - Height Validation", PassportValidatorAdvanced.validateHeight("60in"), true);
        Test.assertEqual("Day 4 - Part B - Height Validation", PassportValidatorAdvanced.validateHeight("190cm"), true);
        Test.assertEqual("Day 4 - Part B - Height Validation", PassportValidatorAdvanced.validateHeight("190in"), false);
        Test.assertEqual("Day 4 - Part B - Height Validation", PassportValidatorAdvanced.validateHeight("190"), false);
        Test.assertEqual("Day 4 - Part B - Hair Color Validation", PassportValidatorAdvanced.validateHairColor("#123abc"), true);
        Test.assertEqual("Day 4 - Part B - Hair Color Validation", PassportValidatorAdvanced.validateHairColor("#123abz"), false);
        Test.assertEqual("Day 4 - Part B - Hair Color Validation", PassportValidatorAdvanced.validateHairColor("123abc"), false);
        Test.assertEqual("Day 4 - Part B - Eye Color Validation", PassportValidatorAdvanced.validateEyeColor("brn"), true);
        Test.assertEqual("Day 4 - Part B - Eye Color Validation", PassportValidatorAdvanced.validateEyeColor("wat"), false);
        Test.assertEqual("Day 4 - Part B - Passport ID Validation", PassportValidatorAdvanced.validatePassportID("000000001"), true);
        Test.assertEqual("Day 4 - Part B - Passport ID Validation", PassportValidatorAdvanced.validatePassportID("0123456789"), false);
    }

    public static void main(String[] args)
    {
        run();
    }
}
