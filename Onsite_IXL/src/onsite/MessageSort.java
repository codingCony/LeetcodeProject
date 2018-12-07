package onsite;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Created by yuegu on 12/3/18.
 */
public class MessageSort {

  // List<Message>

  // A BC has to be in this order
  // add all id for B and print in order

  /**
   * example
   * MessageList A1, A2, B, B, C2, C1
   * output: A1,A2,B1,B2,C2, C1
   * or A1,A2,B2,B1,C2, C1
   *
   * A1, B, A2, B,BM C2, C1
   * OUTPUT: A1,B1,A2, B2, C2, CA
   *
   * Because A1, B2, A2,B1, C2,C1 is not valid because B2 is ahead of A2
   */
// only A B C three types A and C has id but B has no id

  static class Message {

    int id;
    String type;

    public Message(int id, String type) {
      this.id = id;
      this.type = type;
    }

    public Message(String type) {
      this.type = type;
    }

    @Override
    public String toString() {
      return type + id;
    }
  }

  public static List<Message> process(List<Message> messageList) {
    Stack<Message> stackA = new Stack<>();
    Stack<Message> stackB = new Stack<>();
    Stack<Message> stackC = new Stack<>();
    for (Message message : messageList) {
      if (message.type.equals("A")) {
        stackA.push(message);
      } else if (message.type.equals("B")) {
        stackB.push(message);
      } else if (message.type.equals("C")) {
        stackC.push(message);
        while (!stackA.isEmpty() && !stackC.isEmpty() && stackA.peek().id == stackC.peek().id) {
          stackB.pop().id = stackA.peek().id;
          stackA.pop();
          stackC.pop();
        }
      }
    }
    return messageList;
  }

  public static List<Message> order(List<Message> messageList) {
    Set<Integer> set = new HashSet<>();
    Queue<Message> q = new LinkedList<>();
    for (int i = 0; i < messageList.size(); i++) {
      Message cur = messageList.get(i);
      if (cur.type.equals("A")) {
        set.add(cur.id);
      } else if (cur.type.equals("B")) {
        if (set.size() == 1) {
          cur.id = set.iterator().next();
          set.clear();
        } else {
          q.add(cur);
        }
      } else if (cur.type.equals("C")) {
        if (set.contains(cur.id) && !q.isEmpty()) {
          q.poll().id = cur.id;
          set.remove(cur.id);
        } else if (set.isEmpty() && q.isEmpty()) {
          continue;
        } else
          throw new IllegalArgumentException();
        }
      }
    return messageList;
  }

  // A1 B A2 B C2 C1
  public static void main(String[] args) {
    Message a1 = new Message(1, "A");
    Message b1 = new Message("B");
    Message a2 = new Message(2, "A");
    Message b2 = new Message("B");
    Message c1 = new Message(1, "C");
    Message c2 = new Message(2, "C");
    List<Message> list1 = Arrays.asList(new Message[]{a1, b1, a2, b2, c1, c2});

    List<Message> list2 = Arrays.asList(new Message[]{a1, a2, b1, b2, c1, c2});

    List<Message> list3 = Arrays.asList(new Message[]{a1, a2, b1, b2, c2, c1});
    order(list1);
    for (int i = 0; i < list1.size(); i++) {
      System.out.print(list1.get(i));
    }
    System.out.println();
    order(list2);
    for (int i = 0; i < list2.size(); i++) {
      System.out.print(list2.get(i));
    }
    System.out.println();
    order(list3);
    for (int i = 0; i < list3.size(); i++) {
      System.out.print(list3.get(i));
    }
  }

}
