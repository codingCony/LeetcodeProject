/**
 * Created by yuegu on 11/14/18.
 */
public class _42_TrappingRainWater {

  public static int trap(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int min = 0;
    int ret = 0;
    while (left < right) {
      while (height[left] == min) {
        left++;
      }
      while (height[right] == min) {
        right--;
      }
      min = Math.min(height[left], height[right]);
      for (int i = left; i < right; i++) {
        if (height[i] < min) {
          ret += min - height[i];
          height[i] = min;
        }
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    int[] test = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    System.out.print(trap(test));
  }
}
