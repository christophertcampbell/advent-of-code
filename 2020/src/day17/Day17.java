package day17;

import java.util.ArrayList;
import java.util.HashMap;
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
    HashMap<Point3d, Boolean> pointsMap = buildMap(input);

    for (int i = 1; i <= 6; i++)
    {
      expandMap(pointsMap);
      pointsMap = applyRulesForChangingState(pointsMap);
    }

    return getCountActivePoints(pointsMap);
  }

  /**
   * Builds the initial map of points
   */
  public static HashMap<Point3d, Boolean> buildMap(String[] input)
  {
    HashMap<Point3d, Boolean> pointsMap = new HashMap<>();
    int x = 0;
    int y = 0;

    /**
     * Iterate through each character on each line
     * and translate into a map of 3d points
     */
    for (String line : input)
    {
      for (char c : line.toCharArray())
      {
        Point3d point = new Point3d(x, y, 0);
        boolean isActive = c == '#';
        pointsMap.put(point, isActive);
        x++;
      }
      x = 0;
      y++;
    }

    return pointsMap;
  }

  /**
   * Expands the map to contain all surrounding points
   */
  public static void expandMap(HashMap<Point3d, Boolean> pointsMap)
  {
    ArrayList<Point3d> newPoints = new ArrayList<>();

    /**
     * Iterate through all neighbors of all points and
     * flag for adding to the map if they are not already present
     */
    for (Point3d point : pointsMap.keySet())
    {
      ArrayList<Point3d> adjacentPoints = getAdjacentPoints(point);
      for (Point3d adjacentPoint : adjacentPoints)
      {
        if (pointsMap.containsKey(adjacentPoint) == false) {
          newPoints.add(adjacentPoint);
        }
      }
    }

    /**
     * Add missing neighboring points to the map
     */
    for (Point3d newPoint : newPoints)
    {
      pointsMap.put(newPoint, false);
    }
  }

  /**
   * Applies the modified Game of Life rules for changing the
   * points' active/inactive states. Returns the mofified map.
   */
  public static HashMap<Point3d, Boolean> applyRulesForChangingState(HashMap<Point3d, Boolean> pointsMap)
  {
    HashMap<Point3d, Boolean> newMap = new HashMap<>();

    for (Point3d point : pointsMap.keySet())
    {
      // Count active neighbors
      int activeNeighborCount = 0;
      ArrayList<Point3d> adjacentPoints = getAdjacentPoints(point);
      for (Point3d adjacentPoint : adjacentPoints)
      {
        if (pointsMap.containsKey(adjacentPoint) && pointsMap.get(adjacentPoint) == true) {
          activeNeighborCount++;
        }
      }

      // Apply the rules and add the point to the new map with the correct active/inactive state
      boolean isActive = pointsMap.get(point);
      if (isActive && activeNeighborCount != 2 && activeNeighborCount != 3) {
        newMap.put(point, false);
      } else if (isActive == false && activeNeighborCount == 3) {
        newMap.put(point, true);
      } else {
        newMap.put(point, isActive);
      }
    }

    return newMap;
  }

  /**
   * Returns an ArrayList of all neighboring points
   */
  public static ArrayList<Point3d> getAdjacentPoints(Point3d point)
  {
    ArrayList<Point3d> adjacentPoints = new ArrayList<>();

    // Iterate through all adjacent points and add to the ArrayList
    for (int x = point.getX() - 1; x <= point.getX() + 1; x++)
    {
      for (int y = point.getY() - 1; y <= point.getY() + 1; y++)
      {
        for (int z = point.getZ() - 1; z <= point.getZ() + 1; z++)
        {
          if (x == point.getX() && y == point.getY() && z == point.getZ()) {
            continue;
          }

          adjacentPoints.add(new Point3d(x, y, z));
        }
      }
    }

    return adjacentPoints;
  }

  /**
   * Returns a count of all active points
   */
  public static int getCountActivePoints(HashMap<Point3d, Boolean> pointsMap)
  {
    int count = 0;
    for (Point3d point : pointsMap.keySet())
    {
      if (pointsMap.get(point)) {
        count++;
      }
    }
    return count;
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
