package day17;

import java.util.ArrayList;
import java.util.HashSet;

public class PointSet4d
{
  private HashSet<Point4d> activePoints;

  public PointSet4d()
  {
    activePoints = new HashSet<>();
  }

  /**
   * Adds a point to the set
   */
  public void add(Point4d point)
  {
    activePoints.add(point);
  }

  /**
   * Removes a point from the set
   */
  public void remove(Point4d point)
  {
    activePoints.remove(point);
  }

  /**
   * Returns true if the point is in the set, false otherwise
   */
  public boolean contains(Point4d point)
  {
    return activePoints.contains(point);
  }

  /**
   * Returns a count of all points in the set
   */
  public int size()
  {
    return activePoints.size();
  }

  /**
   * Returns an ArrayList of all points in the set
   */
  public ArrayList<Point4d> getAll()
  {
    return new ArrayList<Point4d>(activePoints);
  }

  /**
   * Returns an ArrayList of all neighboring points in 3d space,
   * whether or not they exist in the set
   */
  public ArrayList<Point4d> getAllAdjacent3d(Point4d point)
  {
    ArrayList<Point4d> adjacentPoints = new ArrayList<>();

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

          adjacentPoints.add(new Point4d(x, y, z, 0));
        }
      }
    }

    return adjacentPoints;
  }

  /**
   * Returns an ArrayList of all neighboring points in 4d space,
   * whether or not they exist in the set
   */
  public ArrayList<Point4d> getAllAdjacent4d(Point4d point)
  {
    ArrayList<Point4d> adjacentPoints = new ArrayList<>();

    // Iterate through all adjacent points and add to the ArrayList
    for (int x = point.getX() - 1; x <= point.getX() + 1; x++)
    {
      for (int y = point.getY() - 1; y <= point.getY() + 1; y++)
      {
        for (int z = point.getZ() - 1; z <= point.getZ() + 1; z++)
        {
          for (int w = point.getW() - 1; w <= point.getW() + 1; w++)
          {
            if (x == point.getX() && y == point.getY() && z == point.getZ() && w == point.getW()) {
              continue;
            }

            adjacentPoints.add(new Point4d(x, y, z, w));
          }
        }
      }
    }

    return adjacentPoints;
  }

  /**
   * Returns a count of how many of the provided points are in the set
   */
  public int getCountInSet(ArrayList<Point4d> points)
  {
    int count = 0;
    for (Point4d point : points)
    {
      if (activePoints.contains(point)) {
        count++;
      }
    }
    return count;
  }

  /**
   * Returns a shallow clone of the point set
   */
  public PointSet4d clone()
  {
    PointSet4d clonedMap = new PointSet4d();

    for (Point4d point : activePoints)
    {
      clonedMap.add(point);
    }

    return clonedMap;
  }
}
