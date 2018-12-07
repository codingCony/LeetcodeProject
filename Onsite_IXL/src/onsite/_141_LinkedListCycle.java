package onsite;

import java.util.List;

/**
 * Created by yuegu on 12/3/18.
 */
public class _141_LinkedListCycle {
//https://blog.csdn.net/sinat_35261315/article/details/79205157
  class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  // my accepted solution
  public boolean hasCycle(ListNode head) {
    if(head == null || head.next == null)
      return false;
    ListNode slow = head;
    ListNode fast = head;
    while(fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if(fast == slow)
        return true;
    }
    return false;
  }
}
