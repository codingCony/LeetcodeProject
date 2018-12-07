package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuegu on 11/1/18.
 */
public class _366_palindromePairs {

  public List<List<Integer>> palindromePairs(String[] words) {
    List<List<Integer>> ret = new ArrayList<>();
    Map<String, Integer> map = new HashMap<String, Integer>();
    for (int i = 0; i < words.length; i++) {
      map.put(words[i], i);
    }
    for (int i = 0; i < words.length; i++) {
      String cur = words[i];
      // abcd cba dcba
      String needed = new StringBuilder(cur).reverse().toString();
      for (int j = 0; j < needed.length(); j++) {
        String tmp = needed.substring(j);
        if (map.containsKey(tmp) && isPalindrome(cur + tmp)) {
          List<Integer> res1 = new ArrayList<>(Arrays.asList(i, map.get(tmp)));
          ret.add(res1);
          if (isPalindrome(tmp + cur)) {
            List<Integer> res2 = new ArrayList<>(Arrays.asList(map.get(tmp), i));
            ret.add(res2);
          }
        }
      }

    }
    return ret;
  }

  public List<List<Integer>> palindromePairsOther(String[] words) {
    List<List<Integer>> ret = new ArrayList<>();
    if (words == null || words.length < 2) {
      return ret;
    }
    Map<String, Integer> map = new HashMap<String, Integer>();
    // get reverse index
    for (int i = 0; i < words.length; i++) {
      map.put(words[i], i);
    }
    for (int i = 0; i < words.length; i++) {
      for (int j = 0; j <= words[i].length(); j++) { // notice it should be "j <= words[i].length()"
        String str1 = words[i].substring(0, j);
        String str2 = words[i].substring(j);
        if (isPalindrome(str1)) {
          String str2rvs = new StringBuilder(str2).reverse().toString();
          if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(map.get(str2rvs));
            list.add(i);
            ret.add(list);
          }
        }
        if (isPalindrome(str2)) {
          String str1rvs = new StringBuilder(str1).reverse().toString();
          if (map.containsKey(str1rvs) && map.get(str1rvs) != i && str2.length() != 0) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(i);
            list.add(map.get(str1rvs));
            ret.add(list);
          }
        }
      }
    }
    return ret;
  }

  private boolean isPalindrome(String str) {
    int left = 0;
    int right = str.length() - 1;
    while (left <= right) {
      if (str.charAt(left++) != str.charAt(right--)) {
        return false;
      }
    }
    return true;
  }

  /**
   * There are several cases to be considered that isPalindrome(s1 + s2):

   Case1: If s1 is a blank string, then for any string that is palindrome s2, s1+s2 and s2+s1 are palindrome.

   Case 2: If s2 is the reversing string of s1, then s1+s2 and s2+s1 are palindrome.

   Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut+1:] , then s2+s1 is palindrome.

   Case 4: Similiar to case3. If s1[cut+1: ] is palindrome and there exists s2 is the reversing string of s1[0:cut] , then s1+s2 is palindrome.

   To make the search faster, build a HashMap to store the String-idx pairs.
   * @param words
   * @return
   */



}
