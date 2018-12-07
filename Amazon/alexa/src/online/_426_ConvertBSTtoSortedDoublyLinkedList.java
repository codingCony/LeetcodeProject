package online;

import java.util.Stack;

/**
 * Created by yuegu on 11/5/18.
 */
public class _426_ConvertBSTtoSortedDoublyLinkedList {
//https://www.1point3acres.com/bbs/thread-225086-1-1.html
  // bst转成按顺序排列的双链表
  public Node treeToDoublyList(Node root) {
    Node head = new Node();
    head.left = null;
    Stack<Node> stack = new Stack<>();
    Node itr = root;
    while (itr != null) {
      stack.push(itr);
      itr = itr.left;
    }
    Node pre = head;
    while (!stack.isEmpty()) {
      Node cur = stack.pop();
      if (cur.right != null) {
        Node itr2 = cur.right;
        while (itr2.left != null) {
          stack.push(itr2);
          itr2 = itr2.left;
        }
        stack.push(itr2);
      }
      pre.right = cur;
      cur.left = pre;
      pre = cur;
    }
    return head;
  }
}
