package online;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by yuegu on 11/5/18.
 */
public class BinarySearchTreeIterator implements Iterator<Node> {

  Stack<Node> stack;

  public BinarySearchTreeIterator(Node root) {
    stack = new Stack<Node>();
    Node itr = root;
    while (itr != null) {
      stack.push(itr);
      itr = itr.left;
    }
  }

  /**
   * @return whether we have a next smallest number
   */
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  /**
   * @return the next smallest number
   */
  public Node next() {
    Node cur = stack.pop();
    if (cur.right != null) {
      Node itr = cur.right;
      stack.push(itr);
      while (itr.left != null) {
        stack.push(itr.left);
        itr = itr.left;
      }
    }
    return cur;
  }
}
