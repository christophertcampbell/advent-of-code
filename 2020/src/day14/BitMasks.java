package day14;

import java.util.ArrayList;

public class BitMasks
{
    public long setMask = 0;
    public long unsetMask = 0;
    public ArrayList<Long> floatingBitMasks = new ArrayList<>();

    public BitMasks() {}

    public BitMasks (long setMask, long unsetMask, ArrayList<Long> floatingBitMasks)
    {
        this.setMask = setMask;
        this.unsetMask = unsetMask;
        this.floatingBitMasks = floatingBitMasks;
    }
}
