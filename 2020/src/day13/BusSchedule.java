package day13;

import java.util.ArrayList;

public class BusSchedule
{
    private ArrayList<Bus> buses;

    public BusSchedule(String busIDsCommaSeparated)
    {
        buses = parseBuses(busIDsCommaSeparated);
    }

    private ArrayList<Bus> parseBuses(String busIDsCommaSeparated)
    {
        String[] busIDStrings = busIDsCommaSeparated.split(",", 0);
        ArrayList<Bus> busList = new ArrayList<Bus>();
        for (int i = 0; i < busIDStrings.length; i++)
        {
            if (busIDStrings[i].equals("x")) continue;
            busList.add(new Bus(Integer.parseInt(busIDStrings[i]), i));
        }
        return busList;
    }

    /**
     * Finds the bus which will be at the station soonest after
     * the target time and returns the bus ID multiplied by
     * the number of minutes after the target time
     */
    public int getEarliestBusMultiple(int targetTime)
    {
        int soonestTimeDifference = Integer.MAX_VALUE;
        int soonestBusID = -1;

        // Find the bus arriving soonest after the target time
        for (Bus bus : buses)
        {
            int timeDifference = bus.getID() - (targetTime % bus.getID());
            if (timeDifference < soonestTimeDifference) {
                soonestTimeDifference = timeDifference;
                soonestBusID = bus.getID();
            }
        }

        return soonestBusID * soonestTimeDifference;
    }

    /**
     * Returns the earliest time at which all buses are synchronized
     * (each bus arrives at a time equal to the time + the bus index)
     * 
     * I got a hint for this one from a friend's solution. My initial
     * solution was too brute force and would solve the tests but
     * not the actual challenge input (took too long).
     */
    public long getEarliestSynchronizedTime()
    {
        // Begin with the first bus id as the first arrival time and period
        long baseTime = buses.get(0).getID();
        long period = baseTime;

        for (Bus bus : buses)
        {
            // Skip first bus
            if (bus.getIndex() == 0) continue;

            // Find when the next bus is synchronized
            boolean isSynchronized = false;
            while (isSynchronized == false)
            {
                baseTime += period;
                if ((baseTime + bus.getIndex()) % bus.getID() == 0) {
                    isSynchronized = true;
                }
            }

            // Now that the current bus is synchronized, multiply
            // the period by the current bus id because any further
            // synchronizations will happen on this period
            period *= bus.getID();
        }

        return baseTime;
    }
}
