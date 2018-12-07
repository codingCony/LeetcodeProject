package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Created by yuegu on 11/1/18. There is a new alien language which uses the latin alphabet.
 * However, the order among letters are unknown to you. You receive a list of non-empty words from
 * the dictionary, where words are sorted lexicographically by the rules of this new language.
 * Derive the order of letters in this language.
 *
 * Example 1:
 *
 * Input: [ "wrt", "wrf", "er", "ett", "rftt" ]
 *
 * Output: "wertf"
 *
 * t -> f w -> e -> r r -> t
 */
public class _269_AlienDictionary {

  class Solution {

    public String alienOrder(String[] words) {

      Map<Character, Set<Character>> map = new HashMap<>();
      Map<Character, Integer> degree = new HashMap<Character, Integer>();
      buildGraph(words, map, degree);
      Queue<Character> q = new LinkedList<>();
      for (Character c : degree.keySet()) {
        if (degree.get(c) == 0) {
          q.offer(c);
        }
      }
      StringBuilder sb = new StringBuilder();
      getOrder(q, map, sb, degree);
      if (sb.length() != degree.size()) {
        return "";
      }
      return sb.toString();
    }

    public void getOrder(Queue<Character> q, Map<Character, Set<Character>> map, StringBuilder sb,
        Map<Character, Integer> degree) {
      if (!q.isEmpty()) {
        Character cur = q.poll();
        sb.append(cur);
        for (Character c : map.get(cur)) {
          degree.put(c, degree.get(c) - 1);
          if (degree.get(c) == 0) {
            q.offer(c);
          }
        }
      }

    }

    private void buildGraph(String[] words, Map<Character, Set<Character>> map,
        Map<Character, Integer> degree) {
      String pre = words[0];
      for (int i = 1; i < words.length; i++) {
        String cur = words[i];
        int j = 0;
        while (cur.charAt(j) == pre.charAt(j)) {
          j++;
        }
        if (map.get(pre.charAt(j)) == null) {
          map.put(pre.charAt(j), new HashSet<>());
          map.get(pre.charAt(j)).add(cur.charAt(j));
        } else {
          map.get(pre.charAt(j)).add(cur.charAt(j));
        }

        degree.put(cur.charAt(j), degree.getOrDefault(cur.charAt(j), 0) + 1);
        pre = cur;
      }
    }
  }
}
