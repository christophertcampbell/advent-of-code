package day12;

public class NavInstruction
{
    private char direction;
    private int amount;

    public NavInstruction(char direction, int amount)
    {
        this.direction = direction;
        this.amount = amount;
    }

    public char getDirection()
    {
        return direction;
    }

    public int getAmount()
    {
        return amount;
    }
}
