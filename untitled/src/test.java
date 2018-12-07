/**
 * Created by yuegu on 10/8/18.
 */
public class test {
  public static void main(String[] args) {
    int[] test = new int[]{1,2,3};
    System.out.print(subarraySum(test, 3));
  }
  public static int subarraySum(int[] nums, int k) {
    int counter = 0;
    for(int i = 0; i < nums.length; i++) {
      int tmp = nums[i];
      int sum = nums[i];
      if(k % sum == 0) {
        counter++;
      }
      for(int j = i + 1; j < nums.length;) {
        sum = sum + nums[j];
        if(k % ( sum + tmp)  == 0 ) {
          counter++;
          j++;
        } else {
          j++;
        }
      }
    }
    return counter;
  }


}
