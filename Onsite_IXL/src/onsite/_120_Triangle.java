package onsite;
import java.util.*;
/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

 For example, given the following triangle

 [
 [2],
 [3,4],
 [6,5,7],
 [4,1,8,3]
 ]
 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).



 */


public class _120_Triangle {
  public int minimumTotal(List<List<Integer>> triangle) {
    int[][] dp = new int[triangle.size()][triangle.size()];
    dp[0][0] = triangle.get(0).get(0);
    for(int i = 1; i < triangle.size(); i++) {
      for(int j = 0; j < triangle.get(i).size(); j++) {
        int cur = triangle.get(i).get(j);
        if(j == 0) {
          dp[i][j] = dp[i - 1][j] + cur;
        } else if(j == triangle.get(i).size() - 1) {
          dp[i][j] = dp[i - 1][j - 1] + cur;
        }   else {
          dp[i][j] = Math.min(dp[i - 1][j - 1],dp[i - 1][j]) + cur;
        }
      }
    }
    int row = triangle.size() - 1;
    int min = Integer.MAX_VALUE;
    for(int k = 0; k < triangle.get(row).size();k++) {
      min = Math.min(dp[row][k], min);
      System.out.println(dp[row][k]);
    }

    return min;
  }
}
