package onsite;

/**
 * Created by yuegu on 12/4/18.
 */
public class _162_findPeakElement {

  // my accepted solution
  public int findPeakElement(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    if (nums.length == 1) {
      return 0;
    }
    int pre = Integer.MIN_VALUE;
    int next = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      int cur = nums[i];
      next = Integer.MIN_VALUE;
      if (i + 1 <= nums.length - 1) {
        next = nums[i + 1];
      }
      if (cur > pre && cur > next) {
        return i;
      } else {
        pre = cur;
      }
    }
    return -1;
  }

  // my accepted version of binary search
  public int findPeakElement2(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    if (nums.length == 1) {
      return 0;
    }
    int start = 0;
    int end = nums.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (mid - 1 >= 0 && mid + 1 < nums.length) {
        if (nums[mid - 1] < nums[mid] && nums[mid + 1] < nums[mid]) {
          return mid;
        }
        if (nums[mid - 1] > nums[mid]) {
          end = mid - 1;
        } else {
          start = mid + 1;
        }
      }
      if (mid == 0) {
        return nums[mid] > nums[mid + 1] ? mid : mid + 1;
      }
      if (mid == nums.length - 1) {
        return nums[mid] > nums[mid - 1] ? mid : mid - 1;
      }
      // [0,1]

    }
    return end;
  }

  // concise version of binary search
  public int findPeakElement3(int[] nums) {
    int n = nums.length;
    int lo = 0, hi = n - 1;
    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      if (nums[mid] < nums[mid + 1]) {
        lo = mid + 1;
      } else {
        hi = mid;
      }
    }
    return lo;
  }
}
