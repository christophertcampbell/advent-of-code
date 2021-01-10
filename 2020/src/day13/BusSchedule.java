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
     * Works for all test inputs, but takes too long to solve the
     * actual challenge input
     */
    public long getEarliestSynchronizedTime()
    {
        // Find the bus with the highest ID to use as our control
        // in order to minimize the number of times we need to loop
        Bus controlBus = null;
        for (Bus bus : buses)
        {
            if (controlBus == null || bus.getID() > controlBus.getID()) {
                controlBus = bus;
            }
        }

        long baseTime = controlBus.getID() - controlBus.getIndex();
        boolean busesAreSynchronized = false;

        while (busesAreSynchronized == false)
        {
            // Increment the base time to the next multiple of the control bus id
            baseTime += controlBus.getID();
            busesAreSynchronized = true;

            // Loop through all buses and check if they are synchronized
            // at this base time
            for (int i = 0; i < buses.size(); i++)
            {
                Bus currentBus = buses.get(i);
                if ((baseTime + currentBus.getIndex()) % currentBus.getID() != 0) {
                    busesAreSynchronized = false;
                    break;
                }
            }
        }

        return baseTime;
    }
}
