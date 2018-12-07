package phone;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by yuegu on 10/30/18.
 */
public class _399_evaluate_division {

  class Solution {

    public class Pair {

      String s;
      double k;

      public Pair(String s, double k) {
        this.s = s;
        this.k = k;
      }
    }

    /**
     * Equations are given in the format A / B = k, where A and B are variables represented as
     * strings, and k is a real number (floating point number). Given some queries, return the
     * answers. If the answer does not exist, return -1.0.
     *
     * Example: Given a / b = 2.0, b / c = 3.0. queries are: a / c = ?, b / a = ?, a / e = ?, a / a
     * = ?, x / x = ? . return [6.0, 0.5, -1.0, 1.0, -1.0 ].
     */
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
      Set<String> valid = new HashSet<>();
      Map<String, Set<Pair>> map = new HashMap<>();
      for (int i = 0; i < equations.length; i++) {
        valid.add(equations[i][0]);
        valid.add(equations[i][1]);

        if (!map.containsKey(equations[i][0])) {
          map.put(equations[i][0], new HashSet<>());
          map.get(equations[i][0]).add(new Pair(equations[i][1], values[i]));
        } else {
          map.get(equations[i][0]).add(new Pair(equations[i][1], values[i]));
        }
        if (!map.containsKey(equations[i][1])) {
          map.put(equations[i][1], new HashSet<>());
          map.get(equations[i][1]).add(new Pair(equations[i][0], 1 / values[i]));
        } else {
          map.get(equations[i][1]).add(new Pair(equations[i][0], 1 / values[i]));
        }
      }
      double[] ret = new double[queries.length];
      for (int i = 0; i < queries.length; i++) {
        get(queries[i][0], queries[i][1], map, 1, ret, i, valid);
      }
      return ret;
    }

    // a/b   b/c  c/d
    public void get(String a, String b, Map<String, Set<Pair>> map, double cur, double[] ret, int i,
        Set<String> valid) {
      if (!valid.contains(a) || !valid.contains(b)) {
        ret[i] = -1.0;
        return;
      }

      if (a.equals(b)) {
        ret[i] = cur;
        return;
      }
      if (map.get(a) == null) {
        return;
      }
      for (Pair pair : map.get(a)) {
        if (pair.s.equals(b)) {
          ret[i] = cur * pair.k;
          return;
        }
        if(pair.s.equals(a))
        get(pair.s, b, map, cur * pair.k, ret, i,valid);
      }
    }
  }
}
