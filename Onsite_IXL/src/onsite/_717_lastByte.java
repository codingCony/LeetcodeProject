package onsite;

/**
 * Created by yuegu on 12/3/18.
 */
public class _717_lastByte {

  /**
   * input是数组，这个数组代表的其实是一个字符串，如果第一个byte>127，那么这个字符就是2byte的，第二个byte可以是任意范围，否则这个字符是1byte。问题是判断最后一个字符是1byte还是2byte的
   * ［128，127，127］last byte是1byte ［127，128，127］output是2bytes
   *
   * 最后一轮，也是director of engineer, 负责content team, 这个人就非常非常nice。题目是给你一个byte array,
   * 一个byte如果大于127那么就是double byte, 否则是single byte。举个例子： 100, 200, 123 100是single,
   * 200是double，所以123不用看，最后就是double 200，10，20 200是double, 10不用看，最后一个byte是20，所以是single
   */

  public class LastByte {

    // Treat unsigned byte that is larger than 127 as 1 and the opposite as 0.
    // Valid combinations are {0, 10, 11}. Check whether the last byte belongs
    // to a single byte or a double byte combination.
    public boolean isSingleByte(byte[] bytes) {
      if (bytes == null || bytes.length == 0) {
        throw new IllegalArgumentException();
      }
      int n = bytes.length;
      if (n == 1 || bytes[n - 1] < 0) {
        return false;
      }
      if (bytes[n - 2] >= 0) {
        return true;
      }
      int i = n - 2;
      while (i >= 0 && bytes[i] < 0) {
        i--;
      }
      return (n - i) % 2 == 0;
    }
  }
}
