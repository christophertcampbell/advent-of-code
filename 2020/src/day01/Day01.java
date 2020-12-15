/*
 * Solution to Advent of Code 2020, Day 01
 * by Chris Campbell
 */

package day01;

import java.util.Arrays;

public class Day01
{
    /*
     * Search an array of integers for two numbers
     * whose sum is 2020, and return the product of
     * the two numbers
     */
    public int partA(int[] nums)
    {      
        return getProductOfTwoNumbersWhoseSumEquals(nums, 2020);
    }

    /*
     * Search an array of integers for three numbers
     * whose sum is 2020, and return the product of
     * the three numbers
     */
    public int partB(int[] nums)
    {
        for (int num : nums)
        {
            /*
             * For each number, determine the matching addend
             * which would equal 2020 and then check if there
             * are two numbers in the array which sum up to
             * equal the matching addend
             */
            int matchingNum = 2020 - num;
            int matchingProduct = getProductOfTwoNumbersWhoseSumEquals(nums, matchingNum);
            if (matchingProduct > 0) {
                return num * matchingProduct;
            }
        }

        return -1;
    }

    /*
     * Searches an array for two numbers whose sum equals the target sum
     * Returns the product of the two numbers, or -1 if a match is not found
     */
    private int getProductOfTwoNumbersWhoseSumEquals(int[] nums, int targetSum)
    {
        // Sort the array so we can use a binary search
        Arrays.sort(nums);

        for (int num : nums)
        {
            /*
             * If the complementary number is present in the array,
             * return the product
             */
            int matchingNum = targetSum - num;
            if (Arrays.binarySearch(nums, matchingNum) >= 0) {
                return num * matchingNum;
            }
        }
        
        return -1;
    }

    /*
     * Code to run the day's solutions
     */

    private static final int[] testInput = {1721, 979, 366, 299, 675, 1456};
    
    private static final int[] challengeInput = {1713, 1281, 1185, 1501, 1462, 1752, 1363, 1799, 1071,
        1446, 1685, 1706, 1726, 1567, 1867, 1376, 1445, 1971, 1429, 1749, 438, 1291, 1261, 1585, 1859,
        1835, 1630, 1975, 1467, 1829, 1669, 1638, 1961, 1719, 1238, 1751, 1514, 1744, 1547, 1677, 1811,
        1820, 1371, 740, 1925, 1803, 1753, 1208, 1772, 1642, 1140, 1838, 1444, 1321, 1556, 1635, 1687,
        688, 1650, 1580, 1290, 1812, 1814, 1384, 1426, 1374, 1973, 1791, 1643, 1846, 1676, 1724, 1810,
        1911, 1765, 945, 1357, 1919, 1994, 1697, 1632, 1449, 1539, 1725, 1963, 1879, 1731, 1904, 1392,
        1823, 1420, 1504, 204, 1661, 1575, 1401, 1806, 1417, 1965, 1960, 1990, 1409, 1649, 1566, 1957,
        514, 1464, 1352, 1841, 1601, 1473, 1309, 1421, 1190, 1582, 1825, 655, 1666, 1878, 1891, 1579,
        1176, 1557, 1910, 1747, 1388, 1493, 1372, 1522, 1515, 1745, 1494, 1763, 1147, 1364, 1469, 1165,
        1901, 1368, 1234, 1308, 1416, 1678, 1541, 1509, 1427, 1223, 1496, 1600, 1383, 1295, 1415, 1890,
        1694, 1793, 1529, 1984, 1576, 1244, 1348, 1085, 1770, 1358, 1611, 1159, 1964, 1647, 818, 1246,
        1458, 1936, 1370, 1659, 1923, 1619, 1604, 1354, 1118, 1657, 1945, 1898, 1948, 798, 769, 1689,
        1821, 1979, 1460, 1832, 1596, 1679, 1818, 1815, 1977, 1634, 1828, 1386, 1284, 1569, 1970};
    
    public static void main(String[] args)
    {
        Day01 day01 = new Day01();
        
        System.out.printf("Day 1 - Part A - Test output: %d\n", day01.partA(testInput));
        System.out.printf("Day 1 - Part A - Challenge output: %d\n", day01.partA(challengeInput));
        System.out.printf("Day 1 - Part B - Test output: %d\n", day01.partB(testInput));
        System.out.printf("Day 1 - Part B - Challenge output: %d\n", day01.partB(challengeInput));
    }
}