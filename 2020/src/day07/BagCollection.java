package day07;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class BagCollection
{
    private static final Pattern PATTERN_BAG_NAME = Pattern.compile("^(.+) bags .*");
    private static final Pattern PATTERN_INNER_BAG = Pattern.compile("((\\d+) (.+?)) bag");

    private HashMap<String, Bag> bags = new HashMap<String, Bag>();

    /**
     * Adds a new bag to the collection by its description
     */
    public void add(String bagDescription)
    {
        // Parse the bag name
        Matcher bagNameMatcher = PATTERN_BAG_NAME.matcher(bagDescription);
        if (bagNameMatcher.matches() == false) {
            System.out.println("Unable to parse bag name");
            return;
        }
        String bagName = bagNameMatcher.group(1);

        // If the bag object doesn't exist yet in the set, create it
        if (bags.containsKey(bagName) == false) {
            bags.put(bagName, new Bag(bagName));
        }

        // Parse inner bags
        // If the bag contains inner bags, add them to the bag
        Matcher innerBagMatcher = PATTERN_INNER_BAG.matcher(bagDescription);
        while (innerBagMatcher.find())
        {
            int innerBagCount = Integer.parseInt(innerBagMatcher.group(2));
            String innerBagName = innerBagMatcher.group(3);

            // If the inner bag object doesn't exist yet in the set, create it
            if (bags.containsKey(innerBagName) == false) {
                bags.put(innerBagName, new Bag(innerBagName));
            }

            // Add the inner bag to the outer bag
            bags.get(bagName).addInnerBag(bags.get(innerBagName), innerBagCount);
        }
    }

    /**
     * Returns an array of all bags in the collection
     */
    public Bag[] getBags()
    {
        return bags.values().toArray(new Bag[0]); 
    }

    /**
     * Returns the bag of the specified name, or null if not found
     */
    public Bag getBag(String bagName)
    {
        return bags.containsKey(bagName) ? bags.get(bagName) : null;
    }
}
