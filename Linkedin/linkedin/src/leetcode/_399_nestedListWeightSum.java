package leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yuegu on 10/31/18.
 */
public class _399_nestedListWeightSum {

  /**
   * Given a nested list of integers, return the sum of all integers in the list weighted by their
   * depth.
   *
   * Each element is either an integer, or a list -- whose elements may also be integers or other
   * lists.
   *
   * Example 1:
   *
   * Input: [[1,1],2,[1,1]] Output: 10 Explanation: Four 1's at depth 2, one 2 at depth 1. Example
   * 2:
   *
   * Input: [1,[4,[6]]] Output: 27 Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at
   * depth 3; 1 + 4*2 + 6*3 = 27.
   */
  public interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
  }

  public int depthSum(List<NestedInteger> nestedList) {
    return depthSum(nestedList, 1);
  }

  public int depthSum(List<NestedInteger> nestedList, int depth) {
    int sum = 0;
    for(int i = 0; i < nestedList.size(); i++) {
      if(nestedList.get(i).isInteger()) {
        sum += nestedList.get(i).getInteger() * depth;
      } else {
        sum += depthSum(nestedList.get(i).getList(), depth + 1);
      }
    }
    return sum;
  }


  public int depthSumBFS(List<NestedInteger> nestedList) {
    if(nestedList == null){
      return 0;
    }

    int sum = 0;
    int level = 1;

    Queue<NestedInteger> queue = new LinkedList<NestedInteger>(nestedList);
    while(queue.size() > 0){
      int size = queue.size();

      for(int i = 0; i < size; i++){
        NestedInteger ni = queue.poll();

        if(ni.isInteger()){
          sum += ni.getInteger() * level;
        }else{
          queue.addAll(ni.getList());
        }
      }

      level++;
    }

    return sum;
  }
}
