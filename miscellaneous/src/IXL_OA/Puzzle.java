package IXL_OA;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yuegu on 11/12/18.
 */
public class Puzzle {

  //给你一个数，找数字中的圈，规定1，2，3，5，7没有圈，
  // 4，6，9，0有一个圈，8有两个圈，样例：输入1234， 输出1（因为4有一个圈)。
  public int getCircle(int num) {
    Set<Integer> one = new HashSet<>(Arrays.asList(4, 6, 9, 0));
    int count = 0;
    int itr = num;
    while (itr != 0) {
      int remaider = itr % 10;
      itr = itr / 10;
      if (remaider == 8) {
        count += 2;
      } else if (one.contains(remaider)) {
        count += 1;
      }
    }
    return count;
  }
}
