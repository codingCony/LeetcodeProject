package IXL_ONLINE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by yuegu on 11/19/18.
 */
public class generateRandomFibonacciNumber {

  /**
   *
   * @param n
   * @return
   */
  public int getRandomFibonacciNumber(int n ) {
    List<Integer> ret = new ArrayList<>();
    ret.add(0);
    ret.add(1);
    while(ret.get(ret.size() - 1) < n) {
      int cur = ret.size() - 1;
      int to = ret.get(cur - 1) + ret.get(cur - 2);
      if(to > n) {
        break;
      } else {
        ret.add(to);
      }
    }
    Random rand = new Random();

    int index = rand.nextInt(ret.size()) + 1;
    return ret.get(index);
  }
}
