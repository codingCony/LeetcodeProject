package leetcode;

import java.util.*;
import Basic.*;

/**
 * Created by yuegu on 10/18/18.
 */

/**
 * We are given a binary tree (with root node root), a target node, and an integer value K.

 Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.



 Example 1:

 Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

 Output: [7,4,1]

 Explanation:
 The nodes that are a distance 2 from the target node (with value 5)
 have values 7, 4, and 1.
 */
public class _863_distanceK {
  ////1. build a undirected graph using treenodes as vertices, and the parent-child relation as edges
//2. do BFS with source vertice (target) to find all vertices with distance K to it.

  public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
    List<Integer> ret = new ArrayList<>();
    Map<TreeNode, Set<TreeNode>> map = buildGraph(root);
    Queue<TreeNode> q = new  LinkedList<>();
    q.offer(target);
    while(!q.isEmpty() && K != 0) {
      TreeNode cur = q.poll();
      for(TreeNode itr : map.get(cur)) {
        q.offer(itr);
      }
      K--;
    }
    for(TreeNode itr: q) {
      ret.add(itr.val);
    }
    return ret;
  }

  public Map<TreeNode, Set<TreeNode>> buildGraph(TreeNode root) {
    Map<TreeNode, Set<TreeNode>> map = new HashMap<>();
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while(!q.isEmpty()) {
      TreeNode cur = q.poll();
      map.put(cur, new HashSet<>());
      if(cur.left != null) {
        map.get(cur).add(cur.left);
          map.getOrDefault(cur.left, new HashSet<TreeNode>()).add(cur);

        map.get(cur.left).add(cur);
        q.offer(cur.left);
      }
      if(cur.right != null) {
        map.get(cur).add(cur.right);
        map.get(cur.right).add(cur);
        q.offer(cur.right);
      }

    }
    return map;
  }

}
