package day13;

import java.util.ArrayList;

public class BusSchedule
{
    private ArrayList<Integer> busIDs;

    public BusSchedule(String busIDsCommaSeparated)
    {
        busIDs = parseBusIDs(busIDsCommaSeparated);
    }

    /**
     * Parses the numeric bus IDs from a comma-separated string
     */
    private ArrayList<Integer> parseBusIDs(String busIDsCommaSeparated)
    {
        String[] busIDStrings = busIDsCommaSeparated.split(",", 0);
        ArrayList<Integer> busIDList = new ArrayList<Integer>();
        for (String busIDString : busIDStrings)
        {
            try
            {
                busIDList.add(Integer.parseInt(busIDString));
            }
            catch (Exception e)
            {
                // Do nothing
            }
        }
        return busIDList;
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
        for (Integer busID : busIDs)
        {
            int timeDifference = busID - (targetTime % busID);
            if (timeDifference < soonestTimeDifference) {
                soonestTimeDifference = timeDifference;
                soonestBusID = busID;
            }
        }
        
        return soonestBusID * soonestTimeDifference;
    }
}
