/**
 * Created by yuegu on 11/12/18.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class _692_TopKFrequentWords {
  class Solution {
    public List<String> topKFrequent(String[] words, int k) {
      Map<String, Integer> map = new HashMap<>();
      List<String> ret = new ArrayList<>();
      PriorityQueue<Entry<String, Integer>> pq = new PriorityQueue<>(k,
          new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
              if (o1.getValue() - o2.getValue() == 0) {
                return o2.getKey().compareTo(o1.getKey());
              }
              return o1.getValue() - o2.getValue();
            }
          });
      for (String i : words) {
        if (map.containsKey(i)) {
          map.put(i, map.get(i) + 1);
        } else {
          map.put(i, 1);
        }
      }
      for (Entry<String, Integer> entry : map.entrySet()) {
        pq.offer(entry);
        if (pq.size() > k) {
          pq.poll();
        }
      }
      //
      while (!pq.isEmpty()) {
        ret.add(0, pq.poll().getKey());
      }
      return ret;
    }
  }
}
