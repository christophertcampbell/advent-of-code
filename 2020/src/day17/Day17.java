package day17;

import utilities.FileIO;
import utilities.Test;

public class Day17
{
  /**
   * Runs a modified version of Conway's Game of Life
   * on an initial set of 3d points and returns
   * the final count of active points after 6 iterations
   */
  public static int partA(String[] input)
  {
    PointMap3d map = PointMapFactory.buildMap3d(input);

    for (int i = 1; i <= 6; i++)
    {
      // Expand the map and create a clone
      map.expandMap();
      PointMap3d newMap = map.getClone();

      for (Point3d point : map.getAllPoints())
      {
        // Count active neighbors
        int activeNeighborCount = map.getCountActiveAdjacentPoints(point);
        boolean isActive = map.isActive(point);

        // Apply the rules and update the point in the new map if necessary
        if (isActive && activeNeighborCount != 2 && activeNeighborCount != 3) {
          newMap.put(point, false);
        } else if (isActive == false && activeNeighborCount == 3) {
          newMap.put(point, true);
        }
      }

      map = newMap;
    }

    return map.getCountActivePoints();
  }

  /**
   * Runs the day's solutions
   */
  public static void run()
  {
    String[] testInput = FileIO.readAsStrings("2020/src/day17/Day17TestInput.txt");
    String[] realInput = FileIO.readAsStrings("2020/src/day17/Day17Input.txt");

    Test.assertEqual("Day 17 - Part A - Test input", partA(testInput), 112);
    Test.assertEqual("Day 17 - Part A - Challenge input", partA(realInput), 384);
  }

  public static void main(String[] args)
  {
    run();
  }
}
