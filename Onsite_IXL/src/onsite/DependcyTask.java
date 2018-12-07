package onsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by yuegu on 12/3/18.
 */
public class DependcyTask {

  public static class Task {

    int id;
    int priority;
    Set<Task> dependency;

    public Task(int id, int priority, Set<Task> dependency) {
      this.id = id;
      this.priority = priority;
      this.dependency = dependency;
    }
  }

  public static List<Task> findOrder(List<Task> input) {
    List<Task> ret = new ArrayList<>();
    Map<Task, Set<Task>> map = new HashMap<>();
    PriorityQueue<Task> pq = new PriorityQueue<>((o1, o2) -> o1.priority - o2.priority);
    int count = 0;

    for (int i = 0; i < input.size(); i++) {
      map.put(input.get(i), new HashSet<>());
    }
    // map: dependency -> dependant on it
    for (int i = 0; i < input.size(); i++) {
      Task cur = input.get(i);
      if (!cur.dependency.isEmpty()) {
        for (Task task : cur.dependency) {
          if (map.containsKey(task)) {
            map.get(task).add(cur);
          } else {
            map.put(task, new HashSet<>());
            map.get(task).add(cur);
          }
        }
      }

    }
    for (int i = 0; i < input.size(); i++) {
      if (input.get(i).dependency.size() == 0) {
        pq.add(input.get(i));
      }
    }
    while (!pq.isEmpty()) {
      Task cur = pq.poll();
//      System.out.print(cur.id);
      ret.add(cur);
      for (Task task : map.get(cur)) {
        task.dependency.remove(cur);
        if (task.dependency.isEmpty()) {
          pq.add(task);
        }
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    List<Task> test = new ArrayList<>();
    Task d = new Task(4, 4, new HashSet<>());
    Task b = new Task(2, 2, new HashSet<>(Arrays.asList(new Task[]{d})));
    Task a = new Task(1, 1, new HashSet<>(Arrays.asList(new Task[]{b})));
    Task e = new Task(5, 5, new HashSet<>(Arrays.asList(new Task[]{a})));
    Task c = new Task(3, 3, new HashSet<>(Arrays.asList(new Task[]{b, e})));
    test.add(a);
    test.add(b);
    test.add(c);
    test.add(d);
    test.add(e);
    List<Task> ret = findOrder(test);
    for (Task t : ret) {
      System.out.print(t.id);
    }
  }
}
