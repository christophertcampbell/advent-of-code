package day17;

public class Point3d
{
  private int x;
  private int y;
  private int z;

  public Point3d(int x, int y, int z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }

  public int getZ()
  {
    return z;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof Point3d == false) {
      return false;
    }

    Point3d otherPoint = (Point3d) obj;
    return x == otherPoint.getX() &&
           y == otherPoint.getY() &&
           z == otherPoint.getZ();
  }

  @Override
  public int hashCode()
  {
    return x * y * z + (x + y + z);
  }

  @Override
  public String toString()
  {
    return String.format("(%d, %d, %d)", x, y, z);
  }
}
