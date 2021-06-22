package day17;

public class PointMapFactory
{
  /**
   * Builds a map of 3d points from an array of strings
   * whose characters represent points on an x/y grid
   * with '#' indicating an active point and '.' indicating
   * an inactive point
   */
  public static PointMap3d buildMap3d(String[] input)
  {
    PointMap3d pointsMap = new PointMap3d();
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
}
