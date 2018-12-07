package onsite;

import java.util.Stack;

/**
 * Created by yuegu on 12/3/18.
 *
 *
 * https://leetcode.com/problems/basic-calculator-iii/discuss/113592/Development-of-a-generic-solution-for-the-series-of-the-calculator-problems
 */
public class _227_BasicCalculatorII {

  /**
   * Example 1:
   *
   * Input: "3+2*2"
   * Output: 7
   * Example 2:
   *
   * Input: " 3/2 "
   * Output: 1
   * Example 3:
   *
   * Input: " 3+5 / 2 "
   * Output: 5
   */

  public int calculate(String s) {
    Stack<Character> opertor = new Stack<>();
    Stack<Integer> num = new Stack<>();
    int cur = 0;
    int i = 0;
    while (i < s.length()) {
      while (i < s.length() && Character.isDigit(s.charAt(i))) {
        cur += cur * 10 + s.charAt(i) - '0';
        i++;
      }
      num.push(cur);
      cur = 0;
      if (i < s.length() && !Character.isDigit(s.charAt(i))) {
        char c = s.charAt(i);
        if (c == '/' || c == '*') {
          int num1 = num.pop();
          i++;
          int num2 = 0;
          while (i < s.length() && Character.isDigit(s.charAt(i))) {
            num2 += num2 * 10 + s.charAt(i) - '0';
            i++;
          }
          if(c == '/') {
            num.push(num1 / num2);
          } else {
            num.push(num1 * num2);
          }
        } else {
          opertor.push(c);
        }
        i++;
      }
    }
    int ret = 0;
    while (!opertor.isEmpty()) {
      Character o = opertor.pop();
      int num1 = num.pop();
      int num2 = num.pop();
      if (o == '-') {
        num.push(num2 - num1);
      } else if (o == '+') {
        num.push(num2 + num1);
      }
    }
    return num.pop();
  }
}
