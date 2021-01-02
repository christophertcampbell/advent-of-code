package day07;

import utilities.FileIO;
import utilities.Test;

public class Day07
{
    /**
     * Returns a count of how many bag types can
     * directly or indirectly contain a shiny gold bag
     */
    public static int partA(String[] input)
    {
        // Create a new bag collection from our input
        BagCollection bagCollection = new BagCollection();
        for (String bagDescription : input)
        {
            bagCollection.add(bagDescription);
        }

        // Count how many bags can contain a shiny gold bag
        int count = 0;
        for (Bag bag : bagCollection.getBags())
        {
            if (bag.containsInnerBag("shiny gold")) {
                count ++;
            }
        }

        return count;
    }

    /**
     * Returns a count of how many inner bags
     * a shiny gold bag contains
     */
    public static int partB(String[] input)
    {
        // Create a new bag collection from our input
        BagCollection bagCollection = new BagCollection();
        for (String bagDescription : input)
        {
            bagCollection.add(bagDescription);
        }

        Bag shinyGoldBag = bagCollection.getBag("shiny gold");
        return shinyGoldBag == null ? -1 : shinyGoldBag.getInnerBagCount();
    }

    /**
     * Runs the day's solutions
     */
    public static void run()
    {
        String[] testInput = FileIO.readAsStrings("2020/src/day07/Day07TestInput.txt");
        String[] testInput2 = FileIO.readAsStrings("2020/src/day07/Day07TestInput2.txt");
        String[] realInput = FileIO.readAsStrings("2020/src/day07/Day07Input.txt");

        Test.assertEqual("Day 7 - Part A - Test input", partA(testInput), 4);
        Test.assertEqual("Day 7 - Part A - Challenge input", partA(realInput), 103);
        Test.assertEqual("Day 7 - Part B - Test input", partB(testInput), 32);
        Test.assertEqual("Day 7 - Part B - Test input2", partB(testInput2), 126);
        Test.assertEqual("Day 7 - Part B - Challenge input", partB(realInput), 1469);
    }

    public static void main(String[] args)
    {
        run();
    }
}