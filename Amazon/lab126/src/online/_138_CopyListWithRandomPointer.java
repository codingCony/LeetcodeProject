package online;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuegu on 11/6/18.
 */
public class _138_CopyListWithRandomPointer {
  class RandomListNode {
      int label;
      RandomListNode next, random;
      RandomListNode(int x) { this.label = x; }
  }
  public RandomListNode copyRandomList(RandomListNode head) {
    if (head == null) {
      return null;
    }

    final Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

    RandomListNode cur = head;
    while(cur != null) {
      map.put(cur, new RandomListNode(cur.label));
      cur = cur.next;
    }

    for (Map.Entry<RandomListNode, RandomListNode> entry : map.entrySet()) {
      final RandomListNode newNode = entry.getValue();
      newNode.next = map.get(entry.getKey().next);
      newNode.random = map.get(entry.getKey().random);
    }

    return map.get(head);
  }
}
