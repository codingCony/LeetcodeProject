import java.util.ArrayList;
import java.util.*;

/**
 * Created by yuegu on 11/28/18.
 */
public class _49_GroupAnagrams {
  public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
      if (strs == null || strs.length == 0)
        return new ArrayList<List<String>>();
      Map<String, List<String>> map = new HashMap<String, List<String>>();
      for (String s : strs) {
        char[] ca = s.toCharArray();
        Arrays.sort(ca);
        String keyStr = String.valueOf(ca);
        if (!map.containsKey(keyStr))
          map.put(keyStr, new ArrayList<String>());
        map.get(keyStr).add(s);
      }
      return new ArrayList<List<String>>(map.values());
    }
  }
}
