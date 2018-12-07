package wepay;


import com.sun.tools.javac.util.List;

/**
 * Created by yuegu on 10/24/18.
 */
public class alerter {

  public static boolean alertMine(int[] array, int k, double ratio) {
    double avg = 0;
    boolean[][] record = new boolean[array.length][k];
    double minAvg = Integer.MAX_VALUE;
    int sum = 0;
    int start = 0;
    int end = 0;
    int curMax = Integer.MIN_VALUE;
    while (end <= array.length - 1) {
      sum += array[end];
      curMax = Math.max(curMax, array[end]);
      end++;
      if (end - start == k) {
        avg = ((double) sum) / k;
        if (curMax / avg > ratio) {
          return true;
        }
        if (minAvg != Integer.MIN_VALUE && avg / minAvg > ratio) {
          return true;
        }
        minAvg = Math.min(avg, minAvg);
        curMax = Integer.MIN_VALUE;
        sum -= array[start];
        start++;
      }
    }
    return false;
  }


  public static boolean alert(List<Integer> inputs, int windowSize, double allowedIncrease) {
    if (windowSize > inputs.size()) {
      return false;
    }
    boolean[][] exceeded = new boolean[inputs.size()][windowSize];
    double minAverage = Integer.MAX_VALUE;
    for (int i = 0; i < inputs.size() - windowSize + 1; i++) {
      double sum = 0;
      for (int j = 0; j < windowSize; j++) {
        sum += inputs.get(i + j);
      }
      double average = sum / windowSize;
      if (average > minAverage * allowedIncrease) {
        return true;
      }
      minAverage = Math.min(average, minAverage);
      double maxIncrease = average * allowedIncrease;
      for (int j = 0; j < windowSize; j++) {
        if (inputs.get(i + j) > maxIncrease) {
          exceeded[i + j][j] = true;
        }
      }
    }
    for (int i = 0; i < inputs.size(); i++) {
      boolean foundExceed = true;
      for (int j = Math.max(0, windowSize - (inputs.size() - i)); j < Math.min(windowSize, i + 1);
          j++) {
        if (!exceeded[i][j]) {
          foundExceed = false;
          break;
        }
      }
      if (foundExceed) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.out.println(alert(List.of(1, 2, 100, 2, 2), 3, 1.5));
    System.out.println(alert(List.of(1, 2, 4, 2, 2), 3, 2));
    System.out.println(alert(List.of(1, 2, 100, 2, 2), 2, 2.5));
  }

}
