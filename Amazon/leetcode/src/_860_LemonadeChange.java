/**
 * Created by yuegu on 11/9/18.
 */
public class _860_LemonadeChange {

  /**
   * If it is a $5 just accept, no refund
   * if it is $10, accept and refund $5 if available
   * if it is $20, accept and see if we have (one $10 bill and one $5 bill ) or (three $5 bills)
   */
  public boolean lemonadeChange(int[] bills) {
    int five = 0, ten = 0;
    for (int i : bills) {
      if (i == 5) {
        five++;
      } else if (i == 10) {
        five--;
        ten++;
      } else if (ten > 0) {
        ten--;
        five--;
      } else {
        five -= 3;
      }
      if (five < 0) {
        return false;
      }
    }
    return true;
  }
}
