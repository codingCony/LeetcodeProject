package online;

/**
 * Created by yuegu on 11/6/18.
 */
public class countClumpsOfSameColor {

  public static class TreeNode {

    TreeNode left;
    TreeNode right;
    char val;

    public TreeNode(char val) {
      this.val = val;
    }
  }

  private static class Internal {

    char color;
    int count;
    int clump;

    public Internal(char color, int count, int clump) {
      this.color = color;
      this.count = count;
      this.clump = clump;
    }

    public void addCount(int i) {
      this.count += i;
    }

    public void addClump(int i) {
      this.clump += i;
    }
  }

  public static int getClump(TreeNode root) {
    Internal ret = helper(root);
    return ret.count >= 3 ? ret.clump + 1 : ret.clump;
  }

  public static Internal helper(TreeNode root) {
    if (root == null) {
      return new Internal('X', 0, 0);
    }
    if (root.left == null && root.right == null) {
      return new Internal(root.val, 1, 0);
    }
    Internal left = helper(root.left);
    Internal right = helper(root.right);
    Internal ret = new Internal(root.val, 1, 0);
    if (root.val == left.color && root.val == right.color) {
      ret.addCount(left.count);
      ret.addCount(right.count);
      ret.addClump(left.clump);
      ret.addClump(right.clump);
    } else if (root.val == left.color) {
      ret.addCount(left.count);
      ret.addClump(left.clump);
      if (right.count >= 3) {
        right.addClump(1);
      }
      ret.addClump(right.clump);
    } else if (root.val == right.color) {
      ret.addCount(right.count);
      ret.addClump(right.clump);
      if (left.count >= 3) {
        left.addClump(1);
      }
      ret.addClump(left.clump);
    } else {
      if (left.count >= 3) {
        left.addClump(1);
      }
      if (right.count >= 3) {
        right.addClump(1);
      }
      ret.addClump(left.clump);
      ret.addClump(right.clump);
    }
    return ret;
  }

  /**
   * R
   * R       R
   * B    B  R    G
   * B  B     G G
   * G
   * G
   */
  public static void main(String[] args) {
    TreeNode n0 = new TreeNode('R');
    TreeNode n1 = new TreeNode('R');
    n0.left = n1;
    TreeNode n2 = new TreeNode('R');
    n0.right = n2;
    TreeNode n3 = new TreeNode('B');
    n1.left = n3;
    TreeNode n4 = new TreeNode('B');
    n1.right = n4;
    TreeNode n5 = new TreeNode('R');
    n2.left = n5;
    TreeNode n6 = new TreeNode('G');
    n2.right = n6;
    TreeNode n7 = new TreeNode('B');
    n4.left = n7;
    TreeNode n8 = new TreeNode('B');
    n4.right = n8;
    TreeNode n9 = new TreeNode('G');
    n6.left = n9;
    TreeNode n10 = new TreeNode('G');
    n6.right = n10;
    TreeNode n11 = new TreeNode('G');
    n10.right = n11;

    System.out.println(getClump(n0));
  }

}
