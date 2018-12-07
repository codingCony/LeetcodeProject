/**
 * Created by yuegu on 11/14/18.
 */
public class _5_LongestPalindromicSubstring {

  public String longestPalindromeExtand(String s) {
    int[] maxStart = new int[1], maxEnd = new int[1];
    // use array in order to pass by reference instead of pass by value
    for (int i = 0; i < s.length()-1; i++) {
      extend(s, i, i, maxStart, maxEnd);
      extend(s, i, i+1, maxStart, maxEnd);
    }

    return s.substring(maxStart[0], maxEnd[0]+1);
  }

  private void extend(String s, int i, int j, int[] maxStart, int[] maxEnd) {
    // loop until meet invalid match
    while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
      i--; j++;
    }

    i++; j--; // back to the last valid match

    if (j - i + 1 > maxEnd[0] - maxStart[0] + 1) {
      maxStart[0] = i;
      maxEnd[0] = j;
    }

  }

  public String longestPalindromeOther(String s) {
    char[] ca = s.toCharArray();
    int rs = 0, re = 0;
    int max = 0;
    for(int i = 0; i < ca.length; i++) {
      if(isPalindrome(ca, i - max - 1, i)) {
        rs = i - max - 1; re = i;
        max += 2;
      } else if(isPalindrome(ca, i - max, i)) {
        rs = i - max; re = i;
        max += 1;
      }
    }
    return s.substring(rs, re + 1);
  }

  private boolean isPalindrome(char[] ca, int s, int e) {
    if(s < 0) return false;

    while(s < e) {
      if(ca[s++] != ca[e--]) return false;
    }
    return true;
  }

  public String longestPalindrome(String s) {
    int len = s.length();
    if (len < 2)
      return s;
    int max = 0;
    String ret;
    for(int i = 0; i < s.length(); i++) {
      int cur = exploreP(s, i);
      if( cur > max) {
        max = cur;
        if(cur % 2 == 0)
          ret = s.substring(i - cur + 1,);
      }
    }
  }

  private int exploreP(String s, int pivot) {
    int start = 0;
    int end = 2 * pivot + 1;
    int len = 2 * (pivot + 1);
    // explore even
    while(start < end) {
      while(s.charAt(start) == s.charAt(end)) {
        start++;
        end--;
      }
      // a a b b a a
      if(start == end - 1)
        return len;
      start++;
      end--;
      len -= 2;
    }
     start = 0;
     end = 2 * pivot;
     len = 2 * pivot + 1;
    // explore even
    while(start < end) {
      while(s.charAt(start) == s.charAt(end)) {
        start++;
        end--;
      }
      // a a b  a a
      if(start == end - 2)
        return len;
      start++;
      end--;
      len -= 2;
    }
    return 0;
  }
}
