package topologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yuegu on 12/6/18.
 */

/*
There are a total of n courses you have to take, labeled from 0 to n-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1
which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses
*/
public class _207_CourseSchedule {

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    // use an array of list to store the information
    List<Integer>[] preList = new List[numCourses];
    // init the array with empty arraylist
    for (int i = 0; i < numCourses; i++) {
      preList[i] = new ArrayList<>();
    }
    // index is the course key
    for (int[] pair : prerequisites) {
      preList[pair[0]].add(pair[1]);
    }
    boolean[] visited = new boolean[numCourses];
    boolean[] visiting = new boolean[numCourses];
    for (int i = 0; i < numCourses; i++) {
      if (visited[i]) {
        continue;
      }
      if (hasCycle(preList, visited, visiting, i)) {
        return false;
      }
    }
    return true;
  }

  private boolean hasCycle(List<Integer>[] preList, boolean[] visited, boolean[] visiting, int i) {
    if (visited[i]) {
      return false;
    }
    if (visiting[i]) {
      return true;
    }
    visiting[i] = true;
    for (int k : preList[i]) {
      if (hasCycle(preList, visited, visiting, k)) {
        return true;
      }
    }
    visiting[i] = false;
    visited[i] = true;
    return false;
  }


  public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
    ArrayList[] graph = new ArrayList[numCourses];
    int[] degree = new int[numCourses];
    Queue queue = new LinkedList();
    int count=0;

    for(int i=0;i<numCourses;i++)
      graph[i] = new ArrayList();

    for(int i=0; i<prerequisites.length;i++){
      degree[prerequisites[i][1]]++;
      graph[prerequisites[i][0]].add(prerequisites[i][1]);
    }
    for(int i=0; i<degree.length;i++){
      if(degree[i] == 0){
        queue.add(i);
        count++;
      }
    }

    while(queue.size() != 0){
      int course = (int)queue.poll();
      for(int i=0; i<graph[course].size();i++){
        int pointer = (int)graph[course].get(i);
        degree[pointer]--;
        if(degree[pointer] == 0){
          queue.add(pointer);
          count++;
        }
      }
    }
    if(count == numCourses)
      return true;
    else
      return false;
  }

}
