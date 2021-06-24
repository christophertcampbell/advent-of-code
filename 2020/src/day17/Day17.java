package day17;

import java.util.ArrayList;
import utilities.FileIO;
import utilities.Test;
import utilities.Timing;

enum Mode
{
  ANALYZE3D, ANALYZE4D
}

public class Day17
{
  /**
   * Runs the challenge in 3d mode
   */
  public static int partA(String[] input)
  {
    return runGame(input, Mode.ANALYZE3D);
  }

  /**
   * Runs the challenge in 4d mode
   */
  public static int partB(String[] input)
  {
    return runGame(input, Mode.ANALYZE4D);
  }

  /**
   * Runs a modified version of Conway's Game of Life and returns
   * the final count of active points after 6 iterations
   */
  public static int runGame(String[] input, Mode mode)
  {
    PointSet4d activePoints = PointSetFactory.buildSetOfActivePoints(input);

    for (int i = 1; i <= 6; i++)
    {
      // Create a clone of the set
      PointSet4d newActivePoints = activePoints.clone();

      // Construct a set of all active points and their neighbors
      // to analyze. This makes sure we analyze any neighboring
      // inactive points along with the active points.
      PointSet4d pointsToAnalyze = new PointSet4d();
      for (Point4d activePoint : activePoints.getAll())
      {
        pointsToAnalyze.add(activePoint);
        ArrayList<Point4d> adjacentPoints = mode == Mode.ANALYZE3D
          ? activePoints.getAllAdjacent3d(activePoint)
          : activePoints.getAllAdjacent4d(activePoint);
        pointsToAnalyze.addAll(adjacentPoints);
      }

      // Analyze all active and adjacent points
      for (Point4d pointToAnalyze : pointsToAnalyze.getAll())
      {
        // Check if the point is active
        boolean isActive = activePoints.contains(pointToAnalyze);

        // Count active neighbors
        ArrayList<Point4d> adjacentPoints = mode == Mode.ANALYZE3D
          ? activePoints.getAllAdjacent3d(pointToAnalyze)
          : activePoints.getAllAdjacent4d(pointToAnalyze);
        int activeNeighborCount = activePoints.getCountInSet(adjacentPoints);

        // Apply the rules and update the point in the new set if necessary
        if (isActive && activeNeighborCount != 2 && activeNeighborCount != 3) {
          newActivePoints.remove(pointToAnalyze);
        } else if (isActive == false && activeNeighborCount == 3) {
          newActivePoints.add(pointToAnalyze);
        }
      }

      // Update the set of active points
      activePoints = newActivePoints;
    }

    return activePoints.size();
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
    Test.assertEqual("Day 17 - Part B - Test input", partB(testInput), 848);
    Test.assertEqual("Day 17 - Part B - Challenge input", partB(realInput), 2012);
  }

  public static void main(String[] args)
  {
    Timing.start();
    run();
    Timing.end();
  }
}
