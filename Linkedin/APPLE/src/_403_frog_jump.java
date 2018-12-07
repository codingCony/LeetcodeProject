import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yuegu on 11/5/18.
 */
public class _403_frog_jump {

  public boolean canCrossMine(int[] stones) {
    if (stones.length == 0) {
      return true;
    }

    HashMap<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>(stones.length);
    map.put(0, new HashSet<Integer>());
    map.get(0).add(1);
    //[0,1,3,5,6,8,12,17]
    // {0,1}
    for(int i = 0; i < stones.length; i++) {

      if(map.containsKey(stones[i])) {
        Set<Integer> available = map.get(stones[i]);
        for(int j : available) {
          if(Arrays.binarySearch(stones, stones[i] + j) > 0) {
            if(stones[i] + j == stones[stones.length - 1]) {
              return true;
            }
            if (map.containsKey(stones[i] + j)) {
              map.get(stones[i] + j).add(j);
            } else {
              map.put(stones[i] + j, new HashSet<Integer>());
              map.get(stones[i] + j).add(j);
            }
            if(j - 1 > 0)
              map.get(stones[i] + j).add(j - 1);
            map.get(stones[i] + j).add(j + 1);
          }
        }
      }
    }
    return false;
  }


  /**
   * The idea is simple:

   (1) Using a HashSet to store all the positions of the stones. So when you are trying to jump to a position, you will know whether there is a stone at that position or not.
   (2) Starting from the first valid position (the second stone if it is 1), you try to jump as far as possible. At each position, if you jumped x steps to this position, the next possible positions are position + x - 1, position + x and position + x + 1. If any of them is the last stone's position, then you can return true. If not, use DFS from the longest jump first.
   (3) This path finding process can be terminated much earlier if there are two stones that are too far away.


   * @param stones
   * @return
   */
  public boolean canCross(int[] stones) {
    if (stones == null || stones.length == 0) {return false;}
    int n = stones.length;
    if (n == 1) {return true;}
    if (stones[1] != 1) {return false;}
    if (n == 2) {return true;}
    int last = stones[n - 1];
    HashSet<Integer> hs = new HashSet();
    for (int i = 0; i < n; i++) {
      if (i > 3 && stones[i] > stones[i - 1] * 2) {return false;} // The two stones are too far away.
      hs.add(stones[i]);
    }
    return canReach(hs, last, 1, 1);
  }

  private boolean canReach(HashSet<Integer> hs, int last, int pos, int jump) {
    if (pos + jump - 1 == last || pos + jump == last || pos + jump + 1 == last) {
      return true;
    }
    if (hs.contains(pos + jump + 1)) {
      if (canReach(hs, last, pos + jump + 1, jump + 1)) {return true;}
    }
    if (hs.contains(pos + jump)) {
      if (canReach(hs, last, pos + jump, jump)) {return true;}
    }
    if (jump > 1 && hs.contains(pos + jump - 1)) {
      if (canReach(hs, last, pos + jump - 1, jump - 1)) {return true;}
    }
    return false;
  }
}
