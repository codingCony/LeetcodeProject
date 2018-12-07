/**
 * Created by yuegu on 11/12/18.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class _675_CutOffTreesForGolfEvent {

  public class Coordinate {

    int i;
    int j;

    public Coordinate(int i, int j) {
      this.i = i;
      this.j = j;
    }
  }

  public class InternalObject {

    int val;
    Coordinate d;

    public InternalObject(Coordinate d, int val) {
      this.d = d;
      this.val = val;
    }

    public InternalObject() {
    }
  }

  public List<Coordinate> four = new ArrayList<Coordinate>(
      Arrays.asList(new Coordinate(-1, 0), new Coordinate(1, 0), new Coordinate(0, -1),
          new Coordinate(0, 1)));

  public int cutOffTree(List<List<Integer>> forest) {

    PriorityQueue<InternalObject> pq = new PriorityQueue<>(new Comparator<InternalObject>() {
      @Override
      public int compare(InternalObject o1, InternalObject o2) {
        return o1.val - o2.val;
      }
    });
    for (int i = 0; i < forest.size(); i++) {
      for (int j = 0; j < forest.get(0).size(); j++) {
        pq.add(new InternalObject(new Coordinate(i, j), forest.get(i).get(j)));
      }
    }

    int ret = 0;
    Queue<InternalObject> q = new LinkedList<>();
    if (forest.get(0).get(0) == 0) {
      return -1;
    }
    q.offer(new InternalObject(new Coordinate(0, 0), forest.get(0).get(0)));
    while (!q.isEmpty()) {
      InternalObject cur = q.poll();
      if (cur.val == 0) {
        if(q.isEmpty())
          return -1;
        continue;
      }
      // ground
      if (cur.val == 1) {
        ret++;
        forest.get(cur.d.i).set(cur.d.j, -1);
      }
      // the correct path
      if (cur.val == pq.peek().val) {
        pq.poll();
        ret++;
      }
      // otherwise, abandon
      if (cur.val != 1) {
        continue;
      }
      for (Coordinate d : four) {
        int curI = cur.d.i + d.i;
        int curJ = cur.d.j + d.j;
        if (curI < 0 || curJ < 0 || curI > forest.size() - 1 || curJ > forest.get(0).size() - 1) {
          continue;
        }
        int current = forest.get(curI).get(curJ);
        if (current != 0 && current != -1) {
          q.offer(new InternalObject(new Coordinate(curI, curJ), current));
        }
      }
    }
    return ret;
  }
}

