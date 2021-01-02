package day07;

import java.util.HashMap;

public class Bag
{
    private String name;
    private HashMap<Bag, Integer> innerBags;

    /**
     * constructs a new Bag instance
     */
    public Bag(String name)
    {
        this.name = name;
        innerBags = new HashMap<Bag, Integer>();
    }

    /**
     * Adds a new inner bag
     */
    public void addInnerBag(Bag innerBag, int quantity)
    {
        innerBags.put(innerBag, quantity);
    }

    /**
     * Returns the name of the bag
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns true if the bag, or any of its own inner bags, contains
     * an inner bag of the specified name 
     */
    public boolean containsInnerBag(String innerBagName)
    {
        for (HashMap.Entry<Bag, Integer> innerBagEntry : innerBags.entrySet())
        {
            Bag innerBag = innerBagEntry.getKey();
            if (innerBag.getName().equals(innerBagName) || innerBag.containsInnerBag(innerBagName)) {
                return true;
            }
        }

        return false;
    }
}
