package onsite;

import java.util.Stack;

/**
 * Created by yuegu on 12/4/18.
 */
public class _772_BasicCalculatorIII {
/**
 * 772 Basic Calculator III
 面试官特别凶。这题我之前没做过，最后也没写出来，只说了思路。
 面试过程中可以 focus 在核心逻辑上，假设输入有效，可以先不做 * /，可以不考虑空格、溢出

 */

//"(2+6* 3+5- (3*14/7+2)*5)+3"=-12

  /**
   * use two stacks
   */
  public static int calculateOther(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    Stack<Integer> nums = new Stack<>(); // the stack that stores numbers
    Stack<Character> ops = new Stack<>(); // the stack that stores operators (including parentheses)
    int num = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == ' ') {
        continue;
      }
      if (Character.isDigit(c)) {
        num = c - '0';
        // iteratively calculate each number
        while (i < s.length() - 1 && Character.isDigit(s.charAt(i + 1))) {
          num = num * 10 + (s.charAt(i + 1) - '0');
          i++;
        }
        nums.push(num);
        num = 0; // reset the number to 0 before next calculation
      } else if (c == '(') {
        ops.push(c);
      } else if (c == ')') {
        // do the math when we encounter a ')' until '('
        while (ops.peek() != '(') {
          nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
        }
        ops.pop(); // get rid of '(' in the ops stack
      } else if (c == '+' || c == '-' || c == '*' || c == '/') {
        while (!ops.isEmpty() && precedence(c, ops.peek())) {
          nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
        }
        ops.push(c);
      }
    }
    while (!ops.isEmpty()) {
      nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
    }
    return nums.pop();
  }

  private static int operation(char op, int b, int a) {
    switch (op) {
      case '+':
        return a + b;
      case '-':
        return a - b;
      case '*':
        return a * b;
      case '/':
        return a / b; // assume b is not 0
    }
    return 0;
  }

  // helper function to check precedence of current operator and the uppermost operator in the ops stack
  private static boolean precedence(char op1, char op2) {
    if (op2 == '(' || op2 == ')') {
      return false;
    }
    if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
      return false;
    }
    return true;
  }


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
          if (c == '/') {
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
    return 0;
  }
}
