package day17;

import java.util.ArrayList;
import java.util.HashMap;

public class PointMap3d
{
  private HashMap<Point3d, Boolean> pointsMap;

  public PointMap3d()
  {
    pointsMap = new HashMap<>();
  }

  /**
   * Adds a new point to the map, or
   * updates the point if it already exists in the map.
   */
  public void put(Point3d point, boolean isActive)
  {
    pointsMap.put(point, isActive);
  }

  /**
   * Expands the map to contain all surrounding points
   */
  public void expandMap()
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
   * Returns an ArrayList of all neighboring points
   */
  private ArrayList<Point3d> getAdjacentPoints(Point3d point)
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
   * Returns true if the point is active, false otherwise
   */
  public boolean isActive(Point3d point)
  {
    return pointsMap.containsKey(point) && pointsMap.get(point) == true;
  }

  /**
   * Returns an ArrayList of all poins in the map
   */
  public ArrayList<Point3d> getAllPoints()
  {
    return new ArrayList<Point3d>(pointsMap.keySet());
  }

  /**
   * Returns the count of all adjacent points that are active
   */
  public int getCountActiveAdjacentPoints(Point3d point)
  {
    int activeNeighborCount = 0;
    ArrayList<Point3d> adjacentPoints = getAdjacentPoints(point);

    for (Point3d adjacentPoint : adjacentPoints)
    {
      if (isActive(adjacentPoint)) {
        activeNeighborCount++;
      }
    }

    return activeNeighborCount;
  }

  /**
   * Returns a count of all points that are active
   */
  public int getCountActivePoints()
  {
    int count = 0;
    for (Point3d point : pointsMap.keySet())
    {
      if (isActive(point)) {
        count++;
      }
    }
    return count;
  }

  /**
   * Returns a deep clone of the point map
   */
  public PointMap3d getClone()
  {
    PointMap3d clonedMap = new PointMap3d();

    for (Point3d point : pointsMap.keySet())
    {
      clonedMap.put(point, pointsMap.get(point));
    }

    return clonedMap;
  }
}
