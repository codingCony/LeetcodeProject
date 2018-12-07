package wepay;

/**
 * Created by yuegu on 10/28/18.
 */
public class ZombieClusters {

  public static int getZombieClusters(String[] zombieConnectedness) {
    // number of zombies
    int n = zombieConnectedness.length;
    for (String connectedness : zombieConnectedness) {
      if (connectedness.length() != n) {
        throw new IllegalArgumentException(String.format("There are %d zombies but for zombie %d there are %d" +
            " connectedness relationships: %s", n, connectedness.length(), connectedness));
      }
    }
    int clusters = n;
    int[] parent = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
    }
    for (int i = 0; i < n; i++) {
      // j must be connected to i if i is connected to j. We can skip half of the matrix.
      for (int j = i + 1; j < n; j++) {
        if (zombieConnectedness[i].charAt(j) == '1') {
          int root1 = find(i, parent);
          int root2 = find(j, parent);
          if (root1 != root2) {
            parent[root1] = root2;
            clusters--;
          }
        }
      }
    }
    return clusters;
  }

  public static int find(int k, int[] parent) {
    while (k != parent[k]) {
      // path compression
      parent[k] = parent[parent[k]];
      k = parent[k];
    }
    return k;
  }

  public static void main(String[] args) {
    System.out.println(getZombieClusters(new String[]{"110", "110", "001"}));
  }

}


