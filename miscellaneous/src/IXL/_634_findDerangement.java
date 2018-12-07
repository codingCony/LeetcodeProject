package IXL;

/**
 * Created by yuegu on 11/29/18.
 */
public class _634_findDerangement {

  public int findDerangement(int n) {
    /**
     For ith element, we have switch it with one of the previous numbers 1,2,...,i-1, and for each picked number j, for the positions left except the one take by i, j can take anyone of them. So there are dp[i - 2] permutation if j can take the original position of i, and dp[i - 1] permutations if j can not take the original position of i.
     **/
    if(n <= 1)
      return 0;
    long[] dp = new long[n + 1];
    dp[2] = 1;
    for(int i = 3; i < dp.length; i++){
      dp[i] = (long)(i - 1) * (dp[i - 1] + dp[i - 2]) % 1000000007;
    }
    return (int)dp[dp.length - 1];
  }
}
