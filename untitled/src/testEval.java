import java.util.Stack;

/**
 * Created by yuegu on 10/9/18.
 */
public class testEval {
  public static int eval(String expression) {
    Stack<Integer> signStack = new Stack<>();
    int curVal = 0;
    int curSum = 0;
    int ret = 0;
    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);
      if (c == '(') {
        char sign = expression.charAt(++i);
        signStack.push(sign == '+' ? 1 : -1);
      } else if (c == ')') {
        ret +=signStack.pop() * curSum;
      } else if (c == ' ') {
        curSum += curVal;
        curVal = 0;
      } else {
        curVal = curVal * 10 + c - '0';
      }
    }
    return ret;
  }


  public static void main(String[] args) {
    String expression = "(- (+ 2 4) (+ 5 5))";
    System.out.print(eval(expression));
  }
}
