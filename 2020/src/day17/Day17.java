package day17;

import java.util.ArrayList;
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
    PointSet3d activePoints = PointSetFactory.buildSetOfActivePoints3d(input);

    for (int i = 1; i <= 6; i++)
    {
      // Create a clone
      PointSet3d newActivePoints = activePoints.clone();

      for (Point3d activePoint : activePoints.getAll())
      {
        // Build a set of the point and its neighbors to analyze.
        // This makes sure we analyze any neighboring inactive
        // points along with the active points.
        ArrayList<Point3d> pointsToAnalyze = new ArrayList<>();
        pointsToAnalyze.add(activePoint);
        pointsToAnalyze.addAll(activePoints.getAllAdjacent(activePoint));

        for (Point3d pointToAnalyze : pointsToAnalyze)
        {
          boolean isActive = activePoints.contains(pointToAnalyze);

          // Count active neighbors
          ArrayList<Point3d> adjacentPoints = activePoints.getAllAdjacent(pointToAnalyze);
          int activeNeighborCount = activePoints.getCountInSet(adjacentPoints);

          // Apply the rules and update the point in the new set if necessary
          if (isActive && activeNeighborCount != 2 && activeNeighborCount != 3) {
            newActivePoints.remove(pointToAnalyze);
          } else if (isActive == false && activeNeighborCount == 3) {
            newActivePoints.add(pointToAnalyze);
          }
        }
      }

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
  }

  public static void main(String[] args)
  {
    run();
  }
}
