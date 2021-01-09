package day13;

import java.util.ArrayList;

public class BusSchedule
{
    private ArrayList<Bus> buses;

    public BusSchedule(String busIDsCommaSeparated)
    {
        buses = parseBuses(busIDsCommaSeparated);
    }

    /**
     * Parses the numeric bus IDs from a comma-separated string
     */
    private ArrayList<Bus> parseBuses(String busIDsCommaSeparated)
    {
        String[] busIDStrings = busIDsCommaSeparated.split(",", 0);
        ArrayList<Bus> busList = new ArrayList<Bus>();
        for (int i = 0; i < busIDStrings.length; i++)
        {
            try
            {
                busList.add(new Bus(Integer.parseInt(busIDStrings[i]), i));
            }
            catch (Exception e)
            {
                // Do nothing
            }
        }
        return busList;
    }

    /**
     * Find the bus which will be at the station
     * closest to the target time
     * 
     * Returns the bus ID multiplied by the
     * arrival time after the target time
     */
    public int getEarliestBusMultiple(int targetTime)
    {
        int soonestTimeDifference = Integer.MAX_VALUE;
        int soonestBusID = -1;

        // Loop through the bus IDs and find the bus
        // arriving soonest after the target time
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
}
