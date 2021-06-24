package utilities;

public class Timing
{
  private static long startMilliseconds = 0;
  private static long endMilliseconds = 0;

  /**
   * Sets the start time
   */
  public static void start()
  {
    startMilliseconds = System.currentTimeMillis();
  }

  /**
   * Sets the end time and prints the elapsed time to the console
   */
  public static void end()
  {
    endMilliseconds = System.currentTimeMillis();
    print();
  }

  /**
   * Prints the elapsed time to the console
   */
  private static void print()
  {
    float elapsedSeconds = (endMilliseconds - startMilliseconds) / 1000F;
    System.out.printf("Completed in %.2f second(s)\n", elapsedSeconds);
  }
}
