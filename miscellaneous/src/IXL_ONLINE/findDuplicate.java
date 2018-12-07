package IXL_ONLINE;

import java.util.Arrays;

//这道题的关键在于0处是没有索引指向的，将数组视为静态链表，从0处开始的链一定不会指回0处，
//即该链一定有节点被指向两次。而这个节点就是要返回的节点（即重复的值）。
//因为只有一个重复数字，其他链上的情况不必考虑。
//通过类似跑步的规则，让两个“指针”，异速跑，当两者第一次相遇时，一定在环上。
//此时若经过了n次迭代，环的长度则为n；假设环外的长度为m，此时从相遇点到入环点也差m步；（可以自己画图）
//所以重置一个指针，走相同步数直到相遇，相遇点即为入环点；
Let's try to follow a chain starting from element at index 0,
    and interpret a value of the element as an index, let's take element at that index and interpret it as a next index, and so on.
    Since there is a duplicate there must be a loop in this chain.
    It's trivial to show that the beginning of the loop is our duplicate.
     Following implementation uses classic two-pointers Floyd's loop detection algorithm to find the beginning of the loop.


/**
 * Created by yuegu on 11/26/18.
 */
public class findDuplicate {
  public int findDuplicate(int[] nums) {
    Arrays.sort(nums);
    for(int i = 0; i < nums.length; i++) {
      if(i > 0 && nums[i] == nums[i - 1]) {
        return nums[i];
      }
    }
    return -1;
  }


    1 3 4 2 2
    0 1 2 3 4


  Slow 1 fast num[1] = 3
  Slow num[1] = 3 fast num[num[3]] = 1
  Slow num[3] = 2 fast = num[num[1]] = 3
  Slow 4 fast num[num[3]] = 2
  Slow 2 fast num[num[2]] = 4
  Slow 4 fast num[num[4]] = 2
  Slow 2 fast num[num[]] = 4

  This solution is based on binary search.

  At first the search space is numbers between 1 to n. Each time I select a number mid (which is the one in the middle) and count all the numbers equal to or less than mid.
      Then if the count is more than mid, the search space will be [1 mid] otherwise [mid+1 n].
  I do this until search space is only one number.


      Let's say n=10 and I select mid=5.
  Then I count all the numbers in the array which are less than equal mid.
  If the there are more than 5 numbers that are less than 5,
  then by Pigeonhole Principle , one of them has occurred more than once. So I shrink the search space from [1 10] to [1 5].
  Otherwise the duplicate number is in the second half so for the next step the search space would be [6 10].


  public int findDuplicate(int[] nums) {
    int low = 1, high = nums.length - 1;
    while (low <= high) {
      int mid = (int) (low + (high - low) * 0.5);
      int cnt = 0;
      for (int a : nums) {
        if (a <= mid) ++cnt;
      }
      if (cnt <= mid) low = mid + 1;
      else high = mid - 1;
    }
    return low;
  }


  Let's try to follow a chain starting from element at index 0,
  and interpret a value of the element as an index, let's take element at that index and interpret it as a next index, and so on.
  Since there is a duplicate there must be a loop in this chain.
      It's trivial to show that the beginning of the loop is our duplicate.
       Following implementation uses classic two-pointers Floyd's loop detection algorithm to find the beginning of the loop.



}
