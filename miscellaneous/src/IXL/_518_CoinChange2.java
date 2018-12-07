package IXL;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuegu on 12/1/18.
 */
public class _518_CoinChange2 {
  public int change(int amount, int[] coins) {
    Map<Integer, Integer> map = new HashMap<>();
    for(int i = 0; i < coins.length; i++) {
      map.put(coins[i], 1);
    }
    Arrays.sort(coins);
    for(int i = coins.length - 1; i >=0; i--) {
      if(amount < coins[i])
        continue;
      else {
        return 1 + change(amount - coins[i], coins);
      }
    }
  }

}
