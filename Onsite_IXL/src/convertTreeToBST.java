import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import javax.swing.tree.TreeNode;

/**
 * Created by yuegu on 12/3/18.
 */
public class convertTreeToBST {

  /**
   * Given a binary tree of integers, rearrange the integers to return a binary search tree with the
   * same integers that has the same shape and structure as the original tree. 比如 输入： 1 2 3 输出： 3 2
   * 1 Follow-up 是如何不用 extra space 达到相同效果，不用实现就说思路就行。
   */
  static class Node {

    int data;
    Node left, right;

    public Node(int data) {
      this.data = data;
      left = right = null;
    }

    @Override
    public String toString() {
      String ret = "";
      if(this.left != null)
        ret += "left is " +  left.data;
      if(this.right != null)
        ret += "right is " +  right.data;
      return ret + "itself " + this.data ;
    }
  }

  public static Node convert(Node root) {
    List<Integer> list = new ArrayList<>();
    flat(root, list);
    Collections.sort(list);
    //inorderTraversal(root, list);
    inorder(root, list, 0);
    return root;
  }

  public static void inorderTraversal(Node root, List<Integer> list) {
    Stack<Node> stack = new Stack<Node>();
    Node cur = root;
    int i = 0;
    while (cur != null || !stack.empty()) {
      while (cur != null) {
        stack.push(cur);
        cur = cur.left;
      }
      cur = stack.pop();
      cur.data = list.get(i);
      i++;
      cur = cur.right;
    }
  }


  public static class HelpNode {
      Node node;
      int idx;

    public HelpNode(Node node, int idx) {
      this.node = node;
      this.idx = idx;
    }
  }
public static HelpNode inorder(Node root, List<Integer> list, int idx) {

    root.left = inorder(root.left, list, idx).node;
    if(root.left == null && root.right == null) {
      root.data = list.get(idx);
      return new HelpNode(root, idx);
    }
    root.right =inorder(root.right, list, idx).node;

}

  private void helper(Node root, List<Integer> res) {
    if (root != null) {
      if (root.left != null) {
        helper(root.left, res);
      }
      res.add(root.data);
      if (root.right != null) {
        helper(root.right, res);
      }
    }
  public static void flat(Node root, List<Integer> list) {
    if (root == null) {
      return;
    }
    if (root != null) {
      list.add(root.data);
    }
    if (root.left != null) {
      flat(root.left, list);
    }
    if (root.right != null) {
      flat(root.right, list);
    }

  }

  // Driver program to test the above functions
  public static void main(String[] args) {
    Node root = new Node(1);
    root.right = new Node(20);
    root.right.left = new Node(100);
    root.left = new Node(8);
    root.left.left = new Node(7);
    root.left.left.left = new Node(6);
    root.left.left.left.right = new Node(5);


    print(root);
    convert(root);
    System.out.println("after");

    print(root);
  }

  public static void print(Node root) {
    Stack<Node> stack = new Stack<Node>();
    Node cur = root;
    int i = 0;
    while (cur != null || !stack.empty()) {
      while (cur != null) {
        stack.push(cur);
        cur = cur.left;
      }
      cur = stack.pop();
      System.out.println(cur.toString());
      cur = cur.right;
    }
  }
}