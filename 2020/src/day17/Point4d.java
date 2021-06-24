package day17;

public class Point4d
{
  private int x;
  private int y;
  private int z;
  private int w;

  public Point4d(int x, int y, int z, int w)
  {
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = w;
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

  public int getW()
  {
    return w;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof Point4d == false) {
      return false;
    }

    Point4d otherPoint = (Point4d) obj;
    return x == otherPoint.getX() &&
           y == otherPoint.getY() &&
           z == otherPoint.getZ() &&
           w == otherPoint.getW();
  }

  @Override
  public int hashCode()
  {
    return x * y * z * w + (x + y + z + w);
  }

  @Override
  public String toString()
  {
    return String.format("(%d, %d, %d, %d)", x, y, z, w);
  }
}
