package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuegu on 11/15/18.
 */
public class _68_TextJustification {

  public List<String> fullJustify1(String[] words, int maxWidth) {
    List<String> ret = new ArrayList<>();
    int[] len = new int[words.length];
    for (int i = 0; i < words.length; i++) {
      len[i] = words[i].length();
    }
    int itr = 0;
    while (itr < len.length) {
      int sum = 0;
      int start = itr;
      while (itr < len.length && sum <= maxWidth) {
        sum += len[itr];
        itr++;
      }
      itr = itr - 1;
      StringBuilder cur = getString(new StringBuilder(), words, maxWidth, start, itr, len);
      ret.add(cur.toString());
    }
    return ret;
  }

  private StringBuilder getString(StringBuilder sb, String[] words, int maxWidth, int start,
      int end, int[] len) {
    // last line
    if (end == words.length - 1) {
      for (int i = start; i <= end; i++) {
        sb.append(words[i]);
        sb.append(" ");
      }
    } else {
      int length = 0;
      for (int i = start; i <= end; i++) {
        length += len[i];
      }
      int space = (maxWidth - length) / (end - start + 1);
      for (int i = start; i <= end; i++) {
        sb.append(words[i]);
        for (int k = 0; k < space; k++) {
          sb.append(" ");
        }
      }
    }
    return sb;
  }
  /**
   * For each line, I first figure out which words can fit in.
   * According to the code, these words are words[i] through words[i+k-1].
   * Then spaces are added between the words.
   * The trick here is to use mod operation to manage the spaces that can't be evenly distrubuted: the first (L-l) % (k-1) gaps acquire an additional space.
   */

  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> ret = new ArrayList();
    // traverse all words in the array;
    //
    for(int out = 0; out< words.length; out++) {
      int len = 0;
      int in = out;
      // out is the new line start and in is the line ends
      while(in < words.length && len + words[in].length() + (in - out) < maxWidth) {
        len += words[in].length();
        in++;
      }
      // append string to this new line
      StringBuilder line = new StringBuilder();
      for(int i = out; i < in; i++) {
        // initial padding
        int padding = 1;
        // if it is not the last line
        if(in < words.length - 1) {
          padding = (maxWidth - len) / (in - i) + ((maxWidth - len) % (in - i)) != 0 ? 1 : 0);
        }

      }
    }
    for(int out = 0, in = 0, len = 0; out < words.length; out = in, len = 0) {
      for(in = out; in < words.length && len + words[in].length() + (in - out ) < maxWidth; len += words[in++].length());
      StringBuilder current = new StringBuilder(words[out]);
      for(int k = out + 1; k < in; k++) {
        int padding = 1;
        if(in < words.length) {
          padding = (maxWidth - len) / (in - k) + ((maxWidth - len) % (in - k)) != 0 ? 1 : 0);
          len += padding;
        }
        for(int m = 0; m < padding; m++)
          current.append(" ");
        current.append(words[k]);
      }
      for(int n = 0; n < maxWidth - current.length(); n++)
        current.append(" ");
      ret.add(current.toString());
    }
    return ret;
  }
}
