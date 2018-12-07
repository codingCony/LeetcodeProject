package onsite;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by yuegu on 12/3/18.
 */
public class ChemicalFormula {

  //https://www.1point3acres.com/bbs/thread-299505-1-1.html
  //https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=299491&extra=&page=2
  //https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=275305&extra=&page=1
///第二轮：给个化学表达式，返回每元素的数 第二轮：给个化学表达式，返回每元素的数 第二轮：给个化学表达式，返回每元素的数 第二轮：给个化学表达式，返回每元素的数 第二轮：给个化学表达式，返回每元素的数 第二轮：给个化学表达式，返回每元素的数 第二轮：给个化学表达式，返回每元素的数 第二轮：给个化学表达式，返回每元素的数
//  public class Node {
//    String s;
//    int f;
//    boolean is
//    public Node(String s, int f) {
//      this.s = s;
//      this.f = f;
//    }
//
//  }
  public static Map<String, Integer> count(String s) {
    Map<String, Integer> ret = new HashMap<>();
    int i = 0;
    Stack<String> stack = new Stack<>();
    while (i < s.length()) {
      if (Character.isAlphabetic(s.charAt(i))) {
        String cur = "";
        if (Character.isUpperCase(s.charAt(i))) {
          if (i + 1 <= s.length() - 1 && Character.isLowerCase(s.charAt(i + 1))) {
            cur = (Character.toString(s.charAt(i)) + Character.toString(s.charAt(i + 1)));
            i++;
          } else {
            cur = (Character.toString(s.charAt(i)));
          }
        }
        System.out.println("element " + cur);
        stack.push(cur);
      } else if (s.charAt(i) == '(' || s.charAt(i) == ')') {
        stack.push(Character.toString(s.charAt(i)));
        System.out.println("hit ()");
      } else if (Character.isDigit(s.charAt(i))) {
        int num = 0;
        while (i <s.length() && Character.isDigit(s.charAt(i))) {
          num = num * 10 + s.charAt(i);
          i++;
        }
        String cur = stack.pop();
        String tmp = "";
        if (cur.equals(")")) {
          while (!stack.isEmpty() && stack.peek().equals("(")) {
            tmp += stack.pop();
          }
        } else {
          tmp = cur;
        }
        for (int k = 0; k < num; k++) {
          tmp += tmp;
          k++;
        }
        System.out.println("tmp " + tmp);
        stack.push(tmp);
      }
      i++;
    }
    String flat = stack.peek();
    for (int j = 0; j < flat.length(); j++) {
      String cur = "";
      if (Character.isUpperCase(flat.charAt(j))) {
        if (j + 1 <= flat.length() - 1 && Character.isLowerCase(flat.charAt(i + 1))) {
          j++;
          cur = (Character.toString(s.charAt(i)) + Character.toString(s.charAt(i + 1)));
        } else {
          cur = (Character.toString(s.charAt(i)));
        }
        if (ret.containsKey(cur)) {
          ret.put(cur, ret.get(cur) + 1);
        } else {
          ret.put(cur, 1);
        }

      }
    }
    System.out.print("ret is " + flat);
    return ret;
  }

  public static void main(String[] args) {
    String test = "Fe((OH)2N)3";
    count(test);
  }


  public static HashMap<String, Integer> findElement(String s) {
    if (s == null || s.length() == 0) {
      return new HashMap<String, Integer>();
    }
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    Stack<HashMap<String, Integer>> stack = new Stack<HashMap<String, Integer>>();
    int i = 0;

    while (i < s.length()) {
      if (Character.isUpperCase(s.charAt(i))) {
        String name =
            i + 1 < s.length() && Character.isLowerCase(s.charAt(i + 1)) ? s.substring(i, i + 2)
                : s.substring(i, i + 1);
        i += name.length();
        int left = i;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
          i++;
        }
        int count = left == i ? 1 : Integer.valueOf(s.substring(left, i));
        map.put(name, map.getOrDefault(name, 0) + count);
      } else if (s.charAt(i) == '(') {
        stack.push(new HashMap<String, Integer>(map));
        map.clear();
        i++;
      } else if (s.charAt(i) == ')') {
        i++;
        int left = i;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
          i++;
        }
        int count = left == i ? 1 : Integer.valueOf(s.substring(left, i));

        if (count > 1) {
          for (String name : map.keySet()) {
            map.put(name, map.get(name) * count);
          }
        }

        HashMap<String, Integer> prev = stack.pop();
        for (String name : prev.keySet()) {
          map.put(name, map.getOrDefault(name, 0) + prev.get(name));
        }
      }
    }

    for (String name : map.keySet()) {
      System.out.println(name + ": " + map.get(name));
    }
    return map;
  }


  public static HashMap<String, Integer> findElementRecursion(String s) {
    if (s == null || s.length() == 0) {
      return new HashMap<String, Integer>();
    }
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    int i = 0;

    while (i < s.length()) {
      if (Character.isUpperCase(s.charAt(i))) {
        String name =
            i + 1 < s.length() && Character.isLowerCase(s.charAt(i + 1)) ? s.substring(i, i + 2)
                : s.substring(i, i + 1);
        i += name.length();
        int left = i;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
          i++;
        }
        int count = left == i ? 1 : Integer.valueOf(s.substring(left, i));
        map.put(name, map.getOrDefault(name, 0) + count);
        //System.out.println(name + " " + count);
      } else if (s.charAt(i) == '(') {
        int left = i;
        int parenthesis = 0;
        while (i < s.length()) {
          if (s.charAt(i) == '(') {
            parenthesis++;
          } else if (s.charAt(i) == ')') {
            parenthesis--;
            if (parenthesis == 0) {
              break;
            }
          }
          i++;
        }

        HashMap<String, Integer> cur = findElementRecursion(s.substring(left + 1, i));
        i++;
        left = i;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
          i++;
        }
        int count = left == i ? 1 : Integer.valueOf(s.substring(left, i));

        for (String name : cur.keySet()) {
          map.put(name, map.getOrDefault(name, 0) + cur.get(name) * count);
        }

      }
    }

    return map;
  }

}
