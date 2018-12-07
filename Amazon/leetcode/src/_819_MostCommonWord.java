import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by yuegu on 11/6/18.
 */
public class _819_MostCommonWord {

  public String mostCommonWordMine(String p, String[] banned) {
    // convert array to set:
    Set<String> ban = new HashSet<>(Arrays.asList(banned));
    Map<String, Integer> count = new HashMap<>();
    String[] words = p.replaceAll("\\pP", "").toLowerCase().split("\\s+");
    String res = "";
    int max = 0;
    for (String w : words) {
      if (!ban.contains(w)) {
        count.put(w, count.getOrDefault(w, 0) + 1);
        if (count.get(w) > max) {
          res = w;
          max = count.get(w);
        }
      }
    }
    return res;
  }

  public String mostCommonWord(String p, String[] banned) {
    Set<String> ban = new HashSet<>(Arrays.asList(banned));
    Map<String, Integer> count = new HashMap<>();
    String[] words = p.replaceAll("\\W+", " ").toLowerCase().split("\\s+");
    for (String w : words) {
      if (!ban.contains(w)) {
        count.put(w, count.getOrDefault(w, 0) + 1);
      }
    }
    return Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getKey();
  }
}
