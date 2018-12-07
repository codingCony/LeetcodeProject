/**
 * Created by yuegu on 11/14/18.
 */
public class _165_CompareVersionNumbers {

  /**
   * Compare two version numbers version1 and version2. If version1 > version2 return 1; if version1
   * < version2 return -1;otherwise return 0.
   *
   * You may assume that the version strings are non-empty and contain only digits and the .
   * character. The . character does not represent a decimal point and is used to separate number
   * sequences. For instance, 2.5 is not "two and a half" or "half way to version three", it is the
   * fifth second-level revision of the second first-level revision.
   *
   * Example 1:
   *
   * Input: version1 = "0.1", version2 = "1.1" Output: -1 Example 2:
   *
   * Input: version1 = "1.0.1", version2 = "1" Output: 1 Example 3:
   *
   * Input: version1 = "7.5.2.4", version2 = "7.5.3" Output: -1s
   */
  public static int compare(String v1, String v2) {
    String[] a1 = v1.split(".");
    String[] a2 = v2.split(".");
    int i = 0;
    while (i < a1.length) {
      if (i > a2.length - 1) {
        int k = i;
        while(k < a1.length) {
          if(Integer.parseInt(a1[k]) != 0 )
            return 1;
        }
        return 0;
      }
      int cur = Integer.parseInt(a1[i]) - Integer.parseInt(a2[i]);
      if (cur > 0) {
        return 1;
      } else if (cur < 0) {
        return -1;
      }
      i++;
    }
    if (i < a2.length - 1) {
      return -1;
    }
    return 0;
  }

  public static void main(String[] args) {
    String s1 = "0.1";
    String s2 = "1.1";
    System.out.println(compare(s1, s2));
  }

  public int compareVersion(String version1, String version2) {

    String[] v1 = version1.split("\\.");
    String[] v2 = version2.split("\\.");

    for (int i = 0; i < Math.max(v1.length, v2.length); i++) {
      int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
      int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
      if (num1 < num2) {
        return -1;
      } else if (num1 > num2) {
        return +1;
      }
    }

    return 0;
  }
  //I checked other Java solution and the basic idea is the same. In addition, I simply the logic by making the two version number same length. For example, if version1 = "1.0.2", and version2 = "1.0", the I will convert the version2 to "1.0.0".
}
