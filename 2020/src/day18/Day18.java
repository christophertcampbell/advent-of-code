package day18;

import utilities.FileIO;
import utilities.Test;

public class Day18
{
  /**
   * Returns the sum of the arithmetic expressions,
   * evaluated in left-to-right fashion with all
   * operators having the same precedence
   */
  public static long partA(String[] input)
  {
    long sum = 0;
    for (String line : input)
    {
      ArithmeticExpression expression = new ArithmeticExpression(line);
      sum += expression.evaluate();
    }
    return sum;
  }

  /**
   * Runs the day's solutions
   */
  public static void run()
  {
    String[] testInput = FileIO.readAsStrings("2020/src/day18/Day18TestInput.txt");
    String[] realInput = FileIO.readAsStrings("2020/src/day18/Day18Input.txt");

    Test.assertEqual("Day 18 - Part A - Test input - Line 1", partA(new String[]{testInput[0]}), 51);
    Test.assertEqual("Day 18 - Part A - Test input - Line 2", partA(new String[]{testInput[1]}), 26);
    Test.assertEqual("Day 18 - Part A - Test input - Line 3", partA(new String[]{testInput[2]}), 437);
    Test.assertEqual("Day 18 - Part A - Test input - Line 4", partA(new String[]{testInput[3]}), 12240);
    Test.assertEqual("Day 18 - Part A - Test input - Line 5", partA(new String[]{testInput[4]}), 13632);
    Test.assertEqual("Day 18 - Part A - Test input", partA(testInput), 26386);
    Test.assertEqual("Day 18 - Part A - Challenge input", partA(realInput), 23507031841020L);
  }

  public static void main(String[] args)
  {
    run();
  }
}
