package wepay;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class PowerNumbers {

  public static class PowerNumber {
    public final int base;
    public final int power;
    public final double value;

    public PowerNumber(int base, int power) {
      this.base = base;
      this.power = power;
      value = Math.pow(base, power);
    }
  }

  public static long getPowerNumber(int k) {
    if (k < 0) {
      throw new IllegalArgumentException(String.format("Found negative input: %d", k));
    }
    PriorityQueue<PowerNumber> pq = new PriorityQueue<>(Comparator.comparingDouble(num -> num.value));
    pq.add(new PowerNumber(2, 2));
    Set<Double> visited = new HashSet<>();
    PowerNumber currentNumber = pq.peek();
    int i = 0;
    while (i <= k) {
      currentNumber = pq.poll();
      pq.add(new PowerNumber(currentNumber.base + 1, currentNumber.power));
      pq.add(new PowerNumber(currentNumber.base, currentNumber.power + 1));
      if (!visited.contains(currentNumber.value)) {
        i++;
        visited.add(currentNumber.value);
      }
    }
    return (long) currentNumber.value;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 20; i++) {
      System.out.println(getPowerNumber(i));
    }
  }

}

