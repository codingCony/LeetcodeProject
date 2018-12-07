package IXL_ONLINE;

/**
 * Created by yuegu on 11/22/18.
 */
public class MergeKSortedArray {
/**nlogk?
 In the heap the maximum number of elements will be k. Hence every operation w/r/t is log(k)**/

  public ListNode mergeKLists(ListNode[] lists) {
    if (lists==null||lists.length==0) return null;
    PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.length ,(a,b) -> a.val-b.val);
    ListNode dummy = new ListNode(0);
    ListNode tail=dummy;

    for (ListNode node:lists)
      if (node!=null)
        queue.add(node);

    while (!queue.isEmpty()){
      tail.next=queue.poll();
      tail=tail.next;

      if (tail.next!=null)
        queue.add(tail.next);
    }
    return dummy.next;
  }


  public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
      if (lists==null||lists.size()==0) return null;

      PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.size(),new Comparator<ListNode>(){
        @Override
        public int compare(ListNode o1,ListNode o2){
          if (o1.val<o2.val)
            return -1;
          else if (o1.val==o2.val)
            return 0;
          else
            return 1;
        }
      });

      ListNode dummy = new ListNode(0);
      ListNode tail=dummy;

      for (ListNode node:lists)
        if (node!=null)
          queue.add(node);

      while (!queue.isEmpty()){
        tail.next=queue.poll();
        tail=tail.next;

        if (tail.next!=null)
          queue.add(tail.next);
      }
      return dummy.next;
    }
  }


  /**
   * O(log(k) * k* n), while k is the total number of lists, and n is the average length of all the lists
   * @param lists
   * @return
   */
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0)
      return null;
    return mergeKLists(lists, 0, lists.length - 1);
  }
  private ListNode mergeKLists(ListNode[] lists, int start, int end) {
    if (start == end) {
      return lists[start];
    } else if (start < end){
      int mid = (end - start) / 2 + start;
      ListNode left = mergeKLists(lists, start, mid);
      ListNode right = mergeKLists(lists, mid + 1, end);
      return mergeTwoLists(left, right);
    } else {
      return null;
    }
  }
}
