/**
 * Created by yuegu on 11/28/18.
 */
public class FindAllLongestIncreasingSubsequence {

  /**
   * [10,9,2,5,3,7,101,18]
   *
   * 1 2 4
   *  1  1 1 2 2 3 4   4
   *  //[0,1,2,3,1,5,9,132,2,6]
   *     1 2 3 4 2  6  7  7 8
   *
   *     // 1 3 5 4 2
   *     // 1 2 4
   Output: 4
   * @param nums
   * @return
   */
  public int lengthOfLIS(int[] nums) {
    int[] dp = new int[nums.length];
    dp[0] = 1;
    for(int i = 1; i < nums.length; i++) {
      if(nums[i] > nums[i - 1]) {
        for(int j = i - 1; )
        dp[i] = dp[i - 1] + 1;
      } else {
        for(int j = i - 1; j >= 0; j--) {
          if(nums[j] < nums[i]) {
            dp[i] = dp[j] + 1;
          }
        }
      }
    }
    int max = 1;
    for(int i = 0; i < nums.length; i++)
      max = Math.max(dp[i], max);
    return max;
  }
}
