package topologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;

/**
 * Created by yuegu on 12/6/18.
 *
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 */
public class _210_CourseScheduleII {

  // my accepted version
  public int[] findOrder(int num, int[][] prerequisites) {
    int[] ret = new int[num];
    Set<Integer>[] graph1 = new HashSet[num];
    Set<Integer>[] graph2 = new HashSet[num];
    int[] degree = new int[num];
    Queue<Integer> queue = new LinkedList();
    int count = 0;

    for (int i = 0; i < num; i++) {
      graph1[i] = new HashSet();
      graph2[i] = new HashSet<>();
    }

    for (int i = 0; i < prerequisites.length; i++) {
      // store depency info
      graph1[prerequisites[i][0]].add(prerequisites[i][1]);
      graph2[prerequisites[i][1]].add(prerequisites[i][0]);
    }
    for(int i = 0; i < graph1.length; i++) {
      degree[i] = graph1[i].size();
      if(degree[i] == 0) {
        queue.add(i);
      }
    }

    while(!queue.isEmpty()) {
      int cur = queue.poll();
      ret[count] = cur;
      count++;
      for(int course: graph2[cur]) {
        graph1[course].remove(cur);
        if(graph1[course].isEmpty()) {
          queue.add(course);
        }
      }
    }
    if(count < num)
      return new int[0];
    return ret;
  }

  public int[] findOrderDFS(int num, int[][] prerequisites) {
    List<List<Integer>> adjList = new ArrayList<>();
    List<Integer> ret = new ArrayList<>();
    for (int i = 0; i < num; ++i) {
      adjList.add(new ArrayList<Integer>());
    }
    for (int[] edge : prerequisites) {
      adjList.get(edge[1]).add(edge[0]);
    }
    int[] status = new int[num];
    int index = 0;
    for(int i = 0; i < num; i++) {
      if(dfs(adjList, status, i, ret))
        return new int[0];
    }
    int[] a = new int[num];
    for(int i = 0; i < ret.size(); i++)
      a[i] = ret.get(i);
    return a;
  }

  public boolean dfs(List<List<Integer>> adjList, int[] status, int course, List<Integer> ret) {
    if(status[course] == 2)
      return false;
    if(status[course] == 1)
      return true;
    status[course] = 1;
    for(int i = 0; i < adjList.get(course).size();i++) {
      if(dfs(adjList, status, adjList.get(course).get(i), ret))
        return true;
    }
    status[course] = 2;
    ret.add(course);
    return false;
  }
}
