import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yuegu on 11/6/18.
 */
public class _126_wordLadder {

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    int distance = getDistance(beginWord, endWord);
    Map<String, Set<String>> map = new HashMap<>();
    buildMap(map, wordList);
    return dfs(map, distance, beginWord, endWord);

  }


  public int dfs(Map<String, Set<String>> map, int distance, String beginWord, String endWord) {
    if (!map.containsKey(beginWord)) {
      return 0;
    }
    for (String cur : map.get(beginWord)) {
      if (cur.equals(endWord)) {
        return distance;
      } else {
        dfs(map, distance - 1, cur, endWord);
      }
    }
    return 0;
  }

  public int getDistance(String beginWord, String endWord) {
    int dis = 0;
    for (int i = 0; i < beginWord.length(); i++) {
      if (endWord.charAt(i) != beginWord.charAt(i)) {
        dis++;
      }
    }
    return dis;
  }

  public void buildMap(Map<String, Set<String>> map, List<String> wordList) {
    for (int i = 0; i < wordList.size(); i++) {
      String cur = wordList.get(i);
      for (int j = i + 1; j < wordList.size(); j++) {
        if (getDistance(cur, wordList.get(j)) == 1) {
          if (map.containsKey(cur)) {
            map.get(cur).add(wordList.get(j));
          } else {
            map.put(cur, new HashSet<>());
            map.get(cur).add(wordList.get(j));
          }
        }

      }
    }
  }


  /**
   * if the endword is not in the dic, return 0
   * @param beginWord
   * @param endWord
   * @param wordDict
   * @return
   */
  public int ladderLengthOther(String beginWord, String endWord, List<String> wordDict) {
    Set<String> set = new HashSet<String>(wordDict);
    Set<String> reached = new HashSet<String>();
    reached.add(beginWord);
    int distance = 1;
    if(!set.contains(endWord))
      return 0;
    while (!reached.contains(endWord)) {
      // temportary toAdd it to calcualte in changing one character, if can make a word in the dict
      Set<String> toAdd = new HashSet<String>();
      for (String each : reached) {
        // go through the word, try to replace one character with a-z
        for (int i = 0; i < each.length(); i++) {
          char[] chars = each.toCharArray();
          for (char ch = 'a'; ch <= 'z'; ch++) {
            chars[i] = ch;
            String word = new String(chars);
            if (set.contains(word)) {
              toAdd.add(word);
              // as long as add a word in reached, remove it in wordsets
              set.remove(word);
            }
          }
        }
      }
      distance++;
      if (toAdd.size() == 0) // NO new words
        return 0;
      reached = toAdd; // update reached
    }
    return distance;
  }
}
