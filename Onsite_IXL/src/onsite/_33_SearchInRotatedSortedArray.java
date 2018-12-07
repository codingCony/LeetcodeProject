package onsite;

/**
 * Created by yuegu on 12/3/18.
 */
public class _33_SearchInRotatedSortedArray {

  /**
   * Example 1:
   *                0 1 2 3 4 5 6
   * Input: nums = [4,5,6,7,0,1,2], target = 0
   * Output: 4
   * Example 2:
   *
   * Input: nums = [4,5,6,7,0,1,2], target = 3
   * Output: -1
   */

  /**
   * s = 0 end = 6 mid = 3  7 > 2 end
   *
   * s = 4  end = 6 mid = 5  1 < end 2  end = 5
   * s = 4 end = 5 mid = 4 0
   * @param nums
   * @param target
   * @return
   */
  public static int search(int[] nums, int target) {
    int start = 0;
    int end = nums.length - 1;
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] > nums[end]) {
        if(target == nums[mid])
          return mid;
        if (target < nums[mid] && target > nums[start]) {
          end = mid;
        } else {
          start = mid + 1;
        }
      } else {
        if(target == nums[mid])
          return mid;
        if(target > nums[mid] && target <= nums[end]) {
          start = mid + 1;
        } else {
          end = mid;
        }
      }
    }
    return start;

  }


  public int search2(int[] nums, int target) {
    if(nums == null || nums.length == 0)
      return -1;
    int start = 0, end = nums.length - 1;
    while (start < end) {
      int mid = start + (end - start) /2 ;
      if (nums[mid] > nums[end]) {  // eg. 3,4,5,6,1,2
        if(target <= nums[mid] && target > nums[end])
          end = mid;
        else
          start = mid + 1;
      } else {  // eg. 5,6,1,2,3,4
        if (target > nums[mid] && target <= nums[end]) {
          start = mid + 1;
        } else {
          end = mid;
        }
      }
    }
    if (target != nums[end]) return -1;
    return end;
  }

  public static void main(String[] args) {
    int[] test = new int[]{4,5,6,7,0,1,2};
    int[] t2 = new int[]{3,5,1};
    System.out.print(search(t2, 3));
   // System.out.print(search(test, 3));
  }
}
