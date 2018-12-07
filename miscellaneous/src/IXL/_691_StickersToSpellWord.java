package IXL;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by yuegu on 11/16/18.
 *
 * back tracking + dp
 */
public class _691_StickersToSpellWord {

  public int minStickers(String[] stickers, String target) {
    Map<String, Integer> map = new HashMap<>();
    int[] t = getMap(target);
    int[][] stickersArray = new int[stickers.length][26];
    for (int i = 0; i < stickers.length; i++) {
      stickersArray[i] = getMap(stickers[i]);
    }
    map.put("", 0);
    return helper(stickersArray, map, t);
  }

  public int helper(int[][] stickers, Map<String, Integer> map, int[] target) {
    String current = arrayToString(target);
    if (map.containsKey(current)) {
      return map.get(current);
    }
    int ret = Integer.MAX_VALUE;
    if (isEmpty(target)) {
      return -1;
    }
    for (int i = 0; i < stickers.length; i++) {
      int[] cur = diff(stickers[i], target);
      if (!isEmpty(cur)) {
        String s = arrayToString(cur);
        int currentNumber = helper(stickers, map, cur);
        ret = Math.min(ret, currentNumber + 1);
      }
    }
    if (ret != Integer.MAX_VALUE) {
      map.put(current, ret);
    } else {
      map.put(current, -1);
    }

    return map.get(current);
  }

  public int[] getMap(String s) {
    int[] ret = new int[26];
    for (int i = 0; i < s.length(); i++) {
      ret[s.charAt(i) - 'a']++;
    }
    return ret;
  }

  public int[] diff(int[] a, int[] target) {
    int count = 0;
    for (int i = 0; i < 26; i++) {
      if (target[i] != 0 && a[i] != 0) {
        target[i] = target[i] - a[i];
        if (target[i] < 0) {
          target[i] = 0;
        }
      }
    }
    return target;
  }

  public String arrayToString(int[] array) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < array.length; i++) {
      if (array[i] != 0) {
        for (int k = 0; k < array[i]; k++) {
          sb.append('a' + i);
        }
      }
    }
    return sb.toString();
  }

  public boolean isEmpty(final int[] data) {
    return IntStream.range(0, data.length).parallel().allMatch(i -> data[i] == 0);
  }
}
