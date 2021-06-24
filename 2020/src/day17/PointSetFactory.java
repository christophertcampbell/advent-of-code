package day17;

public class PointSetFactory
{
  /**
   * Builds a map of 4d points from an array of strings
   * whose characters represent points on an x/y grid
   * with '#' indicating an active point and '.' indicating
   * an inactive point
   */
  public static PointSet4d buildSetOfActivePoints(String[] input)
  {
    PointSet4d pointsMap = new PointSet4d();
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
        Point4d point = new Point4d(x, y, 0, 0);
        boolean isActive = c == '#';

        if (isActive) {
          pointsMap.add(point);
        }

        x++;
      }
      x = 0;
      y++;
    }

    return pointsMap;
  }
}
