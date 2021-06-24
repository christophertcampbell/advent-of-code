package day17;

import java.util.ArrayList;
import java.util.HashSet;

public class PointSet3d
{
  private HashSet<Point3d> activePoints;

  public PointSet3d()
  {
    activePoints = new HashSet<>();
  }

  /**
   * Adds a point to the set
   */
  public void add(Point3d point)
  {
    activePoints.add(point);
  }

  /**
   * Removes a point from the set
   */
  public void remove(Point3d point)
  {
    activePoints.remove(point);
  }

  /**
   * Returns true if the point is in the set, false otherwise
   */
  public boolean contains(Point3d point)
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
  public ArrayList<Point3d> getAll()
  {
    return new ArrayList<Point3d>(activePoints);
  }

  /**
   * Returns an ArrayList of all neighboring points,
   * whether or not they exist in the set
   */
  public ArrayList<Point3d> getAllAdjacent(Point3d point)
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
   * Returns a count of how many of the provided points are in the set
   */
  public int getCountInSet(ArrayList<Point3d> points)
  {
    int count = 0;
    for (Point3d point : points)
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
  public PointSet3d clone()
  {
    PointSet3d clonedMap = new PointSet3d();

    for (Point3d point : activePoints)
    {
      clonedMap.add(point);
    }

    return clonedMap;
  }
}
