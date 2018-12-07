import java.util.PriorityQueue;

/**
 * Created by yuegu on 11/12/18.
 */
public class _703_KthLargestElementInAStream {
  class KthLargest {
    final PriorityQueue<Integer> q;
    final int k;

    public KthLargest(int k, int[] a) {
      this.k = k;
      q = new PriorityQueue<>(k);
      for (int n : a)
        add(n);
    }

    public int add(int n) {
      if (q.size() < k)
        q.offer(n);
      else if (q.peek() < n) {
        q.poll();
        q.offer(n);
      }
      return q.peek();
    }
  }

}
