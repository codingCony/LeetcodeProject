package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Created by yuegu on 11/15/18.
 */
public class _787_CheapestFlightsWithinKStop {
//  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
//    Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
//    for (int[] f : flights) {
//      if (!prices.containsKey(f[0]))
//        prices.put(f[0], new HashMap<>());
//      prices.get(f[0]).put(f[1], f[2]);
//    }
//    Queue<int[]>  q = new LinkedList<>();
//    for(int i = 0; i < flights.length; i++) {
//      if(flights[i][0] == src)
//        q.offer(flights[i]);
//    }
//    int price = 0;
//    int stop = 0;
//    while(!q.isEmpty() && stop < K) {
//      int size = q.size();
//      Set<int[]> set = new HashSet<>();
//      for(int i = 0; i < size; i++) {
//        int[] cur = q.poll();
//        if (cur[1] == dst) {
//          price += cur[2];
//          stop++;
//        }
//      }
//    }
//    if(!q.isEmpty())
//      return -1;
//  }
}
