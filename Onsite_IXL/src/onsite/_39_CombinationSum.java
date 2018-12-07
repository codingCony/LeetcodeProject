package onsite;

/**
 * Created by yuegu on 12/3/18.
 */

import java.util.*;

/**
 * Example 1:

 Input: candidates = [2,3,6,7], target = 7,
 A solution set is:
 [
 [7],
 [2,2,3]
 ]
 Example 2:

 Input: candidates = [2,3,5], target = 8,
 A solution set is:
 [
 [2,2,2,2],
 [2,3,3],
 [3,5]
 ]
 */

/**
 * how to avoid duplicate
 */
public class _39_CombinationSum {
// last byte

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> ret = new ArrayList<>();
    helper(ret, new ArrayList<Integer>(), candidates, target);
    return ret;
  }

  private void helper( List<List<Integer>> ret, List<Integer> tmp, int[] candidates, int target) {
    if(target == 0) {
      ret.add(new ArrayList<>(tmp));
      return;
    }
    for(int i = 0; i < candidates.length; i++) {
      if(candidates[i] <= target) {
        tmp.add(candidates[i]);
        helper(ret, tmp, candidates, target - candidates[i]);
        tmp.remove(tmp.size() - 1);
      } else {
        break;
      }
    }
  }
}
