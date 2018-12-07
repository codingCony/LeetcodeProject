package onsite;

/**
 * Created by yuegu on 12/3/18.
 */
public class _160_IntersectionOfTwoLinkedLists {

  class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int a = length(headA);
    int b = length(headB);
    int dif = a - b;
    if (dif > 0) {
      while (dif > 0) {
        headA = headA.next;
        dif--;
      }
    } else {
      while (dif < 0) {
        headB = headB.next;
        dif++;
      }
    }
    ListNode itrA = headA;
    ListNode itrB = headB;
    while (itrA != null && itrA != itrB) {
      itrA = itrA.next;
      itrB = itrB.next;
    }
    return itrA;
  }

  public int length(ListNode head) {
    int i = 0;
    while (head != null) {
      i++;
      head = head.next;
    }
    return i;
  }


  public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
    //boundary check
    if(headA == null || headB == null)
      return null;

    ListNode a = headA;
    ListNode b = headB;

    //if a & b have different len, then we will stop the loop after second iteration
    while( a != b){
      //for the end of first iteration, we just reset the pointer to the head of another linkedlist
      a = a == null? headB : a.next;
      b = b == null? headA : b.next;
    }

    return a;
  }
}
